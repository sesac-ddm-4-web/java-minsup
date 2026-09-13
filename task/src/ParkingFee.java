import java.util.*;

public class ParkingFee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("주차 시간을 입력하세요: ");
        int fee = sc.nextInt();
        if(fee <= 30){
            System.out.println("주차 요금: 0원");
        } else if (fee >= 230) {
            System.out.println("주차 요금: 10000원");
        } else{
            System.out.println("주차 요금: " + ((fee - 30) / 10) * 500 + "원");
        }
    }
}
