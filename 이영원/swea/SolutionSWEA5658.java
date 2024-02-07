import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

		for(int test_case = 1; test_case <= T; test_case++)
		{
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            String input = br.readLine();
            int[] arr = new int[input.length()*2]; // 옮기는 배열
            int[] nums = new int[(N/4)*4]; // 숫자들을 저장하는 배열(Set 쓸거면 굳이 필요는 없지만 귀찮아서 놔둠)
            
            int start=input.length(); // arr 시작 인덱스
            int end=input.length()*2-1; // arr 끝 인덱스
            int numSize = 0;
            
            Set<Integer> s = new HashSet<>(N/4*4); // 중복제거를 위한 set
            
            for(int i=start, j=0;i<input.length()*2;i++, j++){ // 입력
                arr[i]=Integer.parseInt(input.charAt(j)+"", 16);
            }
            
            for(int i=0;i<N/4;i++){ // 총 rotate 수
                for(int j=0;j<4;j++){ // 사각형 각 면
                    for(int k=0;k<N/4;k++){ // 한 면에 있는 숫자들 더해주기
                        //System.out.println(Math.pow(16,N/4-1-k));
                        //System.out.println(arr[start+(N/4)*j+k]);
                        //System.out.println(Arrays.toString(arr));
                        //System.out.println(Math.pow(16, N/4-1-k)*arr[start+(N/4)*j+k]);
                    	nums[numSize]+=Math.pow(16,N/4-1-k)*arr[start+(N/4)*j+k];
                	}
                    //System.out.println(Arrays.toString(nums));
                    s.add(nums[numSize++]);
                }
                arr[--start]=arr[end--];
            }

      // set을 배열로 만들고 정렬하기
            Integer[] answers = s.toArray(new Integer[0]); 
            Arrays.sort(answers);
            System.out.println(answers[answers.length-K]);
            


		}

	}
}
