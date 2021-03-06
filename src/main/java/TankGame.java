import object.GameObject;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankGame {

    private static GameClient gameClient;

    public static GameClient getGameClient() {
        return gameClient;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        gameClient = new GameClient();

        frame.add(gameClient);
        frame.setTitle("坦克大戰!");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //置中處理
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        gameClient.repaint();

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                gameClient.keyPressed(e);
                //System.out.println((char)e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                gameClient.keyReleased(e);
            }
        });
    }
}
