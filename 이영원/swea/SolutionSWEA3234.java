import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Solution
{
    
    static int answer;
    static int[] weights; // 입력받은 무게추 배열
    static boolean[] checkPerm; // 순열 체크 배열
    static int[] sel; // 순서가 정해진 순열이 들어간 배열
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            answer=0;
            int N = Integer.parseInt(br.readLine());
            weights = new int[N];
            checkPerm = new boolean[N];
            sel = new int[N];
            st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++){
                weights[i] = Integer.parseInt(st.nextToken());
            }
            
            perm(0);
            
            System.out.println("#" + test_case + " " + answer);
            
		}
	}
    
    // N! 순서 정하기
    private static void perm(int cnt){
        // basis part
        if(cnt==weights.length){
            //System.out.println(Arrays.toString(sel));
            // N!으로 순서가 정해진다면 거기서 부분집합을 선택한다.
            powerSet(0, 0, 0, sel);
        }
        // inductive part
        for(int i=0;i<weights.length;i++){
            if(!checkPerm[i]){
                checkPerm[i]=true;
                sel[cnt]=weights[i];
                perm(cnt+1);
                checkPerm[i]=false;
            }
        }
    }
    
    private static void powerSet(int idx, int left, int right, int[] sel){
        // basis part(answer 올려주고 리턴)
        if(idx==weights.length){
            //System.out.println("idx = " + idx + " left = " + left + " right = " + right);
            answer++;
            return;
        }
        
        // inductive part(오른쪽, 왼쪽 중 하나에 들어가야한다)
		if(left >= right+sel[idx]){ // 오른쪽에 들어갔을 때 왼쪽보다 커지는지 확인하고 안커진다면 담기
            powerSet(idx+1, left, right+sel[idx], sel);
        }
        // 왼쪽에 들어가는건 아무 상관 없으므로 무조건 시행
        powerSet(idx+1, left+sel[idx], right, sel);
        
    }
}
