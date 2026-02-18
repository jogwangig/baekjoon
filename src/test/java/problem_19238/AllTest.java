package problem_19238;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class AllTest {
	
	
	@Test
	void pointsSortingTest() {
		ArrayList<Point> points = new ArrayList<>(
				List.of(new Point(3, 4), new Point(2, 2), new Point(2, 1), new Point(3, 3)));
		
		Collections.sort(points);
		
		assertEquals(2, points.get(0).getR());
		assertEquals(1, points.get(0).getC());
		
		assertEquals(3, points.get(2).getR());
		assertEquals(3, points.get(2).getC());
//		System.out.println(points);
		
	}
	
	@Test
	void distanceCalculationTest() {
		int[][] m = new int[][] {
			{0,0,0,0,0},
			{0,1,1,1,0},
			{0,1,0,1,0},
			{0,0,0,1,0},
			{0,1,0,0,0}
		};
		
		Map map = new Map(m);
		
		TaxiNavigator tn = new TaxiNavigator();
		
		int d = tn.calculateDistance(new Point(2, 2), new Point(2, 4), map);
		
//		System.out.println(map +"   " + d);
		
		assertEquals(6, d);
		
	}
	
	@Test
	@Disabled
	void copyMapTest() {
		
		int[][] m = new int[][] {
			{0,0,0,0,0},
			{0,1,1,1,0},
			{0,1,0,0,0},
			{0,0,0,1,0},
			{0,1,0,1,0}
		};
		
		Map map = new Map(m);
		
		Map map2 = Map.copy(map);
		
		System.out.println(map + "\n");
		System.out.println(map2);

		
	}
}
