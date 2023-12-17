import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Str {
    private final Supplier<String> string;
    private final Function<Consumer<String>, String> logString;

    private Str(Supplier<String> string,Function<Consumer<String>, String> logString) {
        this.string = string;
        this.logString = logString;
    }

    /**
     * This is the static constructor for stirng.
     * @param string input String
     * @return new Str
     */
    public static Str of(String string) {
        Function<Consumer<String>, String> logString = x -> {
            x.accept("traced Str: " + string);
            return string;
        };
        return new Str(() -> string, logString);
    }

    /**
     * This is the static consturct for str.
     * @param supplier the suppier.
     * @return new Str
     */
    public static Str of(Supplier<String> supplier) {
        Function<Consumer<String>, String> logString = x -> {
            String output = supplier.get();
            x.accept("traced Str: " + output);
            return output;
        };
        return new Str(supplier, logString);
    }

    public void run(Consumer<String> runAction) {
        runAction.accept(this.string.get());
    }

    public void print() {
        Consumer<String> runAction = x -> System.out.println(x);
        this.run(runAction);
    }

    /**
     * Map a string to another string then return the new Str.
     * @param mapper mapper
     * @return new Str
     */
    public Str map(Function<String,String> mapper) {
        Supplier<String> newString = () -> mapper.apply(this.string.get());
        Function<Consumer<String>, String> logString = x -> {
            String prevOutput = this.logString.apply(x);
            String transforemOuput = mapper.apply(prevOutput);
            String trace = "traced map: " + transforemOuput;
            x.accept(trace);
            return transforemOuput;
        };
        return new Str(newString, logString);
    }

    /**
     * Flatmap a string to a str.
     * @param mapper mapper
     * @return new Str
     */
    public Str flatMap(Function<String, Str> mapper) {
        Supplier<Str> strSupplier = () -> mapper.apply(this.string.get());
        Function<Consumer<String>, String> logString = x -> {
            String prevOutput = this.logString.apply(x);
            String transforemOuput = mapper.apply(prevOutput).logString.apply(x);
            String trace = "traced flatMap: " + transforemOuput;
            x.accept(trace);
            return transforemOuput;
        };
        return new Str(() -> strSupplier.get().string.get(), logString);
    }

    public Str join(String otherString) {
        Function<String, String> mapper = x -> x + otherString;
        return this.map(mapper);
    }

    public Str join(Str otherStr) {
        return this.flatMap(x -> otherStr.map(y -> x + y));
    }

    public void trace() {
        Consumer<String> action = x -> System.out.println(x);
        this.trace(action);
    }

    public void trace(Consumer<String> action) {
        String logString = this.logString.apply(action);
        action.accept(logString);
    }
}
