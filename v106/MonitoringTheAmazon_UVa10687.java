package v106;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MonitoringTheAmazon_UVa10687 {
	
	static boolean[] visited;
	static ArrayList<Integer>[] adjList;
	static int counter;
	
	static void dfs(int u)
	{
		counter++;
		visited[u] = true;
		for(int v: adjList[u])
			if(!visited[v])
				dfs(v);
	}
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int N = sc.nextInt();
			if(N == 0)
				break;
			Point[] points = new Point[N];
			for(int i = 0; i < N; ++i)
				points[i] = new Point(sc.nextInt(), sc.nextInt());
			adjList = new ArrayList[N];
			for(int i = 0; i < N; ++i)
				adjList[i] = new ArrayList<Integer>(2);
			counter = 0;
			for(int i = 0; i < N; ++i)
			{
				int n1 = -1, n2 = -1, d1 = (int)1e9, d2 = (int)1e9;
				for(int j = 0; j < N; ++j)
					if(i != j)
					{
						int d = points[i].dist2(points[j]);
						if(d < d1 || d == d1 && compare(points[j], points[n1]) < 0)
						{
							n1 = j;
							d1 = d;
							
							if(d1 < d2 || d1 == d2 && compare(points[n1], points[n2]) < 0)
							{
								int tmp = n1; n1 = n2; n2 = tmp;
								tmp = d1; d1 = d2; d2 = tmp;
							}
						}
					}
				
				if(n1 != -1)
					adjList[i].add(n1);
				if(n2 != -1)
					adjList[i].add(n2);
			}
			visited = new boolean[N];
			dfs(0);
			if(counter == N)
				out.println("All stations are reachable.");
			else
				out.println("There are stations that are unreachable.");
		}
		out.flush();
		out.close();
	}
	
	static int compare(Point p1, Point p2)
	{
		int x = p1.west(p2);
		if(x == 0)
			x = p1.south(p2);
		return x;
	}
	
	static int sq(int x)
	{
		return x * x;
	}
	
	static class Point
	{
		int x, y;
		
		Point(int a, int b) { x = a; y = b; }
		
		int dist2(Point p) { return sq(x - p.x) + sq(y - p.y); }
		
		int west(Point p) { return x - p.x; }
		
		int south(Point p) { return y - p.y; }
	}
		
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine(), ",| ");
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready();}

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


	}
}
