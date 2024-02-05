import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] chemicals = new int[N][2];
		int answer=0;

		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			chemicals[i][0]=Integer.parseInt(st.nextToken());
			chemicals[i][1]=Integer.parseInt(st.nextToken());
		}

		Arrays.sort(chemicals, (o1, o2) -> {return o1[0]-o2[0];});
		int start = chemicals[0][0];
		int end = chemicals[0][1];

		for(int i=1;i<N;i++){
			if(chemicals[i][1]<=end){ // 안에 완전히 포함된다는 의미이다.
				start = chemicals[i][0];
				end = chemicals[i][1];
			}else{
				if(chemicals[i][0]>end){ // 완전히 분리된 공간인 경우
					answer++;
					start = chemicals[i][0];
					end = chemicals[i][1];
				}else{ // 겹치는 부분이 존재하는 경우
					start = chemicals[i][0];
				}
			}
		}
		answer++;

		System.out.println(answer);
	}
}
