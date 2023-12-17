public class Main {
    public static double simulate(int seed, int n) {
        int count = 0;
        Circle circle = new Circle(new Point(0.0, 0.0), 1.0);
        Rand random = Rand.of(seed);
        for (int i = 0; i < n; i++) {
            random = Rand.of(seed).next();
        }
    }
}
