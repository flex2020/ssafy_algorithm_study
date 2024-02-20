import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;

class Solution
{

    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상

    static class Cell{
        int x; // x 좌표
        int y; // y 좌표
        int type; // 현재상태(활성(3), 비활성(2), 죽음(1), 아무것도 없는 상태(0))
        int life; // 생명력 수치
        int time; // 현재 수치
        int setT; // 업데이트시간

        Cell(int x, int y, int type, int life, int time, int setT){
            this.x = x;
            this.y = y;
            this.type = type;
            this.life = life;
            this.time = time;
            this.setT = setT;
        }

        @Override
        public String toString() {
            return "Cell [x=" + x + ", y=" + y + ", type=" + type + ", life=" + life + ", time=" + time + ", t=" + setT + "]\n";
        }



    }

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            Deque<Cell> dq = new ArrayDeque<>();
            // 배열 맵으로 만든다.
            Cell[][] map = new Cell[700][700];
            int mid = 700/2;
            int t=0;

            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<M;j++){ // 그냥 칸(0)은 스킵
                    int life = Integer.parseInt(st.nextToken());
                    if(life!=0){ // 세포는 map, check, dq에 넣어준다.
                        Cell c = new Cell(i, j, 3, life, life, t);
                        map[mid+i][mid+j]=c;
                        dq.offerLast(new Cell(c.x, c.y, 3, c.life, c.life, t));
                    }
                }
            }
            
            // 맵은 기록, dq는 들어간거, 둘다 업데이트를 같이 해줘야한다.
            int size;
            while(t!=K){
                size = dq.size(); // 시간순으로 구하기 위해 size를 구한다.
//                System.out.println(dq.toString());
//                System.out.println("time = " + t + " size = " + size);
                for(int i=0;i<size;i++) { // size만큼 반복문을 돌린다.
                    Cell c = dq.pollFirst();
                    if(map[mid+c.x][mid+c.y].time != c.time) {
                        continue; // 중복된게 들어올 수 있으므로 continue 처리를 통해 무시한다.
                    }
                    if(c.type<=1){ // 죽은것이므로 map에 표시해주고 끝낸다.
                        map[mid+c.x][mid+c.y] = new Cell(c.x, c.y, 0, c.life, 0, t);
                    }else{ // 죽은게 아니면 증식해야할지 아니면 그냥 내리고 끝낼지 정한다.
                        if(c.type == 2 && c.life == c.time){ // type이 2고, life와 time이 같다면 활성상태라는 뜻이므로 증식해야한다.
                            for(int d=0;d<4;d++){
                                int nx = c.x + dx[d];
                                int ny = c.y + dy[d];
                                // 맵에 뭔가가 이미 있다면 다시 생각해보자.
                                if(map[mid+nx][mid+ny]!=null){
                                    // 일단 지금 현재 세팅된 시간값(전에 세팅된 값 제외)이어야 한다.
                                    // 그리고 지금 증식할 세포의 생명력이 이미 설정된 것보다 강해야한다.
                                    // 그리고 방금 증식된 따끈따근한 거여야 한다.
                                    if(map[mid+nx][mid+ny].setT==t && c.life > map[mid+nx][mid+ny].life
                                    && map[mid+nx][mid+ny].type==3 && map[mid+nx][mid+ny].life==map[mid+nx][mid+ny].time){
                                        map[mid+nx][mid+ny] = new Cell(nx, ny, 3, c.life, c.life, t);
                                        dq.offerLast(new Cell(nx, ny, 3, c.life, c.life, t));
                                    }
                                }
                                // 맵에 null이라면 그냥 추가해주면 된다.
                                else{
                                    map[mid+nx][mid+ny] = new Cell(nx, ny, 3, c.life, c.life, t);
                                    dq.offerLast(new Cell(nx, ny, 3, c.life, c.life, t));
                                }
                            }
                        }

                        // 이외의 경우에는 시간, 타입 변환하고 다시 넣어주면 된다.
                        c.time--;
                        if(c.time==0) {
                            c.type--; // 수명이 다되었다면 다음 타입으로 넘어간다.
                            c.time=c.life;
                        }
                        map[mid+c.x][mid+c.y]=new Cell(c.x, c.y, c.type, c.life, c.time, t);
                        dq.offerLast(new Cell(c.x, c.y, c.type, c.life, c.time, t));
                    }
                }
//                System.out.println("time = " + t + " size = " + size);
//                System.out.println(dq.toString());
                t++;
            }

            size = dq.size();
            for (int i = 0; i < size; i++) { // 마지막으로 중복이거나 죽은거 제외
                Cell c = dq.pollFirst();
                if(map[mid+c.x][mid+c.y].time != c.time || c.type<=1) continue;
                else dq.offerLast(c);
            }

            System.out.println("#" + test_case + " " + dq.size());

        }
    }
}
