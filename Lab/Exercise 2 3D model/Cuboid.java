public class Cuboid implements Shape3D {
    private final double height;
    private final double length;
    private final double width;

    /**
    * Constructor.
    *
    * @param height the height
    * @param length the length
    * @param width the width
    **/
    public Cuboid(double height, double length, double width) {
        this.height = height;
        this.length = length;
        this.width = width;
    }

    //get the volumne of the cuboid
    public double volume() {
        return this.height * this.length * this.width;
    }

    @Override
    public String toString() {
        return String.format("cuboid [%.2f x %.2f x %.2f]", 
                             this.height, 
                             this.length, 
                             this.width);   
    }


}