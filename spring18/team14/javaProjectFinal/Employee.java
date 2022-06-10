
import java.io.Serializable;

public class Employee implements TableModelElement, Serializable {
    static final long serialVersionUID = 59033788722L;
    String number = "";
    String name = "";
    String money = "";
    String level = "";
    String join = "";
    String telNumber = "";

    @Override
    public String getElementAt(int column) {
        switch(column) {
            case 0:
                return number;
            case 1:
                return name;
            case 2:
                return money;
            case 3:
                return level;
            case 4:
                return join;
            case 5:
                return telNumber;
        }

        return null;
    }

    @Override
    public void setElementAt(int column, String data) {
        switch(column) {
            case 0:
                number = data;
                break;
            case 1:
                name = data;
                break;
            case 2:
                money = data;
                break;
            case 3:
                level = data;
                break;
            case 4:
                join = data;
                break;
            case 5:
                telNumber = data;
                break;

        }
    }


}
