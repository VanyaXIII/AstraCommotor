package Engine;

import java.awt.*;
import java.util.ArrayList;

public abstract class Body {
    public Vector c;
    public Vector cA = new Vector(0, 0, 0);
    public Vector cV = new Vector(0, 0, 0);
    public double m = 1e2;
    public double radius = 5;
    public boolean fullCircle = false;
    public ArrayList<Vector> trajectory = new ArrayList<>();
    public int number;

    public void update(double dt){
        updateC(dt);
    }

    public Body(Body body) {
        this.c = new Vector(body.c);
        this.cA = new Vector(body.cA);
        this.cV = new Vector(body.cV);
        this.m = body.m;
        this.radius = body.radius;
        this.fullCircle = body.fullCircle;
        this.number = body.number;
        this.trajectory = new ArrayList<Vector>();
        for (int i = 0; i < body.trajectory.size(); i++) {
            trajectory.add(new Vector(body.trajectory.get(i)));
        }
    }

    public Body() {
    }

    public void updateC(double dt){
        cV.plusMe(cA.x(dt));
        c.plusMe(cV.x(dt));
        if (trajectory.size() == 0 || trajectory.get(trajectory.size()-1).distanceTo(c)  >= 5) {
            trajectory.add(new Vector(c));
            if (fullCircle){
                trajectory.remove(0);
            }
        }
        if (trajectory.size() > 10 && trajectory.get(0).distanceTo(c) <= 10){
            fullCircle = true;
        }
    }

    abstract public void draw(Graphics g);

    public void plusMe(Body b){
        cV = cV.x(m).plus(b.cV.x(b.m)).x((1/(m + b.m)));
        m += b.m;
        radius = Math.pow(radius*radius*radius + b.radius*b.radius*b.radius, 1.0/3);

    }
}