public class Parallel {
    public int solution(int[][] dots){

        //12-34
        if (getSlope(dots[0], dots[1]) == getSlope(dots[2], dots[3])) return 1;

        //13-24
        if (getSlope(dots[0], dots[2]) == getSlope(dots[1], dots[3])) return 1;

        //14-23
        if (getSlope(dots[0], dots[3]) == getSlope(dots[1], dots[2])) return 1;

        return 0;
    }
    // 두 점 사이의 기울기를 구하는 메서드 (Math.abs 사용 금지)
    private double getSlope(int[] p1, int[] p2) {
        return (double) (p2[1] - p1[1]) / (p2[0] - p1[0]);
    }
}
