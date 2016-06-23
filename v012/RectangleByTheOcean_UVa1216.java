package v012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class RectangleByTheOcean_UVa1216 {

	public static void main(String[] args) throws IOException {
	
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt();
			Point[] g = new Point[n + 1];
			for(int i = 0; i < n; ++i)
				g[i] = new Point(sc.nextInt(), sc.nextInt());
			g[n] = g[0];
			double area = area(g);
			double bestFitDiff = 1e9;
			Point ll = null, ur = null;
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j)
					if(i != j)
						for(int k = 0; k < n; ++k)
							if(k != i && k != j)
							{
								Point p = g[i], q= g[j], r = g[k];
								if(p.y != q.y || p.x != r.x)
									continue;
								int areaRec = Math.abs(p.x - q.x) * Math.abs(p.y - r.y);
								if(Math.abs(areaRec - area) + EPS < bestFitDiff)
								{
									bestFitDiff = Math.abs(areaRec - area);
									Point[] ret = findCorners(p, q, r);
									ll = ret[0];
									ur = ret[1];
								}
								else
									if(Math.abs(bestFitDiff - Math.abs(areaRec - area)) < EPS)
									{
										Point[] ret = findCorners(p, q, r);
										if(ret[0].compareTo(ll) < 0 || ret[0].compareTo(ll) == 0 && ret[1].compareTo(ur) < 0)
										{
											ll = ret[0];
											ur = ret[1];
										}
									}
							}
			
			out.format("%.1f %d %d %d %d\n", area, ll.x, ll.y, ur.x, ur.y);
		}
		out.flush();
		out.close();
	}
	
	static Point[] findCorners(Point p, Point q, Point r)
	{
		int xL = Math.min(p.x, q.x), xU = Math.max(p.x, q.x);
		int yL = Math.min(p.y, r.y), yU = Math.max(p.y, r.y);
		return new Point[]{new Point(xL, yL), new Point(xU, yU)};
	}
	
	static final double EPS = 1e-9;
	static double area(Point[] g)
	{
		double ret = 0.0;
		for(int i = 0; i < g.length - 1; i++)
			ret += g[i].x * g[i+1].y - g[i+1].x * g[i].y;
		return Math.abs(ret) / 2.0;
	}
	
	static class Point implements Comparable<Point>
	{
		int x, y;
		
		Point(int a, int b) { x = a; y = b; }
		
		public int compareTo(Point p)
		{
			if(x != p.x)
				return x - p.x;
			return y - p.y;
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


		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready(); }


	}
}


