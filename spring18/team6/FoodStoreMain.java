package FoodStore;

import java.io.*;

public class FoodStoreMain implements Serializable{
    public static void main(String[] args) throws IOException {
        FoodStore.FirstSettingPanel firstSettingPanel=null;
        try {
            ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("FoodStoreMain.dat"));
            firstSettingPanel=(FoodStore.FirstSettingPanel)objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (FileNotFoundException e){
            firstSettingPanel=new FirstSettingPanel();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        firstSettingPanel.setVisible(true);
    }
}
