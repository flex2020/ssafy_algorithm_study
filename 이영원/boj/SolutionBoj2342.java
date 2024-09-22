import java.io.*;
import java.util.*;

public class Main {

    static int size; // 입력받은 지시 사항의 길이
    static List<Integer> list; // 지시 사항을 저장할 리스트
    static int answer; // 결과적으로 최소 힘을 저장할 변수

    // 움직임에 따른 힘을 저장한 2D 배열 (move[a][b]는 위치 a에서 b로 움직일 때 필요한 힘)
    static int[][] move = {
            {0, 2, 2, 2, 2}, // 중앙에서 각 방향으로 이동할 때 필요한 힘
            {2, 1, 3, 4, 3}, // 1번에서 각 위치로 이동할 때 필요한 힘
            {2, 3, 1, 3, 4}, // 2번에서 각 위치로 이동할 때 필요한 힘
            {2, 4, 3, 1, 3}, // 3번에서 각 위치로 이동할 때 필요한 힘
            {2, 3, 4, 3, 1}  // 4번에서 각 위치로 이동할 때 필요한 힘
    };

    static int[][][] dp; // dp 배열: dp[turn][left][right]는 현재 차례(turn)에서 왼발이 left, 오른발이 right에 있을 때 필요한 최소 힘

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        list = new ArrayList<>(); // 지시 사항을 저장할 리스트 초기화
        dp = new int[100001][5][5]; // 최대 100,000개의 지시사항과 5개의 위치를 고려한 dp 배열 초기화

        // 입력을 처리하며 0이 나오면 종료
        while(true){
            int num = Integer.parseInt(st.nextToken());
            if(num == 0) break; // 0이 입력되면 종료
            list.add(num); // 리스트에 지시사항 추가
        }

        size = list.size(); // 지시 사항의 총 길이 저장

        answer = dfs(0, 0, 0); // 0번 위치에서 시작하는 dfs 호출

        System.out.println(answer); // 결과 출력
    }

    // 깊이 우선 탐색(DFS)을 통해 최소 힘을 계산하는 함수
    // left: 왼발의 현재 위치, right: 오른발의 현재 위치, turn: 현재 몇 번째 지시 사항을 처리 중인지
    private static int dfs(int left, int right, int turn){
        if(turn == size) return 0; // 마지막 지시 사항까지 모두 처리했으면 0 반환 (더 이상 이동할 필요 없음)

        if(dp[turn][left][right] != 0) return dp[turn][left][right]; // 이미 계산된 값이 있으면 그 값을 반환 (중복 계산 방지)

        int num = list.get(turn); // 현재 처리할 지시 사항 (이동할 방향)

        // 왼발을 이동시키는 경우와 오른발을 이동시키는 경우에 대한 최소 힘 계산
        int curLeft = dfs(num, right, turn + 1) + move[left][num]; // 왼발을 이동시키고 다음 지시 사항 처리
        int curRight = dfs(left, num, turn + 1) + move[right][num]; // 오른발을 이동시키고 다음 지시 사항 처리

        // 왼발을 움직이는 경우와 오른발을 움직이는 경우 중 더 적은 힘이 드는 선택을 dp 배열에 저장
        dp[turn][left][right] = Math.min(curLeft, curRight);

        return dp[turn][left][right]; // 최소 힘을 반환
    }

    // 디버깅을 위한 dp 배열 출력 함수
    private static void print(){
        for (int t = 0; t < size; t++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    System.out.print(dp[t][i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

}
