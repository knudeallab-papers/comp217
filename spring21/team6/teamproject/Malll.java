package teamproject;

import java.io.Serializable;


public class Malll implements Cloneable, Serializable {
	private int location;//λ§? ?μΉ?
	private int tryNumber;// λͺ¨λ ?·, ?‘??? ?λ²? ? ?μ§? ? ??λ‘? μΉ΄μ΄?Έ
	private int num;//?«?μ§? ??«?μ§? ??Έ
	private int bflocation;
	private int checkNum;
	private int midcheck;
	public Malll() {
		location = 0;
		tryNumber = 0;
		num = 0;//λ°±λ?  ? ?΄? λ°©ν₯?? ??μ§? ??Έ
		bflocation=0;
		checkNum=0;
		midcheck=0;
	}
	
	public int getTryNumber() {
		return tryNumber;
	}
	public int getLocation() {
		return location;
	}
	public int getNum() {
		return num;
	}
	public int getcheckNum() {
		return checkNum;
	}
	public void setTryNumber(int a) {
		tryNumber = a;
	}
	public void goStart() {
		location = 0;
		checkNum=0;
		midcheck=0;
	}
	public void setLocation(int a) {
		location += a;
		location = move();
	}
	public void setNum(int a) {
		num = a;
	}public void setcheckNum(int a) {
		checkNum = a;
	}
	public int move() {//λ§? ?΄? 
		if(location == 5)	{
			//System.out.println("---");
			//quiz ?€?
		
				location=60;
					
			//λͺ? ???? ??Όλ‘? κ°??κ±?
		}
		else if(location==10) {
			//System.out.println("---");			
					
			
				location=40;
			
	//		location=location+a.yutDun();
			//?λ²? Β κΊ½λ κ΅¬κ° 
		}else if(location==14&&checkNum==1) {
			System.out.println("---");
			location=65;
	//		location=location+a.yutDun();
			//?λ²? Β κΊ½λ κ΅¬κ° 
		}
		

		else if(location == 63) {
			//System.out.println("---");
			location-=20;
			midcheck=1;
		//	location+=a.yutDun();
		}	//μ€μ? ?μ°©ν? ? κΊ½κΈ°
		else if(location>65) {

			//System.out.println("---");
			if(location == 66) {
				//λ½κΈ° ?€?
				location = 15;
				
			}
			else {
				location -= 51;
			}		
			checkNum=1;
		}
		else if(location==59) {
			//System.out.println("---");
			location = 4;
		}
		else if (location==42&&midcheck==1) {
			location=62;
		}
		else if(location==59) {
			System.out.println("---");
			location = 4;//λͺ? ?λ¦¬μ? λ°±λ
		}
		else if(location==20) {
			System.out.println("---");
			location=46;//λ§μ?λ§μ? 46?Όλ‘? ?΅?Ό
			checkNum=1;
		}else if(location==45&&checkNum==1) {
			System.out.println("---");
			location=19;// ?¨ λ°©ν₯??λ‘? λ°±λ
		}else if(location == 39) {
			System.out.println("---");
			location=9;//2λ²?Β λͺ? ?λ¦¬μ? λ°±λ
		}else if(location==0) {
			location=46;
		}
		//else if(location == )
		else if(num==0){
    
			//location+=a.yutDun();
			//???°κ²½μ° ??? ?μ§?κΈ?
		}
		else if(location>46) {
			num++;
		}
		if(num==0) {
			if(20<location&&location<40) {
				//ComplNanmall();//? ??Όλ‘? ??? ?΄λ¦¬μ΄
				num++;
			}if(46<location&&location<60) {
				//super.ComplNanmall();//??κ°μ  λ°©ν₯?Όλ‘? ?€?΄??? ?΄λ¦¬μ΄
				num++;
			}if(71<location) {
				//super.ComplNanmall();
				num++;//μ²μ λͺ? κ±Έλ¦° ? ?¬κ²λ? ?΄λ¦¬μ΄
			}
		}
		return location;
	}
	public void success() {
		num++;
	}
	
	protected Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
	public int preview(int a) {
		int lc2 = location + a;
		if(lc2 == 5)	{
			//System.out.println("---");
			lc2=60;
			//λͺ? ???? ??Όλ‘? κ°??κ±?
		}
		else if(lc2==10) {
			//System.out.println("---");
			lc2=40;
	//		location=location+a.yutDun();
			//?λ²? Β κΊ½λ κ΅¬κ° 
		}
		else if(lc2 == 63) {
			//System.out.println("---");
			lc2-=20;
		//	location+=a.yutDun();
		}	//μ€μ? ?μ°©ν? ? κΊ½κΈ°
		else if(lc2>65) {
			//System.out.println("---");
			lc2-=51;
		}
		else if(lc2==59) {
			//System.out.println("---");
			lc2 = 4;
		}else if(lc2==0) {
			lc2=46;
		}else if(lc2==20) {
			lc2=46;
		}
		else if(lc2==45&&checkNum==1) {
		//	System.out.println("---");
			lc2=19;// ?¨ λ°©ν₯??λ‘? λ°±λ
		}else if (lc2==42&&midcheck==1) {
			lc2=62;
		}else if(lc2 == 39) {
	//		System.out.println("---");
			lc2=9;//2λ²?Β λͺ? ?λ¦¬μ? λ°±λ
		}
		
		 
		else if(num==0){
			//location+=a.yutDun();
			//???°κ²½μ° ??? ?μ§?κΈ?
		}
		else if(location>46) {
			//complite
		}
		
		return lc2;
	}
}