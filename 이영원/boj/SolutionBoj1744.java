import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> plusPQ = new PriorityQueue<>((a, b) -> {
            return b-a;
        });
        PriorityQueue<Integer> minusPQ = new PriorityQueue<>((a, b) -> {
            return a-b;
        });
        long answer = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num>0) plusPQ.offer(num);
            else minusPQ.offer(num);
        }

        // plus인거 더하기
        while(!plusPQ.isEmpty()){
            Integer a = plusPQ.poll();
            Integer b = plusPQ.poll();
            if(a==null || b==null) {
                if(a!=null) answer+=a;
                break;
            }

            if(a*b <= a+b) {
                answer+=a+b;
            }else{
                answer+=a*b;
            }
        }

        // minus인거 더하기
        while(!minusPQ.isEmpty()){
            Integer a = minusPQ.poll();
            Integer b = minusPQ.poll();
            if(a==null || b==null) {
                if(a!=null) answer+=a;
                break;
            }

            if(a*b <= a+b) {
                answer+=a+b;
            }else{
                answer+=a*b;
            }
        }


        System.out.println(answer);
    }
}
