feat: [BOJ] 11729 하노이 탑 이동 순서
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        System.out.println((int)Math.pow(2, N)-1);
        hanoi(1, 3, 2, N);
        System.out.println(sb);
	}
	
	public static void hanoi(int start, int end, int mid, int num) {
		if(num==1) {
			sb.append(start);
			sb.append(" ");
			sb.append(end);
			sb.append("\n");
			return;
		}
		hanoi(start, mid, end, num-1);
		sb.append(start);
		sb.append(" ");
		sb.append(end);
		sb.append("\n");
		hanoi(mid, end, start, num-1);
	}
}

//규칙성이 있는것도 아니고
//처음 스타트가 무조건 1 3인것도 아니고
//
//1. 가장 크기가 큰 놈은 무조건 1 3의 시행만 진행한다.
//2. 첫시행이 무조건 1 3 인것은 아니다.
//
//메소드 파라미터로 뭘 줘야할까?
//함수 hanoi(int start, int end, int num);
//
//큰 그림으로는
//처음 : 1->2
//중간(가장 큰 놈) : 1->3
//마지막 : 2->3
//
//즉, start -> end라면
//start -> start도 end도 아닌 다른 곳
//start -> end
//start도 end도 아닌 다른 곳 -> end
//의 시행이 반복된다는 것을 의미한다.
//
//그렇다면 종료조건은?
//크기를 파라미터로 받아야하나?
