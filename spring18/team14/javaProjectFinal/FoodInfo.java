
public class FoodInfo implements TableModelElementInfo {
    @Override
    public String getElementName(int column) {
        switch (column) {
            case 0:
                return "이름";
            case 1:
                return "재고";
            case 2:
                return "주문";
            case 3:
                return "가격";
        }

        return null;
    }

    @Override
    public int getElementSize() {
        return 4;
    }

    @Override
    public boolean canEditable(int column) {
        return false;
    }
}
