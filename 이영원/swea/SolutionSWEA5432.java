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

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int answer=0;
            int cnt = 0;
            String input = br.readLine();
            for(int i=0;i<input.length()-1;i++){
                char c = input.charAt(i);
                char nextC = input.charAt(i+1);
                if(c=='(' && nextC==')'){ // 레이저가 오는 경우, 그 레이저를 지나는 막대 수만큼 더하기
                    answer+=cnt;
                    i++; // 레이저 다음부터 다시 확인하기 위해 i++를 추가로 해주기
                }else if(c=='('){ // 새로운 막대가 오는 것이므로 막대 수 추가
                    cnt++;
                }else if(c==')'){ // 막대 하나가 닫히는 것이므로 해당 막대의 남는 부분을 추가
                    answer++;
                    cnt--;
                }
            }
						// 마지막에 레이저가 오지 않는 경우를 제외하고는 무조건 하나의 막대가 남으므로 추가해준다.
            if(!(input.charAt(input.length()-2)=='(' && input.charAt(input.length()-1)==')')) {
                answer++;
            }
            
            
            System.out.println("#" + test_case + " " + answer);
		}
	}
}
