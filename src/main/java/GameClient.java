import object.Direction;
import object.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class  GameClient extends JComponent {
    private int screenWidth;
    private int screenHeight;
    private boolean stop;

    private Tank playerTank;
    private List<GameObject> gameObjects = new ArrayList<GameObject>();
    private List<Tank> enemyTank= new ArrayList<Tank>();
    private List<Wall> walls= new ArrayList<Wall>();

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public List<Tank> getEnemyTank() {
        return enemyTank;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    GameClient() {
        this(800, 600);
    }

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        init();
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

    public void init(){
        Image brickImage = Tools.getImage("brick.png");
        Image[] iTankImage = new Image[8];
        Image[] eTankImage = new Image[8];

        String[] sub = {"U.png","D.png","L.png","R.png","LU.png","RU.png","LD.png","RD.png"};

        for(int i = 0;i<iTankImage.length;i++){
            iTankImage[i] = Tools.getImage("itank"+sub[i]);
            eTankImage[i] = Tools.getImage("etank"+sub[i]);
        }

        playerTank = new Tank(400, 70, Direction.DOWN,iTankImage);

        for(int i=0;i<3;i++){
            for(int j = 0;j<4;j++){
                enemyTank.add(new Tank(300+j*70,320+i*70,Direction.UP,true,eTankImage));
            }
        }



        walls.add(new Wall(230,170,true,12,new Image[]{brickImage}));
        walls.add(new Wall(650,220,false,10,new Image[]{brickImage}));
        walls.add(new Wall(150,220,false,10,new Image[]{brickImage}));

        gameObjects.add(playerTank);
        gameObjects.addAll(enemyTank);
        gameObjects.addAll(walls);

    }


    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,screenWidth,screenHeight);

        for(GameObject gameObject:gameObjects){
            gameObject.draw(g);
        }

//        playerTank.draw(g);
//        for(Tank tank:enemyTank){
//            tank.draw(g);
//        }
//        for(Wall wall:walls){
//            wall.draw(g);
//        }
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




