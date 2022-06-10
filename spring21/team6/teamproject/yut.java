package teamproject;

import java.util.Random;
public class yut {
	int[] yut=new int[4];
	public int yutDun() {
		Random rand = new Random();
		  
		  int testNum=(int)(rand.nextGaussian()%2);
		  int temp=0;
			/*
			 * for(;;) { if (testNum!=0) yut[0]=testNum;break;}
			 */
		  yut[0]=rand.nextInt(2);
		  yut[1]=rand.nextInt(2);
		  yut[2]=rand.nextInt(2);
		  yut[3]=rand.nextInt(2);
		  if (yut[0]==0) {
			  for(int i=0;i<4;i++) {	  
				  temp=temp+yut[i];
			  }if(temp==0) {
				  temp=5;
			  }
			  
		  }else if(yut[0]==1) {
			  if(yut[1]==0&&yut[2]==0&&yut[3]==0) {
				  temp=-1;
			  }
			  
			  else{for(int i=0;i<4;i++) {
				  temp=temp+yut[i];
			  }
			  }
		  }
		
		  return temp;
	}
	
	public int getYut(int i) {
		return yut[i];
	}
}

