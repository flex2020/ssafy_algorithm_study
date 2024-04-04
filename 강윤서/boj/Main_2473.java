package 강윤서.boj;
import java.io.*;
import java.util.*;
public class Main_2473 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        long[] arr = new long[N];
        for (int i=0; i<N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);
        List<Long> answer = new ArrayList<>();
        long value = Long.MAX_VALUE; // 세 용액의 합의 최소 절댓값
        for (int i=0; i<N-2; i++) { // 시작점
        	int start = i+1;
        	int end = N-1;
        	while (start < end) {
        		long sum = arr[i] + arr[start] + arr[end];
        		if (Math.abs(sum) < value) {
        			value = Math.abs(sum);
        			answer.clear();
        			answer.add(arr[i]);
        			answer.add(arr[start]);
        			answer.add(arr[end]);
        		}
        		if (sum <= 0) start += 1;
    			else end -= 1;
        	}
        	
        }
       Collections.sort(answer);
        for (long x : answer) {
        	System.out.print(x + " ");
        }
    }
}
