import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class excelRead implements Serializable{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		excelRead ex = new excelRead();
		ex.lesson.get(0).settimeString();
		ex.courseSerialize();
		ex.lesson.get(0).setTimeTable();
	}
	private static float lat[];
	private static float lng;
	private final static int Num =24;
	private ArrayList<String> temp;
	ArrayList<course> lesson;
	ArrayList<course> Test;
	
	//excel파일 읽어오는 메소드
	public excelRead() {
		// TODO Auto-generated method stub
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("summer.xls");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HSSFWorkbook workbook=null;
		try {
			workbook = new HSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int rowindex=0;
		int columnindex=0;
		//임시 String 입력
		temp = new ArrayList<String>(Num);
		lesson = new ArrayList<course>(10);

		//시트 수 (첫번째에만 존재하므로 0을 준다)
		//만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
		HSSFSheet sheet=workbook.getSheetAt(0);
		//행의 수
		int rows=sheet.getPhysicalNumberOfRows();
		for(rowindex=3;rowindex<rows;rowindex++){
		    //행을 읽는다
		    HSSFRow row=sheet.getRow(rowindex);
		    if(row !=null){
		        //셀의 수
		        int cells=row.getPhysicalNumberOfCells();
		        String all="";
		        temp.removeAll(temp);
		        //course 임시 저장소
		        course input = new course() {public void describe() {};};
		        for(columnindex=0;columnindex<=cells;columnindex++){
		            //셀값을 읽는다
		            HSSFCell cell=row.getCell((short) columnindex);
		            String value="";
		            //셀이 빈값일경우를 위한 널체크
		            if(cell==null){
		                continue;
		            }else{
		                //타입별로 내용 읽기
		                switch (cell.getCellType()){
		                case HSSFCell.CELL_TYPE_FORMULA:
		                    value=cell.getCellFormula();
		                    break;
		                case HSSFCell.CELL_TYPE_NUMERIC:
		                    value=cell.getNumericCellValue()+"";
		                    break;
		                case HSSFCell.CELL_TYPE_STRING:
		                    value=cell.getStringCellValue()+"";
		                    break;
		                case HSSFCell.CELL_TYPE_BLANK:
		                    value=cell.getBooleanCellValue()+"";
		                    break;
		                case HSSFCell.CELL_TYPE_ERROR:
		                    value=cell.getErrorCellValue()+"";
		                    break;
		                }
		            }
		            temp.add(value);
		          
	            }
		        if(!temp.get(13).equals("false")) {
		        	temp.set(12, temp.get(12) + " " + temp.get(13));
		        }
		        input.setTemp(temp);
		        input.save();
		        lesson.add(input);
		    }
		}
	}
	public void courseSerialize() {
//		serialization을 한 배열을 파일에 써주는 역할
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("arrayFile2"));
			outputStream.writeObject(lesson);
			outputStream.close();
		}catch(IOException e){
			System.err.println("Error writing to file");
			System.exit(0);
		}
//		serialization을 한 배열을 쓴 파일에서 읽어오는 과정
		
//		Test = new ArrayList<course>(10);
		Test = null;
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("arrayFile2"));
			Test = (ArrayList<course>)inputStream.readObject();
		}catch(FileNotFoundException e){
			System.out.println("Cannot find file arrayfile.");
			System.exit(0);
		}catch(ClassNotFoundException e) {
			System.err.println("Problems with the file input");
			System.exit(0);
		}catch(IOException e) {
			System.out.println("Problems with the file input15");
			System.exit(0);
		}
		
	}
	//테마 수정 혹은 작성시 파일로 업로드
	   public void themeUpload() {
//	      serialization을 한 배열을 파일에 써주는 역할
	      try {
	         ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("themeFile"));
	         outputStream.writeObject(lesson);
	         outputStream.close();
	      }catch(IOException e){
	         System.err.println("Error writing to file");
	         System.exit(0);
	      }
	   }
	   //테마 읽어야 할시에 파일에서 읽어오기
	   public void themeRead() {
//	      serialization을 한 배열을 쓴 파일에서 읽어오는 과정
	      
	  
//	      Test = new ArrayList<course>(10);
	      try {
	         ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("themeFile"));
	         lesson = (ArrayList<course>)inputStream.readObject();
	      }catch(FileNotFoundException e){
	         System.out.println("Cannot find file arrayfile.");
	         System.exit(0);
	      }catch(ClassNotFoundException e) {
	         System.err.println("Problems with the file input");
	         System.exit(0);
	      }catch(IOException e) {
	         System.out.println("Problems with the file input16");
	         System.exit(0);
	      }
	      

	   }

}
