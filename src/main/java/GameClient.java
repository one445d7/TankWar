import object.Direction;
import object.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHeight;
    private boolean stop;

    private Tank playerTank;


    GameClient() {
        this(800, 600);
    }

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        playerTank = new Tank(400, 100, Direction.UP);

        new Thread(new Runnable() {
            public void run() {
                while (!stop){
                repaint();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getScreenWidth(),getScreenHeight());
        playerTank.draw(g);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void keyPressed(KeyEvent e) {
        boolean[] dirs =playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0]= true;
                break;

            case KeyEvent.VK_DOWN:
                dirs[1]= true;
                break;

            case KeyEvent.VK_LEFT:
                dirs[2]= true;
                break;

            case KeyEvent.VK_RIGHT:
                dirs[3]= true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        boolean[] dirs =playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0]= false;
                break;

            case KeyEvent.VK_DOWN:
                dirs[1]= false;
                break;

            case KeyEvent.VK_LEFT:
                dirs[2]= false;
                break;

            case KeyEvent.VK_RIGHT:
                dirs[3]= false;
                break;
        }
    }
}




