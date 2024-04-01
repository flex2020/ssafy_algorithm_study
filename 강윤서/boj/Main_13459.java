package 강윤서.boj;
import java.io.*;
import java.util.*;

public class Main_13459 {
    static int N, M, answer = Integer.MAX_VALUE, top = -1;
    static char[][] board;
    static int[] order;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int i=0; i<N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j=0; j<M; j++) {
                board[i][j] = input[j];
            }
        }
        for (int i=1; i<=10; i++) {
            order = new int[i];
            top = -1;
            perm(0, i);
        }
        if (answer == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(1);

    }
    public static void perm(int cnt, int goal) {
        if (cnt == goal) {
            int result = rotate();
            if (result > 0) {
                answer = Math.min(answer, result);
            }
            return ;
        }
        for (int i=0; i<4; i++) {
//        	System.out.println("top: " + top + " i: " + i);
        	if (top == i) continue;
        	if (top%2 == 0 && top+1 == i) continue;
        	if (top%2 == 1 && top-1 == i) continue;
        	top = i;
    		order[cnt] = i;
            perm(cnt+1, goal);
            top = -1;
        }
    }
    public static int rotate() { // return 값만큼 움직여 R이 구멍에 빠짐
//        System.out.println(Arrays.toString(order));
        int move = 0;
        char[][] map = new char[N][M];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                map[i][j] = board[i][j];
            }
        }
        // order 순으로 회전해보기
        for (int cnt=0; cnt<order.length; cnt++) {
            move++;
            if (move > answer) return -1;
            int dir = order[cnt];
            if (dir == 0) { // 상
                for (int j=1; j<M-1; j++) { // 테두리를 제외한 안의 내부에서 위로 밀기
                    Deque<Character> DQ = new ArrayDeque<>();
                    for (int i=N-1; i>=0; i--) {
                        if (map[i][j] == 'R' || map[i][j] == 'B') DQ.offerLast(map[i][j]);
                        else if (map[i][j] == '#') { // 장애물
                            for (int k=i+1; k<N; k++) {
                                if (map[k][j] == '#' || map[k][j] == 'O') break;
                                if (!DQ.isEmpty()) {
                                    map[k][j] = DQ.pollLast();
                                } else {
                                    map[k][j] = '.';
                                }
                            }
                        } else if (map[i][j] == 'O') {
                            if (!DQ.isEmpty()) {
                                char top = DQ.pollLast();
                                if (top == 'R') {
                                    if (!DQ.isEmpty()) return -1;
                                    return move;
                                }
                                else return -1;
                            }
                        }
                    }
                }
            } else if (dir == 1) { // 하
                for (int j=1; j<M-1; j++) { // 테두리를 제외한 안의 내부에서 위로 밀기
                    Deque<Character> DQ = new ArrayDeque<>();
                    for (int i=1; i<N; i++) {
                        if (map[i][j] == 'R' || map[i][j] == 'B') DQ.offerLast(map[i][j]);
                        else if (map[i][j] == '#') { // 장애물
                            for (int k=i-1; k>=0; k--) {
                                if (map[k][j] == '#' || map[k][j] == 'O') break;
                                if (!DQ.isEmpty()) {
                                    map[k][j] = DQ.pollLast();
                                } else {
                                    map[k][j] = '.';
                                }
                            }
                        } else if (map[i][j] == 'O') {
                            if (!DQ.isEmpty()) {
                                char top = DQ.pollLast();
                                if (top == 'R') {
                                    if (!DQ.isEmpty()) return -1;
                                    return move;
                                }
                                else return -1;
                            }
                        }
                    }
                }
            } else if (dir == 2) { // 좌
                for (int i=1; i<N-1; i++) {
                    Deque<Character> DQ = new ArrayDeque<>();
                    for (int j=M-1; j>=0; j--) {
                        if (map[i][j] == 'R' || map[i][j] == 'B') DQ.offerLast(map[i][j]);
                        else if (map[i][j] == '#') { // 장애물
                            for (int k=j+1; k<M; k++) {
                                if (map[i][k] == '#' || map[i][k] == 'O') break;
                                if (!DQ.isEmpty()) {
                                    map[i][k] = DQ.pollLast();
                                } else {
                                    map[i][k] = '.';
                                }
                            }
                        } else if (map[i][j] == 'O') {
                            if (!DQ.isEmpty()) {
                                char top = DQ.pollLast();
                                if (top == 'R') {
                                    if (!DQ.isEmpty()) return -1;
                                    return move;
                                }
                                else return -1;
                            }
                        }
                    }
                }
            } else { // 우
                for (int i=1; i<N-1; i++) {
                    Deque<Character> DQ = new ArrayDeque<>();
                    for (int j=1; j<M; j++) {
                        if (map[i][j] == 'R' || map[i][j] == 'B') DQ.offerLast(map[i][j]);
                        else if (map[i][j] == '#') { // 장애물
                            for (int k=j-1; k>=0; k--) {
                                if (map[i][k] == '#' || map[i][k] == 'O') break;
                                if (!DQ.isEmpty()) {
                                    map[i][k] = DQ.pollLast();
                                } else {
                                    map[i][k] = '.';
                                }
                            }
                        } else if (map[i][j] == 'O') {
                            if (!DQ.isEmpty()) {
                                char top = DQ.pollLast();
                                if (top == 'R') {
                                    if (!DQ.isEmpty()) return -1;
                                    return move;
                                }
                                else return -1;
                            }
                        }
                    }
                }
            }
//            print(map);
        }
        return -1;
    }
    public static void print(char[][] map) {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}