package v113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Rectangles_UVa11345 {
	
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; t++)
		{
			int n = sc.nextInt();
			Rectangle[] rec = new Rectangle[n];
			for(int i = 0; i < n; i++) rec[i] = new Rectangle(new Point(sc.nextInt(), sc.nextInt()), new Point(sc.nextInt(), sc.nextInt()));
			Rectangle intersect = rec[0];
			for(int i = 1; i < n && intersect != null; i++)
			{
				Point ll = new Point(Math.max(rec[i].ll.x, intersect.ll.x), Math.max(rec[i].ll.y, intersect.ll.y));
				Point ur = new Point(Math.min(rec[i].ur.x, intersect.ur.x), Math.min(rec[i].ur.y, intersect.ur.y));
				if(ll.x != ur.x && ll.y != ur.y && rec[i].contains(ll) && rec[i].contains(ur) && intersect.contains(ll) && intersect.contains(ur))
					intersect = new Rectangle(ll, ur);
				else
					intersect = null;
			}
			out.printf("Case %d: %d\n", t, intersect == null ? 0 : intersect.area());
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
		
		boolean contains(Point p)
		{
			return p.x <= ur.x && p.x >= ll.x && p.y <= ur.y && p.y >= ll.y;
		}
		
		int area()
		{
			return (ur.x - ll.x) * (ur.y - ll.y);
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
