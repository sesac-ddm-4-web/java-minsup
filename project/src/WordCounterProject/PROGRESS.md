# PROGRESS

## 1. 실행 방법

| 항목 | 내용 |
|---|---|
| JDK | [ ] (예: JDK 21) |
| 실행 클래스 | `kr.sesac.wordcounter.Main` |
| 작업 디렉터리 | [ ] (IntelliJ Run 설정의 Working directory) |
| 외부 라이브러리 | Apache Commons CSV ([ ] 버전) |
| 설정 위치와 현재 값 | `CsvParser.TARGET_COLUMNS` = `List.of("text")`, 'TsvParser.TARGET_COLUMNS` = `List.of("document")`, 'Html.SELECTOR = "#content" |
| 결과 저장 위치 | `<작업 디렉터리>/out/counts.tsv` |

## 2. 구현한 기능과 확인한 입력·결과

| 기능 | 상태(완료·진행 중·미구현) | 확인한 입력과 결과 |
|---|---|---|
| TXT 카운팅 | 구현 | samples/equivalent/basic.txt |
| CSV·TSV·HTML 처리 | 구현 | samples/equivalent/basic.tsv / samples/equivalent/basic.csv / samples/equivalent/basic.html |
| 여러 파일 순차 처리 | 구현 | samples/equivalent |
| 상위 단어·특정 단어 조회 | 구현 | samples/equivalent/basic.txt |
| 전체 결과 저장 | 구현 | samples/equivalent/basic.txt / out/counts.tsv |
| 잘못된 입력·실패 파일·빈 파일 처리 | 구현 | samples/invalid/missing-column.csv |

전체 처리 흐름은 간단히 3~4줄로 적기:
`Main → numCheck → startNewAnalysis → processFile → 파서 선택 → 정규식 토큰화 → AnalysisResult 누적 → 요약 출력`

## 3. 정확성 확인과 처리 시간

- 작은 기본 샘플의 전체 결과를 정답과 비교한 방법: `Hello, world! (Hello) 안녕 123 abc123`을 손으로 센 결과(hello 2, abc123 1,
  world 1, 안녕 1, 전체 5개/종류 4개)와 counts.tsv를 한 줄씩 대조했다.
  같은 내용을 txt/csv/tsv/html로 만들어 네 형식 모두 [일치했다].
- CSV 따옴표·줄바꿈을 확인한 결과: 따옴표 안의 쉼표, 따옴표 안의 줄바꿈, 이스케이프된 따옴표를 한 파일에 넣었고 기대 결과와 같이 분석 열인 text열만 카운트했다.
- 일부 파일이 실패했을 때 확인한 결과: 폴더 안 일부 파일이 실패했을 때, 요약문에서 몇 번 실패했는 지 알 수 있었고 분석 결과 집계에 반영하지 않았다.
- 결과 저장 파일 위치: 코드 기준으로 확정할 수 있고 out/counts.tsv로 지정함

CSV의 분석 열은 `text`입니다. 아래 입력은 각각 따로 실행합니다. 정답은 [필수 요구사항의 큰 데이터 처리](docs/requirements.md#10-큰-데이터-처리)를 참고하세요.

| 입력 | 데이터 건수 / 파일 수 | 전체 단어 수 | 종류 수 | 처리 시간 | 완료·오류 |
|---|---|---|---|---|---|
| `data/klue-ynat/news-1000.csv` | 1,000 / 1 | 6991 | 5052 | 54.92ms | 완 |
| `data/klue-ynat/news-10000.csv` | 10,000 / 1 | 70374 | 28871 | 122.37ms | 완 |
| `data/klue-ynat/news-full.csv` | 45,678 / 1 | 321084 | 78309 |  | 완 |
| `data/klue-ynat/many` | 45,678 / 16, 순차 처리 | 321084 | 78309 | 263.05ms | 완 |

- 전체 파일 하나와 16개 파일의 **모든 단어별 횟수**를 비교한 방법과 결과:

## 4. 구현 중 해결한 문제

1~3가지를 골라 적으세요. 잘 해결되지 않은 문제도 시도한 내용과 함께 적어도 됩니다.

- 문제: 구분자가 다양해 `split`으로는 기호가 붙은 토큰이 남음
- 원인: `split`은 버릴 것을 정의하므로 구분자를 하나라도 빠뜨리면 오염됨
- 해결하거나 시도한 방법: `[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]+`로 남길 단어를 정의하고 `Matcher.find()` 반복


- 문제: 실패 파일이 결과를 오염시킬까 봐 임시 맵을 두었음
- 원인: `parse()`가 텍스트 전체를 반환한 뒤에 집계하므로 집계 중 예외가 없음
- 해결하거나 시도한 방법: 임시 맵 제거, 루프에서 바로 `addWordCount
- 확인한 입력과 결과: 형식 오류 CSV가 섞인 폴더에서 실패 파일 단어가 집계에 없음 [ ] (실제 확인)

## 5. AI 활용 내용과 직접 확인한 방법

| AI에게 물은 것 | 받은 답 | 직접 확인한 방법 |
|---|---|---|
| 전체 처리 흐름 설명 | 흐름 정리, 폴더 처리 버그 지적 | 코드를 다시 읽고 `entry` 인자 수정 후 폴더 입력 테스트 |
| 정규식 vs split 차이 | 남길 것/버릴 것 개념 | 같은 문자열로 두 방식 직접 실행 비교 |
| `localCounts` 필요성 | 집계 구간에 예외 없음 | 형식 오류 파일로 실행해 확인 |
