package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_2477 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		int smallH = 500;
		int smallW = 500;
		int largeH = 0;
		int largeW = 0;
        int[][] value = new int[6][2];
        int[] dir = new int[5];
		
		for (int i=0; i<6; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dir[a]++;
            value[i][0] = a;
            value[i][1] = b;
		}
        for (int i=0; i<6; i++) {
            if (dir[value[i][0]] == 1) {
                if (value[i][0] == 1 || value[i][0] == 2) // 동서
                    largeW = value[i][1];
                else // 남북
                    largeH = value[i][1];
            } else {
                if (value[i][0] == 1 || value[i][0] == 2) { // 동서
                    if (i == 0) {
                        if (value[5][0] == value[i+1][0]) 
                            smallW = value[i][1];
                    }
                    else if (i == 5) {
                        if (value[i-1][0] == value[0][0]) 
                            smallW = value[i][1];
                    }
                    else {
                        if (value[i-1][0] == value[i+1][0]) 
                            smallW = value[i][1];
                    }
                } else { // 남북
                    if (i == 0) {
                        if (value[5][0] == value[i+1][0]) 
                            smallH = value[i][1];
                    }
                    else if (i == 5) {
                        if (value[i-1][0] == value[0][0]) 
                            smallH = value[i][1];
                    }
                    else {
                        if (value[i-1][0] == value[i+1][0]) 
                            smallH = value[i][1];
                    }
                }
            }
        }
		System.out.println(K * (largeH * largeW - smallH * smallW));
	}
}