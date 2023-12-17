public class SolidSphere extends Sphere {
    private final double density;

    SolidSphere(double radius, double density) {
        super(radius);
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
               " with a mass of " + 
               String.format("%.2f",this.mass());
    }


}