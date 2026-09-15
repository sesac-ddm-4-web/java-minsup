import java.util.List;

public class LongWordCount {
    public static void main(String[] args) {
        List<String> words = List.of("java", "if", "stream", "for", "lambda");
        long count = words.stream()
                    .filter(n -> n.length() >= 5)
                    .count();
        System.out.println("긴 단어: " + count);
    }
}
