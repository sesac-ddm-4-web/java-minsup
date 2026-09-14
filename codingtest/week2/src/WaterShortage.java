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
    static int solution(int[] change, int storage, int usage){
        double a = storage;
        double b = usage;

            for(int i=0; i<change.length; i++){
                b = b * (change[i] + 100.0) / 100;

                if(b > a){
                    return i+1;
                }
                a -= b;
            }
            return -1;
    }
}
