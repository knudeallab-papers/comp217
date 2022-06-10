package dataClass;

import java.io.Serializable;
import java.util.Vector;

import javax.swing.ImageIcon;

public class book implements Serializable
{
   private String title;
   private String author;
   private String link;
   private String img_adress;
   private String isbn;
   private String explain;
//   private ArrayList<String> explain;
   private boolean lentChk=true;
   private boolean overdueChk;
   private String borrowDate;
   
   
   
   public boolean equal(book inputBook) {
	   if(inputBook.getTitle().equals(title)&&
			   inputBook.getAuthor().equals(author))
	   {		   
		   return true; 
	   }
	   return false;
   }
   public Vector<Object> getall()
   { // 제네릭 설정
      Vector<Object> mylist = new Vector<Object>();
      mylist.add(title);
      mylist.add(author);
      mylist.add(lentChk);
      return mylist;
   }
   public Vector<Object> getLentVector() throws Exception{
	   Vector<Object> lentVector = new Vector<Object>();
	   lentVector.add(title);
	   lentVector.add(borrowDate);
	   lentVector.add(DateCheck.doDateAdd(borrowDate));
	   return lentVector;
   }
   
   public void setTitle(String title) 
   {
      this.title = title;
   }
   public void setAuthor(String author) 
   {
      this.author = author;
   }

   public void setLink(String link) 
   {
      this.link = link;
   }
   public void setImgAddress(String img_adress) 
   {
      this.img_adress = img_adress;
   }
   public void setIsbn(String isbn) 
   {
      this.isbn = isbn;
   }
   public void setExpalin(String explain) 
   {
      this.explain = explain;
   }
   public void setLentChk(boolean Chk)
   {
	   lentChk = Chk;
   }
   public void setBorrowDate(String date)
   {
	   borrowDate = date;
   }
//   public void setExplain(ArrayList<String> explain) {
   //   this.explain = explain;
//   }
   public String getBorrowDate() {
	   return borrowDate;
	}
   
   public boolean getLentChk()
   {
	   return lentChk;
   }
   public String getTitle() 
   {
      return title;
   }
   public String getAuthor() 
   {
      return author;
   }

   public String getLink() 
   {
      return link;
   }

   public String getImgAddres() 
   {
      return img_adress;
   }
   public String getIsbn() 
   {
      return isbn;
   }
   public String getExplain()
   {
      return explain;
   }
//   public ArrayList<String> getExplain() {
//      return explain;
//   }
   
   public static ImageIcon makeImage(String img_adrr) 
   {
      ImageIcon img = new ImageIcon(img_adrr);
      return img;
   }
}