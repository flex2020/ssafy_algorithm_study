package 강윤서.boj;
import java.io.*;
import java.util.*;
public class Main_2295 {
	static int N, answer;
	static int[] A;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		for (int i=0; i<N; i++) {
			A[i] = Integer.parseInt(br.readLine());
			
		}
		Arrays.sort(A);
		L: for (int i=N-1; i>=0; i--) { // 가장 큰 수를 만들어보자
			int goal = A[i]; // 만들어야 하는 합
			for (int j=0; j<i; j++) {
				int start = j;
				int end = i;
				while (start <= end) {
					if (A[j] + A[start] + A[end] == goal) {
						answer = goal;
						break L;
					}
					if (A[j] + A[start] + A[end] > goal) {
						end-=1;
					} else {
						start+=1;
					}
				}
			}
		}
		
		System.out.println(answer);
	}
}
