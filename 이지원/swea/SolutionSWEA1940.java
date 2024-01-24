import java.util.Scanner;

public class SolutionSWEA1940 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int testCase = 1; testCase <= T; testCase++) {
            int N = sc.nextInt();

            int result = 0;
            int speed = 0;
            for (int i = 0; i < N; i++) {
                int command = sc.nextInt();
                switch (command) {
                    case 0:
                        result += speed;
                        break;
                    case 1:
                        speed += sc.nextInt();
                        result += speed;
                        break;
                    case 2:
                        speed -= sc.nextInt();
                        if (speed < 0) {
                            speed = 0;
                        }
                        result += speed;
                        break;
                }
            }

            System.out.println("#" + testCase + " " + result);
        }
    }
}