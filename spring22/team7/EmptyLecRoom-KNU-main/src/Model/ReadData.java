package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadData {
    public static ArrayList<ArrayList<String>> dataArray = new ArrayList<>(); // 파일에서 데이터 읽어옴.

    public ArrayList<ArrayList<String>> getArrayLists() {
        return dataArray;
    }

    public void setArrayLists() throws FileNotFoundException {

        File file = new File("finalCrawling.txt");
        Scanner sc = new Scanner(file);

        /**
         *  데이터를 읽어온다.
         *  한줄이 비어있을때마다 새로운 리스트 객체를 dataArray 에 add 해준다.
         *  새로운 리스트 객체는 한 강의의 정보를 모두 담고 있는 리스트이다.
         */
        int idx = 0;
        while (sc.hasNextLine()) {
            dataArray.add(new ArrayList<>()); //객체 생성
            while (true) {
                if (!sc.hasNextLine()) break;
                String line = sc.nextLine();
                if (line.isEmpty()) break;
                dataArray.get(idx).add(line);
            }
            idx++;
        }
    }

}
