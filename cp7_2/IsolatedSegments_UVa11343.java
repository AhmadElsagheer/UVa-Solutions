package cp7_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.StringTokenizer;

public class IsolatedSegments_UVa11343 {

	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt();
			LineSegment[] segments = new LineSegment[n];
			for(int i = 0; i < n; i++)
				segments[i] = new LineSegment(new Point(sc.nextInt(), sc.nextInt()), new Point(sc.nextInt(), sc.nextInt()));
			
			BitSet isolated = new BitSet(n);
			isolated.set(0, n);
			
			for(int i = 0; i < n; i++)
				for(int j = i + 1; j < n; j++)
					if(segments[i].intersect(segments[j]))
					{
						isolated.clear(i);
						isolated.clear(j);
					}
			out.println(isolated.cardinality());
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
		
		boolean between(Point p, Point q)
		{
			return x <= Math.max(p.x, q.x) + EPS && x + EPS >= Math.min(p.x, q.x) && y <= Math.max(p.y, q.y) + EPS && y + EPS >= Math.min(p.y, q.y);
		}
	}
	
	static class Line
	{
		double a, b, c;
		
		Line(Point p, Point q)
		{
			if(Math.abs(p.x - q.x) < EPS)
			{
				a = 1.0; b = 0.0; c = -p.x;
			}
			else
			{
				a = (p.y - q.y) / (q.x - p.x);
				b = 1.0;
				c = -a * p.x - p.y;
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
			
			double x = (b * l.c - c * l.b) / (a * l.b - b * l.a);
			double y;
			if(Math.abs(b) > EPS)
				y = - a * x - c;
			else
				y = - l.a * x - l.c;
			return new Point(x, y);
		}
	}
	
	static class LineSegment
	{
		Point p, q;
		
		LineSegment(Point a, Point b)
		{
			p = a;
			q = b;
		}
		
		boolean intersect(LineSegment ls)
		{
			Line l1 = new Line(p, q), l2 = new Line(ls.p, ls.q);
			if(l1.parallel(l2))
			{
				if(l1.same(l2))
					if(p.between(ls.p, ls.q) || q.between(ls.p, ls.q) || ls.p.between(p, q) || ls.q.between(p, q))
						return true;
				return false;
			}
			Point c = l1.intersect(l2);
			if(c.between(p, q) && c.between(ls.p, ls.q))
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
					res = Integer.parseInt(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Integer.parseInt(sb.toString()) / f;
			return res * (neg?-1:1);
		}
		
		public boolean ready() throws IOException {return br.ready();}


	}
}
