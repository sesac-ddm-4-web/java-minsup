package week3;

public class Bandage {
    public static void main(String[] args) {
        int[] bandage = {1,1,1};
        int health = 5;
        int[][] attacks = {{1,2}, {3,2}};
        int answer = solution(bandage,health,attacks);
        System.out.println(answer);
    }
    public static int solution(int[] bandage, int health, int[][] attacks){
        int attacktime;
        int anwer = 0;
        int healthcopy = health;
        for(int i=0; i< attacks.length; i++){
            // 공격시간
            if(i == 0) {
                attacktime = attacks[i][0];
            }else {
                attacktime = attacks[i][0] - attacks[i-1][0];
            }

            for(int j=1; j<attacktime; j++){
                // 공격시간이 다가오기 전 붕대감기
                health += bandage[1];
                // 시전시간이 되면 추가 회복량 더하기
                if(j == bandage[0]){
                    health += bandage[2];
                }
                //다 하고 나서 이 모든 과정의 합이 체력량을 넘으면 다시 체력량으로 초기화
                if(health > healthcopy) {
                    health = healthcopy;
                }
            }
            health -= attacks[i][1];
        }
        if(health == 0){
            anwer = -1;
        }else {
            anwer = health;
        }
        return anwer;
    }
}
