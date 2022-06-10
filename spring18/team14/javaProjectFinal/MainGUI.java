
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;


public class MainGUI extends JFrame {
    /*
    창고 = Food클래스
    메뉴 = Menu클래스
     */

    private static Color mainColor = new Color(128, 128, 200);
    private static Border mainLineBorder = BorderFactory.createLineBorder(mainColor, 1);

    private CardLayout centerCardLayout = new CardLayout();

    /* top */
    private JButton select_tableButton = new JButton("테이블");
    private JButton select_storageButton = new JButton("창고");
    private JButton select_customerButton = new JButton("회원");
    private JButton select_menuButton = new JButton("메뉴");
    private JButton select_employeeButton = new JButton("직원");

    private JButton shutdown = new JButton("종료");

    private JTextField dayView = new JTextField("1970년 1월 1일");
    private JButton end = new JButton("마감");

    private JTextField dayMoney = new JTextField("오늘 매출: 0원");
    private JTextField money = new JTextField("전체 잔고: 0원");

    /* sub panels */
    private JPanel main_north = new JPanel();

    private JPanel main_viewCenter = new JPanel();

    private JPanel main_center = new JPanel();
    private JPanel main_center_table = new JPanel();
    private JPanel main_center_storage = new JPanel();
    private JPanel main_center_customer = new JPanel();
    private JPanel main_center_menu = new JPanel();
    private JPanel main_center_employee = new JPanel();

    private JPanel main_north_top = new JPanel();
    private JPanel main_north_bottom = new JPanel();

    /* table */
    private List<Table> tables = new ArrayList<>();

    private TableModel table_model = new TableModel(new MenuInTableInfo());
    private GoldenJTable table_table = new GoldenJTable(table_model);

    private JButton table_add = new JButton("추가");
    private JButton table_credit = new JButton("결제");

    private int selectedTable = 0;

    //private List<Employee> customers = new ArrayList<>();

    /* storage */
    private TableModel storageModel = new TableModel(new FoodInfo());
    private GoldenJTable storageTable = new GoldenJTable(storageModel);
    private JLabel storage_name = new JLabel("이름: ");
    private JLabel storage_price = new JLabel("가격: ");
    private JLabel storage_selled = new JLabel("판매처: ");
    private JLabel storage_tel = new JLabel("연락처: ");
    //private JTextField storage_number = new JTextField("연락처: ");
    private JLabel storage_amount = new JLabel("주문량: ");

    private JButton storage_orderSuccess = new JButton("주문");
    private JButton storage_orderFail = new JButton("주문취소");

    private JButton storage_orderAdd = new JButton("추가");
    private JButton storage_orderRemove = new JButton("제거");

    /* customer */
    private TableModel customer_model = new TableModel(new CustomerInfo());
    private GoldenJTable customer_table = new GoldenJTable(customer_model);

    private JButton customer_edit = new JButton("편집");
    private JButton customer_add = new JButton("추가");
    private JButton customer_remove = new JButton("삭제");

    /* menu */
    TableModel menu_model = new TableModel(new MenuInfo());
    GoldenJTable menu_table = new GoldenJTable(menu_model);
    private JButton menu_add = new JButton("추가");
    private JButton menu_edit = new JButton("편집");
    private JButton menu_remove = new JButton("제거");

    private JLabel menu_name = new JLabel("이름: ");
    private JLabel menu_price = new JLabel("가격: ");
    private JLabel menu_unitprice = new JLabel("단가: ");
    private JLabel menu_material = new JLabel("재료: ");

    /* employees */
    private TableModel employeesModel = new TableModel(new EmployeeInfo());
    private GoldenJTable employees = new GoldenJTable(employeesModel);
    private JButton employeesEdit = new JButton("편집");
    private JButton employeesAdd = new JButton("추가");
    private JButton employeesRemove = new JButton("제거");

    private long endDay = System.currentTimeMillis();

    private int totalPrice;
    private int dayPrice;

    private int bonusInitalize;

    //private HashMap<String, Food> foods = new HashMap<>();

    private Frame instance;

    {
        instance = this;
        dayView.setEditable(false); // 수정불가능하게
        dayMoney.setEditable(false);
        money.setEditable(false);

        main_center_table.setLayout(new BorderLayout());
        main_center_storage.setLayout(new BorderLayout());
        main_center_customer.setLayout(new BorderLayout());
        main_center_menu.setLayout(new BorderLayout());
        main_center_employee.setLayout(new BorderLayout());
    }


    public MainGUI() {
        setTitle("POS"); // 타이틀 설정
        setSize(800, 600); // 사이즈 설정

        addWindowListener(new WindowAdapter() { // X버튼 눌렀을때 종료하기
            @Override
            public void windowClosing(WindowEvent e) {
                closing();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                closing();
            }


        });

        //JPanel main_north_gridbag = new JPanel();

        main_center.setLayout(centerCardLayout);

        //main_center.setLayout(new BorderLayout());

        main_north.setLayout(new GridBagLayout());
        main_north_top.setLayout(new GridBagLayout());
        main_north_bottom.setLayout(new GridBagLayout());

        try {
            loadAllResources(); // 리소스 로드
        } catch (Exception ex) {
        }

        initalizeMainNorthTop(); // 패널들 뷰(뷰=컴포넌트) 배치
        initalizeMainNorthBottom();
        initalizeMenu();
        initalizeEmployee();
        initalizeStorage();
        initalizeTable();
        initalizeCenter();
        initalizeCustomer();

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.BOTH;
            main_north.add(main_north_top, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.BOTH;
            main_north.add(main_north_bottom, gbc);
        }

        select_customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cardLayout은 문자열을 넣으면 해당 문자열로 등록한 패널을 띄워줌 등록은 아래 메서드에서 하였음
                centerCardLayout.show(main_center, "회원");
            }
        });

        select_employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerCardLayout.show(main_center, "직원");
            }
        });

        select_menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerCardLayout.show(main_center, "메뉴");
            }
        });

        select_storageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerCardLayout.show(main_center, "창고");
            }
        });

        select_tableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerCardLayout.show(main_center, "테이블");
            }
        });

        shutdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closing();
            }
        });

        main_north.setBorder(mainLineBorder);
        //setLayout(new BorderLayout());
        add(main_north, "North");
        add(main_center, "Center");

        // 매달 1일 마일리지 초기화 코드
        int month = Calendar.getInstance().get(Calendar.MONTH); // 현재 달 얻기
        if (bonusInitalize != month) { // 만약 마지막으로 마일리지를 초기화했을때와 현재 달이 다르다면
            bonusInitalize = month; // 지금 달로 설정해주고

            // 모든 고객의 마일리지 초기화
            List<TableModelElement> elements = customer_model.getContents();
            for (int i = 0; i < elements.size(); i++) {
                Customer customer = (Customer) elements.get(i);

                customer.setBonus("0");
                customer.setLevel("일반");
            }

            // 달 정보 저장
            savePrice();
        }

        // 마감 날짜 초기화
        setEndDay();


        //보여주자
        setVisible(true);
    }

    private void initalizeCenter() {
        main_center.setBorder(mainLineBorder);

        // 이제 카드레이아웃에서 show(테이블)하면 테이블이 보임
        main_center.add(main_center_table, "테이블");
        main_center.add(main_center_storage, "창고");
        main_center.add(main_center_customer, "회원");
        main_center.add(main_center_menu, "메뉴");
        main_center.add(main_center_employee, "직원");

        centerCardLayout.show(main_center, "테이블");
    }

    private void initalizeTable() {
        JPanel main_center_table_center = new JPanel();
        JPanel main_center_table_east = new JPanel();

        //main_center_center.setLayout(new GridLayout(2, 4));

        final int WIDTH = 4; // 테이블 가로갯수
        final int HEIGHT = 2; // 테이블 세로갯수

        //main_center_table.setLayout(new GridLayout(HEIGHT, WIDTH));
        main_center_table_center.setLayout(new GridBagLayout());
        main_center_table_east.setLayout(new BorderLayout());

        /*
        for(int i = 0; i < WIDTH * HEIGHT; i++) {
            Table table = new Table(i+1);

            main_center_table.add(table);
            tables.add(table);
        }*/

        //if(tables.size()==0)
        {

            List<Table> newerTable = new ArrayList<>();
            int originalTableSize = tables.size();
            //System.out.println(tables.size());

            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    final int index = x + y * WIDTH + 1; // 테이블 번호 계산(1,2,3,4,5...)

                    Table table;

                    // 테이블 정보가 저장되어 있다면 저장된 테이블 정보를 가져옴
                    table = new Table(index);
                    newerTable.add(table);
                    if (index - 1 < originalTableSize) {
                        Table temp = tables.get(index - 1);
                        for (int i = 0; i < temp.getMenuLists().size(); i++) {
                            table.addMenu(temp.getMenuLists().get(i), 1);
                        }
                    }

                    //뷰 설정
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = x;
                    gbc.gridy = y;
                    gbc.insets = new Insets(10, 10, 10, 10); //상하좌우 얼마나 띄울것인가
                    gbc.fill = GridBagConstraints.BOTH; // 만약 뷰가 충분히 더 넓을수 있는 공간이 있다면 가로와 세로 둘다 넓힘(VERTICAL을 하면 세로만 넓어짐)
                    gbc.weightx = 1;
                    gbc.weighty = 1;
                    table.addTableClickEvent(new TableClickEvent() {
                        @Override
                        public void onTableClickEvent(int index) {
                            //테이블을 클릭했을때 현재 선택한 인덱스로 설정하고
                            selectedTable = index;
                            //System.out.println(index);

                            //기존의 보여줬던 목록 제거
                            table_model.removeAll();

                            //현재의 목록을 넣어줌
                            Table table = tables.get(selectedTable);
                            for (int i = 0; i < table.getMenuLists().size(); i++) {
                                Menu menu = table.getMenuLists().get(i);
                                table_model.add(menu);
                            }
                        }
                    });
                    main_center_table_center.add(table.getPanel(), gbc);

                }
            }
            // 새로 다시 초기화한 테이블로 설정함
            tables.clear();
            tables.addAll(newerTable);
        }

        JPanel main_center_table_east_south = new JPanel();
        main_center_table_east_south.setLayout(new GridBagLayout());

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 5, 5, 5);
            main_center_table_east_south.add(table_add, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 5, 5, 5);
            main_center_table_east_south.add(table_credit, gbc);
        }

        main_center_table_east.add(main_center_table_east_south, "South");
        main_center_table_east.add(table_table, "Center");

        main_center_table_east.setMinimumSize(new Dimension(250, 0));
        main_center_table_east.setPreferredSize(new Dimension(250, 0));
        main_center_table_east.setMaximumSize(new Dimension(250, Integer.MAX_VALUE));

        table_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultComboBoxModel model = new DefaultComboBoxModel<String>(); // 콤보박스 모델

                for (int i = 0; i < menu_model.getContents().size(); i++) { // 리스트를 콤보박스에 넣는다
                    Menu customer = (Menu) menu_model.getElement(i);
                    model.addElement(customer.getElementAt(0));
                }

                JComboBox cb = new JComboBox(model); // 해당 콤보박스 모델로 콤보박스를 만듬
                int resultInt = JOptionPane.showConfirmDialog(instance, cb, "음식을 선택하세요", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE); // 고객 선택하는 Dialog를 띄움

                if (resultInt == JOptionPane.OK_OPTION) { // 확인 버튼이 눌렸다면
                    // 선택된 메뉴 얻기
                    Menu menu = (Menu) menu_model.getElement(cb.getSelectedIndex());

                    //해당 메뉴를 테이블에 추가
                    Table table = tables.get(selectedTable);
                    table.addMenu(menu, 1);
                    table_model.add(menu);

                    //테이블 정보 저장
                    saveTables();
                }
            }
        });

        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean canend = true; // 마감할수 있는지 없는지

                for (int i = 0; i < tables.size(); i++) { // 모든 테이블에 루프돌림
                    Table t = tables.get(i);

                    if (t.getMenuLists().size() > 0) { // 테이블에서 음식이 0개 초과(1개이상)인경우
                        canend = false; // 마감 못하게 함
                        break;
                    }
                }
                if (!canend) { // 마감을 못한다면
                    JOptionPane.showMessageDialog(instance, "아직 테이블 모두가 마감되지 않았습니다"); // 메세지 출력
                } else { //마감할 수 있다면

                    //날짜를 1일 더함(60초 * 60분 * 24시간 = 1일 * 1000MS)
                    //첨언: endDay는 System.currentTimeMillis()를 저장하는데
                    //System.currentTimeMillis()는 (1초=1000ms) 1970년 1월 1일부터 지금까지 지난 ms시간을 반환해줌
                    endDay += 60 * 60 * 24 * 1000;

                    totalPrice += dayPrice;
                    dayPrice = 0;

                    money.setText("전체 잔고: " + totalPrice + "원");
                    dayMoney.setText("오늘 매출: 0원");

                    //텍스트 업데이트
                    setEndDay();
                }
            }
        });

        table_credit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        if (table_model.getSize() > 0) { // 결제를 해야하는 상태(테이블에서 시킨 음식이 있을때)라면
                            //List<String> customers = new ArrayList<>();

                            try {
                                DefaultComboBoxModel model = new DefaultComboBoxModel<String>(); // 콤보박스 모델
                                model.addElement("선택 없음"); // 회원 선택 하고싶지 않을때 선택하는것

                                for (int i = 0; i < customer_model.getContents().size(); i++) { // 회원 리스트를 콤보박스에 넣는다
                                    Customer customer = (Customer) customer_model.getElement(i);
                                    model.addElement(customer.getElementAt(2));
                                }

                                JComboBox cb = new JComboBox(model); // 해당 콤보박스 모델로 콤보박스를 만듬
                                int resultInt = JOptionPane.showConfirmDialog(instance, cb, "회원을 선택하세요", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE); // 고객 선택하는 Dialog를 띄움

                                if (resultInt == JOptionPane.OK_OPTION) { // 만약 고객 선택을 하였다면
                                    int result = cb.getSelectedIndex(); // 몇번 선택했니
                                    Table table = tables.get(selectedTable); // 현재 선택한 테이블
                                    int price; // 가격

                                    if (result != 0) { // 만약 고객을 선택했다면(선택없음의 인덱스는 0이니깐)
                                        Customer customer = (Customer) customer_model.getElement(result - 1); // 선택된 고객을 얻는다

                                        // 할인율 구하기(98%,95%,90%)
                                        int priceDiscount = getDiscount(customer.getElementAt(1));

                                        //할인율에 따라 최종 가격 계산
                                        price = (int) Math.round((double) table.getTotalPrice() / 100 * priceDiscount);

                                        // 마일리지 설정(2% 증가)
                                        int bonus = Integer.parseInt(customer.getBonus());
                                        customer.setBonus(String.valueOf(bonus + (int) Math.round((double) table.getTotalPrice() / 50)));

                                        // 마일리지 증가한만큼 등급도 변했다면 등급 업데이트
                                        String level = getBonusLevel(customer.getBonus());
                                        customer.setLevel(level);
                                    } else {
                                        // 고객 선택 안했으니 그냥 그가격 그대로 내야됨
                                        price = table.getTotalPrice();
                                    }


                                    //토탈 가격 올리기
                                    //totalPrice += price;
                                    dayPrice += price;

                                    // 텍스트 업데이트
                                    //money.setText("전체 잔고: " + totalPrice + "원");
                                    dayMoney.setText("오늘 매출: " + dayPrice + "원");

                                    // 얘 호출하면 뷰 사이즈 바뀌었을때 다시 계산해줌
                                    main_north_top.updateUI();

                                    // 테이블에 남아있던 음식 리스트 제거
                                    table_model.removeAll();
                                    table.credit();

                                    // 현재 테이블 정보, 매출 정보 저장
                                    saveTables();
                                    savePrice();
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

            }
        });

        // 패널 속에 패널 넣기
        main_center_table.add(main_center_table_center, "Center");
        main_center_table.add(main_center_table_east, "East");
    }

    // endDay에 저장된 숫자대로 날짜 업데이트
    private void setEndDay() {
        dayView.setText(getDate());
        main_north_top.updateUI();

        savePrice();
    }

    public String getDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(endDay);

        return cal.get(Calendar.YEAR) + "년 " + (cal.get(Calendar.MONTH) + 1) + "월 " + cal.get(Calendar.DATE) + "일";
    }

    private void initalizeMenu() {
        // 뷰 = 컴포넌트
        // 메뉴 뷰들 배치하는 부분
        JPanel main_center_menu_center = new JPanel();
        main_center_menu_center.setLayout(new GridBagLayout());
        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0; // x좌표
            gbc.gridy = 0; // y좌표
            gbc.weighty = 0.1; // 만약 충분히 더 늘어날 공간이 있다면 얼마의 비율로 늘어날 것인가(상대적으로)
            main_center_menu_center.add(menu_name, gbc); // 뷰 추가
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.weighty = 0.1;
            main_center_menu_center.add(menu_price, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.weighty = 0.1;
            main_center_menu_center.add(menu_unitprice, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.weighty = 0.1;
            main_center_menu_center.add(menu_material, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.weighty = 0.4;
            main_center_menu_center.add(new JPanel(), gbc);
        }

        JPanel main_center_menu_west = new JPanel();
        main_center_menu_west.setLayout(new BorderLayout());
        main_center_menu_west.add(menu_table, "Center");

        JPanel main_center_menu_west_south = new JPanel();
        main_center_menu_west_south.setLayout(new GridBagLayout());
        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 5, 5, 5);
            main_center_menu_west_south.add(menu_edit, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 0, 5, 5);
            main_center_menu_west_south.add(menu_add, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 3;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 0, 5, 5);
            main_center_menu_west_south.add(menu_remove, gbc);
        }

        menu_table.addTableClickEvent(new GoldenJTableClickEvent() {
            @Override
            public void TableClickEvent() {
                // 테이블을 클릭했을때 몇번째 줄을 클릭했는지 얻음
                int row = menu_table.getSelectedRow();

                // 줄에 따른 메뉴 얻어냄
                Menu menu = (Menu) menu_model.getElement(row);

                // 메뉴 정보를 출력해줌
                menu_name.setText("이름:" + menu.getElementAt(0));
                menu_price.setText("가격:" + menu.getElementAt(1));
                menu_unitprice.setText("단가:" + menu.getElementAt(2));
                menu_material.setText("재료:" + menu.getElementAt(3));
            }
        });

        menu_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        try {
                            // 선택된 줄 구하기
                            int row = menu_table.getSelectedRow();

                            // 선택된 줄에 대한 메뉴 구하기
                            Menu menu = (Menu) menu_model.getElement(menu_table.getSelectedRow());

                            // 다시 질의함
                            String name = JOptionPane.showInputDialog(instance, "이름을 입력하세요", menu.getElementAt(0));
                            if (name != null) { // name이 null이라는건 입력하고 확인을 누르지 않고 않고 취소버튼을 눌러버렸을때 null이 됨
                                                // null이 아니다 = 확인버튼을 눌렀다
                                String price = JOptionPane.showInputDialog(instance, "가격을 입력하세요", menu.getElementAt(1));
                                if (price != null) {
                                    String unitprice = JOptionPane.showInputDialog(instance, "단가를 입력하세요", menu.getElementAt(2));
                                    if (unitprice != null) {
                                        String materials = JOptionPane.showInputDialog(instance, "재료를 입력하세요", menu.getElementAt(3));
                                        if (materials != null) {
                                            //얻어낸 값을 테이블에 저장해줌
                                            menu_model.setValueAt(name, row, 0);
                                            menu_model.setValueAt(price, row, 1);
                                            menu_model.setValueAt(unitprice, row, 2);
                                            menu_model.setValueAt(materials, row, 3);

                                            // 메뉴 정보를 출력해줌
                                            menu_name.setText("이름:" + menu.getElementAt(0));
                                            menu_price.setText("가격:" + menu.getElementAt(1));
                                            menu_unitprice.setText("단가:" + menu.getElementAt(2));
                                            menu_material.setText("재료:" + menu.getElementAt(3));

                                            // 테이블에 목록 있으면 메뉴정보 업데이트
                                            table_table.updateUI();

                                            // 메뉴 정보 저장
                                            saveMenus();
                                        }
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            /*ignore*/
                        }
            }
        });

        menu_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        try {
                            //정보 질의하기
                            String name = JOptionPane.showInputDialog(instance, "이름을 입력하세요");

                            if (name != null) {
                                String price = JOptionPane.showInputDialog(instance, "가격을 입력하세요");

                                if (price != null) {
                                    String unitprice = JOptionPane.showInputDialog(instance, "단가를 입력하세요");

                                    if (unitprice != null) {
                                        String materials = JOptionPane.showInputDialog(instance, "재료를 입력하세요");

                                        if (materials != null) {
                                            // 메뉴 생성하고
                                            Menu menu = new Menu(name, price, unitprice, materials);

                                            // 메뉴 테이블에 메뉴를 넣어줌
                                            menu_model.add(menu);

                                            // 메뉴 정보 저장
                                            saveMenus();
                                        }
                                    }
                                }
                            }
                        } catch (Exception ex) {

                        }
            }
        });

        menu_remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //선택된 줄 설정
                int row = menu_table.getSelectedRow();

                //제거
                menu_model.remove(row);

                //메뉴 정보 저장
                saveMenus();
            }
        });

        //고정된 크기 설정
        main_center_menu_west.setMinimumSize(new Dimension(250, 0));
        main_center_menu_west.setMaximumSize(new Dimension(250, Integer.MAX_VALUE));
        main_center_menu_west.setPreferredSize(new Dimension(250, 600));

        //패널 안에 패널 넣기
        main_center_menu_west.add(main_center_menu_west_south, "South");
        main_center_menu.add(main_center_menu_west, "West");
        main_center_menu.add(main_center_menu_center, "Center");
    }

    private void initalizeStorage() {
        /*
        이후 initalize~~~() 애들은 구조가 다 똑같아서 설명을 굳이 안해도 될 듯 합니다.
         */
        // 뷰 넣기
        JPanel main_center_storage_east = new JPanel();
        main_center_storage_east.setBorder(mainLineBorder);
        main_center_storage_east.setLayout(new GridBagLayout());

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            gbc.weightx = 1;
            gbc.weighty = 0.1;
            gbc.insets = new Insets(5, 5, 5, 5); // 상하좌우 몇픽셀씩 얼마나 띄울것인가 (상하좌우 5픽셀씩)
            main_center_storage_east.add(storage_name, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 3;
            gbc.weightx = 1;
            gbc.weighty = 0.1;
            gbc.insets = new Insets(5, 5, 5, 5);
            main_center_storage_east.add(storage_price, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.weightx = 1;
            gbc.weighty = 0.1;
            gbc.gridwidth = 3;
            gbc.insets = new Insets(5, 5, 5, 5);
            main_center_storage_east.add(storage_selled, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.weightx = 1;
            gbc.weighty = 0.1;
            gbc.gridwidth = 3;
            gbc.insets = new Insets(5, 5, 5, 5);
            main_center_storage_east.add(storage_tel, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.weightx = 1;
            gbc.weighty = 0.1;
            gbc.gridwidth = 3;
            gbc.insets = new Insets(5, 5, 5, 5);
            main_center_storage_east.add(storage_amount, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.weightx = 1;
            gbc.weighty = 0.5;
            gbc.gridwidth = 3;

            gbc.insets = new Insets(5, 5, 5, 5);
            main_center_storage_east.add(new JPanel(), gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.weightx = 1;
            //gbc.insets = new Insets(5, 5, 5, 5);
            main_center_storage_east.add(new JPanel(), gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 6;
            //gbc.weightx = 1;
            gbc.insets = new Insets(5, 5, 5, 5);
            main_center_storage_east.add(storage_orderSuccess, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 2;
            gbc.gridy = 6;
            //gbc.weightx = 1;
            gbc.insets = new Insets(5, 5, 5, 5);
            main_center_storage_east.add(storage_orderFail, gbc);
        }

        storage_orderAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 질의하기
                String name = JOptionPane.showInputDialog(instance, "이름을 입력하세요");
                if (name != null) { // name == null이라면 취소버튼을 눌러서 입력하지 앟은 것임
                    String amount = JOptionPane.showInputDialog(instance, "재고를 입력하세요");
                    if (amount != null) {
                        String order = JOptionPane.showInputDialog(instance, "주문을 입력하세요");
                        if (order != null) {
                            String price = JOptionPane.showInputDialog(instance, "가격을 입력하세요");
                            if (price != null) {
                                String selled = JOptionPane.showInputDialog(instance, "판매처를 입력하세요");
                                if (selled != null) {
                                    String tel = JOptionPane.showInputDialog(instance, "연락처를 입력하세요");
                                    if (tel != null) {
                                        //음식 생성
                                        Food food = new Food(name, amount, order, price, selled, tel);

                                        //테이블에 음식 추가
                                        storageModel.add(food);

                                        //음식 정보 저장
                                        saveStorages();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        storageTable.addTableClickEvent(new GoldenJTableClickEvent() {
            @Override
            public void TableClickEvent() {
                // 선택된 줄에 대한 정보 출력하기
                int row = storageTable.getSelectedRow();
                Food food = (Food) storageModel.getElement(row);

                storage_name.setText("이름: " + food.getElementAt(0));
                storage_amount.setText("주문량: " + food.getElementAt(1));
                storage_selled.setText("판매처: " + food.getSelled());
                storage_tel.setText("연락처: " + food.getTel());
                storage_price.setText("가격: " + food.getElementAt(3));
            }
        });

        storage_orderRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 제거하기
                int row = storageTable.getSelectedRow();
                storageModel.remove(row);

                saveStorages();
            }
        });


        // 패널 속에 패널 넣기, 뷰 넣기
        JPanel main_center_storage_center = new JPanel();
        main_center_storage_center.setBorder(mainLineBorder);
        main_center_storage_center.setLayout(new BorderLayout());

        main_center_storage_center.add(storageTable, "Center");

        JPanel main_center_storage_center_south = new JPanel();
        main_center_storage_center_south.setLayout(new GridBagLayout());
        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            main_center_storage_center_south.add(new JPanel(), gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 5, 5, 5);
            main_center_storage_center_south.add(storage_orderAdd, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 5, 5, 5);
            main_center_storage_center_south.add(storage_orderRemove, gbc);
        }

        main_center_storage_center.setMinimumSize(new Dimension(400, 0));
        main_center_storage_center.setPreferredSize(new Dimension(400, 0));
        main_center_storage_center.setMaximumSize(new Dimension(400, Integer.MAX_VALUE));

        main_center_storage_center.add(main_center_storage_center_south, "South");
        main_center_storage.add(main_center_storage_center, "West");
        main_center_storage.add(main_center_storage_east, "Center");

    }

    private void initalizeCustomer() {
        JPanel main_center_customer_south = new JPanel();
        main_center_customer_south.setLayout(new GridBagLayout());

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            main_center_customer_south.add(new JPanel(), gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 5, 5, 5);
            main_center_customer_south.add(customer_edit, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 5, 5, 5);
            main_center_customer_south.add(customer_add, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 3;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 5, 5, 5);
            main_center_customer_south.add(customer_remove, gbc);
        }

        customer_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number = JOptionPane.showInputDialog(instance, "번호를 입력하세요");
                if (number != null) {
                    String name = JOptionPane.showInputDialog(instance, "이름을 입력하세요");

                    if (name != null) {
                        String bonus = JOptionPane.showInputDialog(instance, "마일리지를 입력하세요");

                        if (bonus != null) {
                            String tel = JOptionPane.showInputDialog(instance, "연락처를 입력하세요");

                            if (tel != null) {
                                String level = getBonusLevel(bonus);

                                Customer customer = new Customer(number, level, name, bonus, tel);
                                customer_model.add(customer);

                                saveCustomers();
                            }
                        }
                    }
                }
            }
        });

        customer_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = customer_table.getSelectedRow();
                Customer customer = (Customer) customer_table.getSelectedItem();

                String number = JOptionPane.showInputDialog(instance, "번호를 입력하세요", customer.getElementAt(0));

                if (number != null) {
                    String name = JOptionPane.showInputDialog(instance, "이름을 입력하세요", customer.getElementAt(2));

                    if (name != null) {
                        String bonus = JOptionPane.showInputDialog(instance, "마일리지 입력하세요", customer.getElementAt(3));

                        if (bonus != null) {
                            String tel = JOptionPane.showInputDialog(instance, "연락처를 입력하세요", customer.getElementAt(4));

                            if (tel != null) {
                                String level = getBonusLevel(bonus);

                                customer_model.setValueAt(number, row, 0);
                                customer_model.setValueAt(level, row, 1);
                                customer_model.setValueAt(name, row, 2);
                                customer_model.setValueAt(bonus, row, 3);
                                customer_model.setValueAt(tel, row, 4);

                                saveCustomers();
                            }
                        }
                    }
                }
            }
        });

        customer_remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customer_model.remove(customer_table.getSelectedRow());
            }
        });

        main_center_customer.add(main_center_customer_south, "South");
        main_center_customer.add(customer_table, "Center");
    }

    // 할인율 구하기
    private int getDiscount(String level) {
        switch (level) {
            case "골드":
                return 95;
            case "플래티넘":
                return 90;
            default:
                return 98;
        }
    }

    //마일리지에 따른 등급 얻기
    private String getBonusLevel(String priceStr) {
        try {
            int price = Integer.parseInt(priceStr);

            if (price <= 500) {
                return "일반";
            } else if (price >= 500 && price <= 1000) {
                return "골드";
            } else if (price >= 1000) {
                return "플래티넘";
            }
        } catch (Exception ex) {

        }

        return "";
    }

    private void initalizeEmployee() {
        main_center_employee.add(employees, "Center");

        employeesAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String num = JOptionPane.showInputDialog(instance, "번호를 입력하세요");
                    if (num != null) {
                        String name = JOptionPane.showInputDialog(instance, "이름을 입력하세요");
                        if (name != null) {
                            String money = JOptionPane.showInputDialog(instance, "급여를 입력하세요");
                            if (money != null) {
                                String level = JOptionPane.showInputDialog(instance, "직급을 입력하세요");
                                if (level != null) {
                                    String tel = JOptionPane.showInputDialog(instance, "연락처를 입력하세요");
                                    if (tel != null) {

                                        Employee employee = new Employee();
                                        employee.setElementAt(0, num);
                                        employee.setElementAt(1, name);
                                        employee.setElementAt(2, money);
                                        employee.setElementAt(3, level);
                                        employee.setElementAt(4, getDate());
                                        employee.setElementAt(5, tel);

                                        employeesModel.add(employee);

                                        saveEmployees();
                                    }
                                }
                            }
                        }

                    }
                } catch (Exception ex) {
                    //ex.printStackTrace();
                }

            }
        });

        employeesRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    employees.removeCurrentClicked();
                } catch (IndexOutOfBoundsException ex) { /*ignored*/ }
            }
        });

        employeesEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int row = employees.getSelectedRow();
                    Employee employee = (Employee) employees.getSelectedItem();

                    String num = JOptionPane.showInputDialog(instance, "번호를 입력하세요", employee.getElementAt(0));
                    if (num != null) {
                        String name = JOptionPane.showInputDialog(instance, "이름을 입력하세요", employee.getElementAt(1));
                        if (name != null) {
                            String money = JOptionPane.showInputDialog(instance, "급여를 입력하세요", employee.getElementAt(2));
                            if (money != null) {
                                String level = JOptionPane.showInputDialog(instance, "직급을 입력하세요", employee.getElementAt(3));
                                if (level != null) {
                                    String tel = JOptionPane.showInputDialog(instance, "연락처를 입력하세요", employee.getElementAt(5));
                                    if (tel != null) {
                                        employeesModel.setValueAt(num, row, 0);
                                        employeesModel.setValueAt(name, row, 1);
                                        employeesModel.setValueAt(money, row, 2);
                                        employeesModel.setValueAt(level, row, 3);
                                        //employeesModel.setValueAt(c.get(Calendar.YEAR) + "년 " + (c.get(Calendar.MONTH)+1) + "월 " + c.get(Calendar.DATE) + "일", row, 4);
                                        employeesModel.setValueAt(tel, row, 5);

                                        saveEmployees();
                                    }
                                }
                            }
                        }
                    }

                } catch (Exception ex) {
                    //ex.printStackTrace();
                }
            }
        });

        JPanel main_center_employee_south = new JPanel();
        main_center_employee_south.setLayout(new GridBagLayout());

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            //gbc.insets = new Insets(10, 10, 10,0);
            main_center_employee_south.add(new JPanel(), gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 10, 10, 0);
            main_center_employee_south.add(employeesEdit, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 10, 10, 0);
            main_center_employee_south.add(employeesAdd, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 3;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 10, 10, 10);
            main_center_employee_south.add(employeesRemove, gbc);
        }
        main_center_employee.add(main_center_employee_south, "South");
    }


    private void initalizeMainNorthTop() {
        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 10, 0, 0);
            main_north_top.add(dayView, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 10, 0, 0);
            main_north_top.add(end, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.CENTER;
            main_north_top.add(new JPanel(), gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 3;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 0, 0, 10);
            main_north_top.add(dayMoney, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 4;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 0, 0, 10);
            main_north_top.add(money, gbc);
        }
    }

    private void initalizeMainNorthBottom() {
        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(30, 0, 0, 0);
            main_north_bottom.add(select_tableButton, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(30, 0, 0, 0);
            main_north_bottom.add(select_storageButton, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(30, 0, 0, 0);
            main_north_bottom.add(select_customerButton, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 3;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(30, 0, 0, 0);
            main_north_bottom.add(select_menuButton, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 4;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(30, 0, 0, 0);
            main_north_bottom.add(select_employeeButton, gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 5;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.CENTER;
            main_north_bottom.add(new JPanel(), gbc);
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 6;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(0, 0, 0, 10);
            main_north_bottom.add(shutdown, gbc);
        }
    }


    private void closing() {
        saveAllResources();
        System.exit(0);
    }

    public void loadAllResources() {
        loadCustomers();
        loadEmployees();
        loadMenus();
        loadStorages();
        loadTables();
        loadPrice();
    }

    public void loadCustomers() {
        try {
            File directory = new File("saves/customer");
            directory.mkdirs();

            File file = new File("saves/customer/customer.dat");

            if (file.exists()) {
                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file)); // 파일 입력 스트림 열기

                int writed = stream.readInt(); // 몇개 입력받아야 하는지 구함
                for (int i = 0; i < writed; i++) { // 오브젝트 읽어냄
                    customer_model.add((TableModelElement) stream.readObject());
                }
                stream.close(); // 스트림 닫기
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadMenus() {
        try {
            File directory = new File("saves/menu");
            directory.mkdirs();

            File file = new File("saves/menu/menu.dat");

            if (file.exists()) {
                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));

                int writed = stream.readInt();
                for (int i = 0; i < writed; i++) {
                    menu_model.add((TableModelElement) stream.readObject());
                }
                stream.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadStorages() {
        try {
            File directory = new File("saves/food");
            directory.mkdirs();

            File file = new File("saves/food/food.dat");

            if (file.exists()) {
                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));

                int writed = stream.readInt();
                for (int i = 0; i < writed; i++) {
                    storageModel.add((TableModelElement) stream.readObject());
                }
                stream.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadTables() {
        try {
            File directory = new File("saves/table");
            directory.mkdirs();

            File file = new File("saves/table/table.dat");

            if (file.exists()) {

                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));

                int writed = stream.readInt();
                tables.clear();
                for (int i = 0; i < writed; i++) {
                    Table table = (Table) stream.readObject();
                    tables.add(table);
                    table.setTotalPrice(table.getTotalPrice());
                    table.setTableIndex(i);
                }
                stream.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadEmployees() {
        try {
            File directory = new File("saves/employee");
            directory.mkdirs();

            File file = new File("saves/employee/employee.dat");

            if (file.exists()) {
                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));

                int writed = stream.readInt();
                for (int i = 0; i < writed; i++) {
                    employeesModel.add((TableModelElement) stream.readObject());
                }
                stream.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadPrice() {
        try {
            File directory = new File("saves/price");
            directory.mkdirs();

            File file = new File("saves/price/price.dat");

            if (file.exists()) {
                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
                totalPrice = stream.readInt();
                money.setText("현재 잔고: " + totalPrice + "원");
                if (stream.available() > 0)
                    bonusInitalize = stream.readInt();
                if (stream.available() > 0)
                    endDay = stream.readLong();
                if (stream.available() > 0)
                    dayPrice = stream.readInt();
                dayMoney.setText("오늘 매출: " + dayPrice + "원");

                stream.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveAllResources() {
        saveCustomers();
        saveEmployees();
        saveStorages();
        saveMenus();
        saveTables();
        savePrice();
    }

    public void saveCustomers() {
        try {
            List<TableModelElement> elements = customer_model.getContents();
            File directory = new File("saves/customer");
            directory.mkdirs();

            File file = new File("saves/customer/customer.dat");
            if (!file.exists()) {
                file.createNewFile();
            }

            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));

            stream.writeInt(elements.size());

            for (TableModelElement element : elements) {
                stream.writeObject(element);
            }
            stream.flush();
            stream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveStorages() { // storage
        try {
            List<TableModelElement> elements = storageModel.getContents();
            File directory = new File("saves/food");
            directory.mkdirs();

            File file = new File("saves/food/food.dat");
            if (!file.exists()) {
                file.createNewFile();
            }

            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));

            stream.writeInt(elements.size());

            for (TableModelElement element : elements) {
                stream.writeObject(element);
            }
            stream.flush();
            stream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveMenus() {
        try {
            List<TableModelElement> elements = menu_model.getContents();
            File directory = new File("saves/menu");
            directory.mkdirs();

            File file = new File("saves/menu/menu.dat");
            if (!file.exists()) {
                file.createNewFile();
            }

            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));

            stream.writeInt(elements.size());

            for (TableModelElement element : elements) {
                stream.writeObject(element);
            }
            stream.flush();
            stream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveTables() {
        try {


            //List<TableModelElement> elements = customer_model.getContents();
            File directory = new File("saves/table");
            directory.mkdirs();

            File file = new File("saves/table/table.dat");
            if (!file.exists()) {
                file.createNewFile();
            }

            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));

            stream.writeInt(tables.size());

            System.out.println(tables.size());

            for (Table table : tables) {
                stream.writeObject(table);
            }
            stream.flush();
            stream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveEmployees() {
        try {
            List<TableModelElement> elements = employeesModel.getContents();
            File directory = new File("saves/employee");
            directory.mkdirs();

            File file = new File("saves/employee/employee.dat");
            if (!file.exists()) {
                file.createNewFile();
            }

            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));

            stream.writeInt(elements.size());

            for (TableModelElement element : elements) {
                stream.writeObject(element);
                //System.out.println("wrtited");
            }
            stream.flush();
            stream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void savePrice() {
        try {
            //List<TableModelElement> elements = customer_model.getContents();
            File directory = new File("saves/price");
            directory.mkdirs();

            File file = new File("saves/price/price.dat");
            if (!file.exists()) {
                file.createNewFile();
            }

            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
            stream.writeInt(totalPrice);
            stream.writeInt(bonusInitalize);
            stream.writeLong(endDay);
            stream.writeInt(dayPrice);
            stream.flush();
            stream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
