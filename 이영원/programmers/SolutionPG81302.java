import java.util.*;
import java.io.*;
import java.awt.Point;

class Solution {

    static int[] dx = new int[]{0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = new int[]{1, 0, -1, 0}; // 우 하 좌 상

    public int[] solution(String[][] places) {
        int[] answer = {1, 1, 1, 1, 1}; // 일단 다 성공으로 박아두기

        for(int i=0;i<places.length;i++){
            L: for(int j=0;j<places.length;j++){
                for(int k=0;k<places.length;k++){
                    if(places[i][j].charAt(k)=='P'){ // 앉은 사람이면 확인하기
                        if(!check(i, j, k, places)){ // boolean이 false면 바로 다음 대기실로 넘어가기
                            answer[i]=0;
                            break L;
                        }
                    }
                }
            }
        }

        return answer;
    }

    // bfs인데 그냥 check로 했음
    private static boolean check(int level, int x, int y, String[][] places){
        int cnt=0;
        Deque<Point> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[places.length][places.length];
        visited[x][y]=true;
        dq.offerLast(new Point(x, y));

        // 거리 2 이상은 볼 필요가 없으니까 반복문 탈출하기
        while(!dq.isEmpty() && cnt<2){
            int size = dq.size();
            for(int s=0;s<size;s++){
                Point p = dq.pollFirst();
                for(int i=0;i<dx.length;i++){
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];
                    // 범위를 벗어났거나 파티션인 경우 continue
                    if(nx<0 || nx>=places.length || ny<0 || ny>=places.length || visited[nx][ny] || places[level][nx].charAt(ny)=='X') continue;
                    if(places[level][nx].charAt(ny)=='P') { // 범위에는 있는데 'P'면 조건을 만족하지 못하는 것이므로 false 리턴
                        return false;
                    }
                    // 빈 테이블이므로 방문체크해주고 덱에 넣어주기
                    visited[nx][ny]=true; 
                    dq.offerLast(new Point(nx, ny));
                }
            }

            cnt++; // 이동횟수
        }

        // 걸린거 없으면 true 리턴
        return true;
    }
}
