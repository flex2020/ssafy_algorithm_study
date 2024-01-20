import java.util.Scanner;
 
class Position {
    int x;
    int y;
 
    Position (int x, int y) {
        this.x = x;
        this.y = y;
    }
}
 
class SolutionSWEA8382 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
 
        for (int testCase = 1; testCase <= T; testCase++) {
            Position position1 = new Position(sc.nextInt(), sc.nextInt());
            Position position2 = new Position(sc.nextInt(), sc.nextInt());
 
            int xDifference = Math.abs(position1.x - position2.x);
            int yDifference = Math.abs(position1.y - position2.y);
 
            int minDifference;
            int maxDifference;
            if (xDifference > yDifference) {
                maxDifference = xDifference;
                minDifference = yDifference;
            } else {
                maxDifference = yDifference;
                minDifference = xDifference;
            }
 
            int result = minDifference * 2;
            for (int i = 0; i < maxDifference - minDifference; i++) {
                if (i % 2 == 0) {
                    result++;
                } else {
                    result += 3;
                }
            }
 
            System.out.println("#" + testCase + " " + result);
        }
    }
}