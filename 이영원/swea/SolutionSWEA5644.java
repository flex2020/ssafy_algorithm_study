import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.awt.Point;

class BC{
    int x;
    int y;
    int c;
    int p;
    BC(int x, int y, int c, int p){
        this.x = x;
        this.y = y;
        this.c = c;
        this.p = p;
    }
	@Override
	public String toString() {
		return "BC [x=" + x + ", y=" + y + ", c=" + c + ", p=" + p + "]";
	}
    
}

class Solution
{
    
    static int[][] map;
    static int M;
    static int A;
    static BC[] chargers; // BC 배열
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= 1; test_case++)
		{
            st = new StringTokenizer(br.readLine());
            int answer=0;
            M = Integer.parseInt(st.nextToken()); // 총 이동거리
            A = Integer.parseInt(st.nextToken()); // BC의 개수
            int[] moveA = new int[M];
            int[] moveB = new int[M];
            chargers = new BC[A];
            map = new int[10][10]; 
            
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<M;i++){ // A 이동 입력
                moveA[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<M;i++){ // B 이동 입력
                moveB[i] = Integer.parseInt(st.nextToken());
            }
            for(int i=0;i<A;i++){ // BC 정보 입력(0,0) 시작이니까 입력좌표에서 -1씩 해준다.
                st = new StringTokenizer(br.readLine());
                chargers[i] = new BC(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            
            // A, B 시작포인트 잡기
            Point pointA = new Point(0,0);
            Point pointB = new Point(9,9);
            
            answer+=getMax(pointA, pointB);
            for(int i=0;i<M;i++){ // 점 이동 후 최댓값 구하기
                pointA = move(pointA, moveA[i]);
                pointB = move(pointB, moveB[i]);
//                System.out.println(pointA);
//                System.out.println(pointB);
                answer+=getMax(pointA, pointB);
            }
            
            System.out.println("#" + test_case + " " + answer);
		}
	}
    
	// 겹치는 모든 경우의 수에서 가장 최대의 에너지를 얻을 수 있게 만들기
    private static int getMax(Point pointA, Point pointB){
        int result=0;
        boolean[] checkA = new boolean[A];
        boolean[] checkB = new boolean[A];
        for(int i=0;i<A;i++){
            if(getDist(chargers[i], pointA)) checkA[i]=true;
            if(getDist(chargers[i], pointB)) checkB[i]=true;
        }
        
        for(int i=0;i<A;i++){
            for(int j=0;j<A;j++){
                int sum=0;
                if(!checkA[i] && !checkB[j]) continue;
                else if(checkA[i] && checkB[j]){
                    if(i==j){
                        sum=chargers[i].p;
                    }else{
                        sum=chargers[i].p + chargers[j].p;
                    }
                }else if(checkA[i]){
                    sum = chargers[i].p;
                }else if(checkB[j]){
                    sum = chargers[j].p;
                }
                result = Math.max(result, sum);
            }
        }
//        System.out.println(result);
        return result;
    }
    
    // 거리 계산하기
    private static boolean getDist(BC charger, Point p){
        if(Math.abs(charger.x-p.x) + Math.abs(charger.y - p.y) <= charger.c) return true;
        return false;
    }
    
    // 이동하기(x, y 헷갈리지 않게 조심하자. 평소랑 다를 수 있다.)
    private static Point move(Point p, int dir){
        switch(dir){
            case 0: // 가만히 있기
                return p;
            case 1: // 상
                return new Point(p.x, p.y-1);
            case 2: // 우
                return new Point(p.x+1, p.y);
            case 3: // 하
                return new Point(p.x, p.y+1);
            case 4: // 좌
                return new Point(p.x-1, p.y);
        }
        return p;
    }
}
