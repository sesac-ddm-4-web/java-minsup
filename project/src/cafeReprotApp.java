package task.lesson7;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class CafeReportApp {
    public static void main(String[] args) {
//        System.out.println("현재 Working Directory: " + System.getProperty("user.dir"));
        Path[] inputFiles = {
                Path.of("data", "miniproject", "cafe-report", "branch-a.txt"),
                Path.of("data", "miniproject", "cafe-report", "branch-b.txt")
        };
        Path output = Path.of("out", "cafe-report.txt");

        // TODO: 전체·지점별·메뉴별 결과를 저장할 변수를 준비하세요.
        // 정상내역, 제외내역, 전체 판매 수량, 총매출
        int right = 0;
        int except = 0;
        int totalquantity = 0;
        int totalprice = 0;

        // branch별 매출
        Map<String, Integer> branchScaleMap = new LinkedHashMap<>();

        // 메뉴별 수량과 가격
        Map<String, Menu> menuMap = new LinkedHashMap<>();

        for (Path input : inputFiles) {
            String branchName = input.getFileName().toString().replace(".txt", "");
            try (BufferedReader reader =
                         Files.newBufferedReader(input, StandardCharsets.UTF_8)) {
                String line;
                int lineNumber = 0;
                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    // TODO: 항목을 나누고 검사한 뒤 정상 내역만 집계하세요.
                    // 아래 출력은 읽기 확인용입니다. 구현하면서 바꿔도 됩니다.
                    if(checkLine(line)){
                        right++;

                        String[] strArr = line.split(",");
                        String menuName = strArr[0].trim();
                        int price = Integer.parseInt(strArr[1].trim());
                        int quantity = Integer.parseInt(strArr[2].trim());
                        int sales = quantity * price;
//
                        // 전체 판매 수량 업뎃
                        totalquantity += quantity;
                        // 총 가격 업뎃
                        totalprice += sales;

                        // 브런치 별 총 가격 초기화 및 업뎃
                        branchScaleMap.put(branchName, branchScaleMap.getOrDefault(branchName,0) + sales);

                        // map에 없으면
                        if (!menuMap.containsKey(menuName)){
                            menuMap.put(menuName, new Menu(sales, quantity));
                        }else {
                            // 이미 있으면 수량/매출 누적
                            menuMap.get(menuName).updateSale(sales, quantity);
                        }

                    }else {
                        except++;
                        System.out.println("파일 이름: " + input.getFileName() + ", 줄 번호:" + lineNumber + "행] 잘못된 내역입니다 -> " + line);
                    }
                }
            } catch (IOException e) {
                System.out.println("파일 읽기 실패: " + input.getFileName());
            }
        }

        // TODO: 보고서를 문자열로 만들고 콘솔 출력과 파일 저장을 구현하세요.
        StringBuilder sb = new StringBuilder();
        sb.append("=== 카페 매출 집계 보고서 ===\n");
        sb.append("정상 내역: ").append(right).append("건\n");
        sb.append("제외한 내역: ").append(except).append("건\n");
        sb.append("전체 판매 수량: ").append(totalquantity).append("잔\n");
        sb.append("총 매출: ").append(totalprice).append("원\n\n");

        sb.append("[지점별 매출]\n");
        for (Map.Entry<String, Integer> entry : branchScaleMap.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("원\n");
        }

        sb.append("\n[메뉴별 판매]\n");
        for (Map.Entry<String, Menu> entry : menuMap.entrySet()) {
            Menu menu = entry.getValue();
            sb.append(entry.getKey()).append(": ").append(menu.getQuantity()).append("잔 / ")
                    .append(menu.getPrice()).append("원\n");
        }

        String reportText = sb.toString();

        // 1. 콘솔 출력
        System.out.print(reportText);

        try{
            if(output.getParent() != null){
                Files.createDirectories(output.getParent());
            }
            Files.writeString(output, reportText, StandardCharsets.UTF_8);
            System.out.println("\n보고서가 저장 완료");
        }catch (IOException e){
            System.out.println("보고서 저장 실패: " + e.getMessage());
        }
    }

    private static boolean checkLine(String line) {
        // 빈줄
        if(line == null){
            return false;
        }
        String[] strArr = line.split(",");
        // 항목이 3개가 아닐 때
        if(strArr.length != 3){
            return false;
        }
        // 각 항목 추출
        String menuName = strArr[0].trim();
        String priceStr = strArr[1].trim();
        String quantityStr = strArr[2].trim();
        // 메뉴명이 없을 때
        if(menuName.isEmpty()){
            return false;
        }
        // 수량과 가격이 양의 정수인지
        try {
            int price = Integer.parseInt(priceStr);
            int quantity = Integer.parseInt(quantityStr);

            // 수량과 가격은 양의 정수여야 함 (0 이하 제외)
            if (quantity <= 0 || price <= 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            // 숫자로 변환할 수 없는 문자열인 경우
            return false;
        }

        // 모든 검증을 통과하면 true
        return true;
    }
}
