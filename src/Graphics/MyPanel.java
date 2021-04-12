package Graphics;

import Engine.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MyPanel extends JPanel implements MouseMotionListener {
    double lastTime;
    double timePast;
    double currentTime;
    public Engine engine = new Engine();
    public RenderEngine renderEngine = new RenderEngine(engine);


    public MyPanel() {
        engine.renderEngine = renderEngine;
        this.addMouseListener(engine.planetaryEngine);
        this.addMouseMotionListener(engine.planetaryEngine);
        lastTime = System.currentTimeMillis();// + 7000;TODO
        engine.planetaryEngine.renderEngine = renderEngine;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //if (System.currentTimeMillis() - engine.startingTime > 7000){
            currentTime = System.currentTimeMillis();
            timePast = (currentTime - lastTime)/1000.0;
            lastTime = currentTime;
            engine.updateAll(timePast);
            ArrayList<Ball> b = new ArrayList<Ball>();
            for (int i = 0 ; i < engine.planetaryEngine.balls.size(); ++i) {
                b.add(new Ball(engine.planetaryEngine.balls.get(i)));
            }
            renderEngine.renderingData = b;
            renderEngine.relocate();
            renderEngine.rotate();
            renderEngine.drawForPE(g);
        /*}else{
            engine.introAnimation(g);
        }*/
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
