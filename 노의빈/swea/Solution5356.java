package swea;

import java.util.*;
import java.io.*;

public class Solution5356 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringBuilder answer = new StringBuilder();
			String[] data = new String[5];
			int maxLength = 0;
			for (int i=0; i<5; i++) {
				data[i] = br.readLine();
				maxLength = Math.max(maxLength, data[i].length());
			}
			for (int j=0; j<maxLength; j++) {
				for (int i=0; i<5; i++) {
					if (j < data[i].length()) {
						answer.append(data[i].charAt(j));
					}
				}
			}
			
			System.out.println("#" + tc + " " + answer.toString());
		}
	}

}
