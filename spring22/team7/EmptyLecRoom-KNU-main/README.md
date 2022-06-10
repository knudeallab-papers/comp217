
## 경북대 빈 강의실 조회 서비스 프로그램
학교에서의 공강 시간때 시간을 보내기 위함 또는, 

공부를 하고 싶을때 빈 강의실을 손쉽게 찾아보고 활용할 수 있는 프로그램을 만들고자 하였음.
<br>

## 개발환경

- Windows 10
- JDK 1.8
- Swing GUI
- Intellij
<br>

## 구현 도메인

- IT5호관 지하1층, 2층, 3층의 빈강의실을 알아봄
- 시간은 오전 9시부터 오후 6시에 한함
- 층별로 보기 쉽게 나누어 놓았음
- 강의실에 마우스를 올리면
    - 비어있을 경우에는 다음 강의시간까지 남은 시간을 알려줌
    - 사용중일 경우 끝나는 시간을 알려줌
- 강의실을 클릭하면 해당 강의실의 시간표를 보여줌
<br>

## 프로그램 실행 흐름

> 데이터 크롤링 -> 데이터 파싱 -> 계산 -> GUI 
> * 데이터 크롤링은 [이곳](https://github.com/whipbaek/Python-Crawling/tree/main) 을 참고
<br>

## 실행 방법 및 실행 화면

1. Test 패키지 내의 ViewTest.java를 실행함

![image](https://user-images.githubusercontent.com/75191916/172535197-d6d924a6-bbf2-44e8-91ff-c6f120247ae5.png)


2. 초기 화면, 원하는 시간을 입력하고 submit 버튼을 클릭한다.

![1](https://user-images.githubusercontent.com/75191916/172535284-7df4d56b-8fbf-4357-a1bf-e0f50623ed2e.png)

3. 이후 층을 선택한다.

![2](https://user-images.githubusercontent.com/75191916/172535295-9af7d6cb-b359-4183-bb81-dfbbd3560eea.png)

4. 층을 클릭하게 되면, 해당 층의 도면 형태의 창이 뜨게 된다. 
   빈 강의실은 파란색으로, 사용중인 강의실은 빨간색으로 표기된다.

![3](https://user-images.githubusercontent.com/75191916/172535358-fd25d5a0-c857-4456-a3a1-20901422b344.png)


5. 강의실에 마우스를 올리면 다음 강의까지 남아있는 시간 or 현재 강의가 끝날때 까지의 남은 시간을 알려준다.

![4](https://user-images.githubusercontent.com/75191916/172535489-2e2e1275-96c6-46b2-9bb3-541e557d1aa2.png)

6. 강의실을 클릭했을시 해당 강의실의 시간표 창이 표시된다.

![5](https://user-images.githubusercontent.com/75191916/172535527-a978058b-3dbb-4c42-892c-1e109087ffc7.png)

