public static void main(String[] args) {
        int[] nums = {2,2,3,-3};
        int k = 2;
        int answer = subarraySum2(nums,k);
        System.out.println(answer);
    }
    public static int subarraySum(int[] nums, int k){
        // 정답 개수
        int count = 0;
        int[] sums = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            sums[i] = nums[i];
            if(sums[i] == k){
                count++;
            }
            for(int j = i + 1; j < nums.length; j++){
                sums[i] += nums[j];
                if(sums[i] == k){
                    count++;
                }
            }
        }
        return count;
    }
    public static int subarraySum2(int[] nums, int k) {
        int answer = 0;
        // sumCount는 부분배열의 합 key가 되고 해당 합이 몇 번 나오는 지가 value
        Map<Integer, Integer> sumCount = new HashMap<Integer, Integer>();
        sumCount.put(0, 1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // nums[i] = prefix[i+1]
            sum += nums[i];
            // k = prefix[i+1] - prefix[j]
            // k = sum - prefix[j]
            // sum - k = prefix[j]
            answer += sumCount.getOrDefault(sum - k, 0);
            sumCount.put(sum, sumCount.getOrDefault(sum, 0) + 1);
        }
        return answer;
    }
