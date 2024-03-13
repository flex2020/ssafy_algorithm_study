package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_1339 {
	static class alphabetClass {
		char c;
		int priority;
		alphabetClass (char c, int priority) {
			this.c = c;
			this.priority = priority;
		}
	}
	static int N;
	static List<String> words;
	static PriorityQueue<alphabetClass> PQ;
	static Map<Character, Integer> alphabet, convert;
	static int value = 9; // 알파벳에 부여할 숫자
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		words = new ArrayList<>();
		alphabet = new HashMap<>(); // letter: priority
		PQ = new PriorityQueue<>((p1, p2) -> p2.priority - p1.priority);
		convert = new HashMap<>(); // letter: value
		
		for (int i=0; i<N; i++) {
			words.add(br.readLine());
		}
		
		for (String word : words) { 
			for (int i=0; i<word.length(); i++) {
				char w = word.charAt(i);
				if (alphabet.containsKey(w)) {
					alphabet.replace(w, (int) (alphabet.get(w) + (Math.pow(10, word.length()-i-1))));
					
				} else {
					alphabet.put(w, (int) (Math.pow(10, word.length()-i-1)));
				}
			}
		}
		
		alphabet.forEach((k, v) -> {
			PQ.offer(new alphabetClass(k, v));
		});
		
		while (!PQ.isEmpty()) {
			alphabetClass cur = PQ.poll();
			convert.put(cur.c, value--);
		}
		
		int answer = 0;
		for (String word : words) {
			String convertInt = "";
			for (int i=0; i<word.length(); i++) {
				convertInt += convert.get(word.charAt(i));
			}
			answer += Integer.parseInt(convertInt);
		}
		System.out.println(answer);

	}

}
