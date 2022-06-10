

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class GoldenList<E> extends JPanel {
    private JList<E> list;
    private DefaultListModel<E> model;
    //private TableBorder border;

    public GoldenList() {
        setLayout(new BorderLayout());

        //border = new TableBorder(listName, font, color, color2);
        model = new DefaultListModel<>();
        list = new JList<>();

        list.setModel(model);
        //setBorder(new CompoundBorder(border, new EmptyBorder(margin, margin, margin, margin)));
        //setBorder(border);

        //JScrollPane tableScrollPane = new JScrollPane(list, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //setBorder(border);
        //setPreferredSize(new Dimension(300, 600));

        //list.setBackground(new Color(0,0,0,0));
        //setBackground(new Color(0,0,0,0));
        //JScrollPane pane = new JScrollPane(list);
        //pane.setForeground(new Color(255, 0,0, 255));
        //setForeground(new Color(255, 0,0, 255));
        //list.setForeground(new Color(255, 0,0, 255));
        //pane.setBackground(new Color(255,0,0,255));
        //list.setBackground(new Color(255, 0, 0, 255));
        //setBackground(new Color(255,0,0,255));
        add(new JScrollPane(list));

        list.setVisible(true);
        setVisible(true);
    }

    public void addElement(E e) {
        model.addElement(e);
    }

    public void addElementReverse(E e) {
        model.add(0, e);
    }

    public void removeElement(int index) {
        model.remove(index);
    }

    public void removeElementAll() {
        model.removeAllElements();
    }

    public JList<E> getList() {
        return list;
    }

    public void setModel(DefaultListModel<E> model) {
        this.model = model;
    }
}
