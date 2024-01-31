import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int switchNum = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] switches = new int[switchNum+1];
        for (int i = 1; i < switches.length; i++) {
			switches[i]=Integer.parseInt(st.nextToken());
		}
        
        int N = Integer.parseInt(br.readLine());
        
        for(int i=0;i<N;i++) {
        	st = new StringTokenizer(br.readLine());
        	int gender = Integer.parseInt(st.nextToken());
        	int number = Integer.parseInt(st.nextToken());
        	
        	if(gender==1) manSwitch(switches, number);
        	else if(gender==2) womanSwitch(switches, number);
        }
        
        for(int i=1;i<switches.length;i++) {
        	System.out.print(switches[i] + " ");
        	// if(i%20!=0) System.out.print(" ");
        	if(i%20==0) System.out.println();
        }
		
	}
	
	public static void manSwitch(int[] switches, int number) {
		for(int i=number;i<switches.length;i+=number) {
			change(switches, i);
		}
	}
	
	public static void womanSwitch(int[] switches, int number) {
		change(switches, number);
		for(int i=1;(number+i<switches.length && number-i>=1);i++) {
			if(switches[number+i]==switches[number-i]) {
				change(switches, number+i);
				change(switches, number-i);
			}else {
				break;
			}
		}
	}
	
	public static void change(int[] switches, int num) {
		if(switches[num]==0) switches[num]=1;
		else if(switches[num]==1) switches[num]=0;
	}
}
