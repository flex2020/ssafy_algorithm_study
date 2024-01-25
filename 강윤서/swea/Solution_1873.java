package 강윤서.swea;

import java.io.*;
class Position {
    private int y;
    private int x;
    private int dir;
    public int getY() {
        return this.y;
    }
    public int getX() {
        return this.x;
    }
    public int getDir() {
        return this.dir;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setDir(int d) {
        this.dir = d;
    }
}
public class Solution_1873 {
    static int H, W, curY, curX;
    static char[][] board;
    static int[] dy = {1, -1, 0, 0}; // 아래 위 오른쪽 왼쪽
    static int[] dx = {0, 0, 1, -1};
    static char[] dir = {'v', '^', '>', '<'};
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            Position p = new Position();
            String[] input = br.readLine().split(" ");
            H = Integer.parseInt(input[0]);
            W = Integer.parseInt(input[1]);
            board = new char[H][W];
            for (int i=0; i<H; i++) {
                char[] row = br.readLine().toCharArray();
                for (int j=0; j<W; j++) {
                    board[i][j] = row[j];
                    if (board[i][j] == '>' || board[i][j] == '<' || board[i][j] == 'v' || board[i][j] == '^') {
                        // 시작 위치와 방향 저장
                        p.setY(i);
                        p.setX(j);
                        if (board[i][j] == '>') p.setDir(2);
                        else if (board[i][j] == '<') p.setDir(3);
                        else if (board[i][j] == 'v') p.setDir(0);
                        else p.setDir(1);
                    }
                }
            }
            int N = Integer.parseInt(br.readLine());
            char[] cmd = br.readLine().toCharArray();
            for (int i=0; i<N; i++) {
                if (cmd[i] == 'S') {
                    shoot(p.getDir(), p.getY(), p.getX());
                } else {
                    p = action(p, cmd[i]);
                }
            }
            board[p.getY()][p.getX()] = dir[p.getDir()];
            
            System.out.print("#" + tc + " ");
            print();
        }
    }
    public static Position action(Position p, char type) {
        // type: 방향
        if (type == 'R') {
            // 오른쪽을 바라보는 전차
            p = move(p, 2);
        } else if (type == 'L') {
            // 왼쪽을 바라보는 전차
            p = move(p, 3);
        } else if (type == 'D') {
            // 아래쪽을 바라보는 전차
            p = move(p, 0);
        } else if (type == 'U') {
            // 위쪽을 바라보는 전차
            p = move(p, 1);
        }
        return p;
    }
    public static Position move(Position p, int d) {
        int curY = p.getY();
        int curX = p.getX();
        
        int ny = curY + dy[d];
        int nx = curX + dx[d];
        if (0<=ny && ny<H && 0<=nx && nx<W) {
            if (board[ny][nx] == '.') {
                board[curY][curX] = '.';
                board[ny][nx] = dir[d];
                p.setY(ny);
                p.setX(nx);
            }
        }
        p.setDir(d);
        return p;
    }
    public static void shoot(int dir, int y, int x) {
        if (0<=y && y<H && 0<=x && x<W) {
            if (board[y][x] == '*') {
                board[y][x] = '.'; // 벽돌 부수기
                return ;
            } else if (board[y][x] == '#') {
                return ;
            } else {
                shoot(dir, y+dy[dir], x+dx[dir]);
            } 
        }
        return ;
    }
    public static void print() {
        for (int i=0; i<H; i++) {
            for (int j=0; j<W; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}