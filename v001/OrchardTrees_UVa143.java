package v001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class OrchardTrees_UVa143 {
	
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(true)
		{
			Point a = new Point(sc.nextDouble(), sc.nextDouble());
			Point b = new Point(sc.nextDouble(), sc.nextDouble());
			Point c = new Point(sc.nextDouble(), sc.nextDouble());
			if(a.x + a.y + b.x + b.y + c.x + c.y < EPS)
				break;
			
			boolean collinear = Point.collinear(a, b, c);
				
			if(c.ccw(a, b) == 0)
			{
				Point tmp = b;
				b = c;
				c = tmp;
			}

			int count = 0;
			for(int i = 1; i < 100; i++)
				for(int j = 1; j < 100; j++)
				{
					Point t = new Point(i, j);
					if(collinear)
					{
						if(Point.collinear(a, b, t) && (t.between(a, b) || t.between(c, b) || t.between(a, c)))
							count++;
					}
					else
					{
						int s = t.ccw(a, b) + t.ccw(b, c) + t.ccw(c, a);
						if(s == 3)
							count++;
					}
					
				}
			out.printf("%4d\n", count);
		}
		out.flush();
	}
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b)
		{
			x = a; y = b;
		}
		
		int ccw(Point a, Point b)
		{
			Vector ab = new Vector(a, b), ap = new Vector(a, this);
			return ab.cross(ap) > 0 || Math.abs(ab.cross(ap)) < EPS? 1 : 0;
		}
		
		boolean between(Point a, Point b)
		{
			return x <= Math.max(a.x, b.x) + EPS && x + EPS >= Math.min(a.x, b.x) + EPS && y + EPS >= Math.min(a.y, b.y) && y <= Math.max(a.y, b.y) + EPS;
		}
		
		static boolean collinear(Point p, Point q, Point r) { return Math.abs(new Vector(p, q).cross(new Vector(p, r))) < EPS; }
	}
	
	static class Vector
	{
		double x, y;
		
		Vector(Point a, Point b)
		{
			x = b.x - a.x;
			y = b.y - a.y;
		}
		
		double cross(Vector v)
		{
			return x * v.y - y * v.x;
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
		
		public boolean nextEmpty() throws IOException
		{
			st = new StringTokenizer(br.readLine());
			return st.countTokens() == 0;
		}
		
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
