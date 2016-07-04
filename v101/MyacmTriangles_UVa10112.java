package v101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MyacmTriangles_UVa10112 {
	
	static final double EPS = 1e-9;
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) break;
			Point[] points = new Point[n];
			char[] name = new char[n];
			for(int i = 0; i < n; i++)
			{
				name[i] = sc.next().charAt(0);
				 points[i] = new Point(sc.nextInt(), sc.nextInt());
			}
			double max = 0.0;
			String t = null;
			for(int i = 0; i < n; i++)
				for(int j = i + 1; j < n; j++)
					for(int k = j + 1; k < n; k++)
					{
						Point a = points[i], b = points[j], c = points[k];
						double area = area(a, b, c);
						boolean yes = true;
						for(int p = 0; p < n && yes; p++)
							if(p != i && p != j && p != k)
							{
								Point d = points[p];
								double x = area(a, b, d) + area(a, c, d) + area(b, c, d);
								if(Math.abs(x - area) < EPS)
									yes = false;
							}
						if(yes && area > max)
						{
							max = area;
							char[] l = new char[]{name[i], name[j], name[k]};
							Arrays.sort(l);
							t = ""+ l[0] + l[1] + l[2];
						}
					}
			out.println(t);
		}
		out.flush();
	}
	
	static boolean ccw(Point a, Point b, Point c)
	{
		return new Vector(a, b).cross(new Vector(a, c)) > 0;
	}
	
	static double area(Point a, Point b, Point c)
	{
		return 0.5 * Math.abs(((c.y - a.y) * (b.x - a.x) - (b.y - a.y) * (c.x - a.x)));
	}
	
	static class Point
	{
		int x, y;
		
		Point(int a, int b)
		{
			x = a; y = b;
		}
		
		
		double dist(Point p)
		{
			return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
		}
	}
	
	static class Vector
	{
		int x, y;
		
		Vector(Point a, Point b) { x = b.x - a.x; y = b.y - a.y; }
		
		int cross(Vector v) { return x * v.y - y * v.x; }
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
