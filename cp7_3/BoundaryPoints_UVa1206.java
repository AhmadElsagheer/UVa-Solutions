package cp7_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BoundaryPoints_UVa1206 {
	
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(sc.ready())
		{
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			int n = st.countTokens();
			Point[] p = new Point[n];
			for(int i = 0; i < n; i++)
			{
				StringTokenizer s = new StringTokenizer(st.nextToken(), ",");
				String x = s.nextToken().substring(1);
				String y = s.nextToken(); y = y.substring(0, y.length() - 1);
				p[i] = new Point(sc.nextDouble(x),sc.nextDouble(y));
			}
			
			p = convexHull(p);
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < p.length; i++)	
				sb.append("(").append(new DecimalFormat("0.000").format(p[i].x)).append(",").append(new DecimalFormat("0.000").format(p[i].y)).append(i == p.length - 1 ?")\n":") ");
			out.print(sb);
				
		}
		out.flush();
	}
	
	static Point[] convexHull(Point[] points)	//all points are unique, remove duplicates
	{											//edit ccw to accept collinear points
		int n = points.length;
		Arrays.sort(points);
		Point[] ans = new Point[n<<1];
		int size = 0, start = 0;
		
		for(int i = 0; i < n; i++)
		{
			Point p = points[i];
			while(size - start >= 2 && !ccw(ans[size-2], ans[size-1], p))	size--;
			ans[size++] = p;
		}
		start = --size;
	 
		for(int i = n-1 ; i >= 0 ; i--)
		{
			Point p = points[i];
			while(size - start >= 2 && !ccw(ans[size-2], ans[size-1], p))	size--;
			ans[size++] = p; 
		}
		if(size < 0) size = 0;		
		return Arrays.copyOf(ans, size);			
	}
	
	static boolean ccw(Point a, Point b, Point c)
	{
		return new Vector(a, b).cross(new Vector(a, c)) > 0;
	}
	static class Point implements Comparable<Point>
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b; }
		
		public int compareTo(Point p)
		{
			if(Math.abs(x - p.x) > EPS) return x > p.x ? 1 : -1;
			if(Math.abs(y - p.y) > EPS) return y > p.y ? 1 : -1;
			return 0;
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
		
		double cross(Vector v)
		{
			return x * v.y - y * v.x;
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
		
		public double nextDouble(String x) throws IOException
		{
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
