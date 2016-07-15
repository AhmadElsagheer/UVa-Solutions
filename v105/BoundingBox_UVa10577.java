package v105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BoundingBox_UVa10577 {
	
	static final double EPS = 1e-9, INF = 1e9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int k = 1;
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) break;
			Point p = new Point(sc.nextDouble(), sc.nextDouble());
			Point q = new Point(sc.nextDouble(), sc.nextDouble());
			Point r = new Point(sc.nextDouble(), sc.nextDouble());
			
			Point center = circumCircle(p, q, r);
			
			double theta = 2 * Math.PI / n, maxX, maxY, minX, minY;
			maxX = minX = p.x;
			maxY = minY = p.y;
			
			for(int i = 1; i < n; i++)
			{
				p = p.rotate(theta, center);
				
				maxX = Math.max(p.x, maxX);
				minX = Math.min(p.x, minX);
				maxY = Math.max(p.y, maxY);
				minY = Math.min(p.y, minY);
			}
			
			out.printf("Polygon %d: %.3f\n", k++, (maxX - minX) * (maxY - minY));
		}
		out.flush();
	}
	
	static Point circumCircle(Point a, Point b, Point c) 
	{
		double bax = b.x - a.x, bay = b.y - a.y;
		double cax = c.x - a.x, cay = c.y - a.y;

		double e = bax * (a.x + b.x) + bay * (a.y + b.y);
		double f = cax * (a.x + c.x) + cay * (a.y + c.y);
		double g = 2.0 * (bax * (c.y - b.y) - bay * (c.x - b.x));

		if (Math.abs(g) < EPS) return null;

		return new Point((cay*e - bay*f) / g, (bax*f - cax*e) / g);

	}

	static class Point
	{
		double x, y;
		
		Point(double a, double b)
		{
			x = a; y = b;
		}
		
		double dist(Point p)
		{
			return Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y));
		}
		
		Point rotate(double theta) 
		{
			double c = Math.cos(theta), s = Math.sin(theta);
			return new Point(x * c - y * s, x * s + y * c); 
		}
		
		Point rotate(double theta, Point p)
		{
			Vector v = new Vector(p, new Point(0, 0));
			return translate(v).rotate(theta).translate(v.reverse());
		}
		
		Point translate(Vector v)
		{
			return new Point(x + v.x, y + v.y);
		}
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
			x = q.x - p.x; y = q.y - p.y;
		}
		
		Vector reverse()
		{
			return new Vector(-x, -y);
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
