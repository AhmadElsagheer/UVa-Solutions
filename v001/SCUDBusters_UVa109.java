package v001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SCUDBusters_UVa109 {
		
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		
		ArrayList<Kingdom> k = new ArrayList<Kingdom>(20);
		while(true)
		{
			int n = sc.nextInt();
			if(n == -1) break;
			
			Point[] sites = new Point[n];
			for(int i = 0; i < n; i++) sites[i] = new Point(sc.nextInt(), sc.nextInt());
			k.add(new Kingdom(sites));
		}
		
		while(sc.ready())
		{
			Point missile = new Point(sc.nextInt(), sc.nextInt());
			for(int i = 0; i < k.size(); i++)
			{
				Kingdom cur = k.get(i);
				if(!cur.destroyed && cur.inside(missile))
					cur.destroyed = true;
			}
		}
		
		double ans = 0.0;
		for(int i = 0; i < k.size(); i++)
		{
			Kingdom cur = k.get(i);
			if(cur.destroyed)
				ans += cur.area();
		}
		System.out.printf("%.2f\n", ans);
	}
	
	static boolean ccw(Point p, Point q, Point r)
	{
		return new Vector(p, q).cross(new Vector(p, r)) > 0;
	}
	
	static double angle(Point a, Point o, Point b)
	{
		Vector oa = new Vector(o, a), ob = new Vector(o, b);
		return Math.acos(oa.dot(ob) / Math.sqrt(oa.norm2() * ob.norm2()));
	}
	
	static class Kingdom
	{
		Point[] g;
		boolean destroyed;
		
		Kingdom(Point[] points) 
		{ 
			int n = points.length;
			Arrays.sort(points);
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
		
		double area()
		{
			double ret = 0.0;
			for(int i = 0; i < g.length - 1; i++)
				ret += g[i].x * g[i+1].y - g[i+1].x * g[i].y;
			return Math.abs(ret) / 2.0;
		}
		
		boolean inside(Point p)
		{
			double sum = 0.0;
			for(int i = 0; i < g.length - 1; i++)
			{
				double angle = angle(g[i], p, g[i+1]);
				if(ccw(p, g[i], g[i+1]))
					sum += angle;
				else
					sum -= angle;
			}
			return Math.abs(2 * Math.PI - Math.abs(sum)) < EPS;
		}
				
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
		
		int dot(Vector v)
		{
			return x * v.x + y * v.y;
		}
		
		double norm2()
		{
			return x * x + y * y;
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
