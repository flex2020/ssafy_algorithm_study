import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static class Jewel{
		int M, V; // 무게, 가격

		public Jewel(int m, int v) {
			super();
			M = m;
			V = v;
		}

		@Override
		public String toString() {
			return "Jewel [M=" + M + ", V=" + V + "]";
		}
		
		
	}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 보석 개수
        int K = Integer.parseInt(st.nextToken()); // 가방 개수
        
        PriorityQueue<Jewel> jewelPQ = new PriorityQueue<>((a, b) -> {
        	return a.M-b.M;
        });
        
        PriorityQueue<Jewel> jewelPQ2 = new PriorityQueue<>((a, b) -> {
        	return b.V-a.V;
        });
        
        PriorityQueue<Integer> bagPQ = new PriorityQueue<>((a, b) -> {
        	return a-b;
        });
        
        for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jewelPQ.offer(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
        
        for (int i = 0; i < K; i++) {
			bagPQ.offer(Integer.parseInt(br.readLine()));
		}
        
        long answer=0;
        
        while(!bagPQ.isEmpty()) {
        	int weight = bagPQ.poll();
        	while(!jewelPQ.isEmpty()) {
        		Jewel j = jewelPQ.poll();
        		if(j.M > weight) {
        			jewelPQ.offer(j);
            		break;
        		}
        		jewelPQ2.offer(j);
        	}
			Jewel j = jewelPQ2.poll();
			if(j!=null) answer+=j.V;
//    		while(!jewelPQ2.isEmpty()) {
//    			jewelPQ.offer(jewelPQ2.poll());
//    		}
        	
//        	if(jewelPQ.size()==0) break;
        }
        System.out.println(answer);
    }
    
}
