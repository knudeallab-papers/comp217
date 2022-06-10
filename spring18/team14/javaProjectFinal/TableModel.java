
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class TableModel extends AbstractTableModel {

    private List<TableModelElement> lstRecords;
    private int len = 0;

    TableModelElementInfo info;

    ValueSetEvent ev;

    public TableModel(TableModelElementInfo info) { // TableModelElementInfo에는 각 테이블 열에대한 이름 등이 저장되어 있음
        this.info = info;
        lstRecords = new ArrayList<>();
    }

    public void setEvent(ValueSetEvent ev) {
        this.ev = ev;
    }

    public List<TableModelElement> getContents() {
        return lstRecords;
    }

    public void add(TableModelElement record) {
        lstRecords.add(record);
        //if(autoUpdate)
        fireTableRowsInserted(lstRecords.size() - 1, lstRecords.size() - 1);
    }

    public void remove(TableModelElement record) {
        if (lstRecords.contains(record)) {
            int index = lstRecords.indexOf(record);
            remove(index);
        }
    }

    public void removeLast() {
        remove(lstRecords.remove(lstRecords.size()-1));
    }

    public void remove(int index) {
        lstRecords.remove(index);
            fireTableRowsDeleted(index, index);
    }

    public void removeAll() {
        len = 0;
        for(int i = lstRecords.size()-1; i>=0; i--) {
            remove(i);
        }
    }

    @Override
    public int getRowCount() {
        return lstRecords.size();
    }

    @Override
    public int getColumnCount() {
        return info.getElementSize();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class clazz = String.class;
        return clazz;
    }

    @Override
    public String getColumnName(int column) {
        return info.getElementName(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TableModelElement record = lstRecords.get(rowIndex);
        return record.getElementAt(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //System.out.println(rowIndex + ", " + columnIndex);
        return info.canEditable(columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        TableModelElement record = lstRecords.get(rowIndex);
        record.setElementAt(columnIndex, aValue==null ? null : aValue.toString());

        fireTableCellUpdated(rowIndex, columnIndex);
        if(ev!=null) {
            ev.onValueSet();
        }
        //System.out.println("SET");
    }

    public void updateRow(int row) {
        for(int i = 0; i < info.getElementSize(); i++) {
            fireTableCellUpdated(row, i);
        }
    }

    public TableModelElement getElement(int selectedRow) {
        return lstRecords.get(selectedRow);
    }

    public TableModelElement getElement(String index, int column) { // 해당 열에서 index와 일치하는 값이 있는 행의 정보 리턴
        for(int i = 0; i < lstRecords.size(); i++) {
            TableModelElement element = lstRecords.get(i);

            if (element.getElementAt(column).equals(index)) {
                return element;
            }
        }

        return null;
    }

    public int getSize() {
        return lstRecords.size();
    }
}
