public class ex01 {
    static void main() {
        int m = 4;
        int n = 3;
        int[][] puddles = {{2,2}};
        System.out.println(solution(m, n, puddles));
    }
    static int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        boolean[][] visited = new boolean[n][m];
        for(int[] puddle : puddles) {
            visited[puddle[1]-1][puddle[0]-1] = true;
        }
        int[][] dp = new int[n][m];
        dp[0][0] = 1;
        for(int i = 1; i < m; i++) {
            if(visited[0][i]){
                break;
            }
            dp[0][i] = 1;
        }
        for(int i = 1; i < n; i++) {
            if(visited[i][0]){
                break;
            }
            dp[i][0] = 1;
        }
        int num = 1000000007;
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                if(visited[i][j]){
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = (dp[i-1][j] % num + dp[i][j-1] % num) % num;
            }
        }
        answer = dp[n-1][m-1];
        return answer;
    }
}
