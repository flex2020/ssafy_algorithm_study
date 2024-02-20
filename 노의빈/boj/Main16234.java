package boj;

import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main16234 {
	private static int N, L, R, answer;
	private static Node[] parent; // 0번에는 부모노드의 번호, 1번에는 인구를 저장
	private static Map<Integer, Group> map; // key: 부모노드, value: 연합정보(연합국의 수, 전체연합국의 인구)
	
	static class Group {
		int cnt, totalPopulation;
		
		public Group(int cnt, int totalPopulation) {
			super();
			this.cnt = cnt;
			this.totalPopulation = totalPopulation;
		}
		

		@Override
		public int hashCode() {
			return Objects.hash(cnt, totalPopulation);
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Group other = (Group) obj;
			return cnt == other.cnt && totalPopulation == other.totalPopulation;
		}


		@Override
		public String toString() {
			return "Group [cnt=" + cnt + ", totalPopulation=" + totalPopulation + "]";
		}
	}
	
	static class Node {
		int parent, population;
		
		public Node() {}

		public Node(int parent, int population) {
			super();
			this.parent = parent;
			this.population = population;
		}

		@Override
		public String toString() {
			return "Node [parent=" + parent + ", population=" + population + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		parent = new Node[N*N];
		for (int i=0; i<parent.length; i++) parent[i] = new Node();
		int idx = 0;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				parent[idx++].population = Integer.parseInt(st.nextToken());
			}
		}
		close();
		simulate();
		System.out.println(answer);
	}
	
	private static void simulate() {
		// 인구이동이 가능할 때 반복
		while (true) {
			// 현재 지도가 인구이동이 일어날 수 있는지 확인
			if (!check()) break;
			// 인구이동 시작
			move();
			// 국경 닫기
			close();
			answer += 1;
		}
	}
	
	private static boolean check() {
		boolean result = false;
		// 모든 노드를 돌아보면서 오른쪽, 아래쪽 국경이 열릴 수 있는지 확인
		for (int i=0; i<parent.length; i++) {
			Point p = getPoint(i);
			int right = p.y + 1 < N ? getNode(new Point(p.x, p.y + 1)) : -1; // 오른쪽
			int down = getNode(new Point(p.x + 1, p.y)); // 아래쪽
			
			// 오른쪽에서 인구이동이 가능하다면 union
			if (right != -1 && right < parent.length 
					&& Math.abs(parent[right].population - parent[i].population) >= L 
					&& Math.abs(parent[right].population - parent[i].population) <= R) {
				if (find(i) != find(right)) {
					union(i, right);
					result = true;
				}
			}
			// 아래쪽에서 인구이동이 가능하다면 union
			if (down < parent.length 
					&& Math.abs(parent[down].population - parent[i].population) >= L 
					&& Math.abs(parent[down].population - parent[i].population) <= R) {
				if (find(i) != find(down)) {
					union(i, down);
					result = true;
				}
			}
		}
		return result;
	}
	 
	private static void move() {
		for (int i=0; i<parent.length; i++) {
			int pIndex = find(i);
			parent[i].population = map.get(pIndex).totalPopulation / map.get(pIndex).cnt;
		}
	}
	
	private static void close() {
		map = new HashMap<>();
		for (int i=0; i<parent.length; i++) {
			parent[i].parent = i;
			map.put(i, new Group(1, parent[i].population));
		}
	}
	
	private static int find(int a) {
		if (parent[a].parent != a) {
			parent[a].parent = find(parent[a].parent);
		}
		return parent[a].parent;
	}
	
	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		Group ga = map.get(pa);
		Group gb = map.get(pb);
		
		if (pb > pa) {
			parent[pb].parent = pa;
			ga.cnt += gb.cnt;
			ga.totalPopulation += gb.totalPopulation;
			map.put(pa, ga);
			map.remove(pb);
		}
		else {
			parent[pa].parent = pb;
			gb.cnt += ga.cnt;
			gb.totalPopulation += ga.totalPopulation;
			map.put(pb, gb);
			map.remove(pa);
		}
		
	}
	
	private static Point getPoint(int n) {
		return new Point(n/N, n%N);
	}
	
	private static int getNode(Point p) {
		return p.x * N + p.y;
	}
}
