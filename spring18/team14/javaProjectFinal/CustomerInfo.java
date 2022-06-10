


public class CustomerInfo implements TableModelElementInfo {
    @Override
    public String getElementName(int column) {
        switch(column) {
            case 0:
                return "번호";
            case 1:
                return "등급";
            case 2:
                return "이름";
            case 3:
                return "마일리지";
            case 4:
                return "연락처";

        }

        return null;
    }

    @Override
    public int getElementSize() {
        return 5;
    }

    @Override
    public boolean canEditable(int column) {
        return false;
    }
}
