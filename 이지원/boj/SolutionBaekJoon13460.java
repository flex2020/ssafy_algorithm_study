import java.util.*;
import java.io.*;

public class SolutionBaekJoon13460 {

    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Position copy() {
            Position p = new Position(x, y);
            return p;
        }
    }

    static int minResult;
    // 상하좌우
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 지도 입력받기
        char[][] map = new char[N][M];
        Position[] beads = new Position[2]; // 0: 빨간, 1: 파란
        for (int i = 0; i < map.length; i++) {
            map[i] = br.readLine().toCharArray();

            // 구슬 찾기
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 'R') {
                    beads[0] = new Position(i, j);
                } else if (map[i][j] == 'B') {
                    beads[1] = new Position(i, j);
                }
            }
        }

        minResult = Integer.MAX_VALUE;
        calculateMinResult(0, map, beads);

        System.out.println(minResult == Integer.MAX_VALUE ? -1 : minResult);
    }

    public static void calculateMinResult(int count, char[][] map, Position[] beads) {
        // 이미 현재보다 더 적게 움직이거나 최대 움직일 수 있는 횟수에 도달한 경우
        if (minResult <= count || count == 10) {
            return;
        }

        // 움직이기
        for (int direction = 0; direction < dx.length; direction++) {
            // 먼저 움직일 구슬 알아내기
            int firstBead = -1;
            switch (direction) {
                case 0: // 상
                    if (beads[0].x < beads[1].x) {
                        firstBead = 0;
                    } else {
                        firstBead = 1;
                    }
                    break;
                case 1: // 하
                    if (beads[0].x > beads[1].x) {
                        firstBead = 0;
                    } else {
                        firstBead = 1;
                    }
                    break;
                case 2: // 좌
                    if (beads[0].y < beads[1].y) {
                        firstBead = 0;
                    } else {
                        firstBead = 1;
                    }
                    break;
                case 3: // 우
                    if (beads[0].y > beads[1].y) {
                        firstBead = 0;
                    } else {
                        firstBead = 1;
                    }
                    break;
            }

            char[][] copiedMap = copyMap(map);
            Position[] copiedBeads = copyBeads(beads);
            // 구슬 움직이기
            boolean isRedGoal = false;
            boolean isBlueGoal = false;
            boolean isMoved = false;
            for (int c = 0; c < 2; c++) {
                Position b = copiedBeads[(firstBead + c) % 2];
                int nextX = b.x;
                int nextY = b.y;

                while (copiedMap[nextX + dx[direction]][nextY + dy[direction]] == '.') {
                    nextX += dx[direction];
                    nextY += dy[direction];
                }

                // 다음 칸이 구멍인 경우
                if (copiedMap[nextX + dx[direction]][nextY + dy[direction]] == 'O') {
                    // 빨간 구슬인 경우
                    if ((firstBead + c) % 2 == 0) {
                        isRedGoal = true;
                    } else {
                        isBlueGoal = true;
                    }

                    // 지도에서 구슬 없애기
                    copiedMap[b.x][b.y] = '.';

                    // 구슬 위치 바꾸기
                    b.x = nextX + dx[direction];
                    b.y = nextY + dy[direction];

                    continue;
                }

                // 다음 위치랑 현재 위치가 다른 경우
                if (nextX != b.x || nextY != b.y) {
                    // 구슬 바꾸기
                    copiedMap[nextX][nextY] = copiedMap[b.x][b.y];
                    copiedMap[b.x][b.y] = '.';

                    // 구슬 위치 바꾸기
                    b.x = nextX;
                    b.y = nextY;

                    isMoved = true;
                }
            }

            if (isRedGoal && !isBlueGoal) {
                minResult = Math.min(minResult, count + 1);
            } else if (!isRedGoal && !isBlueGoal && isMoved) {
                calculateMinResult(count + 1, copiedMap, copiedBeads);
            }
        }
    }

    public static char[][] copyMap(char[][] map) {
        char[][] copiedMap = new char[map.length][map[0].length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                copiedMap[i][j] = map[i][j];
            }
        }

        return copiedMap;
    }

    public static Position[] copyBeads(Position[] beads) {
        Position[] copiedBeads = new Position[beads.length];

        for (int i = 0; i < beads.length; i++) {
            copiedBeads[i] = beads[i].copy();
        }

        return copiedBeads;
    }

}