import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {

    static int G;
    static int P;

    static int[] gates;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine()); // 게이트의 수
        P = Integer.parseInt(br.readLine()); // 비행기의 수

        gates = new int[G+1];

        for (int i = 0; i < G+1; i++) { // 초기화
            gates[i]=i;
        }

        int num = 0;

        for (int i = 0; i < P; i++) {
            int plane = Integer.parseInt(br.readLine());
            int n = find(plane);
            if(n==0) break;
            num++;
            union(n, n-1);
        }

        System.out.println(num);

    }

    private static int find(int a){ // 찾기
        if(a==gates[a]){
            return a;
        }else{
            return gates[a]=find(gates[a]);
        }
    }

    private static void union(int a, int b){ // 합치기
        int parA = find(a);
        int parB = find(b);
        gates[parA] = parB;
    }
}
