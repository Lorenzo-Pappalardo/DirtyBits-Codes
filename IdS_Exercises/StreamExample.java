import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {
        Predicate<Integer> biggerThan = x -> x >= 5;
        Stream<Integer> numbers = Stream.of(1, 6, 7, 2, 4, 3);

        System.out.println(numbers.filter(biggerThan).count());
    }
}