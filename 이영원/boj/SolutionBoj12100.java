import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int answer, N;
    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 맵 크기
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recursive(map, 0, 0);
//        push(map, 3);

        System.out.println(answer);
    }

    private static void recursive(int[][] map, int cnt, int result) {

        if (cnt == 5) {
            answer = Math.max(answer, result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[][] tmpMap = new int[N][N]; // 깊은 복사
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    tmpMap[j][k] = map[j][k];
                }
            }
            tmpMap=push(tmpMap, i);

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if(result < tmpMap[j][k]) result = tmpMap[j][k];
                }
            }
//            System.out.println(cnt + " " + i);
//            print(tmpMap);

            recursive(tmpMap, cnt+1, result);
        }
    }

    // 해당 방향으로 맵을 민다
    private static int[][] push(int[][] map, int dir) {
        int startX = 0;
        int startY = 0;
        int plusX = 0;
        int plusY = 0;
        int edge=0;
        int result=0;

        switch (dir) {
            case 0: // 오른쪽으로 밀기
                startX = 0;
                startY = N - 1;
                plusX = 1;
                plusY = -1;

                edge = N - 1;

                for (int i = startX; i < N; i += plusX) {
                    edge = N-1;
                    while (edge > 0) {
                        int nx = i;
                        int ny = edge;
                        for (int j = edge; j > 0; j += plusY) {
                            nx += dx[(dir + 2) % 4];
                            ny += dy[(dir + 2) % 4];
                            if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] != 0) {
                                if(map[i][edge]!=0){
                                    if (map[i][edge] == map[nx][ny]) {
                                        map[i][edge--] = map[nx][ny] * 2;
                                        map[nx][ny] = 0;
                                    } else{
                                        edge--;
                                        break;
                                    }
                                } else {
                                    map[i][edge] = map[nx][ny];
                                    map[nx][ny] = 0;
                                }
                            } else if(j==1) edge--;
                        }
//                        print(map);
                    }
                }
                break;
            case 1: // 아래로 밀기
                startX = N - 1;
                startY = 0;
                plusX = -1;
                plusY = 1;

                edge = N - 1;

                for (int i = startY; i < N; i += plusY) {
                    edge = N-1;
                    while (edge > 0) {
                        int nx = edge;
                        int ny = i;
                        for (int j = edge; j > 0; j += plusX) {
                            nx += dx[(dir + 2) % 4];
                            ny += dy[(dir + 2) % 4];
                            if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] != 0) {
                                if(map[edge][i]!=0){
                                    if (map[edge][i] == map[nx][ny]) {
                                        map[edge--][i] = map[nx][ny] * 2;
                                        map[nx][ny] = 0;
                                    } else{
                                        edge--;
                                        break;
                                    }
                                } else {
                                    map[edge][i] = map[nx][ny];
                                    map[nx][ny] = 0;
                                }
                            } else if(j==1) edge--;
                        }
//                        print(map);
                    }
                }

                break;
            case 2: // 왼쪽으로 밀기
                startX = 0;
                startY = 0;
                plusX = 1;
                plusY = 1;

                edge = 0;

                for (int i = startX; i < N; i += plusX) {
                    edge = 0;
                    while (edge < N-1) {
                        int nx = i;
                        int ny = edge;
                        for (int j = edge; j < N; j += plusY) {
                            nx += dx[(dir + 2) % 4];
                            ny += dy[(dir + 2) % 4];
                            if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] != 0) {
                                if(map[i][edge]!=0){
                                    if (map[i][edge] == map[nx][ny]) {
                                        map[i][edge++] = map[nx][ny] * 2;
                                        map[nx][ny] = 0;
                                    } else{
                                        edge++;
                                        break;
                                    }
                                } else {
                                    map[i][edge] = map[nx][ny];
                                    map[nx][ny] = 0;
                                }
                            } else if(j==N-1) edge++;
                        }
//                        print(map);
                    }
                }
                break;
            case 3: // 위쪽으로 밀기
                startX = 0;
                startY = 0;
                plusX = 1;
                plusY = 1;

                edge = N - 1;

                for (int i = startY; i < N; i += plusY) {
                    edge = 0;
                    while (edge < N-1) {
                        int nx = edge;
                        int ny = i;
                        for (int j = edge; j < N; j += plusX) {
                            nx += dx[(dir + 2) % 4];
                            ny += dy[(dir + 2) % 4];
                            if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] != 0) {
                                if(map[edge][i]!=0){
                                    if (map[edge][i] == map[nx][ny]) {
                                        map[edge++][i] = map[nx][ny] * 2;
                                        map[nx][ny] = 0;
                                    } else{
                                        edge++;
                                        break;
                                    }
                                } else {
                                    map[edge][i] = map[nx][ny];
                                    map[nx][ny] = 0;
                                }
                            } else if(j==N-1) edge++;
                        }
//                        print(map);
                    }
                }

                break;
        }

//        print(map);

        return map;


//        print(map);


    }

    private static void print(int[][] map){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
