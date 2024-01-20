import java.util.Scanner;
import java.util.Arrays;

class SolutionBaekjoon16435 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int L = sc.nextInt();
        int[] heights = new int[N];

        for (int i = 0; i < N; i++) {
            heights[i] = sc.nextInt();
        }
        Arrays.sort(heights);

        for (int height : heights) {
            if (height <= L) {
                L++;
            } else {
                break;
            }
        }

        System.out.println(L);
    }
}