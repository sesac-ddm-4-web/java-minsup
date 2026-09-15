import java.util.List;

public class AdjustedScoreSummary {
    public static void main(String[] args) {
        List<Integer> scores = List.of(-10, 45, 60, 80, 98, 100, 120);
        long count = scores.stream()
                .filter(n -> n>0 && n<=100)
                .count();
        System.out.println("유효한 점수: " + count);
        int total = scores.stream()
                .filter(n -> n >0 && n<=100)
                .map(n -> n + 5)
                .filter(n -> n<=100)
                .mapToInt(n->n)
                .sum();
        int total2 = scores.stream()
                .filter(n -> n >0 && n<=100)
                .map(n -> n + 5)
                .filter(n -> n>=100)
                .mapToInt(n-> n = 100)
                .sum();
        System.out.println("보정 점수 합계: " + (total + total2));
    }
}
