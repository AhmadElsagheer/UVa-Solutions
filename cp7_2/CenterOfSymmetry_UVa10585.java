package cp7_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CenterOfSymmetry_UVa10585 {

	static final double INF = 1e7, EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt();
			double maxX, maxY, minX, minY;
			maxX = maxY = - (minX = minY = INF);
			Point[] points = new Point[n];
			for(int i = 0; i < n; i++)
			{
				double x = sc.nextInt(), y = sc.nextInt();
				maxX = Math.max(maxX, x);
				minX = Math.min(minX, x);
				maxY = Math.max(maxY, y);
				minY = Math.min(minY, y);
				points[i] = new Point(x, y);
			}
			Point c = new Point((maxX + minX) / 2.0, (maxY + minY) / 2.0);
			Arrays.sort(points);
			boolean has = true;
			for(int i = 0; has && i < n; i++)
			{
				Vector v = new Vector(points[i], c);
				if(!contains(points, points[i].translate(v).translate(v)))
					has = false;
			}
			out.print(has?"yes\n":"no\n");
		}
		out.flush();
		
	}
	
	static boolean contains(Point[] points, Point x)
	{
		int lo = 0, hi = points.length - 1;
		while(lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;
			int c = points[mid].compareTo(x);
			if(c == 0)
				return true;
			if(c == 1)
				hi = mid - 1;
			else
				lo = mid + 1;
		}
		return false;
	}
	
	static class Point implements Comparable<Point>
	{
		double x, y;
		Point(double a, double b)
		{
			x = a; y = b;
		}
		
		public int compareTo(Point o) {
			
			if(Math.abs(x - o.x) > EPS) return x > o.x ? 1 : -1;
			if(Math.abs(y - o.y) > EPS) return y > o.y ? 1 : -1;
			return 0;
		}
		
		Point translate(Vector v)
		{
			return new Point(x + v.x, y + v.y);
		}
		
	}
	
	static class Vector
	{
		double x, y;
		
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
