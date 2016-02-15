package cp7_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SunnyMountains_UVa920 {

	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt();
			Point[] points = new Point[n];
			for(int i = 0; i < n; i++)
				points[i] = new Point(sc.nextInt(), sc.nextInt());
			Arrays.sort(points);
			double ans = 0, lo = 0;
			for(int i = 1; i < n; i++)
				if(points[i].y > lo + EPS)
				{
					Line l = new Line(points[i-1], points[i]);
					if(!l.isHorizontal())
					{
						Point p = new Point(l.getX(lo), lo);
						ans += p.dist(points[i]);
					}
					lo = points[i].y;
				}
			out.printf("%.2f\n",ans);
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
				return p.x > x ? 1 : -1;
			return p.x > y ? 1 : -1;
		}
		
		public double dist(Point p)
		{
			return Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y));
		}
	}
	
	static class Line
	{
		double a, b, c;
		
		Line(Point p, Point q)
		{
			if(Math.abs(q.x - p.x) < EPS)
			{
				a = 1.0;
				b = 0.0;
				c = -p.x;
			}
			else
			{
				a = (p.y - q.y) / (q.x - p.x);
				b = 1.0;
				c = - a * p.x - p.y;
			}
		}
		
		boolean isHorizontal()
		{
			return Math.abs(a) < EPS;
		}
		double getX(double y)
		{
			return (-c - b * y) / a;
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
