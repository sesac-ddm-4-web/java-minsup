package part04;

import java.util.Arrays;

public class ex01 {
    static void main() {
        int[] answers = {1,3,2,4,2};
        System.out.println(Arrays.toString(solution(answers)));
    }
    static int[] solution(int[] answers){
        int[] answer = new int[3];
        for(int i=0;i<answers.length;i++){
            if(answers[i] == getFirst(i)) answer[0]++;
            if(answers[i] == getSecond(i)) answer[1]++;
            if(answers[i] == getThird(i)) answer[2]++;
        }
        Arrays.sort(answer);
        return answer;
    }
    static int getFirst(int num){
        return (num % 5) + 1;
    }
    // 1,3,5,7
    // 0,1,2,3
    static int getSecond(int num){
        if(num % 2 == 0) return 2;
        int[] second = new int[]{1,3,4,5};
        return second[(num / 2) % 4];
    }
    static int getThird(int num){
        int[] third = new int[]{3,1,2,4,5};
        return third[(num % 10) / 2];
    }
}
