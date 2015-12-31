package cp7_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CopsAndRobbers_UVa361 {
	
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int k = 1;
		while(true)
		{
			int c = sc.nextInt(), r = sc.nextInt(), o = sc.nextInt();
			if(c == 0 && r == 0 && o == 0) break;
			
			Polygon cops = null, robbers = null;

			Point[] p = new Point[c]; int size = 0;
			TreeSet<Point> set = new TreeSet<Point>();
			for(int i = 0; i < c; i++)
			{
				Point x = new Point(sc.nextInt(), sc.nextInt());
				if(!set.contains(x))
				{
					set.add(x);
					p[size++] = x;
				}

			}
			if(c >= 3)
				cops = new Polygon(p, size);


			p = new Point[r]; size = 0;
			set = new TreeSet<Point>();
			for(int i = 0; i < r; i++)
			{
				Point x = new Point(sc.nextInt(), sc.nextInt());
				if(!set.contains(x))
				{
					set.add(x);
					p[size++] = x;
				}

			}
			
			if(r >= 3)
				robbers = new Polygon(p, size);

			out.format("Data set %d:\n", k++);
			while(o-->0)
			{
				Point citizen = new Point(sc.nextInt(), sc.nextInt());
				out.format("     Citizen at (%d,%d) is %s.\n", citizen.x, citizen.y, cops != null && cops.inside(citizen) ? "safe" : robbers != null && robbers.inside(citizen) ? "robbed" : "neither");
			}
			out.println();
		}
		out.flush();
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
		
		public String toString()
		{
			return x + " " + y;
		}
		
		boolean between(Point a, Point b)
		{
			return x <= Math.max(a.x, b.x) && x >= Math.min(a.x, b.x) && y <= Math.max(a.y, b.y) && y >= Math.min(a.y, b.y);
		}
		
		double dist(Point p) { return Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y)); }
	}
	
	static class Vector
	{
		double x, y;
		
		Vector(Point a, Point b) { x = b.x - a.x; y = b.y - a.y; }
		
		double cross(Vector v) { return x * v.y - y * v.x; }
		
		double dot(Vector v) { return x * v.x + y * v.y; }
		
		double norm2() { return x * x + y * y; }
	}
	
	static boolean ccw(Point a, Point b, Point c)
	{
		return new Vector(a, b).cross(new Vector(a, c)) + EPS >= 0;
	}
	
	static double angle(Point a, Point o, Point b)
	{
		Vector oa = new Vector(o, a), ob = new Vector(o, b);
		if(oa.norm2() < EPS || ob.norm2() < EPS)
			return 0.0;
		return Math.acos(oa.dot(ob) / Math.sqrt(oa.norm2() * ob.norm2()));
	}
	
	
	static class Polygon
	{
		Point[] g;
			
		Polygon(Point[] points, int n)
		{
			if(n < 3)
			{
				g = Arrays.copyOf(points, n);
				return;
			}
			Arrays.sort(points, 0, n);
			g = new Point[n<<1];
			int start = 0, size = 0;
			
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
			if(size < 0)
				size = 0;
			g = Arrays.copyOf(g, size);
		}
		

		boolean inside(Point p)
		{
			if(g.length == 1)
				return g[0].compareTo(p) == 0;
			if(g.length == 2)
			{
				return Math.abs(p.dist(g[0]) + p.dist(g[1]) - g[0].dist(g[1])) < EPS;
			}
			
			double sum = 0.0;
			for(int i = 0; i < g.length - 1; i++)
			{
				double angle = angle(g[i], p, g[i+1]);
				if((Math.abs(angle) < EPS || Math.abs(angle - Math.PI) < EPS) && p.between(g[i], g[i+1]))
					return true;
				if(ccw(p, g[i], g[i+1]))
					sum += angle;
				else
					sum -= angle;
			}
			return Math.abs(2 * Math.PI - Math.abs(sum)) < EPS;
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
