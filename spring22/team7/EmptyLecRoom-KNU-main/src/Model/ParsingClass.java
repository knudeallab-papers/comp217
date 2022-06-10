package Model;

import java.util.ArrayList;
import java.util.HashMap;

import static Model.ReadData.dataArray;

public class ParsingClass {

    public static ArrayList<ClassInfo> classInfos = new ArrayList<>(); // 읽어온 데이터를 알맞게 파싱함.
    public static HashMap<String, ArrayList<Time>> classByRoom = new HashMap<>(); //읽어온 데이터를 기준으로 호수에 따른 시간표를 파싱함.

    public ArrayList<ClassInfo> getClassInfos() {
        return classInfos;
    }

    public void settingClassInfos() {

        for (ArrayList<String> arrayList : dataArray) {
            int size = arrayList.size(); // 이미 강의수 만큼 만들어진 리스트의 사이즈를 받아옴

            ArrayList<Time> times = new ArrayList<>(); // 각 수업마다 타임 테이블을 생성하기 위함

            //시간 파싱 index : 1 ~ size-2
            for (int k = 1; k < size - 2; ) {

                int tempIdx = k+1;
                int cnt = 1;

                if(arrayList.get(k).startsWith("08", 2)) { // 오전 8시에 시작하는 시간은 제외
                    k++;
                    continue;
                }

                if( (Integer.parseInt((arrayList.get(k)).substring(2,4))) >= 18){ //오후 6시 이후 수업은 제외
                    k++;
                    continue;
                }

                if(arrayList.get(k).substring(0,1).equals("토")) {
                    k++;
                    continue;
                }

                while(true) {
                    // 시간표의 가장 앞 시간과 끝 시간을 파싱하기 위한 처리
                    if (arrayList.get(k).substring(0, 1).equals(arrayList.get(tempIdx).substring(0, 1))) {  // 같은 요일일 경우, 시간이 연달아 있기에 같은 요일인지 확인한다.
                        tempIdx++;
                        cnt++;
                    } else break;
                }
                // 최종적으로 요일이 같지 않아질때 (연달은 강의가 아닐때) 이 쪽으로 탈출하기에 tempIdx 값을 -1 해줄 필요가 있다.
                times.add(new Time(arrayList.get(k).substring(0, 1), arrayList.get(k).substring(2, 7), arrayList.get(tempIdx-1).substring(10, 15)));
                k += cnt;
            }

            FloorEnum floor = null;
            switch (arrayList.get(size-1).charAt(0)){
                case 'B': floor = FloorEnum.BASEMENT; break;
                case '2': floor = FloorEnum.SECOND; break;
                case '3': floor = FloorEnum.THIRD; break;
            }

            //파싱한 데이터를 classInfos 에 add 해준다.
            classInfos.add(new ClassInfo(arrayList.get(0), floor, arrayList.get(size - 1), times)); //classname, floor, classroom, timetable
        }
    }

    public void showAllClassInfo(){
        for (ClassInfo classInfo : classInfos) {
            System.out.println(classInfo.getClassname());
            if(classInfo.getTimetable().size() == 0) System.out.println("null data");
            classInfo.printTimetable();
            System.out.println(classInfo.getClassroom());
            System.out.println(classInfo.getFloor());
            System.out.println();
        }
    }

    public HashMap<String, ArrayList<Time>> getClassByRoom() {
        return classByRoom;
    }


    public void setClassByRoom(){
        /**
         * 먼저 ClassInfo 에서 순환하면서 Key 값 설정 및, ArrayList<Time> 객체를 할당한다.
         */

        //Key 값 설정 및 List 객체 생성
        for (ClassInfo classInfo : classInfos) {
            classByRoom.put(classInfo.getClassroom(),new ArrayList<>());
        }

        for (String classInfo : classByRoom.keySet()) {
            ArrayList<Time> arrayList = new ArrayList<>();
            for(ClassInfo classInfo2 : classInfos){
                if(classInfo.equals(classInfo2.getClassroom())){ // Room 이 같은지 검사한다.
                    if(classInfo2.getTimetable().size() != 0) {
                        for (Time temp : classInfo2.getTimetable()) { // Room 이 같은 수업의 시간표를 가져온다.
                            arrayList.add(temp); //9시에서 6시 사이 아무것도 없는 값들도 존재
                        }
                    }
                }
            }
            classByRoom.put(classInfo, arrayList);
        }
    }

    // 수업이 비어있는지 아닌지 판단하고, 그에 맞는 String 을 반환한다.
    public String IsEmptyRoom(String roomNum, String nowDay, String nowTime){

        TimeCalculator timeCalculator = new TimeCalculator(nowTime);

        ArrayList<Time> times = classByRoom.get(roomNum); //해당 호실의 타임 테이블을 가져온다.
        for (Time time : times) {
            if(nowDay.equals(time.getDay())){ //요일이 같다면
                timeCalculator.setTimes(time.getStart(), time.getEnd());
                timeCalculator.compareTime();
                if(!timeCalculator.isEmpty()){ //강의실이 차 있다면
                    return timeCalculator.getResult();
                }
            }
        }

        //위에서 반환이 되지 않았다면
        if(timeCalculator.getLeftTime() == 99999){ //아예 수업이 없는 경우
            timeCalculator.setResult("이후에 수업이 없습니다.");
        }
        return timeCalculator.getResult();
    }

    //비어있는지 아닌지만 반환
    public boolean IsEmptyRoomBool(String roomNum, String nowDay, String nowTime){
        TimeCalculator timeCalculator2 = new TimeCalculator(nowTime);

        ArrayList<Time> times = classByRoom.get(roomNum); //해당 호실의 타임 테이블을 가져온다.
        for (Time time : times) {
            if(nowDay.equals(time.getDay())){ //요일이 같다면
                timeCalculator2.setTimes(time.getStart(), time.getEnd());
                timeCalculator2.compareTime();
                if(!timeCalculator2.isEmpty()){ //강의실이 차 있다면
                    return false;
                }
            }
        }

        return true;
    }

    public void printClassByAllRooms(){
        for(String room : classByRoom.keySet()){
            System.out.println("room : " + room);
            for(Time time : classByRoom.get(room)){
                System.out.println(time);
            }
            System.out.println();
        }
    }

    public void setClassByRoom(HashMap<String, ArrayList<Time>> classByRoom) {
        this.classByRoom = classByRoom;
    }

}
