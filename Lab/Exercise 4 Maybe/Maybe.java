import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.lang.Runnable;

class Maybe<T> {
    private final T value;

    private Maybe(T value) {
        this.value = value;
    }

    static <U> Maybe<U> of(U value) {
        return new Maybe<U>(value);
    }

    static <T> Maybe<T> empty() {
        return new Maybe<T>(null);
    }

    private T get() {
        return this.value;
    }

    private boolean isEmpty() {
        return this.value == null;
    }

    private boolean isPresent() {
        return !this.isEmpty();
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject instanceof Maybe<?> otherMaybe) {
            return (this.isEmpty() && otherMaybe.isEmpty()) ||
                   (this.isPresent() && otherMaybe.isPresent() &&
                    this.value.equals(otherMaybe.value));
        }
        return false;
    }

    public Maybe<T> filter(Predicate<? super T> pred) {
        return this.isEmpty() ? this : 
            pred.test(this.get()) ? this : Maybe.<T>empty();
    } 

    public void ifPresent(Consumer<? super T> action) {
        if (this.isPresent()) {
            action.accept(this.get());
        }
    }

    public void ifPresentOrElse(Consumer<? super T> trueAction,
                                Runnable emptyAction) {
        if (this.isPresent()) {
            trueAction.accept(this.get());
        } else {
            emptyAction.run();
        }
    }

    public T orElse(T other) {
        if (this.isPresent()) {
            return this.get();
        } else {
            return other;
        }
    }

    public T orElseGet(Supplier<? extends T> supplier) {
        if (this.isPresent()) {
            return this.get();
        } else {
            return supplier.get();
        }
    }

    public Maybe<T> or(Supplier<? extends Maybe<? extends T>> supplier) {
        if (this.isPresent()) {
            return this;
        } else {
            Maybe<? extends T> result = supplier.get();
            return Maybe.<T>of(result.get());
        }
    }

    public <U> Maybe<U> map(Function<? super T, ? extends U> mapper) {
        if (!isPresent()) {
            return empty();
        } else {
            return Maybe.<U>of(mapper.apply(this.get()));
        }
    }

    public <U> Maybe<U> flatMap(Function<? super T, 
                                         ? extends Maybe<? extends U>> mapper) {
        if (!isPresent()) {
            return Maybe.<U>empty();
        } else {
            Maybe<? extends U> result = mapper.apply(this.get());
            return Maybe.<U>of(result.get());
        }
    }

    @Override
    public String toString() {
        if (this.value == null) {
            return "Maybe.empty";
        } else {
            return "Maybe[" + this.value + "]";
        }
    }
}
