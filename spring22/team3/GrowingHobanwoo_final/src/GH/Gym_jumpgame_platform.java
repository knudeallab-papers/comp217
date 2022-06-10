package GH;

import java.awt.Rectangle;
import oneiros.games.OSprite;
import util.Resource;

public class Gym_jumpgame_platform extends OSprite{

    public Gym_jumpgame_platform() {
        super(Resource.getImage("platform.png"));
    }

    public Rectangle getBase(){
        return new Rectangle(getX(), getY(), getWidth(), 2);
    }
}
