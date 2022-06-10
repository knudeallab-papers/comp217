package chatServer;

import java.util.ArrayList;
import java.util.List;

//Just used to give the clients a unique ID
public class ClientIdentifier {
    private static List<Integer> ids = new ArrayList<Integer>();
    private static final int RANGE = 10000;

    private static int index = 0;

    static {
        for (int i = 1; i < RANGE; i++) {
            ids.add(i);
        }
    }

    public static int getIdentifier() {
        if (index > ids.size() - 1) index = 0;
        return ids.get(index++);
    }
}
