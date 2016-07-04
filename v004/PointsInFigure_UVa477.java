package v004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PointsInFigure_UVa477 {
	
	static final double EPS = 1e-9;
	static final double end = 9999.9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		while(true)
		{
			char c = sc.next().charAt(0);
			if(c == '*')
				break;
			if(c == 'r')
				shapes.add(new Rectangle(new Point(sc.nextDouble(), sc.nextDouble()), new Point(sc.nextDouble(), sc.nextDouble())));
			else
				shapes.add(new Circle(new Point(sc.nextDouble(), sc.nextDouble()), sc.nextDouble()));
		}
		int k = 1;
		while(true)
		{
			double x = sc.nextDouble(), y = sc.nextDouble();
			if(Math.abs(end - x) < EPS && Math.abs(end - y) < EPS)
				break;
			Point p = new Point(x, y);
			boolean free = true;
			for(int i = 0; i < shapes.size(); i++)
				if(shapes.get(i).contains(p))
				{
					free = false;
					out.printf("Point %d is contained in figure %d\n", k, i + 1);
				}
			if(free)
				out.printf("Point %d is not contained in any figure\n", k);
			k++;
		}
		out.flush();
	}
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b; }
		
		double dist(Point p) { return Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y)); } 
	}
	
	static interface Shape
	{
		boolean contains(Point p);
	}
	
	static class Rectangle implements Shape
	{
		Point a, b;
		
		Rectangle(Point p, Point q) { a = p; b = q; }

		
		public boolean contains(Point p) 
		{
			if(p.x + EPS < b.x && p.x > a.x + EPS && p.y + EPS < a.y && p.y > b.y + EPS)
				return true;
			return false;
		}
	}
	
	static class Circle implements Shape
	{
		Point c;
		double r;
		
		Circle(Point p, double k) { c = p; r = k; }
			
		public boolean contains(Point p) {	return p.dist(c) + EPS < r;	}
		
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
		
		public double nextDouble() throws IOException
		{
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}
		
		public boolean ready() throws IOException {return br.ready();}


	}
}
