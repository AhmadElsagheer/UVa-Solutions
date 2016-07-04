package v006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Polygon_UVa634 {
		
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) break;
			
			Point[] points = new Point[n+1];
			for(int i = 0; i < n; i++) points[i] = new Point(sc.nextInt(), sc.nextInt());
			points[n] = points[0];
			Point p = new Point(sc.nextInt(), sc.nextInt());
			int c = 0;
			for(int i = 1; i <= n; i++)
				if(points[i].x == points[i-1].x && points[i].x < p.x && p.betweenY(points[i-1], points[i]))
					c ^= 1;
			if((c & 1) == 1)
				out.println('T');
			else
				out.println('F');
		}
		out.flush();
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
		
		boolean betweenY(Point p, Point q)
		{
			return y < q.y && y > p.y || y < p.y && y > q.y;
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
