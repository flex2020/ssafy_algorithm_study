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
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int test_case = 1; test_case <= T; test_case++)
        {

            char[][] charArr = new char[5][]; 
            int maxlen = 0; // 가장 긴 가로문자열 길이
            sb = new StringBuilder();

            for(int i=0;i<charArr.length;i++){
                String input = br.readLine();
                String[] strArr = input.split("");
                if(strArr.length > maxlen){ // maxlen 설정
                    maxlen = strArr.length;
                }
                charArr[i] = new char[strArr.length];
                for(int j=0;j<strArr.length;j++){ // 넣기
                    charArr[i][j]=strArr[j].charAt(0);
                }
            }

            for(int x=0, y=0; y<maxlen;){
                if(y<charArr[x].length){ // 해당 가로문자열의 길이가 작을 경우에만 문자열에 포함
                    sb.append(charArr[x][y]);
                }
                x++; // 공통적으로 x는 하나씩 내린다.
                if(x==charArr.length) { // x가 맨 아래까지 내려가면 y를 하나 올리고 x를 초기화한다.
                    x=0;
                    y++;
                }
            }
            
            System.out.println("#" + test_case + " " + sb.toString());

        }
    }
}
