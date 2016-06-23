package cp7_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FourthPoint_UVa10242 {

	static final double EPS = 1e-9;
	
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		
		while(sc.ready())
		{
			Point[] p = new Point[4];
			
			try
			{
				for(int i = 0; i < 4; i++)
					p[i] = new Point(sc.nextDouble(), sc.nextDouble());
			}
			catch(Exception e)
			{
				break;
			}
			
			Point a, b, c;
			if(p[0].equals(p[2]) || p[0].equals(p[3]))
			{
				a = p[0];
				b = p[1];
				if(p[0].equals(p[2]))
					c = p[3];
				else
					c = p[2];
			}
			else
			{
				a = p[1];
				b = p[0];
				if(p[1].equals(p[2]))
					c = p[3];
				else
					c = p[2];
			}
			
			Point ans = c.translate(new Vector(a, b));
			out.printf("%.3f %.3f\n", ans.x, ans.y);
			
		}
		out.flush();

	}
	
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b; }
		
		boolean equals(Point p)
		{
			return Math.abs(x - p.x) < EPS && Math.abs(y - p.y) < EPS;
		}
		
		Point translate(Vector v)
		{
			return new Point(x + v.x, y + v.y);
		}
	}
	
	static class Vector
	{
		double x, y;
		
		Vector(Point a, Point b)
		{
			x = b.x - a.x;
			y = b.y - a.y;
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
