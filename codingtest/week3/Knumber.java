package week3;

import java.util.Arrays;

public class Knumber {
    public static void main(String[] args) {
        int[] array = {1,5,2,6,3,7,4};
        int[][] commands = {{2,5,3},{4,4,1},{1,7,3}};
        int[] answer = Solution(array, commands);
        System.out.println(Arrays.toString(answer));
    }

    public static int[] Solution(int[] array, int[][] commands){
        int[] answer = new int[commands.length];
        for(int i=0; i< commands.length; i++){
            int start = commands[i][0]-1;
            int end = commands[i][1];
            int k = commands[i][2]-1;
            int[] temp = Arrays.copyOfRange(array,start,end);
            Arrays.sort(temp);
            answer[i] = temp[k];
        }
        return answer;
    }
}
