package problem_19238;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class TaxiNavigator {
	
	public Point bfs(Point startPoint, Set<Point> leftPassengers, Map map) {
		ArrayDeque<Point> q1 = new ArrayDeque<>();
		ArrayDeque<Point> q2 = new ArrayDeque<>();
		
		ArrayList<Point> results = new ArrayList<Point>();
		
		int distance = 0;
		
		q1.add(startPoint);
		
		Point curPoint;
		//결국 핵심은 점에서 점까지의 거리를 계산해주는 bfs 로직이다
		while(isSearchAblePointLeft(q1, q2) && !leftPassengers.isEmpty()) {
			
			while(!q1.isEmpty()) {
				curPoint = q1.pollFirst();
				
				//만약 curPoint가 승객이라면 거리계산하고 담기
				if (leftPassengers.contains(curPoint)) results.add(curPoint);
				//갈수 있는 Point 를 q2에 더함
				q2.addAll(map.getReachablePoints(curPoint));
				//curPoint 를 visited로 함 
				map.setVisited(curPoint);
			}
			
			if(!results.isEmpty()) {
				Collections.sort(results);
				return results.get(0);
			}
			
			distance++;
			//발견된 승객이 존재한다면 그중에서 행과 열이 가장 앞인 걸 반환
			while(!q2.isEmpty()) {
				curPoint = q2.pollFirst();
				
				//만약 curPoint가 승객이라면 거리계산하고 담기
				if (leftPassengers.contains(curPoint)) results.add(curPoint);
				
				//갈수 있는 Point 를 q1에 더함
				q1.addAll(map.getReachablePoints(curPoint));
				//curPoint 를 visited로 함 
				map.setVisited(curPoint);
			}
			
			if(!results.isEmpty()) {
				Collections.sort(results);
				return results.get(0);
			}
			
			distance++;
			//발견된 승객이 존재한다면 그중에서 행과 열이 가장 앞인 걸 반환
		}
		
		//문제를 출력하고 error를 던짐
		throw new RuntimeException("승객을 찾을 수 없습니다.");
		
	}
	
	
	public int calculateDistance(Point startPoint , Point destPoint, Map map) {
		ArrayDeque<Point> q1 = new ArrayDeque<>();
		ArrayDeque<Point> q2 = new ArrayDeque<>();
		int distance = 0;
		
		q1.add(startPoint);
		
		Point curPoint;
		//결국 핵심은 점에서 점까지의 거리를 계산해주는 bfs 로직이다
		while(isSearchAblePointLeft(q1, q2)) {
			
			System.out.println("================거리 : " + distance);
			while(!q1.isEmpty()) {
				curPoint = q1.pollFirst();
				System.out.println(map);
				//만약 curPoint가 승객이라면 거리계산하고 담기
				if (curPoint.equals(destPoint)) return distance;
				//갈수 있는 Point 를 q2에 더함
				q2.addAll(map.getReachablePoints(curPoint));
				//curPoint 를 visited로 함 
				map.setVisited(curPoint);
				System.out.println(map);
			}
			distance++;
			
			System.out.println("================거리 : " + distance);
			//발견된 승객이 존재한다면 그중에서 행과 열이 가장 앞인 걸 반환
			while(!q2.isEmpty()) {
				curPoint = q2.pollFirst();
				System.out.println(map);
				//만약 curPoint가 승객이라면 거리계산하고 담기
				if (curPoint.equals(destPoint)) return distance;
				//갈수 있는 Point 를 q1에 더함
				q1.addAll(map.getReachablePoints(curPoint));
				
				//curPoint 를 visited로 함 
				map.setVisited(curPoint);
				System.out.println(map);
				
			}
			distance++;
			//발견된 승객이 존재한다면 그중에서 행과 열이 가장 앞인 걸 반환
		}
		
		//문제를 출력하고 error를 던짐
		throw new RuntimeException("Point 사이의 거리를 계산 할 수 없습니다.");
		
	}
	
	
	
	private boolean isSearchAblePointLeft(ArrayDeque<Point> q1, ArrayDeque<Point> q2) {
		return (!q1.isEmpty() || !q2.isEmpty());
	}
		
}
