import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[] cmds = new int[N];
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
        	if(Math.abs(o1)==Math.abs(o2)) {
        		return o1-o2;
        	}
        	return Math.abs(o1)-Math.abs(o2);
        });
        
        for(int i=0;i<cmds.length;i++) {
        	cmds[i] = Integer.parseInt(br.readLine());
        }
        
        for(int i=0;i<cmds.length;i++) {
        	if(cmds[i]==0) {
        		if(pq.isEmpty()) System.out.println(0);
        		else System.out.println(pq.poll());
        	}else {
        		pq.add(cmds[i]);
        	}
        }

	}
}
