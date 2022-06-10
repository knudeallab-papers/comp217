package cafe;

import java.io.Serializable;

public class Data implements Serializable
{
	private String PasswordValue;
	
	public Data()
	{
		
	}
	public void setPasswordValue(String str)
	{
		PasswordValue = str;
	}
	public String getPasswordValue()
	{
		return PasswordValue;
	}
	
	
}
