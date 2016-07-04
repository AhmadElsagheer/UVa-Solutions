package v001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CircleThroughThreePoints_UVa190 {
	
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		
		while(sc.ready())
		{
			Point a = new Point(sc.nextDouble(), sc.nextDouble());
			Point b = new Point(sc.nextDouble(), sc.nextDouble());
			Point c = new Point(sc.nextDouble(), sc.nextDouble());
			
			Triangle t = new Triangle(a, b, c);
			
			Point m = t.circumCircle();
			
			double h = round(m.x), k = round(m.y), r = m.dist(b);
			double g = round(- 2 * m.x), d = round(- 2 * m.y), e = round(m.x * m.x + m.y * m.y - r * r);
		
			out.printf("(x %c %.3f)^2 + (y %c %.3f)^2 = %.3f^2\n", h > 0.0 ? '-' : '+', Math.abs(h), k > 0.0 ? '-' : '+', Math.abs(k), r);
				out.printf("x^2 + y^2 %c %.3fx %c %.3fy %c %.3f = 0\n\n", g < 0.0 ? '-' : '+', Math.abs(g), d < 0.0 ? '-' : '+', Math.abs(d), e < 0.0 ? '-' : '+', Math.abs(e));
		}
		out.flush();

	}
	
	static double round(double d)
	{
		return Math.round(d * 1000) / 1000.0;
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
		
	}
	
	static class Triangle
	{
		Point a, b, c;
		double ab, bc, ca;
		
		Triangle(Point p, Point q, Point r)
		{
			a = p; b = q; c = r;
			ab = a.dist(b); bc = b.dist(c); ca = c.dist(a);
		}
		
		double area()
		{
			double s = 0.5 * (ab + bc + ca);
			return Math.sqrt(s * (s - ab) * (s - bc) * (s - ca));
		}
		
		double rCircumCircle()
		{
			return ab * bc * ca / (4.0 * area());
		}
		
		Point circumCircle()
		{
			double bax = b.x - a.x, bay = b.y - a.y;
			double cax = c.x - a.x, cay = c.y - a.y;
			
			double e = bax * (a.x + b.x) + bay * (a.y + b.y);
			double f = cax * (c.x + a.x) + cay * (a.y + c.y);
			double g = 2.0 * (bax * (c.y - b.y) - bay * (c.x - b.x));
			
			return new Point((cay * e - bay * f) / g, (bax * f - cax * e) / g);
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
		
		public boolean nextEmpty() throws IOException
		{
			st = new StringTokenizer(br.readLine());
			return st.countTokens() == 0;
		}
		
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
