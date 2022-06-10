
import java.io.Serializable;


public class Customer implements TableModelElement, Serializable {
    static final long serialVersionUID = 59033788721L;
    String number;
    String level;
    String name;
    String bonus;
    String tel;

    public Customer(String number, String level, String name, String bonus, String tel) {
        this.number = number;
        this.level = level;
        this.name = name;
        this.bonus = bonus;
        this.tel = tel;
    }


    @Override
    public String getElementAt(int column) {
        switch(column) {
            case 0:
                return number;
            case 1:
                return level;
            case 2:
                return name;
            case 3:
                return bonus;
            case 4:
                return tel;
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
                level = data;
                break;
            case 2:
                name = data;
                break;
            case 3:
                bonus = data;
                break;
            case 4:
                tel = data;
                break;
        }
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bouns) {
        this.bonus = bouns;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
