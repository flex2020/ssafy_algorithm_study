import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// 비트마스킹, BufferedWritier 등을 이용했다면 시간을 좀더 단축가능했을지도?
class Solution
{
    // 우 하 좌 상
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static char[][] map;
    static boolean[][] visited;
    static boolean[] myungmool;
    static int answer;
    static int R;
    static int C;
    static int size;
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            answer=0;
            size=1;
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            
            map = new char[R+2][C+2];
            visited = new boolean[R+2][C+2];
            myungmool=new boolean['Z'-'A'+1];
            
            Arrays.fill(map[0], '?');
            Arrays.fill(map[map.length-1], '?');
            
            for(int i=1;i<map.length-1;i++){
                String input = br.readLine();
                for(int j=1;j<map[i].length-1;j++){
					map[i][j]=input.charAt(j-1);
                }
                map[i][0]='?';
                map[i][map.length-1]='?';
            }
            
            myungmool[map[1][1]-'A']=true;
            answer=dfs(1, 1, 1);
			
            System.out.println("#" + test_case + " " + answer);
		}
	}
    
    private static int dfs(int x, int y, int cnt){
        int result=cnt;
        // basis part
        // inductive part
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(map[nx][ny]!='?' && !visited[nx][ny] && !myungmool[map[nx][ny]-'A']){
                visited[nx][ny]=true;
                myungmool[map[nx][ny]-'A']=true;
                result = Math.max(result, dfs(nx,ny, cnt+1));
                visited[nx][ny]=false;
                myungmool[map[nx][ny]-'A']=false;
            }
        }
        return result;
    }
}
