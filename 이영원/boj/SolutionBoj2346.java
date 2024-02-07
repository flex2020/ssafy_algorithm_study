import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        boolean[][] paper = new boolean[100][100];

        int answer=0;

        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int up = Integer.parseInt(st.nextToken());
            for(int x=0;x<10;x++){
                for(int y=0;y<10;y++){
                    if(!paper[left+x][up+y]){
                        answer++;
                        paper[left+x][up+y]=true;
                    }
                }
            }
        }

        System.out.println(answer);

    }
}
