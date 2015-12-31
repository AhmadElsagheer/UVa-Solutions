package cp7_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class IntersectingLines_UVa378 {

	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		out.print("INTERSECTING LINES OUTPUT\n");
		
		while(tc-->0)
		{
			Point p1 = new Point(sc.nextInt(), sc.nextInt()), p2 = new Point(sc.nextInt(), sc.nextInt());
			Point q1 = new Point(sc.nextInt(), sc.nextInt()), q2 = new Point(sc.nextInt(), sc.nextInt());
			
			Line l1 = new Line(p1, p2), l2 = new Line(q1, q2);
			if(l1.parallel(l2))
				if(l1.same(l2))
					out.print("LINE\n");
				else
					out.print("NONE\n");
			else
			{
				Point p = l1.intersect(l2);
				out.printf("POINT %.2f %.2f\n", p.x, p.y);
			}
		}
		out.print("END OF OUTPUT\n");
		out.flush();

	}
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b)
		{
			x = a; y = b;
		}
	}
	
	static class Line
	{
		double a, b, c;
		
		Line(Point p, Point q)
		{
			if(Math.abs(p.x - q.x) < EPS)
			{
				a = 1.0;
				b = 0.0;
				c = -p.x;
			}
			else
			{
				a = (p.y - q.y) / (q.x -p.x);
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
			if(parallel(l)) return null;
			double y, x = (b * l.c - c * l.b) / (a * l.b - b * l.a);
			
			if(Math.abs(b) > EPS)
				y = - a * x - c;
			else
				y = - l.a * x - l.c;
			return new Point(x, y);
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
