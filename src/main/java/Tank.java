import object.Direction;
import object.GameObject;

import javax.swing.*;
import java.awt.*;

public class Tank extends GameObject {

    private int speed;
    private Direction direction;
    private boolean[] dirs = new boolean[4];
    private boolean enemy;

    public boolean[] getDirs() {
        return dirs;
    }

    public Tank(int x, int y, Direction direction,Image[] image) {
        this(x, y, direction, false,image);
    }

    public Tank(int x, int y, Direction direction, boolean enemy,Image[] image) {
        super(x,y,image);
        this.direction = direction;
        speed = 5;
        this.enemy = enemy;
    }

//    public Image getImage() {
//        String name = enemy ? "etank" : "itank";
//
//        if (direction == Direction.UP)
//            return Tools.getImage(name + "U.png");
//        if (direction == Direction.DOWN)
//            return Tools.getImage(name + "D.png");
//        if (direction == Direction.LEFT)
//            return Tools.getImage(name + "L.png");
//        if (direction == Direction.RIGHT)
//            return Tools.getImage(name + "R.png");
//        if (direction == Direction.UP_LEFT)
//            return Tools.getImage(name + "LU.png");
//        if (direction == Direction.UP_RIGHT)
//            return Tools.getImage(name + "RU.png");
//        if (direction == Direction.DOWN_LEFT)
//            return Tools.getImage(name + "LD.png");
//        if (direction == Direction.DOWN_RIGHT)
//            return Tools.getImage(name + "RD.png");
//
//
//        return null;
//    }

    public void move() {
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
}

