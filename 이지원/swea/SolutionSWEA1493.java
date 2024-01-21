import java.util.Scanner;
 
class Position {
    int x;
    int y;
 
    Position () {
        this.x = 1;
        this.y = 1;
    }
 
    Position (int x, int y) {
        this.x = x;
        this.y = y;
    }
}
 
public class SolutionSWEA1493 {

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
 
        for(int test_case = 1; test_case <= T; test_case++)
        {
            Position positionA = function2(sc.nextInt());
            Position positionB = function2(sc.nextInt());
 
            int result = function1(new Position(positionA.x + positionB.x, positionA.y + positionB.y));
             
            System.out.println("#" + test_case + " " + result);
        }
    }
 
    public static int function1 (Position position) {
        int n = position.x + position.y - 1;
        int num = nSum(n - 1) + position.x;
        return num;
    }
 
    public static int nSum(int n) {
        if (n == 0) {
            return 0;
        }
        
        return n * (n + 1) / 2;
    }
 
    public static Position function2 (int num) {
        Position position = new Position();
         
        int n = nSum(position.y - 1) + 1;
        int nextN = nSum(position.y) + 1;
        while (nextN <= num) {
            position.y++;
            n = nextN;
            nextN = nSum(position.y) + 1;
        }
 
        while (n != num) {
            n++;
            position.x++;
            position.y--;
        }
 
        return position;
    }
     
}