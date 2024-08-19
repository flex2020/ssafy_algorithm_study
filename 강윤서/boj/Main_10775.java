import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        int[] maxGate = new int[G+1];
        for (int i=0; i<=G; i++) maxGate[i] = i;
        boolean isClosed = false;
        int answer = 0;

        for (int i=0; i<P; i++) {
            int initGate = Integer.parseInt(br.readLine());
            int gate = initGate;
            if (isClosed) continue;
            while (true) {
                if (maxGate[gate] == gate || maxGate[gate] == 0) {
                    break;
                }
                gate = maxGate[gate];
            }
            if (maxGate[gate] == 0) {
                isClosed = true;
            } else {
                answer++;
                if (initGate != gate) {
                    maxGate[gate] -= 1;
                    maxGate[initGate] -= 1;
                } else {
                    maxGate[gate] -= 1;
                }
            }
        }
        System.out.println(answer);
    }

}
