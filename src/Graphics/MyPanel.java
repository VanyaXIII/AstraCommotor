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
    private int calcDepth;
    public RenderEngine renderEngine = new RenderEngine(engine);

    {
        calcDepth = 10;
    }

    public MyPanel() {
        engine.renderEngine = renderEngine;
        this.addMouseListener(engine.planetaryEngine);
        this.addMouseMotionListener(engine.planetaryEngine);
        lastTime = System.currentTimeMillis();
        engine.planetaryEngine.renderEngine = renderEngine;
    }

    public MyPanel(int calcDepth){
        this();
        this.calcDepth = calcDepth;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            var t1 = System.nanoTime();
            currentTime = System.currentTimeMillis();
            timePast = (currentTime - lastTime)/1000.0;
            lastTime = currentTime;
            for (int i = 0; i < calcDepth; i++)
                engine.updateAll(timePast);
            ArrayList<Ball> b = new ArrayList<>();
            for (int i = 0 ; i < engine.planetaryEngine.balls.size(); ++i) {
                b.add(new Ball(engine.planetaryEngine.balls.get(i)));
            }
            renderEngine.renderingData = b;
            renderEngine.relocate();
            renderEngine.rotate();
            renderEngine.drawForPE(g);
        g.setColor(Color.BLACK);
        g.drawString(String.format("%.2f", 1000d / ((System.nanoTime() - t1) / 1000000d)), 10, 10);
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
