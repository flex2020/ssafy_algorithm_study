import java.util.*;
import java.awt.Point;

class Solution {

    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상

    static int[][] map;

    static int N;
    static int end;

    static class Robot{
        int x, y, order, num;

        Robot(int x, int y, int order, int num){
            this.x = x;
            this.y = y;
            this.order = order;
            this.num = num;
        }
    }

    public static int solution(int[][] points, int[][] routes) {
        int answer = 0;
        N = points.length;
        end = routes[0].length;
        int cnt = 2;

        map = new int [101][101];
        int[][] countMap = new int[101][101];

        Deque<Robot> dq = new ArrayDeque<>();
//        for(int i=0;i<N;i++){
//            map[points[i][0]][points[i][1]]=1;
//        }

        for(int i=0;i<routes.length;i++){
            Robot r = new Robot(points[routes[i][0]-1][0], points[routes[i][0]-1][1], 1, i);
            dq.offerLast(r);
            if(map[r.x][r.y]==1 && countMap[r.x][r.y]!=1){
                countMap[r.x][r.y]=1;
                answer++;
            }
            map[r.x][r.y] = 1;
        }

        while(!dq.isEmpty()){
            int size = dq.size();
            for(int s=0;s<size;s++){
                Robot r = dq.pollFirst();
                int target = routes[r.num][r.order]-1;
                int nx = r.x;
                int ny = r.y;
                if(r.x > points[target][0]){ // 상으로 움직여야 하는 경우
                    nx += dx[3];
                    ny += dy[3];
                }else if(r.x < points[target][0]){ // 하로 움직여야 하는 경우
                    nx += dx[1];
                    ny += dy[1];
                }else{ // 좌우로 움직여야 하는 경우
                    if(r.y > points[target][1]){ // 좌로 움직여야 하는 경우
                        nx += dx[2];
                        ny += dy[2];
                    }else if(r.y < points[target][1]){ // 우로 움직여야 하는 경우
                        nx += dx[0];
                        ny += dy[0];
                    }
                }

                if(map[nx][ny]==cnt && countMap[nx][ny]!=cnt){
                    countMap[nx][ny]=cnt;
                    answer++;
                }
                map[nx][ny] = cnt;
                if(nx==points[target][0] && ny==points[target][1]){
                    if(r.order+1==end) continue;
                    dq.offerLast(new Robot(nx, ny, r.order+1, r.num));
                }else{
                    dq.offerLast(new Robot(nx, ny, r.order, r.num));
                }
            }
//            print();
            cnt++;
        }

        return answer;
    }

    private static void print(){
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
