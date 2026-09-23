package kr.sesac.wordcounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

/**
 * 첫 실행용 코드입니다. TXT 내용을 읽은 뒤 TODO를 채우며 기능을 추가하세요.
 * 구현 기준은 docs/requirements.md에 있습니다.
 */
public class Main {
    // 기준이 되는 pattern 초기화
    private static final Pattern WORD_PATTERN = Pattern.compile("[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]+");
    public static void main(String[] args) throws IOException {
        Path input = Path.of("samples/equivalent/basic.txt");

        System.out.println("문서 단어 분석기 - 시작 코드");
        System.out.println("입력 파일: " + input);
        System.out.println();

        // TODO 1: 단어별 출현 횟수를 저장할 자료구조를 준비하세요. (요구사항 4. 단어별 횟수 집계)
        Map<String, Integer> wordCountMap = new LinkedHashMap<>();
        try (BufferedReader reader =
                     Files.newBufferedReader(input, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                // TODO 2: line을 요구사항 3. 단어 처리 규칙대로 단어(토큰)로 나누세요.
                // matcher를 통해서 WORD_PATTERN의 기준에 맞는 걸 추출
                Matcher matcher = WORD_PATTERN.matcher(line);
                // 공백, 문장부호, 한자, 이모지 등은 매칭 대상에서 자동 제외(경계 역할)
                while (matcher.find()) {
                    // 영문 A-Z, a-z, 숫자 0-9, 한글 가-힣, ㄱ-ㅎ, ㅏ-ㅣ 연속 묶음 추출 후 소문자 변환
                    // 정규식 패턴에 맞는 단어를 추출하여 그룹을 만들고 소문자로 변환 후 String 객체로 담음
                    String token = matcher.group().toLowerCase();

                    // TODO 3: 숫자만 있는 단어는 제외하고 단어별 횟수를 늘리세요.
                    if (!token.matches("\\d+")) { // 숫자(0~9)로만 이루어진 토큰은 제외
                        wordCountMap.put(token, wordCountMap.getOrDefault(token, 0) + 1);
                    }
                }
            }
        }

        System.out.println();
        System.out.println("파일 읽기 성공. 다음 단계는 단어 분리와 카운팅입니다.");
        System.out.println("구현 후 전체 9개·6종인지 expected/basic-counts.tsv와 비교하세요.");
        // TODO 4: 원문 출력 대신 집계 결과를 출력하세요.
        System.out.println("=== 단어 집계 결과 ===");
        // map의 key와 value를 쌍으로 담아 set에 넣는다
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
        wordCountMap.forEach((word, count) -> System.out.println(word + "\t" + count));
        // TXT 카운팅 완성 후 다른 형식, 메뉴, 오류 처리, 저장을 추가하세요.
    }
}
