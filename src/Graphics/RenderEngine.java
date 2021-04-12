package Graphics;

import Engine.Ball;
import Engine.Engine;
import Engine.Vector;
import PlanetaryEngine.PlanetaryEngine;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

public class RenderEngine {
    public ArrayList<Ball> renderingData = new ArrayList<Ball>();
    public static int indexOfFocusedBall;
    public static double angleX = 0;
    public static double angleY = 0;
    Engine engine;


    public RenderEngine(Engine engine) {
        this.engine = engine;

    }

    public void relocate(){
        for (int i = 0; i < renderingData.size(); i++) {
            renderingData.get(i).c.minusMe(engine.planetaryEngine.balls.get(indexOfFocusedBall).c);
            for (int j = 0; j < renderingData.get(i).trajectory.size(); j++) {
                renderingData.get(i).trajectory.get(j).minusMe(engine.planetaryEngine.balls.get(indexOfFocusedBall).c);
            }
        }
    }



    public void rotate() {
        double sinA = Math.sin(angleX), sinB = Math.sin(angleY), cosA = Math.cos(angleX), cosB = Math.cos(angleY);

        for (int i = 0; i < renderingData.size(); i++) {
            Ball b = renderingData.get(i);
            Vector v = new Vector(cosB*b.c.x + sinB*b.c.z,
                    sinA*sinB*b.c.x + cosA*b.c.y - sinA*cosB*b.c.z,
                    -cosA*sinB*b.c.x + sinA*b.c.y + cosA*cosB*b.c.z);
            for (int j = 0; j < renderingData.get(i).trajectory.size(); j++) {
                Vector vT = renderingData.get(i).trajectory.get(j);
                Vector vT1 = new Vector(cosB*vT.x + sinB*vT.z,
                        sinA*sinB*vT.x + cosA*vT.y - sinA*cosB*vT.z,
                        -cosA*sinB*vT.x + sinA*vT.y + cosA*cosB*vT.z);
                renderingData.get(i).trajectory.set(j, vT1);
            }
            renderingData.get(i).c = v;
        }
    }


    public void drawForPE(Graphics g) {
        var a = zBuffering(renderingData);
        for (int i = 0; i < renderingData.size(); i++) {
            renderingData.get(i).draw(g);
        }
    }

    public static int engineToWindowX(double x) {
        return (int) (x * PlanetaryEngine.zoom + Main.WIDTH / 2);
    }
    public static int engineToWindowY(double y) {
        return (int) (y * PlanetaryEngine.zoom + Main.HEIGHT / 2);
    }

    public ArrayList<Ball> zBuffering(ArrayList<Ball> balls) {
        SortedMap<Double, Integer> hotData = new TreeMap<>((a, b) -> {
            if (a < b) return 1;
            else if (a > b) return -1;
            return 0;
        });
        for (int i = 0; i < balls.size(); i++) {
            hotData.put(balls.get(i).c.z, i);
        }
        balls.sort(new Comparator<Ball>() {
            @Override
            public int compare(Ball a, Ball b) {
                if (a.c.z < b.c.z) return 1;
                else if (a.c.z > b.c.z) return -1;
                return 0;
            }
        });
        return balls;
    }
}
