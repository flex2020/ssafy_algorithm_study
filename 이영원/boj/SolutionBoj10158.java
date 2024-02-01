import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    StringBuilder sb = new StringBuilder();
	
	    st = new StringTokenizer(br.readLine());
	    int w = Integer.parseInt(st.nextToken()); // 가로
	    int h = Integer.parseInt(st.nextToken()); // 세로
	    st = new StringTokenizer(br.readLine());
	    int x = Integer.parseInt(st.nextToken()); // 개미 x
	    int y = Integer.parseInt(st.nextToken()); // 개미 y
	    int t = Integer.parseInt(br.readLine()); // 시간
	    
	    int nx = (t+x)%(w*2);
	    int ny = (t+y)%(h*2);
	    
	    if(nx>=w) {
	    	nx = w*2-nx;
	    }
	    if(ny>=h) {
	    	ny = h*2-ny;
	    }
	    
	    System.out.println(nx + " " + ny);
	}
}
