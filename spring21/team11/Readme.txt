//----------JavaCom----------//
Overview: 다양한 1대 1 대전 게임을 즐길 수 있는 하나의 통합 프로그램을 제작 

Run: Main 패키지 안 Main.java의 main 함수 실행

images 폴더 -> 메인프레임 및 체커 이미지 파일
tile 폴더 -> 모노레일 이미지 파일
omokTile 폴더 -> 오목 이미지 파일

프로젝트 폴더의 datafile.dat -> User 클래스의 직렬화를 위한 세이브파일
*.txt -> 유저 파일 (inner format: Name,Win,Lose ex) cat,0,0)

새 게임 임의 추가시 User 객체 p1, p2를 새 게임 클래스의 생성자로 전달 및
Main 패키지 안 User 클래스의 p1Win(), p2Win()함수 활용