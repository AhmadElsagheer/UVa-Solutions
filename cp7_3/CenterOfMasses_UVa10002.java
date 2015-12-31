package cp7_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CenterOfMasses_UVa10002 {
	
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int n;
		while((n = sc.nextInt()) >= 3)
		{
			Point[] points = new Point[n];
			for(int i = 0; i < n; i++)
				points[i] = new Point(sc.nextDouble(), sc.nextDouble());
			points = CH(points);
			double area = area(points);
			double cx = 0.0,  cy = 0.0;
			
			for(int i = 0; i < points.length - 1; i++)
			{
				cx += (points[i].x + points[i+1].x) * (points[i].x * points[i+1].y - points[i+1].x * points[i].y);
				cy += (points[i].y + points[i+1].y) * (points[i].x * points[i+1].y - points[i+1].x * points[i].y);
			}
			
			cx /= 6 * area;
			cy /= 6 * area;
			out.format("%.3f %.3f\n", cx, cy);
		}
		out.flush();
		
	}
	
	static double area(Point[] g)
	{
		double ret = 0.0;
		for(int i = 0; i < g.length - 1; i++)
			ret += g[i].x * g[i+1].y - g[i].y * g[i+1].x;
		return ret / 2.0;
	}
	
	static Point[] CH(Point[] points)
	{
		Arrays.sort(points);
		int n = points.length;
		Point[] ans = new Point[n<<1];
		int start = 0, size = 0;
		for(int i = 0; i < n; i++)
		{
			Point p = points[i];
			while(size - start >= 2 && !ccw(ans[size-2], ans[size-1], p)) size--;
			ans[size++] = p;
		}
		
		start = --size;
		
		for(int i = n - 1; i >= 0; i--)
		{
			Point p = points[i];
			while(size - start >= 2 && !ccw(ans[size-2], ans[size-1], p)) size--;
			ans[size++] = p;
		}
		return Arrays.copyOf(ans, size);
	}
	
	static boolean ccw(Point a, Point b, Point c)
	{
		return new Vector(a, b).cross(new Vector(a, c)) > 0;
	}
	static class Vector
	{
		double x, y;
		
		Vector(Point a, Point b) { x = b.x - a.x; y = b.y - a.y; }
		
		double cross(Vector v) 
		{
			return x * v.y - y * v.x;
		}
	}
	
	static class Point implements Comparable<Point>
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b; }
		
		public int compareTo(Point p)
		{
			if(Math.abs(x - p.x) > EPS) return x > p.x ? 1 : -1;
			if(Math.abs(y - p.y) > EPS) return y > p.y ? 1 : -1;
			return 0;
		}
		
		public String toString()
		{
			return x + " " + y;
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
