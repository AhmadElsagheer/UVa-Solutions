package cp7_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Intersection_UVa191 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			Point start = new Point(sc.nextInt(), sc.nextInt()), end = new Point(sc.nextInt(), sc.nextInt());
			
			Point[] rec = new Point[4];
			
			rec[0] = new Point(sc.nextInt(), sc.nextInt());
			rec[2] = new Point(sc.nextInt(), sc.nextInt());
			rec[1] = new Point(rec[2].x, rec[0].y);
			rec[3] = new Point(rec[0].x, rec[2].y);
			
			if(start.between(rec[0], rec[2]) || end.between(rec[0], rec[2]))
				out.println('T');
			else
			{
				boolean intersect = false;
				Line x = new Line(start, end);
				for(int i = 0; i < 4 && !intersect; i++)
				{
					Line y = new Line(rec[i], rec[(i+1)%4]);
					if(x.parallel(y))
					{
						if(!x.same(y))
							continue;
						if(rec[i].between(start, end) || rec[(i+1)%4].between(start, end))
							intersect = true;
					}
					else
					{
						Point z = x.intersect(y);
						if(z.between(start, end) && z.between(rec[i], rec[(i+1)%4]))
							intersect = true;
					}
				}
				out.println(intersect?'T':'F');
			}
			
		}
		out.flush();
	
	}
	
	static final double EPS = 1e-9;
	
	static class Line
	{
		double a, b, c;
		
		Line(Point p, Point q)
		{
			if(p.x == q.x)
			{
				a = 1.0; b = 0.0; c = -p.x;
			}
			else
			{
				a = (double) (p.y - q.y) / (q.x - p.x);
				b = 1.0;
				c = - a * p.x - p.y;
			}
		}
		
		boolean parallel(Line l)
		{
			return Math.abs(a - l.a) < EPS && Math.abs(b - l.b) < EPS;
		}
		
		boolean same(Line l)
		{
			return parallel(l) && Math.abs(c - l.c) < EPS;
		}
		
		Point intersect(Line l)
		{
			if(this.parallel(l)) return null;
			
			double x = (b * l.c - c * l.b) / (a * l.b - b * l.a);
			double y;
			if(Math.abs(b) > EPS) y = -(a * x + c);
			else				  y = -(l.a * x + l.c);
			
			return new Point(x,y);
		}
	}
	
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b;}
		
		boolean between(Point a, Point b)
		{
			if(Math.min(a.x, b.x) <= x + EPS && EPS + Math.max(a.x, b.x) >= x && Math.max(a.y, b.y) + EPS >= y && Math.min(a.y, b.y) <= y + EPS)
				return true;
			return false;
		}
	}

	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready();}


	}
}
