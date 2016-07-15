package v115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Logo2_UVa11519 {
	
	static final double INF = 1e5, EPS = 1e-9;

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt();
			Point start = new Point(0, 0), end = start;
			Vector v = new Vector(0, INF);
			Point missing = null;
			char o = 'x';
			while(n-->0)
			{
				double d = 1;
				char c = sc.next().charAt(0);
				String x = sc.next();
				if(x.equals("?"))
				{
					missing = end;
					o = c;
					continue;
				}
				int steps = Integer.parseInt(x);
				switch(c)
				{
				case 'r':
					d *= -1;
				case 'l':
					d *= steps;
					v = v.rotate(d);
					break;
				case 'f':
					d = steps;
					end = end.translate(v.scale(d / v.norm()));break;
					default:
						d = steps;
						end = end.translate(v.reverse().scale(d / v.norm()));
						
				}
			}
			if(o == 'f' || o == 'b')
				out.println(Math.round(end.dist(start)));
			else
			{
				int ans = (int) Math.round(radToDeg(Point.angle(start, missing, end)));
				if(Point.ccw(start, end, missing))
					ans = 360 - ans;
				if(o == 'r')
					ans = 360 - ans;
				ans %= 360;
				out.println(ans);
			}
		}
		out.flush();
	}
	
	static double radToDeg(double rad)
	{
		return rad * 180 / Math.PI;
	}
	static double degToRad(double deg)
	{
		return deg * Math.PI / 180.0;
	}
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b)
		{
			x = a; y = b;
		}
		
		Point translate(Vector v)
		{
			return new Point(x + v.x, y + v.y);
		}
		

		Point rotate(double angle)
		{
			angle = degToRad(angle);
			double c = Math.cos(angle), s = Math.sin(angle);
			return new Point(x * c - y * s, x * s + y * c);
		}
		
		double dist(Point p)
		{
			return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
		}
		
		boolean equals(Point p)
		{
			return Math.abs(x - p.x) < EPS && Math.abs(y - p.y) < EPS;
		}
		
		static double angle(Point a, Point o, Point b)
		{
			Vector oa = new Vector(o, a), ob = new Vector(o, b);
			return Math.acos(oa.dot(ob) / (oa.norm() * ob.norm()));
		}
		
		static boolean ccw(Point p, Point q, Point r) { return new Vector(p, q).cross(new Vector(p, r)) > 0; }
	}
	
	static class Vector
	{
		double x, y;
		
		Vector(double a, double b)
		{
			x = a; y = b;
		}
		
		Vector(Point p, Point q)
		{
			x = q.x - p.x;
			y = q.y - p.y;
		}
		
		Vector rotate(double angle)
		{
			angle = degToRad(angle);
			double c = Math.cos(angle), s = Math.sin(angle);
			return new Vector(x * c - y * s, x * s + y * c);
		}
		
		Vector reverse()
		{
			return new Vector(-x, -y);
		}
		
		Vector scale(double s)
		{
			return new Vector(x *s, y * s);
		}
		
		double norm()
		{
			return Math.sqrt(x * x + y * y);
		}
		
		double dot(Vector v)
		{
			return x * v.x + y * v.y;
		}
		
		double cross(Vector v)
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
