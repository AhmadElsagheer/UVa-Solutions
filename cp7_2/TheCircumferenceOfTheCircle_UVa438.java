package cp7_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TheCircumferenceOfTheCircle_UVa438 {
	
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		
		while(sc.ready())
		{
			Point a = new Point(sc.nextDouble(), sc.nextDouble());
			Point b = new Point(sc.nextDouble(), sc.nextDouble());
			Point c = new Point(sc.nextDouble(), sc.nextDouble());
			
			Triangle t = new Triangle(a, b, c);
			
			out.printf("%.2f\n", Math.PI * 2.0 * t.rCircumCircle());
		}
		out.flush();
	
	}
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b; }
		
		double dist(Point p)
		{
			return Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y));
		}
	}
	
	static class Triangle
	{
		double a, b, c;
		
		Triangle(Point p, Point q, Point r)
		{
			a = p.dist(q); b = q.dist(r); c = r.dist(p);
		}
		
		double area()
		{
			double s = (a + b + c) / 2.0;
			return Math.sqrt(s * (s - a) * (s - b) * (s - c));
		}
		
		double rCircumCircle()
		{
			return a * b * c / (4.0 * area());
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
