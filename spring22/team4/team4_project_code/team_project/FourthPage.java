package team_project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;

public class FourthPage extends JFrame implements ActionListener{
	
	ObjectInputStream BfileIStream;
	
	private ArrayList<petData> petList;
	private String title = "MyPet KNU";
	private Font titlefont = new Font("SanSerif", Font.BOLD, 20);
	private petData pet;
	private schedule s;
	private Date today = new Date();
	private boolean IsChanged = false;
	public FourthPage(petData p, schedule s)
	{
		super("Schedule Check");
		pet = new petData(p);
		this.s = new schedule(s);

		setLayout(null);
		setSize(800, 300);
		
		JButton DoneBtn = new JButton("달성");
		DoneBtn.setSize(90, 50);
		DoneBtn.setLocation(200, 100);
		DoneBtn.addActionListener(this);
		
		JButton completeBtn = new JButton("완료");
		completeBtn.setSize(90, 50);
		completeBtn.setLocation(360, 100);
		completeBtn.addActionListener(this);
		add(DoneBtn);
		add(completeBtn);
		
	}

	public void paint(Graphics g) {
		super.paint(g);

		String str = "";
		String str2 = "";
		
		if (s.getIsMission() == false)
		{
			if (s.getDeadLine() == 1) 
			{
				str = pet.getName() + "의 " + s.getScheduleName() + " 날짜는 " + s.getNextDate().toString() + "까지, 오늘 해야 해요!";
				
			} 
			
			else 
			{
				int deadLine = s.getDeadLine() - 1;
				str = pet.getName() + "의 " + s.getScheduleName() + " 날짜는 " + s.getNextDate().toString() + " 까지, " + deadLine + "일 남았어요!";
				
			}
		}
		else
		{

			str = "오늘의 미션은 " + s.getScheduleName() + " 달성했나요 ?";


		}
		g.setFont(titlefont);
		g.setColor(Color.BLACK);
		g.drawString(str, 120, 80);
		
		if (s.getIsDone() == true)
		{
			g.setColor(Color.BLUE);
			str2 = "달성여부:   달성";
		}
		else
		{
			g.setColor(Color.RED);
			str2 = "달성여부:   미달성";
		
		}
		
		g.drawString(str2, 250, 110);

	}
	 public void actionPerformed(ActionEvent e)
	 {
		 String command = e.getActionCommand();
		 
		 if (command.equals("달성"))
		 {
			 s.ChangeIsDone();
			 IsChanged = true;
			 
			 
			 repaint();
		 }
		 
		 else if (command.equals("완료"))
		 {

			 
			 if (s.getIsMission())
			 {
				 
				 MissionreadFromFileAndSave(pet.getName(), s.getScheduleName());
			 }
			 else
				 readFromFileAndSave(pet.getName(), s.getScheduleName());
			 this.dispose();
			 petKNUDemo.reopenThirdPage(petList, pet.getName());
		 }
	 }
	 
	 public void readFromFileAndSave(String petName, String scheduleName) {
			try {
				BfileIStream = new ObjectInputStream(new FileInputStream("arrayFile.dat"));
				
				petList = (ArrayList<petData>) BfileIStream.readObject(); // 바이너리 파일로부터 ArrayList 읽어오기

				// ArrayList로부터 petData 객체 하나씩 읽어들이기
				

				int i , j;
				ArrayList<schedule> newSchedule = null;
							
				
				for(i=0;i<petList.size();i++) {			
					if (petList.get(i).getName().equals(petName))
					{
						newSchedule = petList.get(i).getSchedule();
						
						for (j = 0; j < newSchedule.size(); j++)
						{
							if (newSchedule.get(j).getScheduleName().equals(scheduleName))
							{
								newSchedule.get(j).setGetIsDone(s.getIsDone());
								
								break;
							}
						}
						break;
					}
				}
				

			petList.get(i).setScheduleList(newSchedule);

			
			}catch(IOException e) {
				System.out.println("File does not exist.");
			}catch(ClassNotFoundException e) {
				System.out.println("No data exist.");
			}
			
			try {
				ObjectOutputStream BfileOStream = new ObjectOutputStream(new FileOutputStream("arrayFile.dat"));
				BfileOStream.writeObject(petList);
			}catch(IOException e) {
				System.out.println("File does not exist.");
			}
			
			
			
			
		}
	 
	 public void MissionreadFromFileAndSave(String petName, String scheduleName) {
			try {
				BfileIStream = new ObjectInputStream(new FileInputStream("arrayFile.dat"));
				
				petList = (ArrayList<petData>) BfileIStream.readObject(); // 바이너리 파일로부터 ArrayList 읽어오기

				// ArrayList로부터 petData 객체 하나씩 읽어들이기
				

				int i , j;
				ArrayList<schedule> newSchedule = null;
				
				
				
				for(i=0;i<petList.size();i++) {			
					if (petList.get(i).getName().equals(petName))
					{
						newSchedule = petList.get(i).getMission();
						
						for (j = 0; j < newSchedule.size(); j++)
						{
							if (newSchedule.get(j).getScheduleName().equals(scheduleName))
							{
								newSchedule.get(j).setGetIsDone(s.getIsDone());
								
								break;
							}
						}
						break;
					}
				}
				

			petList.get(i).setMissionList(newSchedule);
				
			
			}catch(IOException e) {
				System.out.println("File does not exist.");
			}catch(ClassNotFoundException e) {
				System.out.println("No data exist.");
			}
			
			try {
				ObjectOutputStream BfileOStream = new ObjectOutputStream(new FileOutputStream("arrayFile.dat"));
				BfileOStream.writeObject(petList);
			}catch(IOException e) {
				System.out.println("File does not exist.");
			}

		}

}
