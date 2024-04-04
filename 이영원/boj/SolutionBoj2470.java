import java.util.*;
import java.io.*;

public class Main {
	
	// 투우우 포인터어어
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	int N = Integer.parseInt(br.readLine());
    	
    	int[] liquids = new int[N];
    	
    	st = new StringTokenizer(br.readLine());
    	PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)-> {
    		return Math.abs(a)-Math.abs(b);
    	});
    	
    	for (int i = 0; i < liquids.length; i++) {
			liquids[i] = Integer.parseInt(st.nextToken());
			pq.offer(liquids[i]);
    	}
    	
    	Arrays.sort(liquids);
    	
    	int answer = Integer.MAX_VALUE;
    	int start=0;
    	int end = liquids.length-1;
    	
    	int[] arr = new int[2];
    	
    	while(start<end) {
    	// 저장된 값보다 작은 경우 진행 업데이트
    		if(Math.abs(liquids[start]+liquids[end]) < Math.abs(answer)) {
    			answer=liquids[start]+liquids[end];
    			arr[0]=liquids[start];
    			arr[1]=liquids[end];
    		}
			if(liquids[start]+liquids[end]>0) { // 0보다 크면 end 내림
				end--;
			}else if(liquids[start]+liquids[end]<0) { // 0보다 작으면 start 올림
				start++;
			}
			if(answer==0) break;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(arr[0]).append(" ").append(arr[1]);
    	
    	System.out.println(sb);
    	
    }
}
