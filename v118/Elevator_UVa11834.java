package v118;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Elevator_UVa11834 {
	
	static final double EPS = 1e-9;
	static final double sqrt2 = Math.sqrt(2);
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int l = sc.nextInt(), w = sc.nextInt(), r1 = sc.nextInt(), r2 = sc.nextInt();
			
			if(l == 0) break;
			
			if(r1 << 1 > l || r1 <<1 > w || r2 << 1 > l || r2 << 1 > w)
				out.format("%c\n", 'N');
			else
			{
				Point a = new Point(0, 0), b = new Point(l, w);
				Vector v = new Vector(1.0 / sqrt2, 1.0 / sqrt2);
				
				Circle c1 = new Circle(a.translate(v.scale(r1 * sqrt2)), r1);
				Circle c2 = new Circle(b.translate(v.scale(-r2 * sqrt2)), r2);
				out.format("%c\n", c1.intersect(c2) ? 'N' : 'S');				
			}
		}
		
		out.flush();
	}
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b)
		{
			x = a; y = b;
		}
		
		double dist2(Point p)
		{
			return (x - p.x) * (x - p.x) + (y -p.y) * (y -p.y);
		}
		
		Point translate(Vector v)
		{
			return new Point(x + v.x, y + v.y);
		}
	}
	
	static class Vector
	{
		double x, y;
		
		Vector(Point p, Point q)
		{
			x = q.x - p.x;
			y = q.y - p.y;
		}
		
		Vector(double a, double b)
		{
			x = a; y = b;
		}
		
		Vector scale(double s)
		{
			return new Vector(x * s, y * s);
		}
	}
	
	static class Circle
	{
		Point c;
		double r;
		
		Circle(Point p, double k)
		{
			c = p;
			r = k;
		}
		
		boolean intersect(Circle cir)
		{
			return c.dist2(cir.c) + EPS < (r + cir.r) * (r + cir.r);
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
