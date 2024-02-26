import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static class Task{
		int point; // 점수
		int remain; // 남은 분
		
		public Task(int point, int remain) {
			super();
			this.point = point;
			this.remain = remain;
		}
		@Override
		public String toString() {
			return "Task [point=" + point + ", remain=" + remain + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		
		Deque<Task> dq = new ArrayDeque<>();
		Task task = null;
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			
			if(task==null) { // 아무것도 없으면 넣어주기
				task = dq.pollLast();
			}
			
			switch(type) {
				case 0: // 0이면 나가기
					break;
				case 1: // 1이면 새로운 최신 업무가 들어왔으므로 task 업데이트
					int A = Integer.parseInt(st.nextToken());
					int T = Integer.parseInt(st.nextToken());
					
					if(task!=null) dq.offerLast(task);
					task = new Task(A, T);
					break;
			}
//			System.out.println(task);
			if(task==null) continue; // task가 null이면 현재 진행중인 업무가 없으므로 continue;
			task.remain--; // 현재 진행중인 task 남은시간 줄이기
			if(task.remain==0) { // remain이 0이면 다 한거니까 점수 추가하고 task 초기화
				answer+=task.point;
				task=null;
			}
		}
		
		System.out.println(answer);
	}

}
