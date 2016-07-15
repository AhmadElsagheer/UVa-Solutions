package v100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PackingPolygons_UVa10005 {
	
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) break;
			
			Point[] vertices = new Point[n];
			for(int i = 0; i < n; i++) vertices[i] = new Point(sc.nextInt(), sc.nextInt());
			
			double r = sc.nextDouble();
			
			boolean possible = false;
			if(n == 1)
				possible = true;
			for(int i = 0; i < n && !possible; i++)
				for(int j = i + 1; j < n && !possible; j++)
					if(i != j)
					{
						Point c = findCenter(vertices[i], vertices[j], r);
										
						if(c == null)
							continue;
						
						if(inCircle(c, r, vertices))
							possible = true;
						else
						{
							c = findCenter(vertices[j], vertices[i], r);
							if(inCircle(c, r, vertices))
								possible = true;
						}
					}
			out.print(possible?"The polygon can be packed in the circle.\n":"There is no way of packing that polygon.\n");
		}
		out.flush();
	}
	
	static boolean inCircle(Point c, double r, Point[] vertices)
	{
		for(int i = 0; i < vertices.length; i++)
			if(!inCircle(c, r, vertices[i]))
				return false;
		return true;
	}
	
	static boolean inCircle(Point c, double r, Point p)
	{
		double a = (p.x - c.x) * (p.x - c.x) + (p.y - c.y) * (p.y  - c.y);
		
		return r * r  + EPS >= a;
	}
	
	static Point findCenter(Point p, Point q, double r)
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
