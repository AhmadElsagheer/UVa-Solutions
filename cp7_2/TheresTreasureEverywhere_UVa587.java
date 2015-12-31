package cp7_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class TheresTreasureEverywhere_UVa587 {

	static final double EPS = 1e-9;
	static final double sqrt2 = Math.sqrt(2);
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		TreeMap<String, Vector> map = new TreeMap<String, Vector>();
		map.put("N", new Vector(0, 1));map.put("S", new Vector(0, -1));
		map.put("E", new Vector(1, 0));map.put("W", new Vector(-1, 0));
		map.put("NE", new Vector(1/sqrt2, 1/sqrt2));map.put("NW", new Vector(-1/sqrt2, 1/sqrt2));
		map.put("SE", new Vector(1/sqrt2, -1/sqrt2));map.put("SW", new Vector(-1/sqrt2, -1/sqrt2));
		int k = 1;
		while(true)
		{
			String in = sc.next();
			if(in.equals("END")) break;
			String[] x = in.substring(0, in.length() - 1).split("\\,");
			
			Point pos = new Point(0, 0);
			for(int i = 0; i < x.length; i++)
			{
				char c = x[i].charAt(x[i].length() - 2);
				int e = c >= '0' && c <= '9' ? (x[i].length() - 1) : (x[i].length() - 2);
				int steps = Integer.parseInt(x[i].substring(0, e));
				String dir = x[i].substring(e);
				
				pos = pos.translate(map.get(dir).scale(steps));
			}
			out.printf("Map #%d\nThe treasure is located at (%.3f,%.3f).\nThe distance to the treasure is %.3f.\n\n",k++, pos.x, pos.y, pos.dist(new Point(0,0)));
			
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
		
		double dist(Point p) { return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y)); }
		
		Point translate(Vector v)
		{
			return new Point(x + v.x, y + v.y);
		}
	}
	
	static class Vector
	{
		double x, y;
		
		Vector(double a, double b)
		{
			x = a; y = b;
		}
		
		Vector scale(int s)
		{
			return new Vector(x * s, y * s);
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

		public boolean ready() throws IOException {return br.ready();}


	}
}
