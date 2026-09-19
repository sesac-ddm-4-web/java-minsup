import java.util.Arrays;
import java.util.Scanner;

public class Water {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] change = {10, -10, 10, -10, 10, -10, 10, -10, 10, -10};
        int storage;
        int usage;
        System.out.println("Storage: ");
        storage = sc.nextInt();
        System.out.println("usage: ");
        usage = sc.nextInt();
        System.out.println(Arrays.toString(change));
        System.out.println("결과: " + solution(storage, usage, change));
        sc.close();
    }

        public static int solution(int storage, int usage, int[] change) {
            int total_usage = 0;
            for(int i=0; i<change.length; i++){
                // 물 사용량 변동량
                // 매달 물 사용량은 소수점 이하를 버린 정수로 계산
                // usage = (int) (usage * (1 + change[i] / 100.0));
                usage = usage * change[i] / 100;
                // total 사용량에 더하기
                total_usage += usage;
                // total 사용량이 더 많으면 n개월 리턴
                if(total_usage > storage){
                    return i;
                }
            }
            return -1;
        }

}

import java.util.Arrays;
import java.util.Scanner;

public class Water {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] change = {10, -10, 10, -10, 10, -10, 10, -10, 10, -10};
        int storage;
        int usage;
        System.out.println("Storage: ");
        storage = sc.nextInt();
        System.out.println("usage: ");
        usage = sc.nextInt();
        System.out.println(Arrays.toString(change));
        System.out.println("결과: " + solution(change,storage,usage));
        sc.close();
    }

    // 저장량에 사용량 뺄셈
    // 매달 물 사용량을 정확하게 나타내고 싶어서 double
    static int solution(int[] change, int storage, int usage){
        double a = storage;
        double b = usage;

            for(int i=0; i<change.length; i++){
                b = b * (change[i] + 100.0) / 100;

                if(b > a){
                    return i;
                }
                a -= b;
            }
            return -1;
    }
}
