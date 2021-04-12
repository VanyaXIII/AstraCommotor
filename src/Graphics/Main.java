package Graphics;

import Engine.Engine;

import javax.swing.*;

import java.awt.event.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static int WIDTH = 800;
    public static int HEIGHT = 800;
    public static void main(String[]args) {

        JFrame frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE){
                    Engine.timeStopped = !Engine.timeStopped;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });

        MyPanel p = new MyPanel();
        frame.add(p);
        frame.setVisible(true);
        while (true) {
            frame.repaint();
        }
    }
}