public class tableinfo{
		int color=0; // no select;
		int price=0; //total price
		int tnumber; //table number;
		String[][] menu;//menu +menu price+menu serial number;
		int mnuber=0; // total menu number
		
		public tableinfo()
		{
			tnumber=0;
			color=price=0;
			tnumber=mnuber=0;
			menu=new String[10][3];
		}
		public tableinfo(int i)
		{
			this();
			tnumber=i+1;
			color=price=0;
			tnumber=mnuber=0;
			menu=new String[10][3];
		}
	}