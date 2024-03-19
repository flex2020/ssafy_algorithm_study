import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Solution {

    static int[][] map, color;
    static List<Point> whiteList, blackList;
    static boolean[][] pointArr;
    static int whiteSize, blackSize, answer, N;
    static int[] dx = {1, 1}; // 오른아래 왼아래
    static int[] dy = {1, -1}; // 오른아래 왼아래

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        int startN=0;
        color = new int[N][N];
        whiteList = new ArrayList<>();
        blackList = new ArrayList<>();


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            startN=(startN+1)%2;
            int n=startN;
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                color[i][j]=(n++)%2; // 흑백 색칠하기
                if(map[i][j]==1) { // 흑백에 따라 whiteList, blackList에 넣기
                    if(color[i][j]==1){
                        whiteList.add(new Point(i, j));
                    }else{
                        blackList.add(new Point(i, j));
                    }
                }
            }
        }
        int result=0;

        whiteSize = whiteList.size();
        blackSize = blackList.size();
        pointArr = new boolean[N][N];

        // 백일때 진행
        dfs(0, 0, whiteSize, new int[N][N], whiteSize, whiteList);
        result=answer;
        answer=0;
        // 흑일때 진행
        dfs(0, 0, blackSize, new int[N][N], blackSize, blackList);

        System.out.println(result+answer);

    }

    // i : 현재 인덱스, cnt : 현재까지 배치한 비숍 수, remain : 비숍 배치할 수 있는 남는 자리 수, visited : 봤는지 안봤는지 확인하는 방문배열
    // size : list 사이즈, list : list
    private static void dfs(int i, int cnt, int remain, int[][] visited, int size, List<Point> list){

        if(remain+cnt <= answer) return; // 남는거 더 해봤자 의미없을때 리턴

        if(i==size){
            answer = Math.max(answer, cnt);
            return;
        }

        Point p = list.get(i); // 점 하나하나 확인
        if(visited[p.x][p.y]==0) { // 한번도 체크되지 않은경우 진행
            // 범위 칠하면서 안되는 자리 지우면서 remain 업데이트
            remain=paint(p, visited, 1, remain);
            dfs(i+1, cnt+1, remain, visited, size, list);
            // 범위 칠한거 원복하면서 remain 업데이트
            remain=paint(p, visited, -1, remain);
        }
        // 해당 점을 선택하지 않고 다음 점으로 진행
        dfs(i+1, cnt, remain-1, visited, size, list);

    }

    // visited 칠하고 remain(가능한 남는 자리) 업데이트
    private static int paint(Point p, int[][] visited, int num, int remain){
        int result = remain;
        visited[p.x][p.y]+=num;
        pointArr[p.x][p.y]=true;
        if(num==1) result--;
        else result++;

        // 점이 왼 위에서 오른 아래로 저장되어 있으므로 왼아래, 오른아래만 확인하면 된다.
        for (int i = 0; i < 2; i++) {
            int nx = p.x;
            int ny = p.y;
            while(true){
                nx += dx[i];
                ny += dy[i];
                if(nx>=N || nx<0 || ny>=N || ny<0) break;
                visited[nx][ny]+=num;
                if(map[nx][ny]==1){
                    if(visited[nx][ny]!=0 && !pointArr[nx][ny]) {
                        pointArr[nx][ny]=true;
                        result--;
                    }else{
                        pointArr[nx][ny]=false;
                        result++;
                    }
                }
            }
        }
        return result;
    }
}
