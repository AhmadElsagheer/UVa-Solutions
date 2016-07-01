package v108;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class GopherStrategy_UVa10804 {

	static final double EPS = 1e-9;
	static int V, n, m, match[];
	static double dist;
	static Edge[][] adjList;
	static boolean[] vis;
	
	static int go(double d)
	{
		dist = d;
		int matches = 0;
		match = new int[m];
		Arrays.fill(match, -1);
		for(int i = 0; i < n; ++i)
		{
			vis = new boolean[n];
			matches += aug(i);
		}
		return matches;
	}
	
	static int aug(int u)
	{
		vis[u] = true;
		for(Edge e: adjList[u])
			if(e.dist < dist + EPS && (match[e.node] == -1 || !vis[match[e.node]] && aug(match[e.node]) == 1))
			{
				match[e.node] = u;
				return 1;
			}	
		return 0;
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			n = sc.nextInt(); m = sc.nextInt();
			int k = sc.nextInt();
			adjList = new Edge[n][m];
			Point[] gophers = new Point[n], holes = new Point[m];
			for(int i = 0; i < n; ++i)
				gophers[i] = new Point(sc.nextDouble(), sc.nextDouble());
			for(int i = 0; i < m; ++i)
				holes[i] = new Point(sc.nextDouble(), sc.nextDouble());

			for(int i = 0; i < n; ++i)
				for(int j = 0; j < m; ++j)
					adjList[i][j] = new Edge(j, gophers[i].dist2(holes[j]));
			
			
			double ans = -10, lo = 0, hi = 1e18;
			for(int i = 0; i < 100; ++i)
			{
				double mid = (lo + hi) / 2;
				if(n - go(mid) <= k)
				{
					ans = mid;
					hi = mid;
				}
				else
					lo = mid;
			}
			out.printf("Case #%d:\n", t);
			if(ans > -5)
				out.printf("%.3f\n\n",Math.sqrt(ans));
			else
				out.println("Too bad.\n");
		}
		out.flush();
		out.close();

	}

	static class Edge
	{
		int node;
		double dist;
		
		Edge(int x, double y) { node = x; dist = y; }
		
	}
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b; }
		
		double dist2(Point p) { return sq(x - p.x) + sq(y - p.y); }
		
		double sq(double x) { return x * x; }
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