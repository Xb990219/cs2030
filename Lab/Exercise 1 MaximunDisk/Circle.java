class Circle {
    private final Point centre;
    private final double radius;

    Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }
    
    public boolean contains(Point p) {
        if (this.centre.distanceTo(p) > this.radius) {
            return false;
        }
        return true;
    }

    public Point getCentre() {
        return this.centre;
    }

    @Override
    public String toString() {
        return "circle of radius " + this.radius + " centred at " + this.centre;
    }
}
