import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;

class Solution
{
    
    static List<Point> points;
    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상
    static boolean[][] visited;
    static int[][] map;
    static int answer;
    static int connectAnswer;
    static int N;
    static int size;
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            answer=Integer.MAX_VALUE;
            connectAnswer = 0;
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];
            points = new ArrayList<>();
            
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(i>=1 && i<N-1 && j>=1 && j<N-1 && map[i][j]==1){
                        points.add(new Point(i, j));
                    }
                }
            }
            size = points.size(); // 외곽을 제외한 코어 수
//            System.out.println(points);
            
            for(int i=0;i<4;i++){ // 첫번째 걸로부터 4방향 반복
                recursive(0, i, 0, 0);
            }
            
            System.out.println("#" + test_case + " " + answer);

		}
	}
    
	// cnt = 현재까지 확인한 코어 수, dir = 현재 연결될 방향, result = 현재까지 연결한 전선 길이, connectNum = 현재 연결된 코어 수
    private static void recursive(int cnt, int dir, int result, int connectNum) throws Exception{

        // basis part
        if(cnt==size){ // 모든 코어를 탐색해봤다면 끝
//        	System.out.println(result);
        	if(connectNum >= connectAnswer) { // 연결 코어수가 현재 저장된 answer의 연결 수보다 크거나 같을경우 비교
        		if(connectNum > connectAnswer) answer=result; // result의 연결된 코어 수가 현재 answer의 연결 코어수보다 클 경우 answer를 그냥 업데이트
        		connectAnswer = connectNum;
        		answer = Math.min(answer, result); // 같을경우 최소치
//        		System.out.println(connectAnswer + " " + answer + " " + result);
        	}
            return;
        }
        Point p = points.get(cnt);
        int currentCnt=0;
        int nx = p.x;
        int ny = p.y;
        // inductive part
        while(true){ // dir 방향으로 벽을 만날때까지 진행
            nx += dx[dir];
            ny += dy[dir];
//        	print();	
//        	Thread.sleep(1000);
//            System.out.println(nx + " " + ny);
            if(nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny] && map[nx][ny]!=1){
                if(nx==0 || nx==N-1 || ny==0 || ny==N-1){
                	visited[nx][ny]=true;
                    for(int i=0;i<4;i++){
                    	// 더 연결해도 현재 최대 코어 연결수보다 낮을경우 제외
                    	if(connectAnswer > size-cnt+connectNum+1) continue;
                        recursive(cnt+1, i, result+currentCnt+1, connectNum+1);
                    }
                }else{ // 방문체크
                    currentCnt++;
                    visited[nx][ny]=true;
                }
            }else{ // visited 원복
            	int px = nx;
            	int py = ny;
                for(int i=0;i<currentCnt+1;i++){
                    px -= dx[dir];
            		py -= dy[dir];
                    visited[px][py]=false;
                }
                for(int i=0;i<4;i++){ // 코어를 연결하지 않고 다음 코어를 확인하는 경우
//                	System.out.println("하하 " + currentCnt);
                	// 더 연결해도 현재 최대 코어 연결수보다 낮을경우 제외
                	if(connectAnswer > size-cnt+connectNum) continue;
                    recursive(cnt+1, i, result, connectNum);
                }
//                System.out.println("원복실패");
                break;
            }
        }

    }
    
    private static void print() {
    	for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(visited[i][j]?1:0);
				System.out.print(" ");
			}
			System.out.println();
		}
    	System.out.println();
    }
}
