import java.util.Scanner;

public class Solution8382 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc=1; tc<=T; tc++) {
			int answer = 0;
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			
			int dx = Math.abs(x1 - x2);
			int dy = Math.abs(y1 - y2);
			int diff = Math.abs(dx - dy);
			if (diff % 2 == 1) {
				answer = Math.max(dx, dy) * 2 - 1;
			}
			else {
				answer = Math.max(dx, dy) * 2;
			}
			
			System.out.println("#" + tc + " " + answer);
		}
	}

}