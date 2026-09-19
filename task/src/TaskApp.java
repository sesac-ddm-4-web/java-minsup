package task.lesson7;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class TaskApp {
    public static void main(String[] args) {
        // TODO: 할 일 목록을 준비하고 안내에 나온 세 항목을 등록하세요.
        // 목록은 아래 while 바깥에 두어 메뉴를 바꿔도 유지되도록 합니다.

        try (Scanner scanner = new Scanner(System.in)) {
            Map<Integer, Task> map = new HashMap<>();
            map.put(1, new Task("자바 복습", "미완료"));
            map.put(2, new Task("예외 처리 정리", "완료"));
            map.put(3, new Task("파일 입출력 예제 실행", "미완료"));
            Integer count = 4;
            boolean running = true;
            while (running) {
                printMenu();
                int menu = readInt(scanner, "선택: ");
                switch (menu) {
                    case 1: {
                        String title = readText(scanner, "할 일 제목: ");
                        // TODO: 제목을 검사하고 미완료 항목을 추가하세요.
                        if(!isEmpty(title)){
                           map.put(count,new Task(title, "미완료"));
                           count++;
                        }
//                        System.out.println("할 일 추가 기능을 구현하세요.");
                        break;
                    }
                    case 2:
                        // TODO: 전체 목록을 출력하세요.
                        printListAll(map);
//                        System.out.println("전체 목록 조회 기능을 구현하세요.");
                        break;
                    case 3:
                        // TODO: 미완료 항목만 출력하세요.
                        printListCompleted(map);
//                        System.out.println("미완료 목록 조회 기능을 구현하세요.");
                        break;
                    case 4: {
                        int targetId = readInt(scanner, "완료할 번호: ");
                        // TODO: 번호로 찾은 항목을 완료 처리하세요.
                        changeCompleted(map, targetId);
//                        System.out.println("완료 처리 기능을 구현하세요.");
                        break;
                    }
                    case 5:
                        // TODO: 전체·완료·미완료 개수를 출력하세요.
                        printCount(map);
//                        System.out.println("진행 상황 조회 기능을 구현하세요.");
                        break;
                    case 0:
                        running = false;
                        System.out.println("종료합니다.");
                        break;
                    default:
                        System.out.println("메뉴에 있는 번호를 선택하세요.");
                }
            }
        }
    }

    private static void printCount(Map<Integer, Task> map){
        System.out.println("전체: " + map.size() + "개");
        int completed = 0;
        for(Integer id : map.keySet()){
            Task task = map.get(id);
            if(task.getCompleted().equals("완료")){
                completed++;
            }
        }
        System.out.println("완료: " + completed + "개");
        System.out.println("미완료: " + (map.size() - completed) + "개");
    }

    private static void changeCompleted(Map<Integer, Task> map, int targetId){
        Task task = map.get(targetId);
        if(task == null){
            System.out.println("해당 id에 목록이 없음");
        }else {
            task.completed = "완료";
        }
    }

    private static void printListCompleted(Map<Integer, Task> map){
        for(Integer id : map.keySet()){
            Task task = map.get(id);
            if(task.getCompleted().equals("미완료")){
                System.out.println(id + ". " + task.getTitle() + " / " + task.getCompleted());
            }
        }
    }

    private static void printListAll(Map<Integer, Task> map){
        if(map.isEmpty()){
            System.out.println("목록이 비어있습니다.");
        }
        for(Integer id : map.keySet()){
            Task task = map.get(id);
            System.out.println(id + ". " + task.getTitle() + " / " + task.getCompleted());
        }
    }

    private static boolean isEmpty(String title){
        return title == null || title.isEmpty();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. 할 일 추가");
        System.out.println("2. 전체 목록 보기");
        System.out.println("3. 미완료 목록 보기");
        System.out.println("4. 완료 처리");
        System.out.println("5. 진행 상황 보기");
        System.out.println("0. 종료");
    }

    private static String readText(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            String text = readText(scanner, prompt);
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.println("정수로 입력하세요.");
            }
        }
    }
}
