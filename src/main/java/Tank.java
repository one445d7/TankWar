import object.Direction;
import object.GameObject;

import java.awt.*;

public class Tank extends GameObject {

    private int speed;
    private Direction direction;
    private boolean[] dirs = new boolean[4];
    protected boolean enemy;

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean[] getDirs() {
        return dirs;
    }

    public Tank(int x, int y, Direction direction, Image[] image) {
        this(x, y, direction, false, image);
    }

    public Tank(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, image);
        this.direction = direction;
        speed = 5;
        this.enemy = enemy;
    }

    public void move() {
        oldX = x;
        oldY = y;
        switch (direction) {
            case UP:
                y -= speed;
                break;

            case DOWN:
                y += speed;
                break;

            case LEFT:
                x -= speed;
                break;

            case RIGHT:
                x += speed;
                break;
            case UP_LEFT:
                y -= speed;
                x -= speed;
                break;
            case UP_RIGHT:
                y -= speed;
                x += speed;
                break;
            case DOWN_LEFT:
                y += speed;
                x -= speed;
                break;
            case DOWN_RIGHT:
                y += speed;
                x += speed;
                break;
        }
        collision();
    }

    public  boolean collisionBound(){
        boolean collision =false;
        if (x < 0) {
            x = 0;
            collision = true;
        } else if (x > TankGame.getGameClient().getScreenWidth() - width) {
            x = TankGame.getGameClient().getScreenWidth() - width;
            collision = true;
        }

        if (y < 0) {
            y = 0;
            collision = true;
        } else if (y > TankGame.getGameClient().getScreenHeight() - width) {
            y = TankGame.getGameClient().getScreenHeight() - width;
            collision = true;
        }
        return collision;
    }

    public void collision() {
        if(collisionBound()){
            alive = false;
            return;
        }

        for (GameObject object :TankGame.getGameClient().getGameObjects()){

            if(object == this){
                continue;
            }

            if(object instanceof Tank){
                if(((Tank)object).enemy == enemy){
                    continue;
                }
            }

            if(object != this && getRectangle().intersects(object.getRectangle())){
                alive = false;
                return;
            }
        }


    }

    public void determineDirection() {
        if (dirs[0] && !dirs[1] && dirs[2] && !dirs[3]) {
            direction = Direction.UP_LEFT;
        } else if (dirs[0] && !dirs[1] && !dirs[2] && dirs[3]) {
            direction = Direction.UP_RIGHT;
        } else if (!dirs[0] && dirs[1] && dirs[2] && !dirs[3]) {
            direction = Direction.DOWN_LEFT;
        } else if (!dirs[0] && dirs[1] && !dirs[2] && dirs[3]) {
            direction = Direction.DOWN_RIGHT;
        } else if (dirs[0] && !dirs[1] && !dirs[2] && !dirs[3]) {
            direction = Direction.UP;
        } else if (!dirs[0] && dirs[1] && !dirs[2] && !dirs[3]) {
            direction = Direction.DOWN;
        } else if (!dirs[0] && !dirs[1] && dirs[2] && !dirs[3]) {
            direction = Direction.LEFT;
        } else if (!dirs[0] && !dirs[1] && !dirs[2] && dirs[3]) {
            direction = Direction.RIGHT;
        }
    }

    public boolean isStop() {
        for (int i = 0; i < dirs.length; i++) {
            if (dirs[i]) {
                return false;
            }
        }
        return true;
    }

    public void draw(Graphics g) {
        if (!isStop()) {
            determineDirection();
            move();
        }
        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    public void fire(){
        TankGame.getGameClient().addGameObject(new Bullet(x+width/2 - GameClient.bulletImage[0].getWidth(null)/2,
                y+height/2 - GameClient.bulletImage[0].getHeight(null)/2,direction,enemy,GameClient.bulletImage));
    }
}

