package cp7_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Nails_UVa11096 {
	
	
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int l = sc.nextInt(), n = sc.nextInt();
			Point[] p = new Point[n];
		
			for(int i = 0; i < n; i++)
				p[i] = new Point(sc.nextInt(), sc.nextInt());
			
			out.format("%.5f\n", Math.max(perimeter(CH(p)), l));
		}
		out.flush();
		
	}
	
	static Point[] CH(Point[] points)
	{
		Arrays.sort(points);
		int n = points.length;
		int size = 0, start = 0;
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
	
	static double perimeter(Point[] g)
	{
		double ans = 0;
		for(int i = 0; i < g.length - 1; i++)
			ans += g[i].dist(g[i+1]);
		return ans;
	}
	static boolean ccw(Point a, Point b, Point c)
	{
		return new Vector(a, b).cross(new Vector(a, c)) > 0;
	}
	
	static class Vector
	{
		int x, y;
		
		Vector(Point p, Point q)
		{
			x = q.x - p.x;
			y = q.y - p.y;
		}
		
		long cross(Vector v)
		{
			return (long) x * v.y - (long) y * v.x;
		}
	}
	
	static class Point implements Comparable<Point>
	{
		int x, y;
		
		Point(int a, int b) { x = a; y = b; }
		
		public int compareTo(Point p)
		{
			if(x != p.x) return x > p.x ? 1 : -1;
			if(y != p.y) return y > p.y ? 1 : -1;
			return 0;
		}
		
		double dist(Point p)
		{
			return Math.sqrt((double)(p.x - x) * (p.x - x) + (double)(p.y - y) * (p.y - y)); 
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
