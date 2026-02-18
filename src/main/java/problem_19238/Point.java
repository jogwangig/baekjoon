package problem_19238;

public  class Point implements Comparable<Point> {
	int r,c;
	
	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
	public int getR() {
		return this.r;
	}
	
	public int getC() {
		return this.c;
	}
	
	@Override
	public int compareTo(Point p) {
		if(this.r != p.r)
			return this.r - p.r;
		
		return this.c - p.c;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		
		if(!(o instanceof Point)) return false;
		
		Point p = (Point)o;
		
		return (p.r == this.r) && (p.c == this.c);
	}
	
	@Override
	public String toString() {
		return "(" + r + "," + c + ")";
	}
}