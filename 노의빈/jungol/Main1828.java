import java.io.*;
import java.util.*;

public class Main1828 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Chemical[] items = new Chemical[N];
		List<Refrigerator> rlist = new ArrayList<>();
		int answer = 1; // 냉장고의 수
		for (int i=0; i<N; i++) {
			String[] input = br.readLine().split(" ");
			int min = Integer.parseInt(input[0]);
			int max = Integer.parseInt(input[1]);
			items[i] = new Chemical(min, max);
		}
		Arrays.sort(items, (a, b) -> a.minTemp - b.minTemp);
		// 첫번재 냉장고 생성
		rlist.add(new Refrigerator(items[0].minTemp, items[0].maxTemp));
		for (int i=1; i<N; i++) {
			// 두번째 화학물질이 냉장고 범위 안에 들어가는지 확인
			// 냉장고에 넣지 못하면 냉장고 새로 생성
			boolean storeFlag = false;
			for (int j=0; j<answer; j++) {
				Refrigerator r = rlist.get(j);
				if (r.canStore(items[i])) {
					r.addItem(items[i]);
					storeFlag = true;
					break;
				}
			}
			// 보관할 수 없는 경우 냉장고 추가
			if (!storeFlag) {
				answer += 1;
				rlist.add(new Refrigerator(items[i].minTemp, items[i].maxTemp));
			}
		}
		System.out.println(answer);
	}
}

class Chemical {
	int minTemp;
	int maxTemp;
	public Chemical(int minTemp, int maxTemp) {
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
	}
}

class Refrigerator {
	int minTemp;
	int maxTemp;
	public Refrigerator(int minTemp, int maxTemp) {
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
	}
	public boolean canStore(Chemical c) {
		return !(this.maxTemp < c.minTemp || this.minTemp > c.maxTemp);
	}
	public void addItem(Chemical c) {
		this.minTemp = Math.max(this.minTemp, c.minTemp);
		this.maxTemp = Math.min(this.maxTemp, c.maxTemp);
	}
}