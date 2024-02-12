package 강윤서.swea;

import java.util.*;
import java.io.*;
 
/*
 * [ 함수 호출 순서 ]
 * 1. checkAdjacent: 회전을 시작한 톱니바퀴에서 인접한 톱니바퀴 확인 (돌리지 않았고, 인접해있을 경우)
 * 2. canRotate: 현재 톱니바퀴와 인접한 톱니바퀴의 붙어있는 극을 확인하고 돌릴 수 있는지 아닌지 확인
 * 3. rotate: 실제로 돌림! 
 */
public class Solution_4013 {
    static int T, K, answer;
    static List<Deque<Integer>> list;
    static Queue<int[]> orderQ = new ArrayDeque<>();
    static Queue<int[]> rotateQ = new ArrayDeque<>();
    static int[][] canGo;
    static boolean[] visited, rotated;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        canGo = new int[5][5];
        canGo[1][2] = 1;
        canGo[2][1] = 1;
        canGo[2][3] = 1;
        canGo[3][2] = 1;
        canGo[3][4] = 1;
        canGo[4][3] = 1;
        T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            answer = 0;
            K = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            list.add(new ArrayDeque<>()); // 1부터 인덱스 맞춰주기 위해 빈 덱 넣어주기
            for (int i=0; i<4; i++) {
                st = new StringTokenizer(br.readLine());
                Deque<Integer> DQ = new ArrayDeque<>();
                for (int j=0; j<8; j++) {
                    DQ.offer(Integer.parseInt(st.nextToken()));
                }
                list.add(DQ);
            }
            for (int k=0; k<K; k++) {
                st = new StringTokenizer(br.readLine());
                int current = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());
                visited = new boolean[5];
                rotated = new boolean[5];
                visited[current] = true; // bfs 시작점 방문 체크
                orderQ = new ArrayDeque<>();
                rotateQ = new ArrayDeque<>();
                orderQ.offer(new int[] {current, direction});
                checkAdjacent();
                while (!rotateQ.isEmpty()) {
                    rotate(rotateQ.peek()[0], rotateQ.peek()[1]);
                    rotateQ.poll();
                }
            }
            for (int i=1; i<=4; i++) {
                if (list.get(i).pollFirst() == 1) {
                    answer += (int)Math.pow(2, i-1);
                }
            }
            System.out.printf("#%d %d\n", tc, answer);
        }
    }
    public static void checkAdjacent() {
        // dfs로 이 함수를 호출했다가 3 -> 2 -> 1 로 가며 3이 회전해버려서 3 -> 4에는 잘못된 값과 비교하게 되어 bfs로 변경
        while (!orderQ.isEmpty()) {
            int[] number = orderQ.poll();
            if (!rotated[number[0]]) {
                rotateQ.offer(new int[] {number[0], number[1]});
                rotated[number[0]] = true;
            }
            for (int i=1; i<=4; i++) {
                // 해당 톱니바퀴가 인접에 영향을 미치고 이를 체크하지 않았을 때
                if (number[0] != i && canGo[number[0]][i] == 1 && !visited[i]) {
                    canRotate(number[0], i, number[1]); // 돌릴 수 있는지 확인
                }
            }
        }
    }
    public static void canRotate(int current, int next, int direction) {
        visited[next] = true;
        if (current < next) { // next가 현재 값보다 오른쪽에 있으면 오른쪽으로 3번째 있는 값이 맞닿는 것
            for (int i=0; i<2; i++) {
                list.get(current).offerLast(list.get(current).pollFirst()); // 현재 톱니바퀴에서 next와 붙어있는 극값 확인
                list.get(next).offerFirst(list.get(next).pollLast()); // next 톱니바퀴가 현재 톱니바퀴와 붙어있는 극값 확인
            }
            int curM = list.get(current).peekFirst();
            int nextM = list.get(next).peekFirst();
            for (int i=0; i<2; i++) { // 원상태로 되돌리기
                list.get(current).offerFirst(list.get(current).pollLast());
                list.get(next).offerLast(list.get(next).pollFirst());
            }
            if (curM != nextM) { // 회전
                orderQ.offer(new int[] {next, -1*direction});
            }
        } else { // 다음 톱니바퀴가 왼쪽에 있으면 왼쪽으로 3번째 있는 값이 맞닿는 것
            for (int i=0; i<2; i++) {
                list.get(current).offerFirst(list.get(current).pollLast());
                list.get(next).offerLast(list.get(next).pollFirst());
            }
            int curM = list.get(current).peekFirst();
            int nextM = list.get(next).peekFirst();
            for (int i=0; i<2; i++) { // 원상태로 되돌리기
                list.get(current).offerLast(list.get(current).pollFirst());
                list.get(next).offerFirst(list.get(next).pollLast());
            }
            if (curM != nextM) { // 회전
                orderQ.offer(new int[] {next, -1*direction});
            }
        }
    }
    public static void rotate(int number, int direction) {
        if (direction == 1) { // 시계방향 회전
            list.get(number).offerFirst(list.get(number).pollLast());
        } else { // 반시계방향 회전
            list.get(number).offerLast(list.get(number).pollFirst());
        }
    }
}