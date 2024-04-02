import java.io.*;
import java.util.*;

class Solution
{
    
    static int D, W, K; // 행, 열, 합격기준
    static int[][] map;
    static int answer;
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            
            map=new int[D][W];
            answer=0;
            
            for(int i=0;i<D;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<W;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            if(powerCheck(map)) {
            	answer=0;
            }else {
                for(int i=1;i<=D;i++){
                    if(powerSet(0, 0, new int[i])) break;
                }
            }
            
            System.out.println("#" + test_case + " " + answer);
		}
	}
    
	// 부분집합
    private static boolean powerSet(int cnt, int idx, int[] sel){
        // basis part
        if(cnt==sel.length){
        	// 부분집합 걸리면 맵 만들고 깊은복사하고 paintRecursive 진행. 무슨 색깔로 칠하든 거기는 무조건 바뀌기 때문에 여기서 깊은복사를 해준다.
    		int[][] tmpMap = new int[D][W];
        	for (int i = 0; i < D; i++) {
    			for (int j = 0; j < W; j++) {
    				tmpMap[i][j] = map[i][j];
    			}
    		}
            if(paintRecursive(0, new int[sel.length], sel, tmpMap)) {
            	answer=cnt;
            	return true;
            }
            return false;
        }
        
        // inductive part
        for(int i=idx;i<D;i++){
            sel[cnt]=i;
            if(powerSet(cnt+1, i+1, sel)) return true;
        }
        return false;
    }
    
    // 조건을 만족하는지 확인하는 메소드
    private static boolean powerCheck(int[][] map){
        for(int i=0;i<W;i++){
            int result=1;
            int prev = map[0][i];
            boolean flag=false;
            for(int j=1;j<D;j++){
//            	System.out.println(prev + " " + map[j][i]);
                if(prev==map[j][i]) result++;
                else{
                    prev = map[j][i];
                    result=1;
                }
                if(result==K){
                    flag=true;
                    break;
                }
            }
            if(!flag) return false;
//            System.out.println(flag);
        }
        return true;
    }
    
    // 어떤 색깔로 칠할건지 정하는 재귀메소드
    private static boolean paintRecursive(int cnt, int[] arr, int[] sel, int[][] map) {
    	// basis part
    	if(cnt==arr.length) {
    		// 어떤 색깔로 칠할지 정해지면 paint 메소드 실행
    		if(paint(arr, sel, map)) return true;
            //print(tmpMap);
    		return false;
    	}
    	
    	// inductive part
    	for (int i = 0; i < 2; i++) {
			arr[cnt]=i;
			if(paintRecursive(cnt+1, arr, sel, map)) return true;
		}
		return false;
	}

    // 실제로 원하는 자리에 원하는 색깔로 칠하는 메소드
	private static boolean paint(int[] arr, int[] sel, int[][] map){
    	
    	for (int i = 0; i < arr.length; i++) {
    		for(int j=0;j<W;j++) {
    			map[sel[i]][j] = arr[i];
    		}
		}
    	
    	if(powerCheck(map)) return true;
    	
    	return false;
    	
    }
    
	// 출력 테스트용 메소드
    private static void print(int[][] map) {
    	for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
    	System.out.println();
    }
    
}
