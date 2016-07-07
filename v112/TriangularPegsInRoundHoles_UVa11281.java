package v112;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TriangularPegsInRoundHoles_UVa11281 {
	
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int m = sc.nextInt();
		double[] holes = new double[m];
		for(int i = 0; i < m; i++)
			holes[i] = sc.nextDouble() / 2.0;
		
		int n = sc.nextInt();
		for(int i = 0; i < n; i++)
		{
			ArrayList<Integer> ans = new ArrayList<Integer>();
			double a = sc.nextDouble(), b = sc.nextDouble(), c = sc.nextDouble();
			
			for(int j = 0; j < m; j++)
			{
				Point p = new Point(0, 0), q = new Point(0, a);
				double angle = acos(a, c, b);
				Point r = q.rotate(angle);
				Vector v = new Vector(p, r);
				r = r.translate(v.scale((c - a) / a));
				
				if(possible(p, q, r, holes[j]) || possible(r, q, p, holes[j]) || possible(p, r, q, holes[j]))
					ans.add(j + 1);
			}
			if(ans.isEmpty())
				out.printf("Peg %c will not fit into any holes\n", i + 'A');
			else
			{
				out.printf("Peg %c will fit into hole(s):", i + 'A');
				for(int j = 0; j < ans.size(); j++)
					out.printf(" %d",ans.get(j));
				out.println();
			}
		}
		out.flush();
	}
	
	static boolean possible(Point p, Point q, Point r, double radius)
	{
		if(p.dist(q) > radius * 2 + EPS)
			return false;
		Point c1 = findCenter(p, q, radius), c2 = findCenter(q, p, radius);
		if(c1 == null)
			System.out.println(p.dist(q)+ " " + radius);
		return insideCircle(c1, r, radius) || insideCircle(c2, r, radius);
	}
	
	static double acos(double a, double b, double c)
	{
		return Math.acos((a * a + b * b - c * c) / (2.0 * a * b));
	}
	
	static boolean insideCircle(Point c, Point p, double r)
	{
		double a = (p.x - c.x) * (p.x - c.x) + (p.y - c.y) * (p.y - c.y), b = r * r;
		
		return a <= b + EPS;
	}
	static Point findCenter(Point p, Point q, double r)		//for the other center, swap p and q
	{
		double d2 = (p.x - q.x) * (p.x - q.x) + (p.y - q.y) * (p.y - q.y);
		double det = r * r / d2 - 0.25;
		if(Math.abs(det) < EPS)
			det = 0.0;
		if(det < 0.0)
			return null;
		double h = Math.sqrt(det);
		return new Point((p.x + q.x) / 2.0 + (p.y - q.y) * h, (p.y + q.y) / 2.0 + (q.x - p.x) * h);
	}
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b; }
		
		double dist(Point p)
		{
			return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
		}
		
		Point translate(Vector v)
		{
			return new Point(x + v.x, y + v.y);
		}
		
		Point rotate(double rad) 
		{
			double c = Math.cos(rad), s = Math.sin(rad);
			return new Point(x * c - y * s, x * s + y * c); 
		}
		
		public String toString()
		{
			return "[" + x + ", " + y + "]";
		}
	}
	
	static class Vector
	{
		double x, y;
		
		Vector(double a, double b) { x = a; y = b; }
		
		Vector(Point a, Point b)
		{
			x = b.x - a.x;
			y = b.y - a.y;
		}
		
		Vector scale(double s)
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
