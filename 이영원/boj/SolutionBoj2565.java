import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	N = Integer.parseInt(br.readLine());
    	
    	int[][] cables = new int[N][2];
    	
    	for (int i = 0; i < cables.length; i++) {
			st = new StringTokenizer(br.readLine());
			cables[i][0] = Integer.parseInt(st.nextToken());
			cables[i][1] = Integer.parseInt(st.nextToken());
		}
    	
    	int[] dp = new int[N+1];
    	
    	Arrays.sort(cables, (a,b) -> {
    		return a[0]-b[0];
    	});
    	
    	// LIS with binarySearch(더 공부하기)
    	int len = 0;
    	for (int i = 0; i < cables.length; i++) {
    		int num = binarySearch(cables[i][1], 0, len, dp);
    		if(num>len) {
    			len++;
    		}
			dp[num]=cables[i][1];
//    		System.out.println(Arrays.toString(dp));
		}
    	
    	System.out.println(N-len);
//    	StringBuilder sb = new StringBuilder();
//    	int idx=1;
//    	for (int i = 0; i < cables.length; i++) {
//			if(dp[idx]==cables[i][1]) {
//				idx++;
//				continue;
//			}
//			sb.append(cables[i][0]).append("\n");
//		}
//    	System.out.println(sb);
    }
    
    // 만들어놓고 왜 되는지 모르겠누
    private static int binarySearch(int key, int low, int high, int[] dp) {
    	int mid=0;
    	
    	while(low<=high) {
    		mid = (low+high)/2;
    		
    		if(key < dp[mid]) {
    			high=mid-1;
    		}else {
    			low = mid+1;
    		}
    	}
    	return low;
    }
}
