public static int[] solution3(String[] gems){
        int need = new HashSet<>(Arrays.asList(gems)).size();
        Map<String, Integer> window = new HashMap<>();
        int bestlen = Integer.MAX_VALUE;
        // 기준이 되는 window 선언
        int left = 0;
        int bestStart = 0;
        for(int right = left; right <= gems.length-1; right++){
            window.merge(gems[right], 1, Integer::sum);
            while(window.size() == need){
                int len = right - left + 1;
                if(len < bestlen){
                    bestlen = len;
                    bestStart = left + 1;
                }
                String g = gems[left++];
                // 합산 0이면 빼야함, 안 빼면 음수로 계속 더해짐
                if(window.merge(g,-1, Integer::sum) == 0){
                    window.remove(g);
                }
            }
        }
        return  new int[]{bestStart, bestlen + bestStart - 1};
    }
