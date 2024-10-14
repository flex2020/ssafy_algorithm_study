import java.util.*;
import java.awt.Point;
class Solution {
    static int answer, doneCnt = 0;
    static int[][] visited;
    static Point[] pointArray;
    static Robot[] robotArray;
    static class Robot {
        int r, c, targetIdx = 1;
        int[] path;
        boolean isDone = false;
        Robot (int r, int c, int[] path) {
            this.r = r;
            this.c = c;
            this.path = path;
        }
        public String toString() {
            return "r: " + r + " c:" + c + " path: " + Arrays.toString(path) + " isDone: " + isDone + " targetIdx: " + targetIdx; 
        }
    }
    public int solution(int[][] points, int[][] routes) {
        visited = new int[101][101];
        pointArray = new Point[points.length+1];
        robotArray = new Robot[routes.length+1];
        for (int p = 1; p<=points.length; p++) {
            pointArray[p] = new Point(points[p-1][0], points[p-1][1]);
        }
        for (int r = 0; r<routes.length; r++) {
            robotArray[r+1] = new Robot(pointArray[routes[r][0]].x, pointArray[routes[r][0]].y, routes[r]);
            if (visited[robotArray[r+1].r][robotArray[r+1].c] == 1) answer++;
            visited[robotArray[r+1].r][robotArray[r+1].c]++;
        }
        
        // 모든 로봇이 운송을 마무리할 때까지
        while (true) {
            if (doneCnt == routes.length) {
                break;
            }
            visited = new int[101][101];
            for (int r=1; r<=routes.length; r++) {
                Robot robot = robotArray[r];
                if (robot.isDone) continue;
                if (robot.targetIdx >= robot.path.length) continue;
                Point target = pointArray[robot.path[robot.targetIdx]]; // 목표 운송지점 확인
                
                if (robot.r < target.x) {
                    robot.r++;
                } else if (robot.r > target.x) {
                    robot.r--;
                } else if (robot.c < target.y) {
                    robot.c++;
                } else if (robot.c > target.y) {
                    robot.c--;
                } 
                if (robot.r == target.x && robot.c == target.y) {
                    robot.targetIdx++;
                    if (robot.targetIdx >= robot.path.length) {
                        robot.isDone = true;
                        doneCnt++;
                    }
                    robotArray[r] = robot;
                }
                if (visited[robot.r][robot.c] == 1) {
                    answer++;
                    // System.out.println(robot.r + " " + robot.c);
                }
                visited[robot.r][robot.c]++;
                robotArray[r] = robot;
            }
        }
        
        // System.out.println(Arrays.toString(robotArray));
        return answer;
    }
    
}
