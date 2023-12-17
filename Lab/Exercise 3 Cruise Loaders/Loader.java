public class Loader {
    private final int identifier;

    public Loader(int identifier) {
        this.identifier = identifier;
    }

    int getIdentifier() {
        return this.identifier;
    }

    @Override
    public String toString() {
        return "Loader #" + this.identifier;
    }

}
