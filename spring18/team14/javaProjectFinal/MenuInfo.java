
public class MenuInfo implements TableModelElementInfo{

    @Override
    public String getElementName(int column) {
        switch(column) {
            case 0:
                return "음식명";
        }
        return null;
    }

    @Override
    public int getElementSize() {
        return 1;
    }

    @Override
    public boolean canEditable(int column) {
        return false;
    }
}
