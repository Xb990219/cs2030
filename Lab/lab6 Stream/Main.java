import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.List;
import java.util.function.UnaryOperator;

public class Main {
    public static boolean isPrime(int n) {
        return n > 1 && IntStream.range(2, n)
                                 .noneMatch(x -> n % x == 0);
    }

    /**
     * Find TwinPrimes.
     * @param n maximum number
     * @return a Stream with all tiwn primes
     */
    public static IntStream twinPrimes(int n) {
        return IntStream.rangeClosed(2, n)
                        .filter(x -> isPrime(x))
                        .filter(x -> isPrime(x + 2) || isPrime(x - 2));
    }   

    /**
     * Find Reverse.
     * @param str the input string  
     * @return reverseStr
     */
    public static String reverse(String str) {
        return Stream.<String>of(str.split(""))
                     .reduce("", (x,y) -> y + x);
    }

    /**
     * Find repeats.
     * @param list input list
     * @return the number of repeated number
     */
    public static long countRepeats(List<Integer> list) {
        return IntStream.range(0, list.size() - 1)
                        .filter(x -> x == 0 ? list.get(0) == list.get(x + 1) : 
                                              list.get(x) != list.get(x - 1) && 
                                              list.get(x) == list.get(x + 1))
                        .count();
    }

    /**
     * The unaryOperator to generate new generation.
     * @return the operator
     */
    static UnaryOperator<List<Integer>> generateRule() {
        UnaryOperator<List<Integer>> rule = list -> IntStream.rangeClosed(0, list.size() - 1)
            .mapToObj(index -> list.get(index) == 1 ? 0 
                    : index == 0 ? (list.get(index + 1) == 1 ? 1 : 0)
                    : index == list.size() - 1 ? (list.get(index - 1) == 1 ? 1 : 0)
                    : (list.get(index - 1) == 1 || list.get(index + 1) == 1) ? 
                    ((list.get(index - 1) == 1 && list.get(index + 1) == 1) ? 0 : 1) : 0)
            .toList();
        return rule;
    }

    /**
     * gameOfLife.
     * @param list the original list
     * @param rule the rule
     * @param n the number of generation
     * @return the Stream of all generations
     */
    public static Stream<String> gameOfLife(List<Integer> list, 
                                            UnaryOperator<List<Integer>> rule, 
                                            int n) {
        return Stream.iterate(list, rule).limit(n).map(x -> {
            return IntStream.range(0, x.size()).mapToObj(y -> {
                if (x.get(y) == 0) {
                    return ".";
                }
                return "x";
            }).reduce("", (a, b) -> a + b);
        });
    }
}