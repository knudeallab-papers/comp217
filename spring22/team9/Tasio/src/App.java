import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Direction;
import model.Location;
import model.Map;

public class App {
	private JFrame frame;
	private JTextField idField;
	private JPasswordField pwField;
	private JButton LogInBtn;
//	private JButton CoupCheckBtn;
//	private JButton CoupRegBtn;
//	private JButton balanceBtn;
//	private JButton chargeBtn;
//	private JButton historyBtn;
//	private JButton accountBtn;
//	private JButton rideBtn;

//	public User user = new User("", "", "", 9999);
	public User user = null;
	private Direction dir = null;
	
	private JTextField coupRegField;
	
	ImagePanel initPanel;
	ImagePanel joinOrLoginPanel;
	ImagePanel joinPanel;
	ImagePanel loginPanel;
	ImagePanel homePanel;
	ImagePanel chargePanel;
	ImagePanel couponregisterPanel;
	ImagePanel bulletinPanel;
	ImagePanel couponcheckPanel;
	ImagePanel rideMakingPanel;
	ImagePanel matchingPanel;
	ImagePanel movingPanel;
	ImagePanel endPanel;
	ImagePanel calcPanel;
	JLabel joinCouponLabel;
	
	ImageIcon img; 
	
	
	private JPasswordField passwordField;
	private JPasswordField passwordcheckField;
	private JTextField loginField;
	private JTextField couponField;
	private JTextArea balanceField;
	private JTextField chargeField;
	private JTextField departureField;
	private JTextField destinationField;
	
	private String imgFileName;
	
	private final int WINDOW_WIDTH = 640*9/16;
	private final int WINDOW_HEIGHT = 640;
	
//	ImagePanel currentPanel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize2() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640*9/16, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImagePanel currentPanel =getChargePanel();
		
		frame.setSize(new Dimension(1069, 875));
		frame.setPreferredSize(currentPanel.getDim());
		frame.getContentPane().add(currentPanel, BorderLayout.WEST);
		
		frame.pack();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640*9/16, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImagePanel initPanel = getInitPanel();
		ImagePanel joinOrLoginPanel = getJoinOrLoginPanel();
		ImagePanel joinPanel = getJoinPanel();
		ImagePanel loginPanel = getLoginPanel();
		ImagePanel homePanel = getHomePanel();
		ImagePanel chargePanel = getChargePanel();
		ImagePanel couponregisterPanel = getCouponregisterPanel();
		ImagePanel bulletinPanel = getBulletinPanel();
		ImagePanel couponcheckPanel = getCouponcheckPanel();
		ImagePanel movingPanel = getMovingPanel();
		ImagePanel rideMakingPanel = getRidemakingPanel();
		ImagePanel matchingPanel = getMatchingPanel();
		ImagePanel endPanel = getEndPanel();
		ImagePanel calcPanel = getCalcPanel();
		
		frame.setSize(new Dimension(1069, 875));
		frame.setPreferredSize(initPanel.getDim());
		frame.getContentPane().add(initPanel, BorderLayout.WEST);
//		initPanel.setVisible(false);
		
		frame.setSize(new Dimension(1069, 875));
		frame.setPreferredSize(joinOrLoginPanel.getDim());
		frame.getContentPane().add(joinOrLoginPanel, BorderLayout.WEST);
		joinOrLoginPanel.setVisible(false);
		
		frame.setSize(new Dimension(1069, 875));
		frame.setPreferredSize(joinPanel.getDim());
		frame.getContentPane().add(joinPanel, BorderLayout.WEST);
		joinPanel.setVisible(false);
		
		frame.setSize(new Dimension(1069, 875));
		frame.setPreferredSize(loginPanel.getDim());
		frame.getContentPane().add(loginPanel, BorderLayout.WEST);
		loginPanel.setVisible(false);
		
		frame.setPreferredSize(homePanel.getDim());
		frame.getContentPane().add(homePanel, BorderLayout.WEST);
		homePanel.setVisible(false);
		
		frame.setPreferredSize(chargePanel.getDim());
		frame.getContentPane().add(chargePanel, BorderLayout.WEST);
		chargePanel.setVisible(false);
		
		frame.setPreferredSize(couponregisterPanel.getDim());
		frame.getContentPane().add(couponregisterPanel, BorderLayout.WEST);
		couponregisterPanel.setVisible(false);
		
		frame.setPreferredSize(bulletinPanel.getDim());
		frame.getContentPane().add(bulletinPanel, BorderLayout.WEST);
		bulletinPanel.setVisible(false);
		
		frame.setPreferredSize(couponcheckPanel.getDim());
		frame.getContentPane().add(couponcheckPanel, BorderLayout.WEST);
		couponcheckPanel.setVisible(false);
		
		frame.setPreferredSize(movingPanel.getDim());
		frame.getContentPane().add(movingPanel, BorderLayout.WEST);
		movingPanel.setVisible(false);
		
		frame.setPreferredSize(rideMakingPanel.getDim());
		frame.getContentPane().add(rideMakingPanel, BorderLayout.WEST);
		rideMakingPanel.setVisible(false);
		
		frame.setPreferredSize(matchingPanel.getDim());
		frame.getContentPane().add(matchingPanel, BorderLayout.WEST);
		matchingPanel.setVisible(false);
		
		frame.setPreferredSize(endPanel.getDim());
		frame.getContentPane().add(endPanel, BorderLayout.WEST);
		endPanel.setVisible(false);
		
		frame.setPreferredSize(calcPanel.getDim());
		frame.getContentPane().add(calcPanel, BorderLayout.WEST);
		calcPanel.setVisible(false);
//		
//		frame.setPreferredSize(historyPanel.getDim());
//		frame.getContentPane().add(historyPanel, BorderLayout.WEST);
//		historyPanel.setVisible(false);
		
		frame.pack();
	}
	
	public ImagePanel getLoginPanel() {
		loginPanel = new ImagePanel(new ImageIcon("./newTerm/Login.png").getImage());
		
		idField = new JTextField();
		idField.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		idField.setBounds(65, 53, 220, 39);
		idField.setBorder(null);
		idField.setOpaque(false); 
		loginPanel.add(idField);
		idField.setColumns(10);
		
		pwField = new JPasswordField();
		pwField.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		pwField.setBorder(null);
		pwField.setBounds(65, 108, 220, 39);
		pwField.setOpaque(false); 
		loginPanel.add(pwField);

		LogInBtn = new JButton("");
		LogInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					user = RealLogIn.login(idField.getText(), pwField.getText());
					System.out.println("check");
					System.out.println(user.account);
				}
				catch(IOException e1){};
				
				if( user.id.compareTo(idField.getText()) == 0 ) {
					JOptionPane.showMessageDialog(null, "로그인 성공");
					loginPanel.setVisible(false);
					homePanel.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "로그인 실패");
				}
			}
		});
		LogInBtn.setBounds(6, 468, 281, 21);
		LogInBtn.setOpaque(false);
		LogInBtn.setContentAreaFilled(false);
		LogInBtn.setBorderPainted(false);
		loginPanel.add(LogInBtn);
		
		return loginPanel;
	}
	
	private ImagePanel getInitPanel() {
		initPanel = new ImagePanel(new ImageIcon("./newTerm/FirstPage.png").getImage());
		
		JButton startBtn = new JButton("");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initPanel.setVisible(false);
				joinOrLoginPanel.setVisible(true);
			}
		});
		startBtn.setBounds(6, 475, 281, 21);
		startBtn.setOpaque(false);
		startBtn.setContentAreaFilled(false);
		startBtn.setBorderPainted(false);
		initPanel.add(startBtn);
		
		return initPanel;
	}
	
	private ImagePanel getJoinOrLoginPanel() {
		joinOrLoginPanel = new ImagePanel(new ImageIcon("./newTerm/SecondPageJoinLogin.png").getImage());
		
		JButton	joinBtn = new JButton("");
		joinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joinOrLoginPanel.setVisible(false);
				joinPanel.setVisible(true);
			}
		});
		joinBtn.setBounds(6, 441, 281, 21);
		joinBtn.setOpaque(false);
		joinBtn.setContentAreaFilled(false);
		joinBtn.setBorderPainted(false);
		joinOrLoginPanel.add(joinBtn);
		
		JButton loginBtn = new JButton("");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joinOrLoginPanel.setVisible(false);
				loginPanel.setVisible(true);
			}
		});
		loginBtn.setBounds(6, 474, 281, 21);
		loginBtn.setOpaque(false);
		loginBtn.setContentAreaFilled(false);
		loginBtn.setBorderPainted(false);
		joinOrLoginPanel.add(loginBtn);
		
		return joinOrLoginPanel;
	}
	
	private ImagePanel getJoinPanel() {
		joinPanel = new ImagePanel(new ImageIcon("./newTerm/Join.png").getImage());
		
		// 비밀번호
		
		passwordField = new JPasswordField("");
		passwordField.setBounds(65, 118, 210, 34);
		joinPanel.add(passwordField);
		
		// 비밀번호 확인
		passwordcheckField = new JPasswordField("");
		passwordcheckField.setBounds(65, 174, 210, 34);
		joinPanel.add(passwordcheckField);
		
		loginField = new JTextField();
		loginField.setBounds(65, 59, 210, 34);
		joinPanel.add(loginField);
		loginField.setColumns(10);
		
		// 성별 선택
		JButton manBtn = new JButton("");
		manBtn.setBounds(44, 239, 67, 80);
		manBtn.setOpaque(false);
		manBtn.setContentAreaFilled(false);
		manBtn.setBorderPainted(false);
		
		joinPanel.add(manBtn);
		
		JButton girlBtn = new JButton("");
		girlBtn.setBounds(182, 239, 67, 80);
		girlBtn.setOpaque(false);
		girlBtn.setContentAreaFilled(false);
		girlBtn.setBorderPainted(false);
		joinPanel.add(girlBtn);
		
		// 쿠폰 입력
	
		couponField = new JTextField("");
		couponField.setColumns(10);
		couponField.setBounds(75, 342, 210, 34);
		joinPanel.add(couponField);
		
		//회원 가입 버튼
		JButton joinBtn = new JButton("");
		joinBtn.setBounds(6, 465, 287, 29);
		joinBtn.setOpaque(false);
		joinBtn.setContentAreaFilled(false);
		joinBtn.setBorderPainted(false);
		joinPanel.add(joinBtn);
		
		joinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RealLogIn.join(loginField.getText(), passwordField.getText(), couponField.getText());
				JOptionPane.showMessageDialog(null, "성공");
				joinPanel.setVisible(false);
				loginPanel.setVisible(true);
			}
		});
		
		return joinPanel;
	}
		
	private ImagePanel getCouponregisterPanel() {
		couponregisterPanel = new ImagePanel(new ImageIcon("./newTerm/CouponRegister.png").getImage());
		
		JButton coupRegBtn = new JButton("");
		coupRegBtn.setBounds(0, 448, 293, 66);
		coupRegBtn.setOpaque(false);
		coupRegBtn.setContentAreaFilled(false);
		coupRegBtn.setBorderPainted(false);
		couponregisterPanel.add(coupRegBtn);
		
		JButton homeBtn = new JButton("");
		homeBtn.setBounds(223, 0, 64, 55);
		homeBtn.setOpaque(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.setBorderPainted(false);
		couponregisterPanel.add(homeBtn);
		
		coupRegField = new JTextField();
		coupRegField.setBounds(21, 112, 245, 42);
		couponregisterPanel.add(coupRegField);
		coupRegField.setColumns(10);
		
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				couponregisterPanel.setVisible(false);
				homePanel.setVisible(true);
			}
		});
		
		coupRegBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String couponid = coupRegField.getText();
				
			}
		});
		
		return couponregisterPanel;
	}
	
	private ImagePanel getMovingPanel() {
		movingPanel = new ImagePanel(new ImageIcon("./newTerm/Moving.png").getImage());

		
		
//		// 경로 이미지
//		JLabel imgLabel = new JLabel();
//		imgLabel.setIcon(img); // 리스너 객체에서 img 바로 생성
//		
//		imgLabel.setBounds(32, 64, 230, 230);
//		
//		
//		movingPanel.add(imgLabel); 
		
		
		
		// 경찰 버튼
		
		JButton policeBtn = new JButton("");
		policeBtn.setOpaque(false);
		policeBtn.setContentAreaFilled(false);
		policeBtn.setBorderPainted(false);
		policeBtn.setBounds(17, 419, 121, 73);
		movingPanel.add(policeBtn);
		
		// TODO : App.this.route.displayMap();
		policeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "신고 접수 완료");
				movingPanel.setVisible(false);
				initPanel.setVisible(true);
			}
		});
		
		// 운행종료
		JButton endBtn = new JButton("");
		endBtn.setOpaque(false);
		endBtn.setContentAreaFilled(false);
		endBtn.setBorderPainted(false);
		endBtn.setBounds(149, 418, 121, 73);
		movingPanel.add(endBtn);
		
		endBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				movingPanel.setVisible(false);
				// 경로 이미지
				JLabel imgLabel = new JLabel();
				imgLabel.setIcon(img); // 리스너 객체에서 img 바로 생성
						
				imgLabel.setBounds(32, 64, 230, 230);						
				endPanel.add(imgLabel); 
				endPanel.setVisible(true);
				
			}
		});
		
		JButton homeBtn = new JButton("");
		homeBtn.setBounds(238, 6, 49, 51);
		homeBtn.setOpaque(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.setBorderPainted(false);
		movingPanel.add(homeBtn);
		
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				couponcheckPanel.setVisible(false);
				homePanel.setVisible(true);
			}
		});
		
		movingPanel.add(endBtn);
		
		return movingPanel;
	}
	
	private ImagePanel getCouponcheckPanel() {
		couponcheckPanel = new ImagePanel(new ImageIcon("./newTerm/CouponCheck.png").getImage());
		
		JButton homeBtn = new JButton("");
		
		homeBtn.setBounds(238, 6, 49, 51);
		homeBtn.setOpaque(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.setBorderPainted(false);
		
		couponcheckPanel.add(homeBtn);
		
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				couponcheckPanel.setVisible(false);
				homePanel.setVisible(true);
			}
		});
		
		joinCouponLabel = new JLabel("");
		joinCouponLabel.setHorizontalAlignment(SwingConstants.CENTER);
		joinCouponLabel.setBounds(6, 131, 281, 28);
		couponcheckPanel.add(joinCouponLabel);
		
		JLabel registerCouponLabel = new JLabel("");
		registerCouponLabel.setHorizontalAlignment(SwingConstants.CENTER);
		registerCouponLabel.setBounds(6, 160, 281, 28);
		couponcheckPanel.add(registerCouponLabel);
		return couponcheckPanel;
	}
	
	// 출발지 목적지 직접 만들기
	private ImagePanel getRidemakingPanel() {
		rideMakingPanel = new ImagePanel(new ImageIcon("./newTerm/RideMaking.png").getImage());
		
		JButton homeBtn = new JButton("");
		homeBtn.setBounds(241, 6, 46, 45);
		homeBtn.setOpaque(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.setBorderPainted(false);
		rideMakingPanel.add(homeBtn);
		
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rideMakingPanel.setVisible(false);
				homePanel.setVisible(true);
			}
		});
		
		JButton ridewithmeBtn = new JButton("");
		ridewithmeBtn.setBounds(6, 436, 281, 29);
		ridewithmeBtn.setOpaque(false);
		ridewithmeBtn.setContentAreaFilled(false);
		ridewithmeBtn.setBorderPainted(false);
		rideMakingPanel.add(ridewithmeBtn);

		
		JButton cancelBtn = new JButton("");
		cancelBtn.setBounds(6, 466, 281, 29);
		cancelBtn.setOpaque(false);
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setBorderPainted(false);
		rideMakingPanel.add(cancelBtn);
		
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rideMakingPanel.setVisible(false);
				homePanel.setVisible(true);
			}
		});
		
		departureField = new JTextField("출발지");
		departureField.setBounds(6, 60, 130, 36);
		rideMakingPanel.add(departureField);
		departureField.setColumns(10);
		
		destinationField = new JTextField("목적지");
		destinationField.setColumns(10);
		destinationField.setBounds(173, 60, 114, 36);
		rideMakingPanel.add(destinationField);
		
		ridewithmeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String departureLocation = departureField.getText();
				String destinationLocation = destinationField.getText();
				// TODO : App.this.route = new Route(departureLocation, destinationLocation);
				
				makeMap(departureLocation, destinationLocation);
				
				rideMakingPanel.setVisible(false);
				movingPanel.setVisible(true);
			}
		});
		
		return rideMakingPanel;
	}
	
	
	private ImagePanel getChargePanel() {
		chargePanel = new ImagePanel(new ImageIcon("./newTerm/Charge.png").getImage());
		
		JButton chargeBtn = new JButton("");
		chargeBtn.setBounds(6, 464, 281, 29);
		chargeBtn.setOpaque(false);
		chargeBtn.setContentAreaFilled(false);
		chargeBtn.setBorderPainted(false);
		chargePanel.add(chargeBtn);
		
		
		
		JButton homeBtn = new JButton("");
		homeBtn.setBounds(230, 6, 57, 48);
		homeBtn.setOpaque(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.setBorderPainted(false);
		chargePanel.add(homeBtn);
		
		if (this.user == null) {
			balanceField = new JTextArea(new Integer(10000).toString());
		}
		else {
			balanceField = new JTextArea(new Integer(this.user.account).toString());
		}
		
		balanceField.setBounds(20, 112, 256, 39);
		chargePanel.add(balanceField);
		balanceField.setColumns(10);
		
		chargeField = new JTextField();
		chargeField.setColumns(10);
		chargeField.setBounds(20, 162, 256, 39);
		chargePanel.add(chargeField);
		
		chargeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int wantcharge = Integer.parseInt(chargeField.getText());
				App.this.user.updateCharge(wantcharge);
				balanceField.setText(new Integer(App.this.user.account).toString());
				chargeField.setText("");
			}
		});

		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chargePanel.setVisible(false);
				homePanel.setVisible(true);
			}
		});
		
		return chargePanel;
	}
	
	private void bulletin() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640*9/16, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImagePanel bulletinPanel = getBulletinPanel();
		
		frame.setSize(new Dimension(1069, 875));
		frame.setPreferredSize(bulletinPanel.getDim());
		frame.getContentPane().add(bulletinPanel, BorderLayout.WEST);
		
		frame.pack();
	}
	
	private ImagePanel getBulletinPanel() {
		bulletinPanel = new ImagePanel(new ImageIcon("./newTerm/RideBulletin.png").getImage());
		
		//합승하기 버튼
		JButton rideWithBtn = new JButton("");
		rideWithBtn.setBounds(6, 437, 281, 29);
		rideWithBtn.setOpaque(false);
		rideWithBtn.setContentAreaFilled(false);
		rideWithBtn.setBorderPainted(false);
		bulletinPanel.add(rideWithBtn);
		
		//합승 제안하기 버튼 -> 출발지 목적지 제안하는 페이지로
		JButton rideWithMeBtn = new JButton("");
		rideWithMeBtn.setBounds(6, 465, 281, 29);
		rideWithMeBtn.setOpaque(false);
		rideWithMeBtn.setContentAreaFilled(false);
		rideWithMeBtn.setBorderPainted(false);
		bulletinPanel.add(rideWithMeBtn);
		
		rideWithMeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bulletinPanel.setVisible(false);
				rideMakingPanel.setVisible(true);
			}
		});
		
		JButton homeBtn = new JButton("");
		homeBtn.setBounds(232, 6, 55, 47);
		homeBtn.setOpaque(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.setBorderPainted(false);
		bulletinPanel.add(homeBtn);
		
		//출발지 목적지 목록
		
		JLabel departureLabel = new JLabel("");
		departureLabel.setBounds(23, 67, 108, 29);
		bulletinPanel.add(departureLabel);
		
		JLabel destinationLabel = new JLabel("");
		destinationLabel.setBounds(179, 65, 108, 29);
		bulletinPanel.add(destinationLabel);
		
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bulletinPanel.setVisible(false);
				homePanel.setVisible(true);
			}
		});
		
		return bulletinPanel;
	}
	
	private ImagePanel getMatchingPanel() {
		matchingPanel = new ImagePanel(new ImageIcon("./newTerm/matching.png").getImage());
		
		// home
		JButton homeBtn = new JButton("");
		homeBtn.setBounds(232, 6, 55, 47);
		homeBtn.setOpaque(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.setBorderPainted(false);
		matchingPanel.add(homeBtn);
		//home으로 돌아가기
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matchingPanel.setVisible(false);
				movingPanel.setVisible(true);
			}
		});
		
		return matchingPanel;
		
	}
	
	private ImagePanel getEndPanel() {
		endPanel = new ImagePanel(new ImageIcon("./newTerm/End.png").getImage());
		
		JButton homeBtn = new JButton("");
		homeBtn.setBounds(232, 6, 55, 47);
		homeBtn.setOpaque(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.setBorderPainted(false);
		endPanel.add(homeBtn);
		
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endPanel.setVisible(false);
				homePanel.setVisible(true);
			}
		});
		
		JButton calcBtn = new JButton("");
		calcBtn.setBounds(6, 454, 281, 60);
		calcBtn.setOpaque(false);
		calcBtn.setContentAreaFilled(false);
		calcBtn.setBorderPainted(false);
		endPanel.add(calcBtn);
		
		calcBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				endPanel.setVisible(false);

				
				// 결제 label 추가
				JLabel distanceLabel = new JLabel(dir.getDistance() +"m"); 
				
				JLabel fareLabel = new JLabel(dir.getTaxiFare() + "원");
				
				int charge = dir.getTaxiFare() / 20; 
				
				JLabel chargeLabel = new JLabel(charge + "원"); 
				
				JLabel yourPayLabel = new JLabel(dir.getTaxiFare() + charge +"원");
				
				//배치
				distanceLabel.setBounds(230, 740, 120, 50);
				fareLabel.setBounds(230, 790, 120, 50);
				chargeLabel.setBounds(230, 840, 120, 50);
				yourPayLabel.setBounds(230, 890, 120, 50);
				
				// 추가
				calcPanel.add(distanceLabel);
				calcPanel.add(fareLabel);
				calcPanel.add(chargeLabel);
				calcPanel.add(yourPayLabel);		
				
				calcPanel.setVisible(true);
			}
		});
		
		return endPanel;
	}
	
	private ImagePanel getCalcPanel() {
		calcPanel = new ImagePanel(new ImageIcon("./newTerm/LastPage.png").getImage());
		
		JButton homeBtn = new JButton("");
		homeBtn.setBounds(232, 6, 55, 47);
		homeBtn.setOpaque(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.setBorderPainted(false);
		calcPanel.add(homeBtn);
		
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcPanel.setVisible(false);
				homePanel.setVisible(true);
			}
		});
		
		JButton chargeBtn = new JButton("");
		chargeBtn.setBounds(6, 464, 281, 29);
		chargeBtn.setOpaque(false);
		chargeBtn.setContentAreaFilled(false);
		chargeBtn.setBorderPainted(false);
		calcPanel.add(chargeBtn);
		
		chargeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcPanel.setVisible(false);
				
				chargePanel.setVisible(true);
			}
		});
		
		JButton offBtn = new JButton("");
		offBtn.setBounds(6, 436, 281, 29);
		offBtn.setOpaque(false);
		offBtn.setContentAreaFilled(false);
		offBtn.setBorderPainted(false);
		calcPanel.add(offBtn);
		
		offBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		});
		
		
		
		return calcPanel;
	}
	
//	private ImagePanel getHistoryPanel() {
//		ImagePanel historyPanel = new ImagePanel(new ImageIcon("/Users/leeheongyu/Desktop/newTerm/historypage.png").getImage());
//		
//		JButton homeBtn = new JButton("");
//		homeBtn.setBounds(236, 6, 51, 54);
//		homeBtn.setOpaque(false);
//		homeBtn.setContentAreaFilled(false);
//		homeBtn.setBorderPainted(false);
//		historyPanel.add(homeBtn);
//		
//		JTextArea textArea = new JTextArea();
//		textArea.setBounds(6, 136, 281, 28);
//		historyPanel.add(textArea);
//		
//		homeBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				historyPanel.setVisible(false);
//				homePanel.setVisible(true);
//			}
//		});
//		
//		return historyPanel;
//	}
	

	public ImagePanel getHomePanel() {
		homePanel = new ImagePanel(new ImageIcon("./newTerm/Home.png").getImage());
		
		JButton CoupCheckBtn = new JButton("");
		CoupCheckBtn.setBounds(21, 109, 111, 66);
		CoupCheckBtn.setOpaque(false);
		CoupCheckBtn.setContentAreaFilled(false);
		CoupCheckBtn.setBorderPainted(false);
		homePanel.add(CoupCheckBtn);
		
		CoupCheckBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homePanel.setVisible(false);
				couponcheckPanel.setVisible(true);
				
				if(user.coupon.compareTo("111111111") == 0) {
					joinCouponLabel.setText("수수료 500원 할인쿠폰");
				}
				else{
					joinCouponLabel.setText("수수료 면제 쿠폰");
				}
				
			}
		});
		
		JButton CoupRegBtn = new JButton("");
		CoupRegBtn.setBounds(157, 109, 111, 66);
		CoupRegBtn.setOpaque(false);
		CoupRegBtn.setContentAreaFilled(false);
		CoupRegBtn.setBorderPainted(false);
		homePanel.add(CoupRegBtn);
		
		CoupRegBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homePanel.setVisible(false);
				couponregisterPanel.setVisible(true);
			}
		});
		
		JButton balanceBtn = new JButton("");
		balanceBtn.setBounds(21, 214, 111, 66);
		balanceBtn.setOpaque(false);
		balanceBtn.setContentAreaFilled(false);
		balanceBtn.setBorderPainted(false);
		homePanel.add(balanceBtn);
		
		JButton chargeBtn = new JButton("");
		chargeBtn.setBounds(157, 214, 111, 66);
		chargeBtn.setOpaque(false);
		chargeBtn.setContentAreaFilled(false);
		chargeBtn.setBorderPainted(false);
		homePanel.add(chargeBtn);
		
		chargeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homePanel.setVisible(false);
				chargePanel.setVisible(true);
			}
		});
		
		JButton accountBtn = new JButton("");
		accountBtn.setBounds(157, 316, 111, 66);
		accountBtn.setOpaque(false);
		accountBtn.setContentAreaFilled(false);
		accountBtn.setBorderPainted(false);
		homePanel.add(accountBtn);
		
//		accountBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				homePanel.setVisible(false);
//				accountPanel.setVisible(true);
//			}
//		});
		
		JButton historyBtn = new JButton("");
		historyBtn.setBounds(21, 316, 111, 66);
		historyBtn.setOpaque(false);
		historyBtn.setContentAreaFilled(false);
		historyBtn.setBorderPainted(false);
		homePanel.add(historyBtn);
		
//		historyBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				homePanel.setVisible(false);
//				historyPanel.setVisible(true);
//			}
//		});
		
		JButton rideBtn = new JButton("");
		rideBtn.setBounds(6, 454, 281, 60);
		rideBtn.setOpaque(false);
		rideBtn.setContentAreaFilled(false);
		rideBtn.setBorderPainted(false);
		homePanel.add(rideBtn);
		
		rideBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homePanel.setVisible(false);
				bulletinPanel.setVisible(true);
			}
		});
		
		return homePanel;
	}
	
	
	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	// 지도 만드는 메서드
	public void makeMap(String departure, String destination) {
		Location start = new Location(departure); 		
		Location end = new Location(destination);

		//direction
		dir = new Direction(start, end);
		dir.findDirection();
		
		Map map = new Map();
		map.mapService(dir);
		
		setImgFileName(map.getImgFileName()); 
		this.img = new ImageIcon(getImgFileName());
		// 경로 이미지
		JLabel imgLabel = new JLabel();
		imgLabel.setIcon(img); // 리스너 객체에서 img 바로 생성
				
		imgLabel.setBounds(32, 64, 230, 230);
				
				
		movingPanel.add(imgLabel); 
	}
}