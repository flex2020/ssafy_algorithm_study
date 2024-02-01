import java.util.*;
import java.io.*;

public class SolutionBaekJoon10158 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());  // 가로 길이
		int H = Integer.parseInt(st.nextToken());  // 세로 길이
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());  // 현재 가로 좌표
		int y = Integer.parseInt(st.nextToken());  // 현재 세로 좌표
		
		int T = Integer.parseInt(br.readLine());  // 시간
		
		x = W - Math.abs(W - (x + T) % (W * 2));
        y = H - Math.abs(H - (y + T) % (H * 2));
		
		System.out.println(x + " " + y);
	}

}