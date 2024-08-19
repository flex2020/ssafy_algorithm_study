import java.util.*;
class Solution {
    static int[][] board; // 1: 기둥, 2: 보: 3: 기둥+보
    
    public int[][] solution(int n, int[][] build_frame) {
        
        board = new int[n+2][n+2];
        
        for (int[] frame : build_frame) {
            work(n, frame);
        }
        
        PriorityQueue<int[]> PQ = new PriorityQueue<>((p1, p2) -> p1[0] == p2[0] ? p1[1] == p2[1] ? p1[2] - p2[2] : p1[1] - p2[1] : p1[0] - p2[0]);
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board.length; j++) {
                if (board[i][j] == 1) { // 기둥
                    PQ.offer(new int[] {i, j, 0});
                } else if (board[i][j] == 2) { // 보
                    PQ.offer(new int[] {i, j, 1});
                } else if (board[i][j] == 3) { // 기둥 + 보
                    PQ.offer(new int[] {i, j, 0});
                    PQ.offer(new int[] {i, j, 1});
                }
            }
        }
        
        List<int[]> result = new ArrayList<>();
        while (!PQ.isEmpty()) {
            result.add(PQ.poll());
        }
        
        int[][] answer = new int[result.size()][3];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        // print();
        
        return answer;
    }
    public static void print() {
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board.length; j++) {
                System.out.print(board[j][i] + " ");
            }
            System.out.println();
        }
    }
    // 해당 작업을 수행해보기
    public static void work(int n, int[] frame) {
        // 맵 복사
        int[][] temp = new int[n+2][n+2];
        for (int i=0; i<temp.length; i++) {
            for (int j=0; j<temp.length; j++) {
                temp[i][j] = board[i][j];
            }
        }
        
        // 기둥 - 보 설치 or 삭제 수행
        if (frame[3] == 1) { // 설치
            temp[frame[0]][frame[1]] += frame[2]+1;
        } else { // 삭제
            temp[frame[0]][frame[1]] -= frame[2]+1;
        }
        
        // 가능하면 board 에 반영
        if (canDo(temp)) {
            board[frame[0]][frame[1]] = temp[frame[0]][frame[1]];
        }
    }
    // 작업을 수행한 후 가능한지 판단
    public static boolean canDo(int[][] temp) {
        for (int i=0; i<temp.length; i++) { // 가로
            for (int j=0; j<temp.length; j++) { // 세로
                if (temp[i][j] == 1 || temp[i][j] == 3) { // 기둥
                    boolean flag = false;
                    if (j == 0) flag = true; // 바닥과 연결
                    if (j>=1 && (temp[i][j-1] == 1 || temp[i][j-1] == 3)) flag = true; // 위아래로 기둥과 연결
                    if (i >= 1 && temp[i-1][j] >= 2) flag = true; // 왼쪽에 보 연결
                    if (temp[i][j] >= 2) flag = true; // 오른쪽에 보 연결
                    if (!flag) return false;
                }
                
                if (temp[i][j] == 2 || temp[i][j] == 3) { // 보
                    boolean flag = false;
                    if (j >= 1 && temp[i][j-1] % 2 == 1) flag = true; // 왼쪽 끝이 기둥과 연결 (1 or 3)
                    if (i+1<board.length && j >= 1 && temp[i+1][j-1] % 2 == 1) flag = true; // 오른쪽 끝이 기둥과 연결 (1 or 3)
                    if (i >= 1 && temp[i-1][j] >= 2 && i+1<temp.length && temp[i+1][j] >= 2) flag = true; // 양쪽 끝이 보와 연결
                    if (!flag) return false;
                }
            }
        }
        return true;
    }
}
