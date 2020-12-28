import object.Direction;
import object.GameObject;

import java.awt.*;

public class Bullet extends Tank {

    public Bullet(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, enemy, image);
        setSpeed(20);
    }

    @Override
    public void draw(Graphics g) {
        if (!alive) {
            return;
        }
        move();
        collision();

        g.drawImage(image[getDirection().ordinal()], x, y, null);
    }

    @Override
    public void collision() {
        if (collisionBound()) {
            alive = false;
            return;
        }

        for (GameObject object : TankGame.getGameClient().getGameObjects()) {

            if (object == this) {
                continue;
            }

            if (object instanceof Tank) {
                if (((Tank) object).enemy == enemy) {
                    continue;
                }
            }

            if (getRectangle().intersects(object.getRectangle())) {
                alive = false;
                if (object instanceof Tank) {
                    object.setAlive(false);
                    return;
                }
            }
        }
    }
}
