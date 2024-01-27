import java.util.*;
import java.io.*;

public class SolutionSWEA5356 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            char[][] tmpInputs = new char[5][];

            int maxLength = Integer.MIN_VALUE;
            for (int i = 0; i < 5; i++) {
                tmpInputs[i] = br.readLine().toCharArray();

                if (tmpInputs[i].length > maxLength) {
                    maxLength = tmpInputs[i].length;
                }
            }

            char[][] inputs = new char[5][maxLength];
            for (int i = 0; i < 5; i++) {
                int tmpInputsLength = tmpInputs[i].length;
                for (int j = 0; j < tmpInputsLength; j++) {
                    inputs[i][j] = tmpInputs[i][j];
                }
                for (int k = tmpInputsLength; k < maxLength; k++) {
                    inputs[i][k] = '-';
                }
            }

            List<Character> results = new ArrayList<>();
            for (int j = 0; j < maxLength; j++) {
                for (int i = 0; i < 5; i++) {
                    if (inputs[i][j] != '-') {
                        results.add(inputs[i][j]);
                    }
                }
            }

            System.out.print("#" + testCase + " ");
            results.forEach(result -> System.out.print(result));
            System.out.println();
        }
    }
}