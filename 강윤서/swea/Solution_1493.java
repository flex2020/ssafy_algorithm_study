package 강윤서.swea;

import java.util.*;
class Solution_1493 {
    private static int T;
    static int[] p = new int[2];
    static int[][] position = new int[500][500];
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++)
		{
            p[0] = sc.nextInt();
            p[1] = sc.nextInt();
            Arrays.sort(p);
            int[] nextP = new int[2];
            for (int i=0; i<2; i++) {
                int[] result = make(p[i]);
                nextP[0] += result[0] - result[1] + 1;
                nextP[1] += result[1];
            }
            int nextRow = nextP[0]+nextP[1];
            int answer = (nextRow-1) * (nextRow-2) / 2;
            for (int i=0; i<nextP[1]; i++) {
                answer++;
            }
            System.out.printf("#%d %d\n", tc, answer);
	    }
    }
    public static int[] make(int idx) {
        int cnt = 1; // 좌표의 행 번호
        int number = 1; // 좌표의 번호
        while (number <= idx) {
            for (int s=1; s<=cnt; s++) { // 좌표의 열 번호
                position[cnt][s] = number;
                if (number == idx) {
                    return new int[] {cnt, s};
                }
                number++;
            }
            cnt++;
        }
        return null;
    }
}