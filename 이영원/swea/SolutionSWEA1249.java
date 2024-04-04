import java.io.*;
import java.util.*;

class Solution
{
	
	static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
	static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상
	static final int INF = 50000000;
    
    static class Node{
        int x, y, weight;

		public Node(int x, int y, int weight) {
			super();
			this.x = x;
			this.y = y;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", weight=" + weight + "]";
		}
        
    }
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int answer=0;
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			
			for (int i = 0; i < map.length; i++) {
				String input = br.readLine();
				for (int j = 0; j < map.length; j++) {
					map[i][j] = Integer.parseInt(input.charAt(j)+"");
				}
			}
			
//			print(map);
			
			int[][] table = new int[N][N];
			
			for (int i = 0; i < table.length; i++) {
				Arrays.fill(table[i], INF);
			}
			
//			print(table);
			
            PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            	return a.weight - b.weight;
            });
            
            pq.offer(new Node(0, 0, 0));
            
            while(!pq.isEmpty()) {
            	Node n = pq.poll();
            	
            	if(table[n.x][n.y]!=INF) continue;
            	
            	table[n.x][n.y]=n.weight;
            	
            	if(n.x==N-1 && n.y==N-1) break;
            	
            	for (int i = 0; i < 4; i++) {
					int nx = n.x + dx[i];
					int ny = n.y + dy[i];
					if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
					int cost = table[n.x][n.y] + map[nx][ny];
					if(cost < table[nx][ny]) {
						pq.offer(new Node(nx, ny, cost));
					}
				}
            	
            	
            }
            
//            print(table);
            
			System.out.println("#" + test_case + " " + table[N-1][N-1]);
		}
	}
	
	private static void print(int[][] map) {
		for(int i=0;i<map.length;i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
