package cp7_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class TheIncredibleHull_UVa596 {
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		String token = sc.next();
		while(!token.equals("END"))
		{
			StringBuilder sb = new StringBuilder();
			int t = sc.st.countTokens();
			while(t-->0)
				sb.append(sc.st.nextToken()).append(" ");
			sb.append("convex hull:\n");
			
			ArrayList<Point> points = new ArrayList<Point>(200);
			while((token = sc.next()).charAt(0) == 'P')
			{
				int n = sc.nextInt();
				while(n-->0)
					points.add(new Point(sc.nextInt(), sc.nextInt()));
			}
			
			Point[] ans = CH(points);
			for(int i = 0; i < ans.length - 1; i++)
				sb.append(" (").append(ans[i].x).append(",").append(ans[i].y).append(")");
			
			out.println(sb);
		}
		out.flush();
	}
	
	static boolean ccw(Point a, Point b, Point c)
	{
		return new Vector(a, b).cross(new Vector(a, c)) + EPS >= 0;
	}
	static Point[] CH(ArrayList<Point> points)
	{
		Collections.sort(points);
		ArrayList<Point> tmp = new ArrayList<Point>();
		for(int i = 0; i < points.size() - 1; i++)
			if(points.get(i).compareTo(points.get(i+1)) != 0)
				tmp.add(points.get(i));
		if(points.size() != 0)
			tmp.add(points.get(points.size() - 1));
		points = tmp;
		
		int n = points.size(), start = 0, size = 0;
		Point[] ans = new Point[n<<1];
		
		for(int i = 0; i < n; i++)
		{
			Point p = points.get(i);
			while(size - start >= 2 && !ccw(ans[size-2], ans[size-1], p)) size--;
			ans[size++] = p;
		}
		
		start = --size;
		
		for(int i = n - 1; i >= 0; i--)
		{
			Point p = points.get(i);
			while(size - start >= 2 && !ccw(ans[size-2], ans[size-1], p)) size--;
			ans[size++] = p;
		}
		if(size <0)
			size = 0;
		return Arrays.copyOf(ans, size);
	}
	
	static class Point implements Comparable<Point>
	{
		int x, y;
		
		Point(int a, int b) { x = a; y = b; }
		
		public int compareTo(Point p)
		{
			if(x != p.x) return p.x - x;
			return y - p.y;
		}
	}
	
	static class Vector
	{
		double x, y;
		
		Vector(Point a, Point b)
		{
			x = b.x - a.x; y = b.y - a.y;
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
