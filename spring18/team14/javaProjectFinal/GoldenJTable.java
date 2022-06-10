
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class GoldenJTable extends JPanel {
    JTable table = new JTable();

    int selectedRow;
    //boolean clicked;

    TableModel model;

    GoldenJTableClickEvent ev;

    public GoldenJTable(TableModel model) {
        setLayout(new BorderLayout());
        this.model = model;
        table.setModel(model);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                selectedRow = table.getSelectedRow();
                //clicked = true;
                //System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
                if(ev!=null) {
                    ev.TableClickEvent();
                }
            }
        });


        add(new JScrollPane(table));
    }

    public void removeCurrentClicked() {
        try {
            //if (clicked)
                model.remove(selectedRow);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addTableClickEvent(GoldenJTableClickEvent event) {
        ev = event;
    }

    public JTable getTable() {
        return table;
    }

    public TableModelElement getSelectedItem() {
        return model.getElement(selectedRow);
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public int getSelectedColumn() {
        return table.getSelectedColumn();
    }
}
