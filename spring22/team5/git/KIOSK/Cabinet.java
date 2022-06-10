package TeamProject;

public class Cabinet
{
	private int ID = 0;
	private String password = "";

	Cabinet(int newID, String newPW)
	{
		if (isValidID(newID) && isValidPW(newPW))
		{
			ID = newID;
			password = newPW;
		}
		else
			errorhandler("invalid initializing");
	}

	public boolean isValidID()
	{
		return (ID > 0);
	}

	public static boolean isValidID(int ID)
	{
		return ID>0;
	}

	public boolean isValidPW()
	{
		if (password.length() != 4)
			return false;

		for (int i = 0; i < 4; i++)
			if (password.charAt(i) < '0' || password.charAt(i) > '9')
				return false;

		return true;
	}

	public static boolean isValidPW(String PW)
	{
		if (PW.length() != 4)
			return false;

		for (int i = 0; i < 4; i++)
			if (PW.charAt(i) < '0' || PW.charAt(i) > '9')
				return false;

		return true;
	}

	public void setPW(String newPW)
	{
		if (isValidPW())
			password = newPW;

		else
			errorhandler("invalid Password Change try");
	}

	public int getID()
	{
		return ID;
	}

	public String getPW()
	{
		return password;
	}

	public String toString()
	{
		return "ID: " + ID + " PW: " + password;
	}

	private void errorhandler(String errorType)
	{
		System.out.println("Error occured in Cabinet class: " + errorType);
	}
}