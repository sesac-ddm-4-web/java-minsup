import java.util.List;

public class EvenNumberSUm {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(3, 6, 1, 8, 5, 2);
        long sum = numbers.stream()
                .filter(n -> n%2 == 0)
                .mapToInt(n -> n)
                .sum();
        System.out.println("짝수 합계: " + sum);
    }
}
