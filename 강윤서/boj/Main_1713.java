package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_1713 {
	static int N, M;
	static class Candidate {
		int number, cnt, time;
		public Candidate(int number, int cnt, int time) {
			this.number = number;
			this.cnt = cnt;
			this.time = time;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		int[] C = new int[101];
		Arrays.fill(C, -1);
		List<Candidate> L = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<M; i++) {
			Collections.sort(L, (l1, l2) -> l1.cnt == l2.cnt ? l1.time - l2.time : l1.cnt - l2.cnt);
			int num = Integer.parseInt(st.nextToken());
			if (C[num] != -1) { // 이미 등록된 사진틀로 가능
				C[num] += 1;
				for (int d=0; d<L.size(); d++) {
                    if (L.get(d).number != num) continue;
					L.get(d).cnt += 1;
				}
				continue;
			}
			if (L.size( ) == N) {
				Candidate remove = L.remove(0);
				C[remove.number] = -1; // 없애주기
			}
			L.add(new Candidate(num, 1, i));
			C[num] = 1;
		}
		Collections.sort(L, (l1, l2) -> l1.number - l2.number);
		for (int i=0; i<L.size(); i++) {
			System.out.print(L.get(i).number + " ");
		}
	}
}

