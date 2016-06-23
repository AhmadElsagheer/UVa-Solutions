package cp7_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GleamingTheCubes_UVa737 {
		
	public static void main(String[] args) throws IOException 
	{
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			Cube[] cubes = new Cube[n];
			for(int i = 0; i < n; i++)
			{
				int x = sc.nextInt(), y = sc.nextInt(), z = sc.nextInt(), d = sc.nextInt();
				cubes[i] = new Cube(new Point(x, y, z), new Point(x + d, y + d, z + d));
			}
			
			Cube intersect = cubes[0];
			for(int i = 1; i < n && intersect != null; i++)
				intersect = intersect.intersect(cubes[i]);
			
			out.println(intersect != null ? intersect.volume() : 0);
		}
		out.flush();

	}
	
	static class Point
	{
		int x, y, z;
		
		Point(int a, int b, int c) { x = a; y = b; z = c; }
	}
	
	static class Cube
	{
		Point l, u;
		
		Cube(Point a, Point b) { l = a; u = b; }
		
		Cube intersect(Cube c)
		{
			Point l = new Point(Math.max(this.l.x, c.l.x), Math.max(this.l.y, c.l.y), Math.max(this.l.z, c.l.z));
			Point u = new Point(Math.min(this.u.x, c.u.x), Math.min(this.u.y, c.u.y), Math.min(this.u.z, c.u.z));
			
			if(l.x == u.x || l.y == u.y || l.z == u.z)
				return null;
			if(this.contains(l) && this.contains(u) && c.contains(l) && c.contains(u))
				return new Cube(l, u);
			return null;
		}
		
		boolean contains(Point p)
		{
			return p.x <= u.x && p.y <= u.y && p.z <= u.z && p.x >= l.x && p.y >= l.y && p.z >= l.z;
		}
		
		int volume()
		{
			return (u.x - l.x) * (u.y - l.y) * (u.z - l.z);
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
