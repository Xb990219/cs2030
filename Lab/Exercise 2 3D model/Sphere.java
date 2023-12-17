public class Sphere implements Shape3D {
    private static final double FOUR = 4.0;
    private static final double THREE = 3.0;
    private final double radius;

    Sphere(double radius) {
        this.radius = radius;
    }

    public double volume() {
        return (FOUR / THREE) * Math.PI * Math.pow(this.radius, (int)THREE);
    }

    @Override
    public String toString() {
        return "sphere " + "[" + String.format("%.2f",this.radius) + "]";
    }

}
