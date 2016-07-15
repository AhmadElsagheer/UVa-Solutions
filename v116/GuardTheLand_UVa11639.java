package v116;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GuardTheLand_UVa11639 {
	
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; t++)
		{
			Rectangle r1 = new Rectangle(new Point(sc.nextInt(), sc.nextInt()), new Point(sc.nextInt(), sc.nextInt()));
			Rectangle r2 = new Rectangle(new Point(sc.nextInt(), sc.nextInt()), new Point(sc.nextInt(), sc.nextInt()));
			
			Rectangle inter = r1.intersect(r2);
			int a = 0, b = r1.area() + r2.area(), c = new Rectangle(new Point(0, 0), new Point(100, 100)).area();
			if(inter != null) a = inter.area();
			
			
			out.format("Night %d: %d %d %d\n", t, a, b - (a<<1), a + c - b);
			
		}
		out.flush();
	
	}
	
	static class Point
	{
		int x, y;
		
		Point(int a, int b) { x = a; y = b; }
	}
	
	static class Rectangle
	{
		Point ll, ur;
		
		Rectangle(Point a, Point b) { ll = a; ur = b; }
		
		int area() { return (ur.x - ll.x) * (ur.y - ll.y); }
		
		boolean contains(Point p)
		{
			return p.x <= ur.x && p.x >= ll.x && p.y <= ur.y && p.y >= ll.y;
		}
		
		Rectangle intersect(Rectangle r)
		{
			Point ll = new Point(Math.max(this.ll.x, r.ll.x), Math.max(this.ll.y, r.ll.y));
			Point ur = new Point(Math.min(this.ur.x, r.ur.x), Math.min(this.ur.y, r.ur.y));
			if(ll.x != ur.x && ll.y != ur.y && this.contains(ll) && this.contains(ur) && r.contains(ll) && r.contains(ur))
				return new Rectangle(ll, ur);
			return null;
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
