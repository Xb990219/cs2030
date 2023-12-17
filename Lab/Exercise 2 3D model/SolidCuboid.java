public class SolidCuboid extends Cuboid implements Solid {
    private final double density;

    SolidCuboid(double height, double length, double width, double density) {
        super(height, length, width);
        this.density = density;
    }

    public double mass() {
        SolidImpl solidImpl = new SolidImpl(this, density);
        return solidImpl.mass();
    }

    @Override
    public String toString() {
        return "solid-" + 
        super.toString() + 
        String.format(" with a mass of %.2f",this.mass());
    }
    
}
