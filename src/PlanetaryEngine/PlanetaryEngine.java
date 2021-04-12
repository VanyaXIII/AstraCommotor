package PlanetaryEngine;
import Engine.Ball;
import Engine.Vector;
import Graphics.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PlanetaryEngine implements MouseListener, KeyEventDispatcher, MouseMotionListener {
    public final double G = 1;
    public static double zoom = 1;
    KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    public RenderEngine renderEngine;
    boolean mousePressed = true;
    int mX = 0;
    int mY = 0;

    public ArrayList<Ball> balls = new ArrayList<>();

    public PlanetaryEngine() {
        createNewSystem();
        manager.addKeyEventDispatcher(this);
    }

    private void createNewSystem() {
        RenderEngine.indexOfFocusedBall = 1;
        balls.add(new Ball(0, 0, 0,0, 0, 0, balls.size()));
        balls.get(0).m = 1e5;
        balls.get(0).radius = 16;
        balls.add(new Ball(0, -30, 0, 55, 0,0, balls.size()));
        balls.add(new Ball(0, -100, 0,30, 0,0, balls.size()));
        balls.add(new Ball(0, -120, 0,0, 0,32, balls.size()));
        balls.add(new Ball(0,-65, 0, 10, 0, 35, balls.size()));
        balls.add(new Ball(-500, -100, 0, 30, 0,0, balls.size()));//Гравитационный монёвр
    }

    public void updateAll(double timePast){
        int b = -1;
        for (int j = 0; j < balls.size(); j++) {
            for (int i = j + 1; i < balls.size();) {
                if (balls.get(j).checkCollision(balls.get(i))){
                    balls.get(j).plusMe(balls.get(i));
                    balls.remove(i);
                    b = i;
                }else{
                    i++;
                }
            }
        }

        if (b != -1){
            for (int i = 0; i < balls.size(); i++) {
                balls.get(i).number = i;
            }
            if (b == RenderEngine.indexOfFocusedBall){
                RenderEngine.indexOfFocusedBall = 0;
            }
        }
        for (int g = 0; g < balls.size(); g++){
            balls.get(g).cA.xMe(0);
            for (int i = 0; i < balls.size(); i++) {
                if ( i != g){
                    double r = balls.get(g).c.distanceTo(balls.get(i).c);
                    Vector a = new Vector((balls.get(i).c.x - balls.get(g).c.x)*G*balls.get(i).m/(r*r*r),
                            (balls.get(i).c.y - balls.get(g).c.y)*G*balls.get(i).m/(r*r*r),
                            (balls.get(i).c.z - balls.get(g).c.z)*G*balls.get(i).m/(r*r*r));
                    balls.get(g).cA.plusMe(a);
                }
            }
            balls.get(g).update(timePast);
        }
    }

    private void zoom() {
            zoom *= 1.03;
    }

    private void moveAway() {
            zoom /= 1.03;
    }

    private void refocus(int x, int y){
        Ball b;
        for (int i = 0; i < renderEngine.renderingData.size(); i++) {
            b = renderEngine.renderingData.get(i);
            int bX = RenderEngine.engineToWindowX(b.c.x);
            int bY = RenderEngine.engineToWindowY(b.c.y);
            double distance = (bX - x)*(bX - x) + (bY - y)*(bY - y);
            if (distance <= b.radius*b.radius*PlanetaryEngine.zoom*PlanetaryEngine.zoom){
                RenderEngine.indexOfFocusedBall = b.number;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1){
            refocus(mouseEvent.getX(), mouseEvent.getY());
        }else if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
            mousePressed = true;
            mX = mouseEvent.getX();
            mY = mouseEvent.getY();
            System.out.println(mX + " " + mY);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP){
            zoom();
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            moveAway();
        }

        if (e.getKeyCode() == KeyEvent.VK_W){
            RenderEngine.angleX-=Math.PI/16;
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            RenderEngine.angleX+=Math.PI/16;
        }else if (e.getKeyCode() == KeyEvent.VK_A){
            RenderEngine.angleY+=Math.PI/16;
        }else if (e.getKeyCode() == KeyEvent.VK_D){
            RenderEngine.angleY-=Math.PI/16;
        }

         return false;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (Math.abs(RenderEngine.angleY) > Math.PI/2){
            RenderEngine.angleY = Math.signum(RenderEngine.angleY)*Math.PI/2;
        }else if (Math.abs(RenderEngine.angleY -= (mouseEvent.getX() - mX)*0.0005) > Math.PI/2) {
            RenderEngine.angleY -= (mouseEvent.getX() - mX)*0.0005;
        }

        if (Math.abs(RenderEngine.angleX) > Math.PI/2){
            RenderEngine.angleX = Math.signum(RenderEngine.angleX)*Math.PI/2;
        }else if (Math.abs(RenderEngine.angleX -= (mouseEvent.getY() - mY)*0.0005) > Math.PI/2) {
            RenderEngine.angleX -= (mouseEvent.getY() - mY)*0.0005;
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
