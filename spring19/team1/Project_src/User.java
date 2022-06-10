import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    public String name;
    private String Password;
    public String FamilyName;
    public int Age;
    public String Description;
    public ImageIcon Picture;
    public ArrayList<User> list;
    public  ArrayList<User> friends;
    private int ID;


    public User(String name, String familyName, int age, String description,
                ImageIcon picture, int ID) {
        this.name = name;
        this.FamilyName = familyName;
        this.Age = age;
        this.Description = description;
        this.Picture = picture;
        Password = "p"; // Use this when logging on as user
        this.ID = ID;
        list = new ArrayList<>();
        friends = new ArrayList<>();
    }

    public  boolean checkConnection(String ID,String Password){
        if (this.name.equals(ID) && this.Password.equals(Password)){
            return true;
        }
        return false;
    }

    public boolean equals(User obj) {
        return this.ID == obj.ID;
    }

    //Checks if friend object has been "liked" by the calling object
    public boolean isfriend(User friend){
        for (User user: friend.list
        ) {
            if (this.equals(user)){
                friends.add(friend);
                friend.friends.add(this);
                return true;
            }
        }
        return false;
    }
}
