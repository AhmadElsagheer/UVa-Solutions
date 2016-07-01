package v100;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TheTouristGuide_UVa10099 {
	
	static final int INF = (int)1e9;
	static ArrayList<Edge>[] adjList;
	
	static int dfs(int u, int t, int p)
	{
		if(u == t)
			return INF;
		for(Edge e: adjList[u])
			if(e.v != p)
			{
				int d = dfs(e.v, t, u);
				if(d != -1)
					return Math.min(e.w, d);
			}
		return -1;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			int V = sc.nextInt(), E = sc.nextInt();
			if(V + E == 0)
				break;
			
			Edge[] edgeList = new Edge[E];
			for(int i = 0; i < E; ++i)
				edgeList[i] = new Edge(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt());
			Arrays.sort(edgeList);
			
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Edge>();
			
			UnionFind uf = new UnionFind(V);
			for(Edge e: edgeList)
				if(uf.union(e.u, e.v))
				{
					adjList[e.u].add(new Edge(e.u, e.v, e.w));
					adjList[e.v].add(new Edge(e.v, e.u, e.w));
				}
			int S = sc.nextInt() - 1, D = sc.nextInt() - 1, T = sc.nextInt();
			int P = dfs(S, D, -1) - 1;
			out.printf("Scenario #%d\nMinimum Number of Trips = %d\n\n", tc++, (T + P - 1) / P);
		}
		out.flush();
		out.close();
	}
	
	static class Edge implements Comparable<Edge>
	{
		int u, v, w;
		
		Edge(int x, int y, int z) { u = x; v = y; w = z; }
		
		public int compareTo(Edge e) { return e.w - w; }
	}
	
	static class UnionFind
	{
		int[] p, rank;
		
		UnionFind(int N)
		{
			p = new int[N];
			rank = new int[N];
			for(int i = 0; i < N; ++i)
				p[i] = i;
		}
		
		int find(int x) { return p[x] == x ? x : (p[x] = find(p[x])); }
		
		boolean union(int x, int y)
		{
			x = find(x);
			y = find(y);
			
			if(x == y)
				return false;
			
			if(rank[x] > rank[y])
				p[y] = x;
			else
			{
				p[x] = y;
				if(rank[x] == rank[y])
					++rank[y];
			}
			return true;
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