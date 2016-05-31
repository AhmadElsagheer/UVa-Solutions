package cp7_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class WaterFalls_UVa833 {
	/*
	 * This solution is missing the case of two parallel line segments one above the other with the
	 * lower segment having the greater maxY and minY. If the source is above the upper line segment
	 * the algorithm will mistakenly consider the lower one to be hit first. To solve this problem,
	 * one can simulate for every source consider the next hit to be the highest intersection with
	 * the vertical line passing through the source point. Thanks for abdo_shabrawy and MostafaAbdullah.
	 * 
	 */
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int lines = sc.nextInt();
			LineSegment[] l = new LineSegment[lines];
			for(int i = 0; i < lines; i++)
				l[i] = new LineSegment(new Point(sc.nextInt(), sc.nextInt()), new Point(sc.nextInt(), sc.nextInt()));
			Arrays.sort(l);
			
			int points = sc.nextInt();
			while(points-->0)
			{
				Point s = new Point(sc.nextInt(), sc.nextInt());
				for(int i = 0; i < lines; i++)
					if(s.above(l[i]))
						s = l[i].getLower();
					
				out.println(s.x);
			}
			if(tc != 0)
				out.println();
		}
		out.flush();


	}
	
	static class Point
	{
		int x, y;
		
		Point(int a, int b)
		{
			x = a; y = b;
		}
		
		boolean above(LineSegment l)
		{
			return x <= l.q.x && x >= l.p.x && ccw(l.p, l.q);
		}
		
		boolean ccw(Point a, Point b)
		{
			return new Vector(a,b).cross(new Vector(a, this)) > 0;
		}
		
		
		
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
	
	static class LineSegment implements Comparable<LineSegment>
	{
		Point p, q;
		LineSegment(Point a, Point b)
		{
			if(a.x > b.x)
			{
				q = a;
				p = b;
			}
			else
			{
				p = a;
				q = b;
			}
		}
		
		Point getLower()
		{
			if(p.y < q.y)
				return p;
			return q;
		}
		
		public int compareTo(LineSegment o) {
			if(Math.max(p.y, q.y) != Math.max(o.p.y, o.q.y))
				return Math.max(o.p.y, o.q.y) - Math.max(p.y, q.y);
			return Math.min(o.p.y, o.q.y) - Math.min(p.y, q.y);
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
			if(dec)
				res += Integer.parseInt(sb.toString()) / f;
			return res * (neg?-1:1);
		}
		public boolean ready() throws IOException {return br.ready();}


	}
}
