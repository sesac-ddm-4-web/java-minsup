public class ex01 {
    static void main() {
        String[] park = {"SOO","OOO","OOO"};
        String[] routes = {"E 2","S 2","W 1"};
        int[] answer = solution(park, routes);
        System.out.println(answer[0] + " " + answer[1]);
    }
    static int[] solution(String[] park, String[] routes) {
        int[] pos = new int[2];
        boolean[][] xpos = new boolean[park.length][park[0].length()];

        for(int i = 0; i < park.length; i++){
            for(int j = 0; j < park[i].length(); j++){
                if(park[i].charAt(j) == 'S'){
                    pos[0] = i;
                    pos[1] = j;
                }
                if(park[i].charAt(j) == 'X'){
                    xpos[i][j] = true;
                }
            }
        }

        for(int i = 0; i < routes.length; i++){
            String direction = routes[i].substring(0, 1);
            String shift2 = routes[i].substring(2);
            Integer shift = Integer.parseInt(shift2);

            boolean check = true;
            if(direction.equals("N")){
                // 공원을 벗어나는 지
                if(pos[0] - shift >= 0 ) {
                    // 장애물이 있는 지
                    for(int j = 1; j <= shift; j++){
                        if(xpos[pos[0]-j][pos[1]]){
                            check = false;
                        }
                    }
                    if(check){
                        pos[0] = pos[0] - shift;
                    }
                }
            } else if (direction.equals("E")) {
                if(pos[1] + shift <= park[0].length() - 1){
                    for(int j = 1; j <= shift; j++){
                        if(xpos[pos[0]][pos[1]+j]){
                            check = false;
                        }
                    }
                    if(check){
                        pos[1] = pos[1] + shift;
                    }
                }
            }else if(direction.equals("W")){
                if(pos[1] - shift >= 0){
                    for(int j = 1; j <= shift; j++){
                        if(xpos[pos[0]][pos[1]-j]){
                            check = false;
                        }
                    }
                    if(check){
                        pos[1] = pos[1] - shift;
                    }
                }
            }else{
                if(pos[0] + shift <= park.length - 1){
                    for(int j = 1; j <= shift; j++){
                        if(xpos[pos[0]+j][pos[1]]){
                            check = false;
                        }
                    }
                    if(check){
                        pos[0] = pos[0] + shift;
                    }
                }
            }
        }
        return pos;
    }
}
