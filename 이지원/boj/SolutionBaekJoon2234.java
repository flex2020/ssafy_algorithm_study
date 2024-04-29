import java.util.*;
import java.io.*;

public class SolutionBaekJoon2234 {

    static class Room {
        int number;
        int wall;

        Room(int wall) {
            this.wall = wall;
        }
    }

    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Room[][] map;
    static int roomNumber;
    static List<Integer> areas;
    static int maxArea;
    static int maxAreaWithBroken;
    // 상하좌우
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 지도 정보 입력받기
        map = new Room[M][N];
        for (int i = 0; i < map.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = new Room(Integer.parseInt(st.nextToken()));
            }
        }

        // 방 개수 세기 & 넘버링하기
        roomNumber = 0;
        areas = new ArrayList<>();
        areas.add(0);
        maxArea = Integer.MIN_VALUE;
        numberingRoom();

        // 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기 구하기
        maxAreaWithBroken = Integer.MIN_VALUE;
        calculateMaxAreaWithBroken();

        StringBuilder sb = new StringBuilder();
        sb.append(roomNumber + "\n");
        sb.append(maxArea + "\n");
        sb.append(maxAreaWithBroken);
        System.out.println(sb);
    }

    public static void numberingRoom() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j].number == 0) {
                    calculateRoomArea(new Position(i, j));
                }
            }
        }
    }

    public static void calculateRoomArea(Position start) {
        Queue<Position> queue = new ArrayDeque<>();

        // 시작점
        int area = 1;
        map[start.x][start.y].number = ++roomNumber;
        queue.offer(start);

        while (!queue.isEmpty()) {
            Position cur = queue.poll();

            int wall = map[cur.x][cur.y].wall;
            for (int direction = 0; direction < dx.length; direction++) {
                // 벽이 있는 경우
                switch (direction) {
                    case 0: // 북
                        if ((wall & (1 << 1)) == 2) {
                            continue;
                        }
                        break;
                    case 1: // 남
                        if ((wall & (1 << 3)) == 8) {
                            continue;
                        }
                        break;
                    case 2: // 서
                        if ((wall & (1 << 0)) == 1) {
                            continue;
                        }
                        break;
                    case 3: // 동
                        if ((wall & (1 << 2)) == 4) {
                            continue;
                        }
                        break;
                }

                int nextX = cur.x + dx[direction];
                int nextY = cur.y + dy[direction];

                // 이미 방문한 경우
                if (map[nextX][nextY].number > 0) {
                    continue;
                }

                area++;
                map[nextX][nextY].number = roomNumber;
                queue.offer(new Position(nextX, nextY));
            }
        }

        areas.add(area);

        // 가장 넓은 방의 넓이 갱신하기
        maxArea = Math.max(maxArea, area);
    }

    public static void calculateMaxAreaWithBroken() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                int wall = map[i][j].wall;

                for (int direction = 0; direction < dx.length; direction++) {
                    // 벽이 없는 경우
                    switch (direction) {
                        case 0: // 북
                            if ((wall & (1 << 1)) != 2) {
                                continue;
                            }
                            break;
                        case 1: // 남
                            if ((wall & (1 << 3)) != 8) {
                                continue;
                            }
                            break;
                        case 2: // 서
                            if ((wall & (1 << 0)) != 1) {
                                continue;
                            }
                            break;
                        case 3: // 동
                            if ((wall & (1 << 2)) != 4) {
                                continue;
                            }
                            break;
                    }

                    int nextX = i + dx[direction];
                    int nextY = j + dy[direction];

                    // 지도를 벗어나는 경우
                    if (nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map[0].length) {
                        continue;
                    }

                    if (map[i][j].number != map[nextX][nextY].number) {
                        int area = areas.get(map[i][j].number) + areas.get(map[nextX][nextY].number);
                        maxAreaWithBroken = Math.max(maxAreaWithBroken, area);
                    }
                }
            }
        }
    }

}
