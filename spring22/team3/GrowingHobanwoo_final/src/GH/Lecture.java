package GH;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Lecture {
	public String name;
	public String prof;
	public int credit;
	
	public double GPA = 0.0; 
	public String grade;
	
	protected boolean attendance = false;
	protected int order = 0;
	
	protected ArrayList<String> wordStorage = new ArrayList<String>();
	protected static int size = 5;
	
	public Object contents;
	
	public boolean[] lib_check = {false, false};
	public int lib_cnt = 0;
	
	public Lecture() {
	}
	
	public Lecture(String name, String prof, int credit) {
		this.name = name;
		this.prof = prof;
		this.credit = credit;
	}
	
	public Lecture(String name, String prof, int credit, double GPA) {
        this.name = name;
        this.prof = prof;
        this.credit = credit;
        this.GPA = GPA;
    }
	
	public String getName() { return this.name; }
	public String getProf() { return this.prof; }
	
	public void attend() {
		this.attendance = true;
	}
	
	public ArrayList<String> wordMake() { 
		String fileName = this.name+".txt";
		Random rand = new Random();
		String[] word = new String[size];
		int count = 0;
		
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(new FileInputStream(fileName));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "필기가 존재하지 않습니다");
			return null;
		}
		while(fileReader.hasNext()) {
			String temp = fileReader.next();
			if ( !temp.equals("\n") )
				wordStorage.add(temp);
		}
		
		if (wordStorage.size() == 0) {
			JOptionPane.showMessageDialog(null, "필기에 내용을 입력해 주세요");
			return null;
		}
		System.out.println(wordStorage.size());
		
		size = wordStorage.size();
		
		Collections.shuffle(wordStorage);
		return wordStorage;
	}
	
	public void lib_attend() { 
		if (lib_check[0] && lib_check[1]) return; 
		
		System.out.println("출석사항: "+lib_cnt);
		lib_check[lib_cnt++] = true;
	}
	
	public void lib_scoreUP(int score) {
		System.out.println("학점 up : "+score*0.1);
	}
	
	public void setContents(String str) {
		contents = str;
	}
	
	@Override
	public String toString() { return name+"     "+prof;}
	
}
