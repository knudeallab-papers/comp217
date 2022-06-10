package Test;

import Model.ParsingClass;
import Model.ReadData;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ModelTest {

    public static void main(String[] args) throws FileNotFoundException {
        ReadData readData = new ReadData();
        readData.setArrayLists(); //파일에서 데이터를 읽어온다.

//        for (ArrayList<String> arrayList : readData.getArrayLists()) {
//            System.out.println("arrayList = " + arrayList);
//        }

        ParsingClass parsingClass = new ParsingClass();

        parsingClass.settingClassInfos(); // 읽어온 데이터를 Cinfos에 저장함
        parsingClass.showAllClassInfo(); // Cinfos 를 모두 출력
//
        parsingClass.setClassByRoom(); // 호실 마다 시간표를 정리
//        parsingClass.printClassByAllRooms(); // 호실 배정된 시간표를 출력

        // 특정시간을 매개변수로 넣고 비어있는지 차 있는지 확인한다.
//        for (String room : parsingClass.getClassByRoom().keySet()) {
//            System.out.println("room : " + room);
//            System.out.println(parsingClass.IsEmptyRoom(room, "월", "11:11"));
//        }


    }
}
