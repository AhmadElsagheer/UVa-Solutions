package v004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class OverlappingRectangles_UVa460 {
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			Rectangle a = new Rectangle(new Point(sc.nextInt(), sc.nextInt()), new Point(sc.nextInt(), sc.nextInt()));
			Rectangle b = new Rectangle(new Point(sc.nextInt(), sc.nextInt()), new Point(sc.nextInt(), sc.nextInt()));
			
			Point ll = new Point(Math.max(a.ll.x, b.ll.x), Math.max(a.ll.y, b.ll.y));
			Point ur = new Point(Math.min(a.ur.x, b.ur.x), Math.min(a.ur.y, b.ur.y));
			if(ll.x != ur.x && ll.y != ur.y && a.contains(ll) && a.contains(ur) && b.contains(ll) && b.contains(ur))
				out.printf("%d %d %d %d\n", ll.x, ll.y, ur.x, ur.y);
			else
				out.print("No Overlap\n");
			if(tc != 0)
				out.println();
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
