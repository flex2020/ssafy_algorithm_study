import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R, C, M;

    static int[] dr = {0, -1, 1, 0, 0}; // 1부터 상하우좌
    static int[] dc = {0, 0, 0, 1, -1}; // 1부터 상하우좌

    static int[] top;
    static int[][] map; // 이동한 상어 위치 기록용 맵

    static int numR, numC; // 행, 열 최대치수?

    static Shark[] sharks; // 상어 배열

    static class Shark{
        int r, c, s, d, z; // 행, 열, 속도, 방향, 크기
        boolean isDead = false;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "r=" + r +
                    ", c=" + c +
                    ", s=" + s +
                    ", d=" + d +
                    ", z=" + z +
                    ", isDead=" + isDead +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken()); // 행
        C = Integer.parseInt(st.nextToken()); // 열
        M = Integer.parseInt(st.nextToken()); // 상어 수

        top = new int[C];
        map = new int[R][C];
        // R이 4라면 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14
        //          0 1 2 3 2 1 0 1 2 3 2  1   0  1  2 이런식이니까 numR은 이 경우 6이다. 그리고 케이스 나눔.
        numR = (R-1)*2;
        numC = (C-1)*2;
        sharks = new Shark[M+1];

        for (int i = 1; i < M+1; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            sharks[i] = new Shark(r, c, s, d, z);
            map[r][c]=i;
        }
        int answer=0;
//
//        if(idx!=0){
//            sharks[idx].isDead=true;
//            answer+=sharks[idx].z;
//        }
//        System.out.println(answer);
        for (int i = 0; i < C; i++) {
            answer+=catchShark(map, i);
            map=moveShark(sharks, i);
//            System.out.println(answer);
        }

        System.out.println(answer);
    }

    // 낚시왕이 상어 잡는거, index에 있는것만 체크해서 가장 먼저 만나는거 죽이고 리턴, 안만나면 0 리턴
    private static int catchShark(int[][] map, int i) {
        for (int j = 0; j < R; j++) {
            if(map[j][i]!=0) {
                sharks[map[j][i]].isDead=true;
                return sharks[map[j][i]].z;
            }
        }
        return 0;
    }

    // 샤크 움직이기(움직이면 같은 장소에 가면 죽이기), map을 업데이트해서 리턴한다.
    private static int[][] moveShark(Shark[] sharks, int idx){
        int top=101;
        int[][] map = new int[R][C];
        for (int i = 1; i < M+1; i++) {
            if(!sharks[i].isDead){
                int nr = sharks[i].r + (dr[sharks[i].d] * sharks[i].s);
                int nc = sharks[i].c + (dc[sharks[i].d] * sharks[i].s);

                if(nr<R && nr>=0 && nc<C && nc>=0){ // 격자내에서 이동할 경우 그냥 좌표만 수정
                    sharks[i].r=nr;
                    sharks[i].c=nc;
                }else{
                    nr = Math.abs(nr)%numR; // 음수일수도 있으니까 절댓값하고 나머지 연산
                    nc = Math.abs(nc)%numC;
                    switch(sharks[i].d){
                        case 1:
                        case 2:
                            if(nr>=R){ // 범위 바깥으로 벗어난 경우, nr, 방향 업데이트
                                nr=numR-nr;
                                sharks[i].d=1;
                            }else{ // 범위 안쪽인 경우 방향만 업데이트
                                sharks[i].d=2;
                            }
                            break;
                        case 3:
                        case 4:
                            if(nc>=C){ // 범위 바깥으로 벗어난 경우, nc, 방향 업데이트
                                nc=numC-nc;
                                sharks[i].d=4;
                            }else{ // 범위 안쪽인 경우 방향만 업데이트
                                sharks[i].d=3;
                            }
                            break;
                    }
                    // 좌표 업데이트
                    sharks[i].r=nr;
                    sharks[i].c=nc;
                }

//                System.out.println(sharks[i]);

                if(map[sharks[i].r][sharks[i].c]!=0){ // 이동한 좌표의 map이 0이 아닌경우, 큰놈 냄기고 남은놈 죽임
                    if(sharks[map[sharks[i].r][sharks[i].c]].z < sharks[i].z){
                        sharks[map[sharks[i].r][sharks[i].c]].isDead=true;
                        map[sharks[i].r][sharks[i].c]=i;
                    }else{
                        sharks[i].isDead = true;
                    }
                }else{ // 이동할 좌표의 map이 0인경우 그냥 인덱스값만 map에 업데이트
                    map[sharks[i].r][sharks[i].c]=i;
                }
            }
        }
        // map 리턴
        return map;
    }
}
