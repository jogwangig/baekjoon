package problem_19238;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SingleSourceShortestPathCalculator {
	
	public void bfs(Point startPoint, Set<Point> leftPassengers) {
		ArrayDeque<Point> q1 = new ArrayDeque<>();
		ArrayDeque<Point> q2 = new ArrayDeque<>();
		int distance = 0;
		
		q1.add(startPoint);
		
		Point curPoint;
		
		while(isSearchAblePointLeft(q1, q2) && !leftPassengers.isEmpty()) {
			
			while(!q1.isEmpty()) {
				curPoint = q1.pollFirst();
				//갈수 있는 Point 를 q2에 더함
				//curPoint 를 visited로 함 
				//만약 curPoint가 승객이라면 거리계산하고 담기
			}
			distance++;
			//발견된 승객이 존재한다면 그중에서 행과 열이 가장 앞인 걸 반환
			while(!q2.isEmpty()) {
				curPoint = q2.pollFirst();
				//갈수 있는 Point 를 q1에 더함
				//curPoint 를 visited로 함 
				//만약 curPoint가 승객이라면 거리계산하고 담기
			}
			distance++;
			//발견된 승객이 존재한다면 그중에서 행과 열이 가장 앞인 걸 반환
		}
		
		//문제를 출력하고 error를 던짐
		
	}
	
	private boolean isSearchAblePointLeft(ArrayDeque<Point> q1, ArrayDeque<Point> q2) {
		return (!q1.isEmpty() || !q2.isEmpty());
	}
		
	public static class Point{
		int x,y;
	}
}
