package kr.sesac.wordcounter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentWordAnalyzer {
    private static final List<String> SUPPORTED_EXTENSIONS = List.of(".txt", ".csv", ".tsv", ".html", ".htm");
    private static final Pattern WORD_PATTERN = Pattern.compile("[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]+");

    private final Scanner sc = new Scanner(System.in);
    private AnalysisResult currentResult; // 최근 분석 결과 저장

    public boolean numCheck(int num) {
        switch (num) {
            case 0:
                System.out.println("프로그램을 종료합니다.");
                return false;
            case 1:
                startNewAnalysis();
                return true;
            case 2:
                showTopNWords();
                return true;
            case 3:
                findWordCount();
                return true;
            case 4:
                saveTotalResult();
                return true;
            case 5:
                showRecentSummary();
                return true;
            default:
                System.out.println("정상 입력 범위는 0~5입니다.");
                return true;
        }
    }

    // 1. 새 분석 시작
    private void startNewAnalysis() {
        while (true) {
            System.out.print("파일 또는 폴더 경로 > ");
            String pathStr = sc.nextLine().trim();
            Path path = Path.of(pathStr);

            if (!Files.exists(path)) {
                System.out.println("경로를 찾을 수 없습니다: " + pathStr);
                continue;
            }

            AnalysisResult result = new AnalysisResult(path);
            long startTime = System.nanoTime();

            try {
                if (Files.isRegularFile(path)) {
                    processFile(path, result);
                } else if (Files.isDirectory(path)) {
                    try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                        for (Path entry : stream) {
                            if (Files.isRegularFile(entry)) {
                                processFile(entry, result);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("경로 접근 중 오류 발생: " + e.getMessage());
            }

            if (result.getSuccess() == 0) {
                System.out.println("분석할 지원 파일(.txt, .csv, .tsv, .html, .htm)이 없습니다.");
                continue;
            }

            long endTime = System.nanoTime();
            result.setElapsedTimeMs((endTime - startTime) / 1_000_000.0);

            this.currentResult = result;
            System.out.println();
            System.out.println("분석 완료");
            result.printSummary();
            break;
        }
    }

    private void processFile(Path filePath, AnalysisResult result) {
        result.attemptIncrease();

        String fileName = filePath.getFileName().toString().toLowerCase();
        boolean isSupported = SUPPORTED_EXTENSIONS.stream().anyMatch(fileName::endsWith);

        if (!isSupported) {
            result.skipIncrease();
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = WORD_PATTERN.matcher(line);
                while (matcher.find()) {
                    String token = matcher.group().toLowerCase();
                    if (!token.matches("\\d+")) { // 숫자만 있는 단어 제외
                        result.addWordCount(token, 1);
                    }
                }
            }
            result.successIncrease();
        } catch (IOException e) {
            result.failIncrease();
        }
    }

    // 2. 상위 N개 단어 보기
    private void showTopNWords() {
        if (!hasAnalysisResult()) return;

        Map<String, Integer> map = currentResult.getWordCountMap();
        while (true) {
            System.out.print("몇 개를 볼까요? (기본 10) > ");
            String input = sc.nextLine().trim();

            int n = 10;
            if (!input.isEmpty()) {
                try {
                    n = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("1 이상의 정수를 입력하세요.");
                    continue;
                }
            }

            if (n < 1) {
                System.out.println("1 이상의 정수를 입력하세요.");
                continue;
            }

            // 출현 횟수 내림차순 정렬 (횟수가 같으면 먼저 등장한 순서 유지)
            List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
            list.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

            int limit = Math.min(n, list.size());
            for (int i = 0; i < limit; i++) {
                Map.Entry<String, Integer> entry = list.get(i);
                System.out.println((i + 1) + ". " + entry.getKey() + " : " + entry.getValue() + "회");
            }
            break;
        }
    }

    // 3. 특정 단어 횟수 찾기
    private void findWordCount() {
        if (!hasAnalysisResult()) return;

        while (true) {
            System.out.print("찾을 단어 > ");
            String target = sc.nextLine().trim();

            if (target.contains(" ")) {
                System.out.println("단어 하나를 입력하세요.");
                continue;
            }

            Matcher matcher = WORD_PATTERN.matcher(target);
            if (matcher.find()) {
                String searchToken = matcher.group().toLowerCase();
                int count = currentResult.getWordCountMap().getOrDefault(searchToken, 0);
                System.out.println(searchToken + " : " + count + "회");
            } else {
                System.out.println(target.toLowerCase() + " : 0회");
            }
            break;
        }
    }

    // 4. 전체 결과 저장
    private void saveTotalResult() {
        if (!hasAnalysisResult()) return;

        Path outputPath = Path.of("out/counts.tsv");
        try {
            if (outputPath.getParent() != null) {
                Files.createDirectories(outputPath.getParent());
            }

            try (BufferedWriter writer = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8)) {
                for (Map.Entry<String, Integer> entry : currentResult.getWordCountMap().entrySet()) {
                    writer.write(entry.getKey() + "\t" + entry.getValue());
                    writer.newLine();
                }
            }
            System.out.println("전체 결과 " + currentResult.getWordCountMap().size() + "개 단어를 " + outputPath + "에 저장했습니다.");
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 5. 최근 분석 요약 보기
    private void showRecentSummary() {
        if (!hasAnalysisResult()) return;
        currentResult.printSummary();
    }

    // 분석 결과 유무 체크 가드 함수
    private boolean hasAnalysisResult() {
        if (currentResult == null) {
            System.out.println("먼저 1번을 선택해 새 분석을 시작하세요.");
            return false;
        }
        return true;
    }
}
