본 프로그램은 2048 게임이다.

1. 3x3 4x4 5x5 6x6 게임판을 고른다.

2. 게임을 시작한다.
   1) 임의의 2칸에 2or4 라는 숫자가 정해진다
   2) 키보드 방향키 입력시 방향키방향으로 모든칸이 움직인다
   3) 움직일때 같은 숫자의 칸이 있으면 숫자가 2배가 되고 합쳐진다
   4) 합쳐진 숫자 만큼 점수가 오른다 (4와 4가 합쳐져 8이 되면 8점이 추가된다)
   5) 임의의 빈칸에 2or4 라는 숫자가 추가된다
   6) 더 이상 합쳐질 숫자가 없어서 못움직이게 되면 GameOver창이 나오고 이름 입력시 점수가 저장된다
	(이름입력시 한 단어만 가능(띄어쓰기안됨))
   6-2) 일정 숫자가 만들어지면 Clear창이 나오고 이름 입력시 점수가 저장된다
	(3x3→1024  4x4→2048  5x5,6x6→4096) 
   +1) 게임 도중 프로그램이 종료되어도 게임진행상황은 저장된다
   +2) 'R'키 입력시 게임이 리셋된다
   +3) 'BackSpace'키 입력시 한수물릴수있다(연속사용불가)

3. 랭킹 페이지에 가면 자신이 지금 껏 했던 기록의 랭킹을 점수순으로 상위 10위까지 볼 수 있다
