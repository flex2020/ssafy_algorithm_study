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
        int num = factorial(n - 1) + 1;
        for (int i = 1; i <= n; i++) {
            if (position.x == i) {
                break;
            }
            num++;
        }
        return num;
    }
 
    public static int factorial(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
         
        return n + factorial(n - 1);
    }
 
    public static Position function2 (int num) {
        Position position = new Position();
         
        int n = factorial(position.y - 1) + 1;
        int nextN = factorial(position.y) + 1;
        while (nextN <= num) {
            position.y++;
            n = nextN;
            nextN = factorial(position.y) + 1;
        }
 
        while (n != num) {
            n++;
            position.x++;
            position.y--;
        }
 
        return position;
    }
     
}