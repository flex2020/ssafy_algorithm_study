import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int answer = 0;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine());
            String input;
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            char[][] field = new char[H][W];
            int[] tank = new int[3]; // 탱크 현재위치 행, 열, 바라보는 방향(1:상, 2:하, 3:좌, 4:우)

            for(int i=0;i<H;i++){ // 필드입력
                input = br.readLine();
                for(int j=0;j<W;j++){
                    field[i][j]=input.charAt(j);
                    if(field[i][j]=='^' || field[i][j]=='v' || field[i][j]=='<' || field[i][j]=='>'){
                        tank[0]=i;
                        tank[1]=j;
                        switch(field[i][j]){
                            case '^':
                                tank[2]=1;
                                break;
                            case 'v':
                                tank[2]=2;
                                break;
                            case '<':
                                tank[2]=3;
                                break;
                            case '>':
                                tank[2]=4;
                                break;
                        }
                    }
                }
            }

            int N = Integer.parseInt(br.readLine()); // 사용자 입력
            char[] fieldInput = new char[N];
            input = br.readLine();
            for(int i=0;i<N;i++){
                fieldInput[i] = input.charAt(i);
                switch(fieldInput[i]){
                    case 'U':
                        if(tank[0]-1>=0 && field[tank[0]-1][tank[1]]=='.'){
                            field[tank[0]][tank[1]]='.';
                            field[tank[0]-1][tank[1]]='^';
                            tank[0]--;
                            tank[2]=1;
                        }else{
                            field[tank[0]][tank[1]]='^';
                            tank[2]=1;
                        }
                        break;
                    case 'D':
                        if(tank[0]+1<H && field[tank[0]+1][tank[1]]=='.'){
                            field[tank[0]][tank[1]]='.';
                            field[tank[0]+1][tank[1]]='v';
                            tank[0]++;
                            tank[2]=2;
                        }else{
                            tank[2]=2;
                            field[tank[0]][tank[1]]='v';
                        }
                        break;
                    case 'L':
                        if(tank[1]-1>=0 && field[tank[0]][tank[1]-1]=='.'){
                            field[tank[0]][tank[1]]='.';
                            field[tank[0]][tank[1]-1]='<';
                            tank[1]--;
                            tank[2]=3;
                        }else{
                            tank[2]=3;
                            field[tank[0]][tank[1]]='<';
                        }
                        break;
                    case 'R':
                        if(tank[1]+1<W && field[tank[0]][tank[1]+1]=='.'){
                            field[tank[0]][tank[1]]='.';
                            field[tank[0]][tank[1]+1]='>';
                            tank[1]++;
                            tank[2]=4;
                        }else{
                            tank[2]=4;
                            field[tank[0]][tank[1]]='>';
                        }
                        break;
                    case 'S':
                        int mv;
                        switch(tank[2]){
                            case 1:
                                mv = tank[0];
                                while(mv>=0){
                                    if(field[mv][tank[1]]=='*'){
                                        field[mv][tank[1]]='.';
                                        break;
                                    }else if(field[mv][tank[1]]=='#') break;
                                    mv--;
                                }
                                break;
                            case 2:
                                mv = tank[0];
                                while(mv<H){
                                    if(field[mv][tank[1]]=='*'){
                                        field[mv][tank[1]]='.';
                                        break;
                                    }else if(field[mv][tank[1]]=='#') break;
                                    mv++;
                                }
                                break;
                            case 3:
                                mv = tank[1];
                                while(mv>=0){
                                    if(field[tank[0]][mv]=='*'){
                                        field[tank[0]][mv]='.';
                                        break;
                                    }else if(field[tank[0]][mv]=='#') break;
                                    mv--;
                                }
                                break;
                            case 4:
                                mv = tank[1];
                                while(mv<W){
                                    if(field[tank[0]][mv]=='*'){
                                        field[tank[0]][mv]='.';
                                        break;
                                    } else if(field[tank[0]][mv]=='#') break;
                                    mv++;
                                }
                                break;
                        }
                        break;
                }
            }


            // 출력부
            System.out.print("#" + test_case + " ");
            for(int i=0; i<H;i++){
                for(int j=0;j<W;j++){
                    System.out.print(field[i][j]);
                }
                System.out.println();
            }
        }
    }
}
