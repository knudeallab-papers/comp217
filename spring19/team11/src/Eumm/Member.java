/*
 * 회원들의 개인정보를 저장하는 클래스
 */
package Eumm;

import java.io.Serializable;

public class Member implements Serializable{

	private String ID;
	private String PW;
	private String name;
	private String telnum;
	private String or;
	private String question;
	private String answer;
	
	
	public Member() {
		
	}

	public Member(String id, String pw, String name, String telnum, String or,String question, String answer)
	{
		this.ID = id;
		this.PW = pw;
		this.name=name;
		this.telnum = telnum;
		this.or=or;
		this.question = question;
		this.answer = answer;
	}
	public String getOr() {
		return or;
	}
	public void setOr(String or) {
		this.or = or;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPW() {
		return PW;
	}
	public void setPW(String pW) {
		PW = pW;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
