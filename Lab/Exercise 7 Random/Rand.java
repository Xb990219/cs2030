import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

public class Rand<T> {
    private final int i;
    private final Function<Integer,T> f;

    private Rand(int i, Function<Integer,T> f) {
        this.i = i;
        this.f = f;
    }

    public static Rand<Integer> of(int i) {
        return new Rand<Integer>(i, x -> x);
    }

    public static <T> Rand<T> of(int i, Function<Integer, T> f) {
        return new Rand<T>(i, f);
    }

    public T get() {
        return this.f.apply(i);
    }

    public Rand<T> next() {
        Random r = new Random(this.i);
        int next = r.nextInt(Integer.MAX_VALUE);
        return new Rand<T>(next, this.f);
    }

    public Stream<T> stream() {
        return Stream.iterate(this.i, x -> {
            Random r = new Random(x);
            int next = r.nextInt(Integer.MAX_VALUE);
            return next;
        }).map(f);
    }

    public static <R> Stream<R> randRange(int i, Function<Integer,R> f) {
        return Stream.iterate(i, x -> {
            Random r = new Random(x);
            int next = r.nextInt(Integer.MAX_VALUE);
            return next;
        }).map(f);
    }

    public <R> Rand<R> map(Function<T,R> f) {
        return Rand.of(i,f.compose(this.f));
    }

    public <R> Rand<R> flatMap(Function<T, Rand<R>> f) {
        Function<Rand<R>, R> getOut = x -> x.get();
        return map(getOut.compose(f));
    }

    @Override
    public String toString() {
        return "" + this.i;
    }
}