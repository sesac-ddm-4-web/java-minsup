static void main() {
        int k = 80;
        int[][] dungeons = {{80,20}, {50,40}, {30,10}};
        System.out.println(solution(k,dungeons));
    }
    static int solution(int k, int[][] dungeons){
        max = 0;
        dfs(k,dungeons,new boolean[dungeons.length],0);
        return max;
    }
    static void dfs(int k, int[][] dungeons, boolean[] visited,int count){
        max = Math.max(max,count);
        for(int i=0; i<dungeons.length; i++){
            if(!visited[i] && k >= dungeons[i][0]){
                visited[i] = true;
                dfs(k-dungeons[i][1], dungeons, visited,count + 1);
                visited[i] = false;
            }
        }
    }
