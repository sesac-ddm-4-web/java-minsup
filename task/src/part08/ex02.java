public class ex02 {
    static void main() {
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        int n = 4;
        int k = 2;
        int answer = networkDelayTime(times, k, n);
        System.out.println(answer);
    }
    static int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        for (int[] e : times) graph.get(e[0]).add(new int[]{e[1], e[2]});

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(k);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int[] next : graph.get(cur)) {
                int newDist = dist[cur] + next[1];
                if (newDist < dist[next[0]]) {      // 더 짧은 경로 발견 → 갱신
                    dist[next[0]] = newDist;
                    queue.offer(next[0]);           // 다시 탐색해야 하므로 재삽입
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1;
            max = Math.max(max, dist[i]);
        }
        return max;
    }
}
