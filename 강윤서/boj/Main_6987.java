package 강윤서.boj;

import java.util.*;
import java.io.*;

public class Main_6987 {
    static boolean result;
	static int[][] board, tmp, order;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        order = new int[15][2];
        int index = 0; // order 배열에 저장하기 위한 인덱스
        for (int i=0; i<5; i++) {
            for (int j=i+1; j<6; j++) {
                order[index][0] = i;
                order[index][1] = j;
                index++;
            }
        }
		for (int tc=0; tc<4; tc++) { // 4가지 테케
            result = false;
			st = new StringTokenizer(br.readLine());
			board = new int[6][3];
            tmp = new int[6][3];
			for (int i=0; i<6; i++) {
				board[i][0] = Integer.parseInt(st.nextToken());
				board[i][1] = Integer.parseInt(st.nextToken());
				board[i][2] = Integer.parseInt(st.nextToken());
			}
            dfs(0);
			if (result) {
                sb.append(1 + " ");
			} else {
                sb.append(0 + " ");
			}
		}
        System.out.println(sb);
	}
	public static void dfs(int cnt) {
        if (cnt == 15) { // 모든 경우의 수 고려
            if (check()) {
                result = true;
            }
            return ;
        }

        int team1 = order[cnt][0], team2 = order[cnt][1];
        // case1 : team1이 team2를 이김
        tmp[team1][0]++;
        tmp[team2][2]++;
        dfs(cnt+1);
        tmp[team1][0]--;
        tmp[team2][2]--;
        // case 2: team1과 team2가 비김
        tmp[team1][1]++;
        tmp[team2][1]++;
        dfs(cnt+1);
        tmp[team1][1]--;
        tmp[team2][1]--;
        // case 3: team1이 teamp2에게 짐
        tmp[team1][2]++;
        tmp[team2][0]++;
        dfs(cnt+1);
        tmp[team1][2]--;
        tmp[team2][0]--;
	}
    public static boolean check() {
        for (int i=0; i<6; i++) {
            for (int j=0; j<3; j++) {
                if (board[i][j] != tmp[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
