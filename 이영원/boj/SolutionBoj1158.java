import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    StringBuilder sb = new StringBuilder();
	    List<Integer> li = new ArrayList<>();
	    
	    int N = Integer.parseInt(st.nextToken());
	    int K = Integer.parseInt(st.nextToken());
	    int cnt=K-1;
	    int size=N-1;
	    
	    for(int i=0;i<N;i++) {
	    	li.add(i+1);
	    }
	    
	    sb.append("<").append(li.remove(cnt));
	    for(int i=0;i<N-1;i++) {
	    	cnt=(cnt+K-1)%size--;
//	    	System.out.println((cnt)%size--);
	    	sb.append(", ").append(li.remove(cnt));
	    }
	    sb.append(">");
	    
	    System.out.println(sb);
	}
}
