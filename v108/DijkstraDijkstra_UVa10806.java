package v108;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class DijkstraDijkstra_UVa10806 {

	static final int INF = (int)1e9;
	static ArrayList<Edge>[] adjList;
	static int N, s, t, cost, p[];
	static Edge[] edges;

	static boolean minCost()
	{
		cost = 0;
		s = 0; t = N - 1;
		int flow = 0;
		while(true)
		{


			int[] dist = new int[N];
			Arrays.fill(dist, INF);
			dist[s] = 0;
			p = new int[N];
			edges = new Edge[N];
			for(int k = 0; k < N - 1; ++k)
				for(int u = 0; u < N; ++u)
					if(adjList[u] != null)
						for(Edge e: adjList[u])
							if(e.cap > 0 && dist[e.node] > dist[u] + e.cost)
							{
								dist[e.node] = dist[u] + e.cost;
								p[e.node] = u;
								edges[e.node] = e;
							}

			if(edges[t] == null)
				return false;
			flow += aug(t, 2 - flow);
			if(flow == 2)
				break;
		}

		return true;
	}

	static int aug(int v, int flow)
	{
		if(v == s)
			return flow;
		int u = p[v];
		Edge e = edges[v];
		flow = aug(u, Math.min(flow, e.cap));
		e.cap -= flow;
		e.rev.cap += flow;
		cost += flow * e.cost;
		return flow;
	}

	static void addEdge(int u, int v, int c, int w)
	{
		if(adjList[u] == null)
			adjList[u] = new ArrayList<Edge>();
		if(adjList[v] == null)
			adjList[v] = new ArrayList<Edge>();
		Edge e1 = new Edge(v, c, w), e2 = new Edge(u, 0, -w);
		e1.rev = e2; e2.rev = e1;
		adjList[u].add(e1);
		adjList[v].add(e2);
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			N = sc.nextInt();
			if(N == 0)
				break;
			adjList = new ArrayList[N];
			int E = sc.nextInt();
			while(E-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1, t = sc.nextInt();
				addEdge(u, v, 1, t);
				addEdge(v, u, 1, t);
			}
			if(minCost())
				out.println(cost);
			else
				out.println("Back to jail");
		}
		out.flush();
		out.close();
	}

	static class Edge
	{
		int node, cap, cost;
		Edge rev;

		Edge(int x, int y, int z) { node = x; cap = y; cost = z; }
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){    br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){    br = new BufferedReader(r);}

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