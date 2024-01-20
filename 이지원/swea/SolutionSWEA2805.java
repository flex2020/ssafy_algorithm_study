import java.util.Scanner;

class SolutionSWEA2805 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int testCase = 1; testCase <= T; testCase++) {
            int N = sc.nextInt();
            char[][] map = new char[N][N];
            for (int i = 0; i < N; i++) {
                map[i] = sc.next().toCharArray();
            }

            int startIndex = N / 2;
            int size = 1;
            int result = 0;
            for (int i = 0; i < N; i++) {
                for (int j = startIndex; j < startIndex + size; j++) {
                    result += (int)(map[i][j] - '0');
                }

                if (i < N / 2) {
                    startIndex--;
                    size += 2;
                } else {
                    startIndex++;
                    size -= 2;
                }
            }

            System.out.println("#" + testCase + " " + result);
        }
    }
}