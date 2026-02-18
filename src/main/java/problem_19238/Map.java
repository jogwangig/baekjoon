package problem_19238;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

public class Map {
	
	private int[][] map;
	
	private int maxIndex;
	
	public static Map copy(Map m) {
		int size = m.map.length;
		
		int[][] copyM = new int[size][size];
		
		for(int i = 0; i < size; i++)
			for(int j = 0; j <size; j++)
				copyM[i][j] = m.map[i][j];
		
		return new Map(copyM);
	}
	
	public Map(int[][] map) {
		if(map.length != map[0].length) throw new RuntimeException("Map의 가로세로 길이는 동일 해야합니다");
		this.map = map;
		maxIndex = map.length - 1;
	}
	
	public List<Point> getReachablePoints(Point p) {
		
		ArrayList<Point> results = new ArrayList<>();
		
		int r = p.getR();
		int c = p.getC();
		
		if((r - 1 > 0) && (map[r-1][c] == 0)) results.add(new Point(r-1, c));
		if((r + 1 <= maxIndex) && (map[r+1][c] == 0)) results.add(new Point(r+1, c));
		
		if((c - 1 > 0)&& (map[r][c-1] == 0)) results.add(new Point(r, c-1));
		if((c + 1 <= maxIndex)&& (map[r][c+1] == 0)) results.add(new Point(r, c+1));
		
		
		return results;
	}
	
	public void setVisited(Point p) {
		this.map[p.getR()][p.getC()] = -1;
	}
	
	
	@Override
	public String toString() {
		String result = "";
		
		for(int[] r : map) {
			for(int c : r) {
				result += " " + c;
			}
			result += "\n";
		}
		
		return result;
	}

}
