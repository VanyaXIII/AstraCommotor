package Engine;
import Graphics.*;
import PlanetaryEngine.PlanetaryEngine;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball extends Body {
    public Ball(double x, double y, double z, double vX, double vY, double vZ, int number) {
        super();
        c = new Vector(x, y, z);
        cV = new Vector(vX, vY, vZ);
        this.number = number;
    }

    public Ball(Ball ball) {
        super(ball);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(RenderEngine.engineToWindowX(c.x - radius),
                RenderEngine.engineToWindowY(c.y - radius),
                2 * radius * PlanetaryEngine.zoom,
                2 * radius * PlanetaryEngine.zoom
        );

        if (m > 1e4) {
            g2d.setColor(new Color(255, 255, 150));
        } else {
            g2d.setColor(Color.BLACK);
        }
        g2d.fill(circle);
        g2d.setColor(Color.BLUE);

        for (int i = 0; i < trajectory.size() - 1; i+=2) {
            g2d.drawLine(RenderEngine.engineToWindowX(trajectory.get(i).x),
                    RenderEngine.engineToWindowY(trajectory.get(i).y),
                    RenderEngine.engineToWindowX(trajectory.get(i+1).x),
                    RenderEngine.engineToWindowY(trajectory.get(i+1).y));
        }
    }

    public boolean checkCollision(Ball b) {
        return radius + b.radius > c.distanceTo(b.c);
    }
}

