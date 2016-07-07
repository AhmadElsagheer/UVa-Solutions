package v102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Railway_UVa10263 {

	static final double INF = 1e9, EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(sc.ready())
		{
			Point m = new Point(sc.nextDouble(), sc.nextDouble());
			int n = sc.nextInt();
			Point[] points = new Point[n + 1];
			for(int i = 0; i <= n; i++)
				points[i] = new Point(sc.nextDouble(), sc.nextDouble());
			Point ans = null;
			double d = INF;
			for(int i = 0; i < n; i++)
			{
				Point c = Point.closestPoint(m, points[i], points[i+1]);
				double dist = c.dist(m);
				if(dist < d)
				{
					ans = c;
					d = dist;
				}
			}
			out.printf("%.4f\n%.4f\n", ans.x, ans.y);
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
		
		double dist(Point p) { return Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y));}
		
		Point translate(Vector v)
		{
			return new Point(x + v.x, y + v.y);
		}
		
		static Point closestPoint(Point p, Point a, Point b)
		{
			Vector ap = new Vector(a, p), ab = new Vector(a, b);
			double u = ap.dot(ab) / ab.norm2();
			
			if(u < 0.0) return a;
			if(u > 1.0) return b;
			return a.translate(ab.scale(u));
		}
	}
	
	static class Vector
	{
		double x, y;
		
		Vector(Point p, Point q)
		{
			x = q.x - p.x;
			y = q.y - p.y;
		}
		
		Vector(double a, double b)
		{
			x = a; y = b;
		}
		
		double dot(Vector v)
		{
			return x * v.x + y * v.y;
		}
		
		double norm2()
		{
			return x * x + y * y;
		}
		Vector scale(double u)
		{
			return new Vector(x * u, y * u);
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
