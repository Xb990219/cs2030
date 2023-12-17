import java.lang.Math;

class Point {
    private final double x;
    private final double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getx() {
        return this.x;
    }

    public double gety() {
        return this.y;
    }
    
    public double distanceTo(Point q) {
        return Math.sqrt((q.x - this.x) * (q.x - this.x) + (q.y - this.y) * (q.y - this.y));
    }
    

    public Point midPoint(Point anotherPoint) {
        double newX = (this.x + anotherPoint.x) / 2;
        double newY = (this.y + anotherPoint.y) / 2;
        return new Point(newX, newY);
    }

    public double angleTo(Point anotherPoint) {
        return Math.atan2((anotherPoint.y - this.y), (anotherPoint.x - this.x));
    }

    public Point moveTo(double angle, double distance) {
        return new Point(this.x + distance * Math.cos(angle), this.y + distance * Math.sin(angle));
    }

    @Override   
    public String toString() {
        String valueX = String.format("%.3f", this.x);
        String valueY = String.format("%.3f", this.y);
        return "point " + "(" + valueX  + ", " + valueY  + ")";
    }
}
