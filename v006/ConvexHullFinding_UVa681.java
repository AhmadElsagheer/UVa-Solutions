package v006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class ConvexHullFinding_UVa681 {
		
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		out.println(tc);
		while(tc-->0)
		{
			int n = sc.nextInt();
			TreeSet<Point> set = new TreeSet<Point>();
			Point[] points = new Point[n];
			for(int i = 0; i < n; i++)
			{
				Point p = new Point(sc.nextInt(), sc.nextInt());
				if(set.contains(p)) { n--; i--; }
				else set.add(points[i] = p);
				
			}
			Arrays.sort(points, 0, n);
			
			Point[] ans = new Point[n<<1];
			int size = 0, start = 0;
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

			out.println(size);
			for(int i = 0; i < size; i++)
				out.format("%d %d\n", ans[i].x, ans[i].y);
			if(tc != 0)
			{
				out.println(-1);
				sc.next();
			}
		}
		out.flush();
		
	}
	
	static boolean ccw(Point p, Point q, Point r)
	{
		return new Vector(p, q).cross(new Vector(p, r)) > 0;
	}
	
	static class Vector
	{
		int x, y;
		Vector(Point a, Point b)
		{
			x = b.x - a.x;
			y = b.y - a.y;
		}
		
		int cross(Vector v)
		{
			return x * v.y - y * v.x;
		}
		
	}
	
	static class Point implements Comparable<Point>
	{
		int x, y;
		
		Point(int a, int b) { x = a; y = b; }
		
		public int compareTo(Point p)
		{
			if(y != p.y) return y - p.y;
			return x - p.x;
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
