import java.util.*;
import java.io.*;

class Top {
	int number;
	int height;
	
	Top(int number, int height) {
		this.number = number;
		this.height = height;
	}
}

public class SolutionBaekJoon2493 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Top> stack = new Stack<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken());
			
			if (stack.isEmpty()) {  // 현재 탑을 기준으로 왼쪽에 탑이 없는 경우
				sb.append("0 ");
			} else {  // 현재 탑을 기준으로 왼쪽에 탑이 있는 경우
				while (!stack.isEmpty()) {
					Top lastTop = stack.peek();
					if (lastTop.height < height) {  // 현재 탑보다 탑 높이가 낮은 경우
						stack.pop();
					} else {  // 현재 탑보다 탑 높이가 높거나 같은 경우
						sb.append(Integer.toString(lastTop.number) + " ");
						break;
					}
				}
				
				if (stack.isEmpty()) {  // 현재 탑 기준으로 왼쪽에 탑을 다 살펴본 경우
					sb.append("0 ");
				}
			}
			stack.push(new Top(i + 1, height));  // 다음 탑과 비교하기 위해 현재 탑 저장
		}
		
		System.out.println(sb);
	}

}