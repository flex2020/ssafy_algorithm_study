import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        //System.setIn(new FileInputStream("res/input.txt"));
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int answer=0;
            int cnt=0;
            int p = sc.nextInt();
            int q = sc.nextInt();

            int repeat=0;
            int rec=1;

            int[] parr = numToCord(p); // p를 좌표로 변환
            int[] qarr = numToCord(q); // q를 좌표로 변환

            int addX = parr[0]+qarr[0];
            int addY = parr[1]+qarr[1];

            answer = CordToNum(parr[2], parr[3], addX, addY); // 계산좌표를 값을 변환

            System.out.println("#"+test_case+" "+answer);
        }
    }

    static int[] numToCord(int num){
        int repeat=0; // 반복수열 수
        int rec=1; // 반복 대각선 수

        while(true){
            repeat+=rec;
            if(repeat>=num) break;
            rec++;
        }

        return new int[]{rec-(repeat-num), 1+(repeat-num), rec, repeat}; // x, y, rec, repeat
    }

    static int CordToNum(int rec, int repeat, int x, int y){
        int cnt=x+y-1;
        rec++;
        while(rec<=cnt){
            repeat+=rec;
            rec++;
        }

        return repeat-(cnt-x);
    }
}