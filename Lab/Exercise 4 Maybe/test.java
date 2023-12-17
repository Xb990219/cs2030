import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class test {
    


    public <U> Optional<U> flatMap(Function<? super T, ? extends Optional<? extends U>> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent()) {
            return empty();
        } else {
            @SuppressWarnings("unchecked")
            Optional<U> r = (Optional<U>) mapper.apply(value);
            return Objects.requireNonNull(r);
        }
    }


}
