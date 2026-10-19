import java.util.*;

public class ex01 {
    static void main() {
        int n = 6;
        int[][] edges = {{3,6},{4,3},{3,2},{1,3},{1,2},{2,4},{5,2}};
        System.out.println(solution2(n, edges));
    }
    public static int solution(int n, int[][] edges) {
        int answer = 0;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n+1];
        Queue<Integer> queue = new LinkedList<>();
        visited[1] = true;
        queue.offer(1);
        while(!queue.isEmpty()) {
            int size = queue.size();
            answer = size;
            for(int i = 0; i < size; i++) {
                int cur = queue.poll();
                for(int next : graph.get(cur)) {
                    if(!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }
        return answer;
    }
    public static int solution2(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        Map<Integer, Integer> distMap = new HashMap<>();   // key: 노드, value: 최단 거리
        Queue<Integer> queue = new LinkedList<>();

        distMap.put(1, 0);
        queue.offer(1);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph.get(cur)) {
                if (!distMap.containsKey(next)) {                 // 아직 방문하지 않은 노드
                    distMap.put(next, distMap.get(cur) + 1);      // 현재 노드 거리 + 1
                    queue.offer(next);
                }
            }
        }

        int max = Collections.max(distMap.values());

        int answer = 0;
        for (int d : distMap.values()) {
            if (d == max) answer++;
        }
        return answer;
    }
}
