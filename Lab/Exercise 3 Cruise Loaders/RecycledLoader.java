public class RecycledLoader extends Loader {

    public RecycledLoader(int identifier) {
        super(identifier);
    }

    @Override
    public String toString() {
        return "Recycled Loader #" + this.getIdentifier();
    }
}
