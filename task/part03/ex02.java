public class Ex02 {
    public static void main(String[] args) {
        int[] nums = {2,2,3,-3};
        int k = 2;
        int answer = subarrySum(nums,k);
        System.out.println(answer);
    }
    public static int subarrySum(int[] nums, int k){
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
}
