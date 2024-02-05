import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Command{
	int idx;
	int num;
	ArrayList<Integer> list;
	Command(int idx, int num, ArrayList<Integer> list){
		this.idx = idx;
		this.num = num;
		this.list = list;
	}
	@Override
	public String toString() {
		return "Command [idx=" + idx + ", num=" + num + ", arr=" + list + "]";
	}
	
	
}

class Solution
{
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		for(int test_case = 1; test_case <= 10; test_case++)
		{
			Command[] cmds;
            StringBuilder sb = new StringBuilder();
			int N = Integer.parseInt(br.readLine());
            Integer[] original = new Integer[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){ // 원본 암호문
                original[i] = Integer.parseInt(st.nextToken());
            }
            
            ArrayList<Integer> answer = new ArrayList<>(Arrays.asList(original));
            
            int cmdN = Integer.parseInt(br.readLine());
            cmds = new Command[cmdN];
            st = new StringTokenizer(br.readLine(), "I");
            for(int i=0;i<cmdN;i++) { // 입력
            	StringTokenizer st1 = new StringTokenizer(st.nextToken());
            	int idx = Integer.parseInt(st1.nextToken());
            	int num = Integer.parseInt(st1.nextToken());
            	Integer[] arr = new Integer[num];
            	for(int j=0;j<num;j++) {
            		arr[j]=Integer.parseInt(st1.nextToken());
            	}
            	cmds[i] = new Command(idx, num, new ArrayList<>(Arrays.asList(arr)));
            	
            }
            
            for(int i=0;i<cmdN;i++) {
                answer.addAll(cmds[i].idx, cmds[i].list);
            }
            
            sb.append("#").append(test_case).append(" ");
            for(int i=0;i<10;i++) {
            	sb.append(answer.get(i)).append(" ");
            }
//            System.out.println(Arrays.toString(cmds));
            System.out.println(sb);
            
            
		}
	}
}
