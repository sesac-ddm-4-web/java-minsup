package week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FunctionDevlop {
    public static void main(String[] args) {
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};

        int[] result = solution(progresses, speeds);
        System.out.println(Arrays.toString(result)); // 출력: [2, 1]
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        int n = progresses.length;
        int[] days = new int[n];

        // 1. 각 작업별 배포에 필요한 일수 계산
        for (int i = 0; i < n; i++) {
            int remaining = 100 - progresses[i];
            days[i] = (remaining % speeds[i] == 0) ? (remaining / speeds[i]) : (remaining / speeds[i] + 1);
        }

        List<Integer> list = new ArrayList<>();
        
        // 2. 첫 번째 기능이 배포되는 날을 기준일로 설정
        int standard = days[0]; 
        int count = 1; // 첫 번째 기능 포함 1부터 시작

        for (int i = 1; i < n; i++) {
            // 뒤의 기능이 앞선 기능(maxDay)보다 일찍 끝나거나 같은 날 끝나면 같이 배포
            if (days[i] <= standard) {
                count++;
            } else {
                // 앞선 기능보다 더 오래 걸리는 기능을 만나면 기존 그룹 배포 확정
                list.add(count);
                standard = days[i]; // 새로운 배포 기준일로 갱신
                count = 1;        // 새 그룹 카운트 초기화
            }
        }
        //if문으로 끝까지 갔을 수도 있잖아, 그리고 다시 시작했는데 뒤에 비교대상이 없으면 1이기때문
        // 마지막 배포 그룹 추가
        list.add(count);

        // 3. List -> int[] 변환
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}
