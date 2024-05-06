import java.util.*;
import java.io.*;

public class SolutionBaekJoon2931 {

    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static char[][] map;
    static List<Position> destinations;
    static boolean[][] isVisited;
    static Position hole;
    static List<Integer> holeDirections;
    static StringBuilder sb;
    // 상하좌우
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // 지도 입력받기
        map = new char[R][C];
        destinations = new ArrayList<>();
        char[] tmp;
        for (int i = 0; i < map.length; i++) {
            tmp = br.readLine().toCharArray();
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = tmp[j];

                if (map[i][j] == 'M' || map[i][j] == 'Z') {
                    destinations.add(new Position(i, j));
                }
            }
        }

        // 파이프가 끊어진 곳 찾기
        isVisited = new boolean[map.length][map[0].length];
        holeDirections = new ArrayList<>();
        for (Position destination : destinations) {
            findHole(destination);
        }

        // 지워진 블록 위치와 모양 구하기
        calculateResult();

        System.out.print(sb);
    }

    public static void findHole(Position start) {
        isVisited[start.x][start.y] = true;

        Queue<Position> queue = new ArrayDeque<>();

        boolean isExistedFirst = false;
        for (int direction = 0; direction < dx.length; direction++) {
            int nextX = start.x + dx[direction];
            int nextY = start.y + dy[direction];

            // 지도를 벗어나는 경우
            if (nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map[0].length) {
                continue;
            }

            // 파이프가 있는 경우
            if (map[nextX][nextY] != '.') {
                isExistedFirst = true;
                isVisited[nextX][nextY] = true;
                queue.offer(new Position(nextX, nextY));
            }
        }

        // 처음 파이프가 없는 경우
        if (!isExistedFirst) {
            return;
        }

        while (!queue.isEmpty()) {
            Position cur = queue.poll();

            aa: for (int direction = 0; direction < dx.length; direction++) {
                int nextX = cur.x;
                int nextY = cur.y;

                switch (direction) {
                    case 0: // 상
                        if (map[cur.x][cur.y] == '|' || map[cur.x][cur.y] == '+' ||
                                map[cur.x][cur.y] == '2' || map[cur.x][cur.y] == '3') {
                            nextX += dx[direction];
                            nextY += dy[direction];
                        } else {
                            continue aa;
                        }
                        break;
                    case 1: // 하
                        if (map[cur.x][cur.y] == '|' || map[cur.x][cur.y] == '+' ||
                                map[cur.x][cur.y] == '1' || map[cur.x][cur.y] == '4') {
                            nextX += dx[direction];
                            nextY += dy[direction];
                        } else {
                            continue aa;
                        }
                        break;
                    case 2: // 좌
                        if (map[cur.x][cur.y] == '-' || map[cur.x][cur.y] == '+' ||
                                map[cur.x][cur.y] == '3' || map[cur.x][cur.y] == '4') {
                            nextX += dx[direction];
                            nextY += dy[direction];
                        } else {
                            continue aa;
                        }
                        break;
                    case 3: // 우
                        if (map[cur.x][cur.y] == '-' || map[cur.x][cur.y] == '+' ||
                                map[cur.x][cur.y] == '1' || map[cur.x][cur.y] == '2') {
                            nextX += dx[direction];
                            nextY += dy[direction];
                        } else {
                            continue aa;
                        }
                        break;
                }

                // 이미 방문한 곳인 경우
                if (isVisited[nextX][nextY]) {
                    continue;
                }

                if (map[nextX][nextY] != '.') { // 파이프가 있는 경우
                    isVisited[nextX][nextY] = true;
                    queue.offer(new Position(nextX, nextY));
                } else {
                    hole = new Position(nextX, nextY);
                    if (direction % 2 == 0) {
                        holeDirections.add(direction + 1);
                    } else {
                        holeDirections.add(direction - 1);
                    }
                }
            }
        }
    }

    public static void calculateResult() {
        sb = new StringBuilder();
        sb.append((hole.x + 1) + " " + (hole.y + 1) + " ");

        if (!isExistedOtherPipe()) {
            int sum = 0;
            if (holeDirections.size() < 2) {
                System.out.println(holeDirections);
                for (int direction = 0; direction < dx.length; direction++) {
                    int nextX = hole.x + dx[direction];
                    int nextY = hole.y + dy[direction];

                    // 지도를 벗어나는 경우
                    if (nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map[0].length) {
                        continue;
                    }

                    if (map[nextX][nextY] == 'M' || map[nextX][nextY] == 'Z') {
                        sum += direction;
                        break;
                    }
                }
            }

            for (int direction : holeDirections) {
                sum += direction;
            }

            switch (sum) {
                case 1:
                    sb.append("|");
                    break;
                case 2:
                    sb.append("3");
                    break;
                case 3:
                    if (holeDirections.contains(0)) {
                        sb.append("2");
                    } else {
                        sb.append("4");
                    }
                    break;
                case 4:
                    sb.append("1");
                    break;
                case 5:
                    sb.append("-");
                    break;
            }
        }
    }

    public static boolean isExistedOtherPipe() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != '.' && !isVisited[i][j]) {
                    sb.append('+');
                    return true;
                }
            }
        }

        return false;
    }

}
