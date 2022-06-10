대학생활도우미 
====================================================
made by : 김동현(2018111328), 허신행(2018111482)
====================================================
사용한 클래스
1.student( String id, Strind pw)
2.person( String id, String pw, String name, int snumber)
3.Time( int Hour, int Min, int totalMin  )
4.Lecture( String lectureName, String professor, int day, Time start, Time end, Time during)
5.seat( int no, String name, int sid, boolean check)

person extends student
Lecture has Time

Class : o    (student, person, Time, Lecture, seat)
Inheritance : o   (person extends student)
File I/O : o     (userdata.txt)
GUI: o      (JFrame, JPanel, JLabel, JTextField, JPasswordField, JButton)
Object Serializtion/Deserialization : o     (seat.dat,  309.dat, 342.dat, 345.dat, 355.dat, 다수의 아이디+planner.dat  )


1.로그인 회원가입
	1)로그인
		맨 처음 프로그램을 실행하여 로그인 창이 뜬다.
		ID부분를 입력하는 부분은 JTextField로 만들었고 비밀번호는 비밀번호 유출을 방지하기위해 JPasswordField로 만들었다. 
		ID와 비밀번호를 입력하고 로그인 버튼을 누르면 userdata.txt에서 저장되어 있는 정보들과 매칭을 해서 해당되는 사용자가 존재하면 로그인에 성공한다.
		로그인에 실패하면 아이디나 비밀번호가 틀렸다는 창이 뜬다.
		로그인에 성공하면 person 클래스 로 만든 전역 객체인 "user"에 현사용자에 모든 정보가 저장된다. 
		user에 저장된 데이터들을 바탕으로 밑에 나오는 메뉴들을 실행할때 사용된다.
		
	2)회원가입
		회원가입을 위해 ID, PW,이름,학번을 입력한다. 회원가입창에는 ID중복확인 기능이 있어 새로만들려는 ID가 이미 userdata.txt에 존재하는지 확인 작업을해서 결과를 창의로 띄워준다.
		4개의 정보를 입력하면 회원가입을 할 수 있다. 회원가입을 하면서 생긴 정보는 userdata.txt 에 저장된다.
		userdata.txt에는 저장되는 형식은, person클래스 객체에 tostring()을 취한 것이다.

2.메뉴


	1)플래너
		사용자 개개인에게 저장된 플래너를 불러온다. ( 없으면 새로 만든다. ) 
		단순 텍스트만 사용한 것이 아닌, JPanel 과 JLabel 등을 이용하여 시각적으로 바로 알아볼수 있도록 구현하였다.
		플래너에 일정 추가와 삭제 기능이 있으며 JMenuBar에 있는 버튼들을 이용하여 추가,삭제가 가능하다.

	2)도서관 좌석
		도서관 자리를 임의로 9개로 정하였다.
		자리가 초록색이면 빈자리라는 뜻이다. 빨간색이면 사용중이라는 뜻이다.
		자리를 대여 하려고 할때 본인이 이미 다른 자리를 사용중이면 이미 대여한 자리 번호를 알려준다.
		만약 이미 대여중인 자리를 클릭하면 그자리를 사용하고 있는 사람을 보여주며, 사용할 수 없다고 알려준다.
		또 반납 버튼을 누르면 자신이 예약한 자리가 반납이 된다.
		만약 대여한 자리가 없는데 반납 버튼을 누르면, 반납할 자리가 존재 하지 않는다라는 창이 뜬다.

	3)강의실 시간표
		각 강의실의 강의 시간표를 보여준다.
		각 강의마다 JPanel 을 사용하여 시각적으로 바로 알아볼수 있도록 구현하였으며 그 외에도
		시간표에 원하는 강의 추가와 강의표 전체 삭제기능을 추가하였다.
		강의 추가를 원하면 JMenuBar 안에 기능을 사용하면 되며, 버튼을 누르면 강의이름, 교수님 성함, 요일, 시작시간, 종료시간 등을 입력할 수 있는 창이 뜬다.
		입력받은 값들을 강의실번호.dat 에 저장하도록 구현하였다.		

	4)기숙사 식단(JSOUP, HTTPCore, HTTPClient 라이브러리 (설치)추가 필요!!!)
		경북대학교 기숙사 홈페이지에있는 내용을 JSOUP을 이용하여 crawling 해 온다.
		문화관과 첨성관 두 기숙사의 식단을 읽어 온다.
		클래스 이름을 이용하여 읽어 왔다. 클래스 이름은 "txt_right"이다.
		두 기숙사의 아침,점심,저녁 총 6개의 데이터를 배열에 순서대로 저장한 다음 출력해준다.
		 
	5)경북대 사진
		메뉴 중앙에 우리학교의 로고를 붙여 놓았다.