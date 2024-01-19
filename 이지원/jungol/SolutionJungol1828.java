import java.util.Scanner;
import java.util.Arrays;

class Refrigerator {
    int minTemperature;
    int maxTemperature;

    Refrigerator (int minTemperature, int maxTemperature) {
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }
}

public class SolutionJungol1828 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Refrigerator[] refrigerators = new Refrigerator[n];
    
        for (int i = 0; i < n; i++) {
            refrigerators[i] = new Refrigerator(sc.nextInt(), sc.nextInt());
        }
        Arrays.sort(refrigerators, (r1, r2) -> Integer.compare(r1.maxTemperature, r2.maxTemperature));
        
        int count = 1;
        int maxTemperature = refrigerators[0].maxTemperature;
        for (int i = 1; i < refrigerators.length; i++) {
            if (refrigerators[i].minTemperature > maxTemperature) {
                count++;
                maxTemperature = refrigerators[i].maxTemperature;
            }
        }
        
        System.out.println(count);
    }
    
}