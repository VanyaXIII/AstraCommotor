package Engine;

public class Vector {
    public double x;
    public double y;
    public double z;


    @Override
    public String toString() {
        return String.format("(%.6f, %.6f)", x, y);
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector plus(Vector v){
        return new Vector(x + v.x, y + v.y, z + v.z);
    }

    public void plusMe(Vector v){
        x+=v.x;
        y+=v.y;
        z+=v.z;
    }

    public void minusMe(Vector v){
        x-=v.x;
        y-=v.y;
        z-=v.z;
    }

    public Vector x(double n){
        return new Vector(x*n, y*n, z*n);
    }

    public void xMe(double n){
        x*=n;
        y*=n;
        z*=n;
    }

    public double distanceTo(Vector v){
        return Math.sqrt((v.y - this.y)*(v.y - this.y) + (v.x - this.x)*(v.x - this.x) + (v.z - this.z)*(v.z - this.z));
    }

    public Vector(Vector v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }
}
