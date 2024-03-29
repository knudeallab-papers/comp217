package statistic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import data.dayRecord;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class SelectStatisticsWayDemo extends JFrame {											//통계 페이지 접근 전 페이지

	private JPanel contentPane;
	
	private String ID;
	private String dayStr;																							//하루통계 기간 String
	private String startStr;																						//기간통계 시작 String
	private String endStr;																							//기간통계 종료 String
	private JCheckBox dayChkBox;																			//하루통계 선택 체크박스								
	private JCheckBox periodChkBox;																		//기간통계 선택 체크박스
	
	private JComboBox<String> dayYComBox;																//하루통계 년/월/일 입력 
	private JComboBox<String> dayMComBox;
	private JComboBox<String> dayDComBox;
	
	private JComboBox<String> startYComBox;															//기간통계 년/월/일 입력
	private JComboBox<String> startMComBox;
	private JComboBox<String> startDComBox;
	private JComboBox<String> endYComBox;
	private JComboBox<String> endMComBox;
	private JComboBox<String> endDComBox;
	
	private LocalDate dayld;
	private LocalDate startld;
	private LocalDate endld;
	private ArrayList<dayRecord> curr_dR_ary;
	
	private String[] yearStrAry = {"2018", "2019", "2020", "2021", "2022"};
	private String[] monthStrAry = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	private String[] dayStrAry;
	
	public SelectStatisticsWayDemo(ArrayList<dayRecord> dR_ary, String nowID) {
		ID = nowID;
		setTitle("SelectStatisticsWayDemo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 497, 291);
		curr_dR_ary = dR_ary;
		dayStr="";
		startStr="";
		endStr="";
		int default_month =dR_ary.get(0).getToday_date().getMonthValue()-1;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(new Color(203, 254, 255));
		contentPane.setLayout(null);
		
		dayChkBox = new JCheckBox("하루통계");
		dayChkBox.setBounds(27, 24, 78, 23);
		contentPane.add(dayChkBox);
		
		periodChkBox = new JCheckBox("기간통계");
		periodChkBox.setBounds(27, 149, 78, 23);
		contentPane.add(periodChkBox);

		dayStrAry = makedayStrAry();
		
		JLabel symbol = new JLabel("~");
		symbol.setHorizontalAlignment(SwingConstants.CENTER);
		symbol.setBounds(245, 153, 32, 15);
		contentPane.add(symbol);

		dayYComBox = new JComboBox<String>(yearStrAry);
		dayYComBox.setBounds(136, 24, 76, 23);
		dayYComBox.setSelectedIndex(4);
		contentPane.add(dayYComBox);
		
		dayMComBox = new JComboBox<String>(monthStrAry);
		dayMComBox.setBounds(224, 24, 73, 23);
		dayMComBox.setSelectedIndex(default_month);
		contentPane.add(dayMComBox);
		
		dayDComBox = new JComboBox<String>(dayStrAry);
		dayDComBox.setBounds(309, 24, 78, 23);
		contentPane.add(dayDComBox);
		
		startYComBox = new JComboBox<String>(yearStrAry);
		startYComBox.setBounds(136, 120, 76, 23);
		startYComBox.setSelectedIndex(4);
		contentPane.add(startYComBox);
		
		startMComBox = new JComboBox<String>(monthStrAry);
		startMComBox.setBounds(224, 120, 73, 23);
		startMComBox.setSelectedIndex(default_month);
		contentPane.add(startMComBox);
		
		startDComBox = new JComboBox<String>(dayStrAry);
		startDComBox.setBounds(309, 120, 78, 23);
		contentPane.add(startDComBox);
		
		endYComBox = new JComboBox<String>(yearStrAry);
		endYComBox.setBounds(136, 178, 76, 23);
		endYComBox.setSelectedIndex(4);
		contentPane.add(endYComBox);
		
		endMComBox = new JComboBox<String>(monthStrAry);
		endMComBox.setBounds(224, 178, 73, 23);
		endMComBox.setSelectedIndex(default_month);
		contentPane.add(endMComBox);
		
		endDComBox = new JComboBox<String>(dayStrAry);
		endDComBox.setBounds(309, 178, 78, 23);
		contentPane.add(endDComBox);
		

		JButton gotoStatisticBtn = new JButton("통계");
		gotoStatisticBtn.setBounds(416, 103, 55, 23);
		contentPane.add(gotoStatisticBtn);
		
		gotoStatisticBtn.addActionListener(new gotoStatisticsHandler());
	}
	
	public String[] makedayStrAry() {
		String[] str = new String[31];
		for(int i=0;i<31;i++) {
			str[i] = String.valueOf(i+1);
		}
		return str;
	}
	public LocalDate makeLocalDate(String todayStr) { 										//입력된 값을 localdate 형태로 바꾸는 코드
		StringTokenizer dateTokens = new StringTokenizer(todayStr, "-" , false);
		
		int y = Integer.parseInt(dateTokens.nextToken());
		int m = Integer.parseInt(dateTokens.nextToken());
		int d = Integer.parseInt(dateTokens.nextToken());
				
		LocalDate day = LocalDate.of(y, m, d);
		
		return day;
	}
	private class gotoStatisticsHandler implements ActionListener { // 통계 페이지로 가는 actionhandler
		public void actionPerformed(ActionEvent e) {	
			boolean day = dayChkBox.isSelected();
			boolean period = periodChkBox.isSelected();
			if(day&&period) {
				JOptionPane.showMessageDialog(null, "둘 중 하나만 선택하세요","경고", JOptionPane.ERROR_MESSAGE);
			}
			else if(!day&&!period) {
				JOptionPane.showMessageDialog(null, "둘 중 하나를 선택해주세요","경고", JOptionPane.ERROR_MESSAGE);
			}
			else {
				if(day) {
					dayStr="";
					dayStr+=dayYComBox.getSelectedItem()+"-"+dayMComBox.getSelectedItem()+"-"
							+dayDComBox.getSelectedItem();
					try {
						dayld=makeLocalDate(dayStr);
						DayStatisticsDemo dsd = new DayStatisticsDemo(curr_dR_ary, dayld);

					}catch(Exception err) {
						JOptionPane.showMessageDialog(null, "해달 월에 존재하지 않는 날짜입니다","경고", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					startStr="";
					startStr+=startYComBox.getSelectedItem()+"-"+startMComBox.getSelectedItem()+"-"
								+startDComBox.getSelectedItem();
					endStr="";
					
					endStr+=endYComBox.getSelectedItem()+"-"+endMComBox.getSelectedItem()+"-"
							+endDComBox.getSelectedItem();
					
					
					switch (PeriodStatisticsFunc.chkDateSeq(startStr, endStr)) {
						case 0:
							JOptionPane.showMessageDialog(null, "같은 날짜입니다. 같은 날짜는 하루통계를 이용하세요.","경고", JOptionPane.ERROR_MESSAGE);
							endStr="";
							startStr="";
							break;
						case -1:
							JOptionPane.showMessageDialog(null, "시작일이 종료일보다 앞 날짜여야 합니다.","경고", JOptionPane.ERROR_MESSAGE);
							endStr="";
							startStr="";
							break;
						case 1:
							boolean chk1 = false;
							boolean chk2 = false;
							int startdayChk;
							int enddayChk;
							
							ArrayList<dayRecord> tmp_drary = new ArrayList<>();
							
							try {
								startld=makeLocalDate(startStr);
								endld=makeLocalDate(endStr);

							}catch(Exception err) {
								JOptionPane.showMessageDialog(null, "해달 월에 존재하지 않는 날짜입니다","경고", JOptionPane.ERROR_MESSAGE);
								endStr="";
								startStr="";
								break;
							}
							
							for(dayRecord dr : curr_dR_ary) {
								if(dr.getToday_date().isAfter(startld) && dr.getToday_date().isBefore(endld))
									tmp_drary.add(new dayRecord(dr));
								else if(dr.getToday_date().equals(startld) || dr.getToday_date().equals(endld))
									tmp_drary.add(new dayRecord(dr));
							}
							
							Collections.sort(tmp_drary);
						
							for(dayRecord dr : tmp_drary) {
								startdayChk = PeriodStatisticsFunc.chkDateSeq(startStr,dr.getToday_date().toString());
								if(startdayChk == 1 || startdayChk==0 && !chk1) {
									chk1=true;
									break;
								}
								
							}
							
							Iterator<dayRecord> iter = tmp_drary.iterator();
							while(iter.hasNext()) {
								dayRecord d = iter.next();
								enddayChk = PeriodStatisticsFunc.chkDateSeq(d.getToday_date().toString(),endStr);
								if(enddayChk==-1) {
									break;
								}
								if(enddayChk==1 || enddayChk==0) {
									chk2=true;
								}
							}

							if(chk1 && chk2) {
								PeriodStatisticsDemo psd = new PeriodStatisticsDemo(tmp_drary, startld, endld, ID);
							}else {
								JOptionPane.showMessageDialog(null, "설정하신 기간 중 운동기록이 없습니다.","경고", JOptionPane.ERROR_MESSAGE);
							}
					}
				}
			}
			
		}
	}
}