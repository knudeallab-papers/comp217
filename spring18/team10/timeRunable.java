
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class timeRunable implements Runnable {
	private JLabel timeLabel;
	private Calendar rightNow;
	private char tempDayOfWeek;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	boolean timeChange = false;
	
	public timeRunable(JLabel tL)
	{
		timeLabel = tL;
	}
	
	public void nextDay()
	{		
		timeChange = true;
		
		if(timeChange == true)
		{
			rightNow = Calendar.getInstance();

			rightNow.add(Calendar.DATE, 1);
			System.out.println("time change");
			rightNow.add(Calendar.DATE, 1);
		}
	}
	
	public void run()
	{
		
		while( true )
		{
			SwingUtilities.invokeLater(
				new Runnable()
				{
					public void run()
					{
						if(timeChange == false)
						{
							rightNow = Calendar.getInstance();
						}

						switch( rightNow.get(Calendar.DAY_OF_WEEK) )
						{
							case 1:
								tempDayOfWeek = '일';
								break;
							case 2:
								tempDayOfWeek = '월';
								break;
							case 3:
								tempDayOfWeek = '화';
								break;
							case 4:
								tempDayOfWeek = '수';
								break;
							case 5:
								tempDayOfWeek = '목';
								break;
							case 6:
								tempDayOfWeek = '금';
								break;
							case 7:
								tempDayOfWeek = '토';
								break;
						}
						timeLabel.setText( String.format("%d년 %d월 %d일(%c) %d:%02d", rightNow.get(Calendar.YEAR), rightNow.get(Calendar.MONTH)+1, rightNow.get(Calendar.DAY_OF_MONTH), 
								tempDayOfWeek, rightNow.get(Calendar.HOUR_OF_DAY), rightNow.get(Calendar.MINUTE)) );
					}
				}		
			);
			
			try {
				Thread.sleep(60000);
			}catch (InterruptedException event) {
				event.printStackTrace();
			}
		}
	}
}
