package v002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MothEradication_UVa218 {
		
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int k = 1;
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) break;
			if(k != 1)
				out.println();
			out.format("Region #%d:\n", k++);
			
			Point[] points = new Point[n];
			for(int i = 0; i < n; i++)
				points[i] = new Point(sc.nextDouble(), sc.nextDouble());
			
			Polygon p = new Polygon(points);
			for(int i = p.g.length - 1; i >= 0; i--)
			{
				out.format("(%.1f,%.1f)", p.g[i].x, p.g[i].y);
				if(i != 0)
					out.print('-');
			}
			out.format("\nPerimeter length = %.2f\n", p.perimeter());
		}
		out.flush();
		
	}
	
	static class Point implements Comparable<Point>
	{
		double x, y;
		
		Point(double a, double b)
		{
			x = a; y = b;
		}
		
		public int compareTo(Point p)
		{
			if(Math.abs(x - p.x) > EPS)
				return x > p.x ? 1 : -1;
			if(Math.abs(y - p.y) > EPS)
				return y > p.y  ? 1 : -1;
			return 0;
		}
		
		double dist(Point p)
		{
			return Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y));
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
		
		double cross(Vector v)
		{
			return x * v.y - y * v.x;
		}
		
		double dot(Vector v)
		{
			return x * v.x + y * v.y;
		}
		
		double norm2()
		{
			return x * x + y * y;
		}
	}
	
	static double angle(Point a, Point o, Point b)
	{
		Vector oa = new Vector(o, a), ob = new Vector(o, b);
		return Math.acos(oa.dot(ob) / Math.sqrt(oa.norm2() * ob.norm2()));
	}
	static boolean ccw(Point p, Point q, Point r)
	{
		return new Vector(p, q).cross(new Vector(p, r)) + EPS >= 0;
	}
	
	static class Polygon
	{
		Point[] g;
		
		Polygon(Point[] points)
		{
			Arrays.sort(points);
			int n = points.length;
			g = new Point[n<<1];
			int size = 0, start = 0;
			
			for(int i = 0; i < n; i++)
			{
				Point p = points[i];
				while(size - start >= 2 && !ccw(g[size-2], g[size-1], p)) size--;
				g[size++] = p;
			}
			start = --size;
			
			for(int i = n - 1; i >= 0; i--)
			{
				Point p = points[i];
				while(size - start >= 2 && !ccw(g[size-2], g[size-1], p)) size--;
				g[size++] = p;
			}
			
			g = Arrays.copyOf(g, size);
		}
		
		double perimeter()
		{
			double sum = 0.0;
			for(int i = 0; i < g.length - 1; i++)
				sum += g[i].dist(g[i+1]);
			return sum;
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
