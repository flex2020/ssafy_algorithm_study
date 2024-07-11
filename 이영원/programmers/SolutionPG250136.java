import java.util.*;
import java.awt.Point;

class Solution {

    static int N, M; // 크기 그냥 static으로 만들었음

    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상

    static int[][] paintMap; // 넘버링맵
    static boolean[][] visited; // 방문체크

    static List<Integer> list; // 몇갠지 저장할 리스트

    public int solution(int[][] land) {
        int answer = 0;

        N = land.length;
        M = land[0].length;

        paintMap = new int[N][M]; // 넘버링맵 크기
        visited = new boolean[N][M]; // 방문체크배열

        list = new ArrayList<>(); // 넘버링 맵의 크기를 넣을 리스트
        list.add(0); // 1부터하니까 0은 0으로 채워주기

        int num=1; // 넘버링 숫자

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                // 방문 안하고 석유가 있는경우 bfs해서 넘버링 진행
                if(!visited[i][j] && land[i][j]==1){
                    bfs(num++, i, j, land);
                }
            }
        }

        // 세로로 봤을 때 뭐가 들어갔는지를 볼 map
        Map<Integer, Boolean> map = new HashMap<>();

        int result = 0; // answer랑 비교할 result

        for(int i=0;i<M;i++){
            // 옆에 세로로 넘어갈 때마다 result와 map 초기화
            result=0;
            map = new HashMap<>();
            for(int j=0;j<N;j++){
                // 석유인데 map에 안들어가있는 놈인 경우 map에 추가
                if(land[j][i]==1 && !map.containsKey(paintMap[j][i])){
                    map.put(paintMap[j][i], true);
                }
            }

            // map에 들어간 keyset을 반복으로 돌면서 result에 넣기
            for(int key : map.keySet()){
                result+=list.get(key);
            }

            // answer와 비교하면 된다.
            answer = Math.max(result, answer);
        }


        return answer;
    }

    // bfs돌면서 넘버링하고 그 크기가 몇인지를 구한 다음에 list에 넣기
    private static void bfs(int num, int x, int y, int[][] land){
        int cnt=0;
        Deque<Point> dq = new ArrayDeque<>();
        dq.offerLast(new Point(x, y));
        visited[x][y] = true;
        paintMap[x][y] = num;
        cnt++;
        while(!dq.isEmpty()){
            Point p = dq.pollFirst();
            for(int i=0;i<dx.length;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx<0 || nx>=N || ny<0 || ny>=M || visited[nx][ny] || land[nx][ny]==0) continue;
                visited[nx][ny] = true;
                cnt++;
                dq.offerLast(new Point(nx, ny));
                paintMap[nx][ny]=num;
            }
        }

        list.add(cnt);
    }
}
