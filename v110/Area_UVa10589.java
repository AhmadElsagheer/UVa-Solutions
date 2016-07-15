package v110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Area_UVa10589 {
	
	static final double EPS = 1e-15;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) break;
			double a = sc.nextDouble();
			
			Circle[] circles = new Circle[4];
			circles[0] = new Circle(new Point(0, 0), a);
			circles[1] = new Circle(new Point(0, a), a);
			circles[2] = new Circle(new Point(a, 0), a);
			circles[3] = new Circle(new Point(a, a), a);
			int m = 0;
			for(int i = 0; i < n; i++)
			{
				Point p = new Point(sc.nextDouble(), sc.nextDouble());
				boolean inside = true;
				for(int j = 0; j < 4 && inside; j++)
					if(!circles[j].inside(p))
						inside = false;
				if(inside)
					m++;
			}
			out.printf("%.5f\n",  a  * a * m / n);
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
		
		boolean inside(Point p)
		{
			double a = (p.x - c.x) * (p.x - c.x) + (p.y - c.y) * (p.y - c.y), b = r * r;
			
			return a <= b + EPS;
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
