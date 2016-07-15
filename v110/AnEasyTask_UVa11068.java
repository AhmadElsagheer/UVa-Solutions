package v110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AnEasyTask_UVa11068 {

	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			int a1 = sc.nextInt(), b1 = sc.nextInt(), c1 = sc.nextInt(), a2 = sc.nextInt(), b2 = sc.nextInt(), c2 = sc.nextInt();
			if(a1 == 0 && b1 == 0 && c1 == 0 && a2 == 0 && b2 == 0 && c2 == 0)
				break;
			Line l1 = new Line(a1, b1, c1), l2 = new Line(a2, b2, c2);
			Point c = l1.intersect(l2);
			if(c == null)
				out.print("No fixed point exists.\n");
			else
				out.printf("The fixed point is at %.2f %.2f.\n", round(c.x), round(c.y));
		}
		out.flush();
	}
	
	static double round(double d) { return Math.round(d * 100) / 100.0; }
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b)
		{
			x = a; y = b;
		}
	}
	static class Line
	{
		double a, b, c;
		
		Line(double x, double y, double z)
		{
			if(Math.abs(y) < EPS)
			{
				a = 1.0; b = 0.0; c = -z / x;
			}
			else
			{
				a = x / y;
				b = 1.0;
				c = -z / y;
			}
						
		}
		
		boolean parallel(Line l)
		{
			return Math.abs(a - l.a) < EPS && Math.abs(b - l.b) < EPS;
		}
		
		Point intersect(Line l)
		{
			if(parallel(l)) return null;
			double x = (b * l.c  - c * l.b) / (a * l.b - b * l.a);
			double y;
			if(Math.abs(b) > EPS)
				y = - a * x - c;
			else
				y = - l.a * x - l.c;
			return new Point(x, y);
			
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
