import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class GoogleMap extends JFrame implements ActionListener{
	
	
	private GoogleAPI googleAPI = new GoogleAPI();
	//private String location = "경북대";
	private JLabel googleLabel = new JLabel();
	private JTextField textField = new JTextField(30);
	private JButton button = new JButton("검색");
	private JButton buttonPlus = new JButton("+");
	private JButton buttonMinus = new JButton("-");
	private JPanel panel = new JPanel(); 
	private int zoom;
	public Double[] d = new Double[2];
	
	public class Event implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			setMap(textField.getText());
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void setMap(String location) {
		int z = zoom;
		googleAPI.downloadMap(location, z);
		googleLabel.setIcon(googleAPI.getMap(location));
		googleAPI.filedelete(location);
		
		
		d = getAdressToPosition(location);
		
		System.out.println(d[0]);
		System.out.println(d[1]);
		
		add(BorderLayout.SOUTH, googleLabel);
		
		pack();
	}
	
	public GoogleMap() {
		zoom = 18;
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Google Map");
		setVisible(true);
		
		panel.add(textField);
		panel.add(button);
		
//		buttonPlus.addActionListener(this);
//		buttonMinus.addActionListener(this);
		panel.add(buttonPlus);
		panel.add(buttonMinus);
		
		button.addMouseListener(new Event());
		
		add(BorderLayout.NORTH, panel);
		pack();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommand = e.getActionCommand();
		
		if (actionCommand.equals("+")) {
			if (zoom != 20)
				zoom++;
			setMap(textField.getText());
		} else if (actionCommand.equals("-")) {
			if (zoom != 0) {
				zoom--;
			}
			setMap(textField.getText());
		}
	}
	
	public static Double[] getAdressToPosition(String korAddress) {
		String urlStr;
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		StringBuilder stringBuilder;
		Double position[];
		
		try {
			urlStr = "http://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=" + URLEncoder.encode(korAddress, "utf-8");
			URL url = new URL(urlStr);
			
			connection = (HttpURLConnection) url.openConnection();
			connection.setReadTimeout(1000);
			// read the output from the server
			
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				stringBuilder = new StringBuilder();
	 
				String line = null;
				while ((line = reader.readLine()) != null)
				{
					stringBuilder.append(line + "\n");
				}
				
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObj = new JSONObject();
				JSONArray arr = new JSONArray();
				try {
					jsonObj = (JSONObject) jsonParser.parse(stringBuilder.toString());
//					Object obj = JSONObject.parse(stringBuilder.toString());
//					System.out.println(obj);
//					JSONObject location = (JSONObject) obj;
					arr = (JSONArray) jsonObj.get("results");
				} catch(ParseException e) {
					e.printStackTrace();
				}
				
				
				jsonObj = (JSONObject) arr.get(0);
				jsonObj = (JSONObject) jsonObj.get("geometry");
				jsonObj = (JSONObject) jsonObj.get("location");
				
				position = new Double[2];
				
				position[0] = Double.parseDouble(String.valueOf(jsonObj.get("lat")));
				position[1] = Double.parseDouble(String.valueOf(jsonObj.get("lng")));
				System.out.println(position[0]);
				System.out.println(position[1]);
				
				
			} else {
				position = null;
			}
		} catch (IOException e) {
			position = null;
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		
		return position;
	}
	
	public Double[] getDouble() {
		return d;
	}
	
	public String getLocation2() {
		return textField.getText();
	}

}
