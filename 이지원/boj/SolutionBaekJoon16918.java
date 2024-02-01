import java.util.Scanner;
import java.util.Arrays;

class Grid {
    int time = 0;
    char status;

    Grid(char status) {
        this.status = status;
    }

    public void clear() {
        time = 0;
        status = '.';
    }
}

public class SolutionBaekJoon16918 {
    
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        int C = sc.nextInt();
        int N = sc.nextInt();
        
        Grid[][] map = new Grid[R + 2][C + 2];
        Arrays.fill(map[0], new Grid('.'));
        Arrays.fill(map[map.length - 1], new Grid('.'));
        for (int i = 1; i <= R; i++) {
            map[i][0] = new Grid('.');
            map[i][C + 1] = new Grid('.');
        }

        for (int i = 1; i <= R; i++) {
            char[] grids = sc.next().toCharArray();

            for (int j = 1; j <= C; j++) {
                map[i][j] = new Grid(grids[j - 1]);
            }
        }

        for (int time = 0; time <= N; time++) {
            if (time > 0 && time % 2 == 0) {
                fillBomb(map);
            }
            explodeBomb(map);
        }

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                System.out.print(map[i][j].status);
            }
            System.out.println();
        }
	}

    // 폭탄 채우기
    // : 폭탄 없는 곳에 폭탄을 채워준다.
    public static void fillBomb(Grid[][] map) {
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[0].length - 1; j++) {
                if (map[i][j].status == '.') {
                    map[i][j].status = 'O';
                }
            }
        }
    }

    // 폭탄 터트리기
    // : 사방을 탐색해서 폭탄이며 시간이 3초 지났으면 터트리고, 안 지났으면 시간만 증가해준다.
    public static void explodeBomb(Grid[][] map) {
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[0].length - 1; j++) {
                if (map[i][j].status == 'O') {
                    if (map[i][j].time == 3) {
                        map[i][j].clear();
                        explodeSideBomb(map[i][j + 1]);
                        explodeSideBomb(map[i][j - 1]);
                        explodeSideBomb(map[i + 1][j]);
                        explodeSideBomb(map[i - 1][j]);
                    } else {
                        map[i][j].time++;
                    }
                }
            }
        }
    }

    public static void explodeSideBomb(Grid grid) {
        if (grid.time == 3) {
            return;
        }
        grid.clear();
    }
    
}