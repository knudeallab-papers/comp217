## 제작자 : 김현진, 최지훈

## 프로젝트 제목 : 15조 자바 비행 슈팅 게임 제작

자바 수업 시간 내 배운 내용을 바탕으로 팀프로젝트에서 이를 응용을 해보기 위해 해당 코드를 제작.

시간이 지날 때 마다 적들이 생성되어 공격하고
보스를 잡을 경우 클리어, 사망 시 게임 오버 판정 후 점수 화면으로 넘어간다.

## 개발 환경 (실행 환경)
64비트 버전 윈도우 10
이클립스 제작 (JDK 1.8 버전 컴파일러)

## 사용방법 

파일을 이클립스를 통해 실행 후
Boss.java
checkscore_Frame.java
Enemy.java
Enemy1.java
Enemy2.java
Enemy3.java
Explosion.java
game_Frame.java
game.java
Missile.java
score_Frame.java
Score.java
Start.java
클래스 들을 default package (혹은 같은)에 넣고

img 폴더 내부의 이미지, 이미지 이름들을 확인한 후
'game.java' 내에 main을 실행시켜 시작화면을 생성하여 사용한다.
(Score.dat 파일은 지워도 실행가능)

게임 화면에서 플레이 시 방향키를 통해 움직이며
space키를 통해 공격하여 조종한다.

//주석이 깨질경우
Preferences > General > Workspace > Text file encoding > Other선택 > UTF-8 선택 > OK버튼 클릭
이후 다시 파일 받기

## 버그들
게임이 끝나고 이미지에 버튼이 가려져 마우스를 올릴 경우에 보이게 된다.

## 사용 기술들
클래스 
상속 
파일 I/O
컬렉션 (ArrayList)
GUI
직렬화, 역 직렬화

쓰레드 (추가 구현)
