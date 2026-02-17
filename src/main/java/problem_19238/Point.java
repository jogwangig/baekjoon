package problem_19238;

public  class Point{
	int x,y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		
		if(!(o instanceof Point)) return false;
		
		Point p = (Point)o;
		
		return (p.x == this.x) && (p.y == this.y);
	}
}