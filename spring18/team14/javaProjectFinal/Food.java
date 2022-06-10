
import java.io.Serializable;


public class Food implements TableModelElement, Serializable {
    static final long serialVersionUID = 59033788723L;
    String name;
    String price;
    String amount;
    String order;
    String selled;
    String tel;

    public Food(String name, String amount, String order, String price, String selled, String tel) {
        this.name = name;
        this.amount = amount;
        this.order = order;
        this.price = price;
        this.selled = selled;
        this.tel = tel;
    }

    public String getAmount() {
        return amount;
    }

    public String getSelled() {
        return selled;
    }

    public String getTel() {
        return tel;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getElementAt(int column) {
        switch(column) {
            case 0:
                return name; // 1
            case 1:
                return amount;
            case 2:
                return order;
            case 3:
                return price; // 2  0, 3
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
                amount = data;
                break;
            case 2:
                order = data;
                break;
            case 3:
                price = data;
                break;
        }
    }

    public String getPrice() {
        return price;
    }
}
