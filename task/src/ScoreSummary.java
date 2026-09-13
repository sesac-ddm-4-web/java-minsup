import java.util.*;

public class ScoreSummary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        //평균을 구하기 위한 합계
        int sum = 0;
        //최대값을 구하기 위한 변수
        int max = 0;
        //60점이상 통과 인원 카운트를 위한 변수
        int pass = 0;
        for(int i=0; i<N; i++){
            int n = sc.nextInt();
            if(n >= max){
                max = n;
            }
            if(n >= 60){
                pass++;
            }
            sum += n;
        }
        double average = (double) sum / N;
        System.out.printf("평균: %.1f%n", average);
        System.out.println("최고점: " + max);
        System.out.println("합격 인원: " + pass);
    }
}
