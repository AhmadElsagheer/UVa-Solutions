	package cp7_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class UselessTilePackers_UVa10065 {
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int k = 1;
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) break;
			
			Point[] p = new Point[n+1];
			for(int i = 0; i < n; i++) p[i] = new Point(sc.nextInt(), sc.nextInt());
			p[n] = p[0];
			double inArea = area(p), totalArea = area(CH(p));
			
			out.printf("Tile #%d\nWasted Space = %.2f %c\n\n", k++, (totalArea - inArea) * 100.0 / totalArea, '%');
		}
		out.flush();
	}
	
	static Point[] CH(Point[] points)
	{
		Arrays.sort(points, 0, points.length - 1);
		int n = points.length - 1;
		int start = 0, size = 0;
		Point[] ans = new Point[n<<1];
		
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
	static double area(Point[] g)
	{
		double ret = 0.0;
		for(int i = 0; i < g.length - 1; i++)
			ret += g[i].x * g[i+1].y - g[i].y * g[i+1].x;
		return Math.abs(ret / 2.0);
	}
	static boolean ccw(Point a, Point b, Point c)
	{
		return new Vector(a, b).cross(new Vector(a, c)) > 0;
	}
	
	static class Point implements Comparable<Point>
	{
		int x, y;
		
		Point(int a, int b) { x = a; y = b; }
		
		public int compareTo(Point p)
		{
			if(x != p.x) return x - p.x;
			return y - p.y;
		}
	}
	
	static class Vector
	{
		int x, y;
		
		Vector(Point p, Point q)
		{
			x = q.x - p.x;
			y = q.y - p.y;
		}
		
		int cross(Vector v)
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
