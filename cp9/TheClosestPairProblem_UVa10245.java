package cp9;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class TheClosestPairProblem_UVa10245 {
	
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n;
		while((n = sc.nextInt()) != 0)
		{
			TreeSet<Point> cands = new TreeSet<Point>();
			TreeMap<Point, Integer> map = new TreeMap<Point, Integer>();
			
			Point[] points = new Point[n];
			for(int i = 0; i < n; i++)
			{
				double x = sc.nextDouble(), y = sc.nextDouble();
				points[i] = new Point(x, y);
				map.put(points[i], i);
			}
			Arrays.sort(points, new X());
			
			cands.add(points[0]);
			int left = 0;
			double d = 1e16;
			
			for(int i = 1; i < points.length; i++)
			{
				double px = points[i].x, py = points[i].y;
				while(left < i && px - points[left].x > d) cands.remove(points[left++]);
				
				Point down = new Point(px, py - d);
				Point up = new Point(px, py + d);
				
				for(Point p: cands.subSet(down, up))
					if(p.dist(points[i]) < d)
						d = p.dist(points[i]);
					
				cands.add(points[i]);
			}
			
			
			if(d > 10000.0)
				out.print("INFINITY\n");
			else		   
				out.format("%.4f\n", d);
		}
		out.flush();
	}	
	
	static class Point implements Comparable<Point>
	{
		double x,y;
		
		Point(double a, double b) {x = a; y = b;}
		
		public int compareTo(Point p) 
		{	
			if(Math.abs(y - p.y) > EPS) return y > p.y ? 1 : -1;
			if(Math.abs(x - p.x) > EPS) return x > p.x ? 1 : -1;
			return 0;
		}
		
		double dist(Point p) { return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y)  * (y - p.y)); }
		
	}
	
	static class X implements Comparator<Point>
	{
		public int compare(Point o1, Point o2) 
		{ 
			if(Math.abs(o1.x - o2.x) > EPS)
				return o1.x > o2.x ? 1 : -1;
			if(Math.abs(o1.y - o2.y) > EPS)
					return o1.y > o2.y ? 1 : -1; 
			return 0;
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
