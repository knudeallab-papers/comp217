
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class Table implements Serializable{
    static final long serialVersionUID = 59033788724L;

    private transient static Color mainColor = new Color(128, 128, 200);
    private transient static Border mainLineBorder = BorderFactory.createLineBorder(mainColor, 2);

    JPanel panel = new JPanel();
    JPanel fillPanel = new JPanel();

    transient JTextField table; // transient = ObjectStream쓸때 저장하지 않을 변수들
    transient JTextField totalPrice;

    int price;

    List<Menu> MenuLists = new ArrayList<Menu>();
    transient TableClickEvent event;

    int index;

    public void addMenu(Menu menu, int time) {
        if(MenuLists.size()==0) {
            table.setBackground(Color.YELLOW);
            totalPrice.setBackground(Color.YELLOW);
            panel.setBackground(Color.YELLOW);
            fillPanel.setBackground(Color.YELLOW);
        }
        for(int i = 0; i < time; i++) {
            MenuLists.add(menu);
        }

        /* update total price */
        int price = 0;
        for(int i = 0; i < MenuLists.size(); i++) {
            Menu curMenu = MenuLists.get(i);
            int MenuPrice = Integer.parseInt(curMenu.getPrice());
            price += MenuPrice;
        }
        setTotalPrice(price);
    }

    public void credit() { // 결제 했을때 초기 상태로 돌아가야 하므로
        MenuLists.clear();
        setTotalPrice(0);
        //System.out.println(index + ", " + MenuLists);

        table.setBackground(Color.GREEN);
        totalPrice.setBackground(Color.GREEN);
        panel.setBackground(Color.GREEN);
        fillPanel.setBackground(Color.GREEN);
    }

    public void addTableClickEvent(TableClickEvent ev) {
        event = ev;
    }

    public Table(int index) {
        this.index = index;
        setTableIndex(index);

        totalPrice = new JTextField();

        panel.setBorder(mainLineBorder);
        totalPrice.setBorder(null);
        table.setBorder(null);
        //panel.setBorder(mainLineBorder);

        //totalPrice = new JTextField();
        setTotalPrice(0);
        totalPrice.setEditable(false);

        panel.setLayout(new GridBagLayout());


        table.setBackground(Color.GREEN);
        totalPrice.setBackground(Color.GREEN);
        panel.setBackground(Color.GREEN);
        fillPanel.setBackground(Color.GREEN);

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0.1;
            gbc.weighty = 0.1;
            gbc.fill = GridBagConstraints.BOTH;
            panel.add(table, gbc);
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(event!=null)
                        event.onTableClickEvent(index-1);
                }
            });
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.weightx = 0.1;
            gbc.weighty = 0.1;
            gbc.fill = GridBagConstraints.BOTH;
            panel.add(totalPrice, gbc);
            totalPrice.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(event!=null)
                        event.onTableClickEvent(index-1);
                    //System.out.println("A");
                }
            });
        }

        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.weightx = 0.1;
            gbc.weighty = 0.1;
            gbc.fill = GridBagConstraints.BOTH;

            //JPanel panel = new JPanel();
            fillPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(event!=null)
                        event.onTableClickEvent(index-1);
                    //System.out.println("A");
                }
            });
            this.panel.add(fillPanel, gbc);
        }

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(event!=null)
                    event.onTableClickEvent(index-1);
                //System.out.println("A");
            }
        });

        table.setHorizontalAlignment(JTextField.CENTER);
        totalPrice.setHorizontalAlignment(JTextField.CENTER);

        table.setVisible(true);
        totalPrice.setVisible(true);
        panel.setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }

    public int getTotalPrice() {
        return price;
    }

    public void setTotalPrice(int price) {
        this.price = price;
        if(totalPrice==null) {
            totalPrice = new JTextField();
        }
        totalPrice.setText("총액: " + price);
    }

    public List<Menu> getMenuLists() {
        return MenuLists;
    }

    public void setTableIndex(int tableIndex) {
        if(table==null) {
            table = new JTextField("테이블 " + tableIndex);
        }
        table.setEditable(false);
    }
}
