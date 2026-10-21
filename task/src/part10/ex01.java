public class ex01 {
    public static void main(String[] args) {
        int n = 5;
        int[] lost = {2, 4};
        int[] reserve = {3};
        int answer = solution2(n, lost, reserve);
        System.out.println(answer);
    }
    static int solution(int n, int[] lost, int[] reserve){
        int answer = 0;

        int[] state = new int[n+2];
        for(int l : lost) state[l]--;
        for(int r : reserve) state[r]++;
        
        answer = n;
        for(int i = 1; i <= n; i++){
            if(state[i] == -1){
                if(state[i-1] == 1){
                    state[i-1] = 0;
                } else if (state[i+1] == 1) {
                    state[i+1] = 0;
                }else{
                    answer--;
                }
            }
        }
        return answer;
    }
    static int solution2(int n, int[] lost, int[] reserve) {
        boolean[] isLost = new boolean[n + 2];     // 체육복이 없는 학생
        boolean[] hasSpare = new boolean[n + 2];   // 빌려줄 수 있는 여벌이 있는 학생

        for (int l : lost) isLost[l] = true;
        for (int r : reserve) {
            if (isLost[r]) isLost[r] = false;      // 자기 여벌을 입음 → 빌려줄 여벌은 없음
            else hasSpare[r] = true;
        }

        int answer = n;
        for (int i = 1; i <= n; i++) {
            if (!isLost[i]) continue;
            if (hasSpare[i - 1]) {                 // 왼쪽 이웃 먼저
                hasSpare[i - 1] = false;
            } else if (hasSpare[i + 1]) {          // 없으면 오른쪽 이웃
                hasSpare[i + 1] = false;
            } else {
                answer--;                          // 빌리지 못함
            }
        }
        return answer;
    }
}
