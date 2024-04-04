import java.util.*;
import java.io.*;

public class Main {
	
	// 투우우 포인터어어
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	int N = Integer.parseInt(br.readLine());
    	
    	long[] liquids = new long[N];
    	
    	st = new StringTokenizer(br.readLine());
    	
    	for (int i = 0; i < liquids.length; i++) {
			liquids[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	Arrays.sort(liquids);
    	
    	long answer = 4000000001L;
    	int start=0;
    	int end = liquids.length-1;
    	
    	long[] arr = new long[3];
    	
    	L: for (int i = 0; i < liquids.length-2; i++) {
        	start=0;
        	end=liquids.length-1;
        	while(start<end) {
        	// 그 자체면 패스
        		if(liquids[start] == liquids[i]) {
        			start++;
        			continue;
        		}
        		else if(liquids[end] == liquids[i]) {
        			end--;
        			continue;
        		}
//        		System.out.println(Arrays.toString(arr));
// 더 가까운 거면 업데이트
        		if(Math.abs(liquids[start]+liquids[end]+liquids[i]) < Math.abs(answer)) {
        			answer=liquids[start]+liquids[end]+liquids[i];
        			arr[0]=liquids[start];
        			arr[1]=liquids[end];
        			arr[2]=liquids[i];
        		}
    			if(liquids[start]+liquids[end]+liquids[i]>0L) {
    				end--;
    			}else if(liquids[start]+liquids[end]+liquids[i]<0L) {
    				start++;
    			}
    			if(answer==0L) break L;
        	}
		}
    	

    	Arrays.sort(arr); // 정렬
    	
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(arr[0]).append(" ").append(arr[1]).append(" ").append(arr[2]);
    	
    	System.out.println(sb);
    	
    }
}
