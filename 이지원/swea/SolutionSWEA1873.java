import java.util.Scanner;
import java.util.Arrays;

class Position {
    int x;
    int y;

    Position (int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class SolutionSWEA1873 {

    public static char[][] map;
    // 왼쪽, 오른쪽, 위쪽, 아래쪽
    public static Position[] directions = new Position[4];
    public static char[] directionChars = {'<', '>', '^', 'v'};
    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        directions[0] = new Position(0, -1);
        directions[1] = new Position(0, 1);
        directions[2] = new Position(-1, 0);
        directions[3] = new Position(1, 0);

        for (int testCase = 1; testCase <= T; testCase++) {
            int H = sc.nextInt();
            int W = sc.nextInt();
            map = new char[H + 2][W + 2];
            // 맵 구성하기
            Arrays.fill(map[0], '#');
            Arrays.fill(map[map.length - 1], '#');
            for (int i = 1; i < map.length - 1; i++) {
                map[i][0] = '#';
                map[i][map[0].length - 1] = '#';
                char[] tmp = sc.next().toCharArray();
                for (int j = 1; j < map[0].length - 1; j++) {
                    map[i][j] = tmp[j - 1];
                }
            }

            // 전차 위치, 방향 파악하기
            Position curPosition = new Position(-1, -1);
            int curDirection = -1;
            aa: for (int i = 1; i < map.length - 1; i++) {
                for (int j = 1; j < map[0].length - 1; j++) {
                    if (map[i][j] == '<' || map[i][j] == '>' || map[i][j] == '^' || map[i][j] == 'v') {
                        curPosition.x = i;
                        curPosition.y = j;
                        switch (map[i][j]) {
                            case '<':
                                curDirection = 0;
                                break;
                            case '>':
                                curDirection = 1;
                                break;
                            case '^':
                                curDirection = 2;
                                break;
                            case 'v':
                                curDirection = 3;
                                break;
                        }
                        break aa;
                    }
                }
            }

            int commandCount = sc.nextInt();
            char[] commands = sc.next().toCharArray();
            for (char command : commands) {
                switch (command) {
                    case 'L':
                        curDirection = 0;
                        go(curPosition, curDirection);
                        break;
                    case 'R':
                        curDirection = 1;
                        go(curPosition, curDirection);
                        break;
                    case 'U':
                        curDirection = 2;
                        go(curPosition, curDirection);
                        break;
                    case 'D':
                        curDirection = 3;
                        go(curPosition, curDirection);
                        break;
                    case 'S':
                        shoot(curPosition, curDirection);
                        break;
                }
            }

            System.out.print("#" + testCase + " ");
            for (int i = 1; i < map.length - 1; i++) {
                for (int j = 1; j < map[0].length - 1; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }
    }

    public static void shoot(Position curPosition, int curDirection) {
        Position position = new Position(curPosition.x + directions[curDirection].x, curPosition.y + directions[curDirection].y);
        while (map[position.x][position.y] != '#') {
            if (map[position.x][position.y] == '*') {
                map[position.x][position.y] = '.';
                break;
            } else {
                position.x += directions[curDirection].x;
                position.y += directions[curDirection].y;
            }
        }
    }

    public static void go(Position curPosition, int curDirection) {
        if (map[curPosition.x + directions[curDirection].x][curPosition.y + directions[curDirection].y] == '.') {
            map[curPosition.x][curPosition.y] = '.';
            curPosition.x += directions[curDirection].x;
            curPosition.y += directions[curDirection].y;
        }
        map[curPosition.x][curPosition.y] = directionChars[curDirection];
    }
}