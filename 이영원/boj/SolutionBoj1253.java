import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int target=N-1;
        int left=0;
        int right=N-1;
        int answer=0;

        while(target>=0){
            while(left<right){
                if(left==target){
                    left++;
                    continue;
                }else if(right==target){
                    right--;
                    continue;
                }

                if(arr[left]+arr[right]==arr[target]){
                    answer++;
                    break;
                }else if(arr[left]+arr[right] < arr[target]){
                    left++;
                }else{
                    right--;
                }
            }
            target--;
            left=0;
            right=N-1;
        }

        System.out.println(answer);

    }
}
