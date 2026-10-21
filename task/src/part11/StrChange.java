public class StrChange {
    static void main() {
        String str = "aabbaccc";
        int answer = solution(str);
        System.out.println(answer);
    }
    static int solution(String s){
        int answer = 0;

        int len = s.length();
        int maxCut = len/2;

        int[] lenArr = new int[maxCut+1];
        Arrays.fill(lenArr,len);

        for(int i=1; i<=maxCut; i++){
            int cur = 0;
           for(int j=cur; j<=len-i; j++){
               String target = s.substring(j, j + i);
               int count = 1;
               int k = j + i;

               while(k + i <= len && target.equals(s.substring(k, k + i))){
                   count++;
                   k += i;
               }

               if(count > 1){
                   int digits = String.valueOf(count).length();
                   lenArr[i] -= count * i - (digits + i);
                   cur = k;
               }else{
                   // 문자열 앞에서부터 i만큼 자른다.
                   cur += i;
               }
           }
       }

       Arrays.sort(lenArr);
       answer = lenArr[0];
        return  answer;
    }
}
