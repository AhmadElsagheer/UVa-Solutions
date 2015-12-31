package cp7_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TrashRemoval_UVa11111 {
	
	static final double EPS = 1e-9, INF = 1e9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int k = 1;
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) break;
			
			Point[] p = new Point[n];
			for(int i = 0; i < n; i++)
				p[i] = new Point(sc.nextInt(), sc.nextInt());
			p = CH(p);
			
			double ans = Math.ceil(findMin(p) * 100) / 100.0;
			out.format("Case %d: %.2f\n", k++, ans);
		}
		out.flush();
	}
	
	static double findMin(Point[] p)
	{
		double min = INF;
		for(int i = 0; i < p.length - 1; i++)
		{
			Point a = p[i], b = p[i+1];
			double max = 0;
			for(int j = 0; j < p.length - 1; j++)
				max = Math.max(max, distToLine(p[j], a, b));
			min = Math.min(max, min);
		}
		return min;
	}
	
	static double distToLine(Point p, Point a, Point b) //distance between point p and a line defined by points a, b (a != b)
	{
		// formula: c = a + u * ab
		Vector ap = new Vector(a, p), ab = new Vector(a, b);
		double u = ap.dot(ab) / ab.norm2();
		Point c = a.translate(ab.scale(u)); 
		return p.dist(c);
	}
	
	static boolean ccw(Point a, Point b, Point c)
	{
		return new Vector(a, b).cross(new Vector(a, c)) > EPS;
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
		
		double dist(Point p)
		{
			return Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y));
		}
		
		Point translate(Vector v)
		{
			return new Point(x + v.x, y + v.y);
		}
	}
	
	static class Vector
	{
		double x, y;
		
		Vector(Point a, Point b) { x = b.x - a.x; y = b.y - a.y; }
		
		Vector(double a, double b) { x = a; y = b; }
		
		double cross(Vector v) { return x * v.y - y * v.x; }
		
		double dot(Vector v) { return x * v.x + y * v.y; }
		
		double norm2() { return x * x + y * y; }
		
		Vector scale(double s) { return new Vector(x * s, y * s); }
	}
	
	static Point[] CH(Point[] points)
	{
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
		return Arrays.copyOf(ans, size);
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
