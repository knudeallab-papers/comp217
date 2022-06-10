package GH;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import oneiros.games.AnimationListener;
import oneiros.games.NoHoldingKeyListener;
import oneiros.games.OSprite;
import oneiros.physic.OVector2D;
import oneiros.sound.SoundManager;
import util.Resource;

import static GH.Character_status.stress;
import static GH.Character_status.hp;
import static GH.Character_status.happy;

import static GH.Main_HOBAN.happy_;

public class Gym_jumpgame_gamepanel extends OSprite{

    static {
        SoundManager.add("jump", Resource.getSoundFile("jump.wav"));
        SoundManager.add("fall", Resource.getSoundFile("pada.wav"));
    }
    private static final OVector2D GRAVITY_VECTOR = new OVector2D(0.2, 270);
    private static final double AIR_FRICTION = 0.02;
    private static final int STAGE_SCROLL_LIMIT = 300;
    private Gym_jumpgame_banwoo banwoo;
    private ArrayList<Gym_jumpgame_platform> platforms;
    private int score;
    private NewGameListener gameListener;
    private MovingBanwooKeyListener movingBanwooKeyListener = new MovingBanwooKeyListener();

    public Gym_jumpgame_gamepanel(Dimension size) {
        super(Resource.getImage("bg.png"));
        this.setSize(size);

        this.addKeyListener(new BanwooGameKeyListener());
        this.addKeyListener(this.movingBanwooKeyListener);

        this.newGame();
    }

    public void start() {
        this.banwoo.startAnimation();
        this.banwoo.jump();
    }

    private void newGame() {
        this.banwoo = new Gym_jumpgame_banwoo();
        this.banwoo.addAnimationListener(new BanwooAnimationListener());
        this.add(this.banwoo);
        this.banwoo.setLocation((this.getWidth() - this.banwoo.getWidth()) / 2,
                this.getHeight() - this.banwoo.getHeight());
        this.banwoo.setAcceleration(GRAVITY_VECTOR);
        this.platforms = new ArrayList<Gym_jumpgame_platform>();

        for (int i = 0; i < 10; i++) {
            Gym_jumpgame_platform p = new Gym_jumpgame_platform();
            p.setLocation((int) (Math.random() * (getWidth() - p.getWidth())),
                    i * ((int) (Math.random() * 50) + 50));
            this.add(p);
            this.platforms.add(p);
        }
        this.score = 0;
    }

    public void gameOver() {
        this.banwoo.stopAnimation();
        this.banwoo.stopAnyMotion();
        this.movingBanwooKeyListener.left = false;
        this.movingBanwooKeyListener.right = false;
        this.movingBanwooKeyListener.flush();
        JOptionPane.showMessageDialog(null, "점수 : " + score,
                "Game Over", JOptionPane.WARNING_MESSAGE);
        this.removeAll();
        
        hp += score/1000;
        stress -= score/1000;
        happy += score/1000;
        
        happy_ += score/1000;
    
        if (this.gameListener != null) {
            this.gameListener.gameOver();
        }
    }

    private void moveStageUp() {
        if (this.banwoo.getY() < STAGE_SCROLL_LIMIT) {
            int offset = STAGE_SCROLL_LIMIT - this.banwoo.getY();
            this.banwoo.setY(STAGE_SCROLL_LIMIT);
            for (Gym_jumpgame_platform p : platforms) {
                p.setLocation(p.getX(), p.getY() + offset);
                if (p.getY() > this.getHeight()) {
                    p.setLocation((int) (Math.random() * (getWidth() - p.getWidth())),
                            ((int) (Math.random() * 50) - 50));
                }
            }
            this.score += offset / 2;
            if (this.gameListener != null) {
                this.gameListener.updateScore(this.score);
            }
        }
    }

    public void setNewGameListener(NewGameListener scoreUpdater) {
        this.gameListener = scoreUpdater;
    }

    private class BanwooAnimationListener extends AnimationListener {

        @Override
        public void postAction() {
            if (banwoo.getY() > getHeight()) {
                SoundManager.play("fall");
                gameOver();
            } else {

                double x = banwoo.getVelocity().getX();
                if (Math.abs(x) < 0.1) {
                    banwoo.setVelocityX(0);
                } else {
                    double friction = Math.abs(x) * AIR_FRICTION;
                    if (x > 0) {
                        banwoo.setVelocityX(x - friction);
                    } else if (x < 0) {
                        banwoo.setVelocityX(x + friction);
                    }
                }

                if (banwoo.getVelocity().x > Gym_jumpgame_banwoo.MOVING_VECTOR.x) {
                    banwoo.turnRight();
                } else if (banwoo.getVelocity().x < -Gym_jumpgame_banwoo.MOVING_VECTOR.x) {
                    banwoo.turnLeft();
                }

                int halfDoodle = banwoo.getWidth() / 2;
                if (banwoo.getX() < -halfDoodle) {
                    banwoo.setX(getWidth() - halfDoodle);
                } else if (banwoo.getX() > getWidth() - halfDoodle) {
                    banwoo.setX(-halfDoodle);
                }

                for (Gym_jumpgame_platform p : platforms) {
                    if (banwoo.getVelocity().y < 0 && banwoo.getFeet().intersects(p.getBase())) {
                        banwoo.setY((int) p.getBounds().getY() - banwoo.getHeight());
                        banwoo.jump();
                    }
                }

                moveStageUp();
            }
        }
    }

    private class MovingBanwooKeyListener extends NoHoldingKeyListener {

        private boolean right = false;
        private boolean left = false;

        @Override
        public void keyPressed2(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                banwoo.startMovingLeft();
                left = true;
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                banwoo.startMovingRight();
                right = true;
            }
        }

        @Override
        public void keyReleased2(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT && left) {
                banwoo.stopMovingLeft();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && right) {
                banwoo.stopMovingRight();
            }
        }
    }

    private class BanwooGameKeyListener extends KeyAdapter {

        private int pauseCount = 0;

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_P) {
                pauseCount++;
                if (pauseCount % 2 == 0) {
                    banwoo.startAnimation();
                } else {
                    banwoo.stopAnimation();
                }
            } else if (e.getKeyCode() == KeyEvent.VK_R) {
                gameOver();
            }
        }
    }

}

interface NewGameListener {

    public void updateScore(int score);

    public void gameOver();
}
