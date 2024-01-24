import java.util.Scanner;

class SolutionSWEA1974 {

    public static final int SIZE = 9;
    public static final int SUM = 45;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int testCase = 1; testCase <= T; testCase++) {
            int[][] nums = new int[SIZE][SIZE];
            
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    nums[i][j] = sc.nextInt();
                }
            }

            int result = (validateLine(nums) && validateSquare(nums)) ? 1 : 0;
            System.out.println("#" + testCase + " " + result);
        }
    }

    public static boolean validateLine(int[][] nums) {
        int horizontalSum = 0;
        int verticalSum = 0;

        for (int i = 0; i < nums.length; i++) {
            horizontalSum = 0;
            verticalSum = 0;
            for (int j = 0; j < nums.length; j++) {
                horizontalSum += nums[i][j];
                verticalSum += nums[j][i];
            }

            if (horizontalSum != SUM || verticalSum != SUM) {
                return false;
            }
        }
        return true;
    }

    public static boolean validateSquare(int[][] nums) {
        int squareSum = 0;

        for (int startI = 0; startI <= nums.length - 3; startI += 3) {
            for (int startJ = 0; startJ <= nums.length - 3; startJ += 3) {
                squareSum = 0;
                for (int i = startI; i < startI + 3; i++) {
                    for (int j = startJ; j < startJ + 3; j++) {
                        squareSum += nums[i][j];
                    }
                }

                if (squareSum != SUM) {
                    return false;
                }
            }
        }

        return true;
    }
    
}