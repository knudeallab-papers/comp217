

public class EmployeeInfo implements TableModelElementInfo {
    @Override
    public String getElementName(int column) {
        switch(column) {
            case 0:
                return "번호";
            case 1:
                return "이름";
            case 2:
                return "급여";
            case 3:
                return "직급";
            case 4:
                return "입사일";
            case 5:
                return "연락처";
        }
        return null;
    }

    @Override
    public int getElementSize() {
        return 6;
    }

    @Override
    public boolean canEditable(int column) {
        //System.out.println(column);
        return column!=4;
    }
}
