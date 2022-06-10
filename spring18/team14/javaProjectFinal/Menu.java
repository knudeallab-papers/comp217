
import java.io.Serializable;


public class Menu implements TableModelElement, Serializable {
    static final long serialVersionUID = 59033788720L;
    String name;
    String price;
    String unitprice;
    String materials;

    public Menu(String name, String price, String unitprice, String materials) {
        this.name = name;
        this.price = price;
        this.unitprice = unitprice;
        this.materials = materials;
    }

    @Override
    public String getElementAt(int column) {
        switch(column) {
            case 0:
                return name;
            case 1:
                return price;
            case 2:
                return unitprice;
            case 3:
                return materials;
        }

        return null;
    }

    @Override
    public void setElementAt(int column, String data) {
        switch(column) {
            case 0:
                name = data;
                break;
            case 1:
                price = data;
                break;
            case 2:
                unitprice = data;
                break;
            case 3:
                materials = data;
                break;
        }
    }

    @Override
    public String toString() {
        return name + ", " + price + ", " + unitprice + ", " + materials;
    }

    public String getPrice() {
        return price;
    }
}
