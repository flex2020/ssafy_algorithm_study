import java.util.Scanner;
import java.io.FileInputStream;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
 
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int answer=0;
            int x1=sc.nextInt();
            int y1=sc.nextInt();
            int x2=sc.nextInt();
            int y2=sc.nextInt();
            int disX = Math.abs(x2-x1);
            int disY = Math.abs(y2-y1);
            int minus = (disX+disY)/2;
             
            answer+=(minus*2) + Math.abs(disX-minus) + Math.abs(disY-minus);
 
            System.out.println("#" + test_case + " " + answer);
        }
    }
}
