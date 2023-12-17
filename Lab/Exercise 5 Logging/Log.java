import java.util.Optional;
import java.util.function.Function;

public class Log<T> {
    private final T item;
    private final String logString; 

    private Log(T item, String logString) {
        this.item = item;
        this.logString = logString;
    }

    public T getItem() {
        return this.item;
    }

    /**
     * Default of method to create new Log.
     * @param <T> user input type
     * @param item the item 
     * @return new log
     */
    public static <T> Log<T> of(T item) {
        return Log.<T>of(item, "");
    }

    /**
     * Default of method to create new Log.
     * @param <T> user input type
     * @param item the item 
     * @param logString the String
     * @return new log
     */
    public static <T> Log<T> of(T item, String logString) {
        return Optional.ofNullable(item)
                       .filter(x -> !(x instanceof Log))
                       .flatMap(x -> Optional.ofNullable(logString).map(y -> new Log<>(x, y)))
                       .orElseThrow(() -> new IllegalArgumentException("Invalid arguments"));
    }

    public <R> Log<R> map(Function<? super T, ? extends R> function) {
        R newItem = function.apply(this.item);
        return new Log<R>(newItem, this.logString);
    }

    /**
     * Standard flatmap method.
     * @param <R> Userinput type
     * @param function mapper function
     * @return new Log
     */
    public <R> Log<R> flatMap(Function<? super T, ? extends Log<? extends R>> function) {
        Log<? extends R> result = function.apply(this.item);
        String resultLogString = this.logString.equals("") ?
                                 result.logString : this.logString + "\n" + result.logString;
        return Log.<R>of(result.item, resultLogString);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof Log<?> logObject) {
            if (this.item.equals(logObject.item) && this.logString.equals(logObject.logString)) {
                return true;
            }
        } 
        return false;
    }

    @Override
    public String toString() {
        return this.logString == "" ? "Log[" + this.item + "]" : 
            "Log[" + this.item + "]" + '\n' + this.logString;
    }

}