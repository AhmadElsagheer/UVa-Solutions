package cp7_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ChocolateChipCookies_UVa10136 {
	
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = sc.nextInt();
		while(tc-->0)
		{
			ArrayList<Point> chips = new ArrayList<Point>(200);
			while(true)
			{
				chips.add(new Point(sc.nextDouble(), sc.nextDouble()));
				if(!sc.ready() || sc.nextEmpty())
					break;
			}
			int max = 1;
			for(int i = 0; i < chips.size(); i++)
			{
				Point p = chips.get(i);
				for(int j = i + 1; j < chips.size(); j++)
				{
					Point q = chips.get(j);
					Point c = findCenter(p, q, 2.5);
						
					if(c == null)
						continue;
					boolean go = false;
					if(p.dist(q) <= 5)
						go = true;
					max = Math.max(max, countInside(c, 2.5, chips, go));
					c = findCenter(q, p, 2.5);
					max = Math.max(max, countInside(c, 2.5, chips, go));
					

				}
			}
			out.println(max);
			if(tc != 0)
				out.println();
		}
		out.flush();
	}
	
	static int countInside(Point c, double r, ArrayList<Point> points, boolean go)
	{
		int count = 0;
		for(int i = 0; i < points.size(); i++)
			if(insideCircle(c, r, points.get(i)))
				count++;
		return count;
	}
	static Point findCenter(Point p, Point q, double r)
	{
		double d2 = (p.x - q.x) * (p.x - q.x) + (p.y - q.y) * (p.y - q.y);
		double det = r * r /d2 - 0.25;
		if(Math.abs(det) < EPS)
			det = 0.0;
		if(det < 0.0)
			return null;
		double h = Math.sqrt(det);
		return new Point((p.x + q.x) / 2.0 + (p.y - q.y) * h, (p.y + q.y) / 2.0 + (q.x - p.x) * h);
	}
	static boolean insideCircle(Point c, double r, Point p)
	{
		
		double a = (p.x - c.x) * (p.x - c.x) + (p.y - c.y) * (p.y - c.y);
		return r * r + EPS >= a || Math.abs(r * r - a) < EPS;
	}
	
	static class Point
	{
		double x,y;
		Point(double a, double b)
		{
			x = a; y = b;
		}
		
		double dist(Point p)
		{
			return Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y));
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
