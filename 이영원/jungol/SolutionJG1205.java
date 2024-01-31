import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr=new int[N];
		int zoker=0; // 조커 개수 세기
		int answer=0;
		int result=0;
		
		for(int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
			if(arr[i]==0) zoker++;
		}
		
		Arrays.sort(arr);
		
		for (int i = zoker+1; i < arr.length; i++) {
			result=countNum(zoker, arr, i);
			answer=Math.max(answer, result);
		}
		if(zoker+1>=arr.length) {
			if(zoker+1==arr.length) answer++;
			answer+=zoker;
		}
		
		System.out.println(answer);
	}
	
	private static int countNum(int zCnt, int[] arr, int idx) {
		int result=0;
		while(idx!=arr.length) {
			if(arr[idx]==0 || arr[idx-1]==0) {
				idx++;
				continue;
			}
			if(arr[idx]-arr[idx-1]==1) {
				idx++;
				result++;
				if(idx==arr.length) result++;
				continue;
			}else if(arr[idx]-arr[idx-1]>0 && arr[idx]-arr[idx-1]-1<=zCnt) {
				result+=arr[idx]-arr[idx-1];
				zCnt-=arr[idx]-arr[idx-1]-1;
				if(idx==arr.length-1) result++;
			}else if(arr[idx]==arr[idx-1]){
				idx++;
				continue;
			}else {
				result+=zCnt+1;
				zCnt=0;
				break;
			}
			idx++;
		}
		if(result==0) result--;
		else if(zCnt!=0) result+=zCnt;
		return result;
	}
    
}	
