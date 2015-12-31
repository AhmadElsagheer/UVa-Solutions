package cp7_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TheOtherTwoTrees_UVa10250 {

	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			Point p = new Point(sc.nextDouble(), sc.nextDouble()), q = new Point(sc.nextDouble(), sc.nextDouble());
			
			Point r = p.midPoint(q);
			Vector v = new Vector(p, q);
			v.rotate90();
			
			Point s = r.translate(v.scale(0.5));
			v.reverse();
			Point t = r.translate(v.scale(0.5));
			
			out.printf("%.10f %.10f %.10f %.10f\n", t.x, t.y, s.x, s.y);
		}
		out.flush();
	}
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b; }
		
		Point translate(Vector v) { return new Point(x + v.x, y + v.y); }
		
		double dist(Point p) { return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y)); }
		
		Point midPoint(Point p) { return new Point((x + p.x) / 2.0, (y + p.y) / 2.0); }
	}
	
	static class Vector
	{
		double x,y;
		
		Vector(double a, double b) { x = a; y = b; }
		
		Vector(Point p, Point q) { x = q.x - p.x; y = q.y - p.y; }
		
		double norm2() { return x * x + y * y; }
		
		Vector scale(double s) { return new Vector(x * s, y * s); }
		
		void rotate90()
		{
			double tmp = x;
			x = -y;
			y = tmp;
		}
		
		void reverse()
		{
			x *= -1;
			y *= -1;
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
