
public class MenuInTableInfo implements TableModelElementInfo {
    @Override
    public String getElementName(int column) {
        return column == 0 ? "메뉴" : "가격";
    }

    @Override
    public int getElementSize() {
        return 2;
    }

    @Override
    public boolean canEditable(int column) {
        return false;
    }
}
