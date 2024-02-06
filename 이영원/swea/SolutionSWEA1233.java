import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int test_case = 1; test_case <= 10; test_case++)
		{
            int N = Integer.parseInt(br.readLine());
			char[] arr = new char[N+1];
            int answer=1;
            for(int i=1;i<arr.length;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken());
                char what = st.nextToken().charAt(0);
                arr[i] = what;
            }
            
            for(int i=1;i<arr.length;i++){
                if(i*2<arr.length && i*2+1<arr.length && isNum(arr[i])){
                    //System.out.println("1i = " + i + " arr[i] = " + arr[i]);
                    answer=0;
                    break;
                }else if((i*2>=arr.length || i*2+1>=arr.length) && !isNum(arr[i])){
                    //System.out.println("2i = " + i + " arr[i] = " + arr[i]);
                    answer=0;
                    break;
                }
            }
            
            System.out.println("#" + test_case + " " + answer);
		}
	}
    
    private static boolean isNum(char a){
        //System.out.println(a);
        if(a>='0' && a<='9') return true;
        else return false;
    }
}
