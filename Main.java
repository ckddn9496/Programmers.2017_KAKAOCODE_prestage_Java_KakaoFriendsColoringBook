import java.util.Arrays;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		int m = 6;
		int n = 4;
		int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};

		long start = System.currentTimeMillis();
		System.out.println(Arrays.toString(new Solution().solution(m, n, picture)));
		long end = System.currentTimeMillis();
		System.out.println("실행 시간 : " + (end - start) / 1000.0);
	}

}
class Solution {
	class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	int MAX_WIDTH, MAX_HEIGHT;
	public int[] solution(int m, int n, int[][] picture) {
		int numberOfArea = 0;
	    int maxSizeOfOneArea = 0;
	    this.MAX_HEIGHT = m; this.MAX_WIDTH = n;
	    boolean[][] visit = new boolean[m][n];
		
//	    printPicture(picture);
	    
	    for (int i = 0; i < m; i++) {
	    	for (int j = 0; j < n; j++) {
	    		Stack<Point> s = new Stack<>();
	    		if (picture[i][j] != 0 && visit[i][j] == false) {
	    			s.add(new Point(i,j));
	    			visit[i][j] = true;
	    		} else
	    			continue;
	    		
	    		int sizeOfArea = 0;
	    		while (!s.isEmpty()) {
	    			Point p = s.pop();
	    			sizeOfArea++;
//	    			System.out.println("color " + picture[p.x][p.y] + " pos : (" + p.x + ", " + p.y + ")");
	    			if (!isBound(p.x, p.y-1)) {
	    				if (picture[p.x][p.y-1] == picture[i][j] && !visit[p.x][p.y-1]) {
		    				s.add(new Point(p.x,p.y-1));
		    				visit[p.x][p.y-1] = true;	    					
	    				}
	    			}
	    			if (!isBound(p.x, p.y+1)) {
	    				if (picture[p.x][p.y+1] == picture[i][j] && !visit[p.x][p.y+1]) {
	    					s.add(new Point(p.x,p.y+1));
		    				visit[p.x][p.y+1] = true;
	    				}
    				}
	    			if (!isBound(p.x-1, p.y)) {
	    				if (picture[p.x-1][p.y] == picture[i][j] && !visit[p.x-1][p.y]) {
		    				s.add(new Point(p.x-1,p.y));
		    				visit[p.x-1][p.y] = true;	    					
	    				}
	    			}
	    			if (!isBound(p.x+1, p.y)) {
	    				if (picture[p.x+1][p.y] == picture[i][j] && !visit[p.x+1][p.y]) {
		    				s.add(new Point(p.x+1,p.y));
		    				visit[p.x+1][p.y] = true;	    					
	    				}
	    			}
	    		}
	    		
	    		if (maxSizeOfOneArea < sizeOfArea)
	    			maxSizeOfOneArea = sizeOfArea;
	    		numberOfArea++;
	    	}
	    }
	    
	    int[] answer = new int[2];
	    answer[0] = numberOfArea;
	    answer[1] = maxSizeOfOneArea;
	    return answer;
   	}

	boolean isBound(int row, int col) {
		if (row < 0 || row >= MAX_HEIGHT || col < 0 || col >= MAX_WIDTH)
			return true;
		return false;
	}
	
	void printPicture(int[][] picture) {
		for (int[] row : picture) {
			for (int col : row)
				System.out.print(String.format("%2d ", col));
			System.out.println();
		}
	}
}