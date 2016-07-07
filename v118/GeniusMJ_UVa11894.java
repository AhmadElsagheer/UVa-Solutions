package v118;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GeniusMJ_UVa11894 {
	
	static final int OFFSET = 1000;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt();
			Point[] plug = new Point[n];
			for(int i = 0; i < n; i++)
				plug[i] = new Point(sc.nextInt(), sc.nextInt());
			
			Point socketPivot = new Point(1000, 1000);
			boolean[][] socket = new boolean[2001][2001];
			for(int i = 0; i < n; i++)
			{
				Point c = new Point(sc.nextInt(), sc.nextInt()); 
				socket[c.x + OFFSET][c.y + OFFSET] = true;
				if(c.compareTo(socketPivot) == -1)
					socketPivot = c;
			}
			
			boolean matched = false;
			for(int i = 0; i < 4 && !matched; i++)
				if(match(plug, socket, socketPivot))
					matched = true;
				else
					rotate90(plug);
			out.println(matched?"MATCHED":"NOT MATCHED");
		}
		out.flush();
	}
	
	static boolean match(Point[] plug, boolean[][] socket, Point socketPivot)
	{
		Point plugPivot = new Point(1000, 1000);
		for(int i = 0; i < plug.length; i++)
			if(plug[i].compareTo(plugPivot) == -1)
				plugPivot = plug[i];
		
		Vector v = new Vector(plugPivot, socketPivot);
		for(int i = 0; i < plug.length; i++)
		{
			Point c = plug[i].translate(v);
			if(Math.abs(c.x) > 1000 || Math.abs(c.y) > 1000 || !socket[c.x+OFFSET][c.y+OFFSET])
				return false;
		}
		return true;
			
	}
	
	static void rotate90(Point[] points)
	{
		for(int i = 0; i < points.length; i++)
			points[i].rotate90();
	}
	static class Point implements Comparable<Point>
	{
		int x, y;
		
		Point(int a, int b)
		{
			x = a; y = b;
		}
		
		Point translate(Vector v)
		{
			return new Point(x + v.x, y + v.y);
		}
		
		public int compareTo(Point p)
		{
			if(x != p.x)
				return x > p.x ? 1 : -1;
			if(y != p.y)
				return y > p.y ? 1 : -1;
			return 0;
		}
		
		void rotate90()
		{
			x ^= y;
			y ^= x;
			x ^= y;
			x *= -1;
		}
	}
	
	static class Vector
	{
		int x, y;
		
		Vector(Point p, Point q)
		{
			x = q.x - p.x;
			y = q.y - p.y;
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
