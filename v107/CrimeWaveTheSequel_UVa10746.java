package v107;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CrimeWaveTheSequel_UVa10746 {

	static final int INF = (int)1e6;
	static int[][] res;
	static double[][] cost;
	static ArrayList<Integer>[] adjList;
	static int V, p[];
	static double totalCost;
	
	static double minCost()
	{
		totalCost = 0;
		
		while(true)
		{
			p = new int[V];
			Arrays.fill(p, -1);
			p[0] = 0;
			double[] dist = new double[V];
			Arrays.fill(dist, INF);
			dist[0] = 0;
			for(int k = 0; k < V - 1; ++k)
				for(int u = 0; u < V; ++u)
					for(int v: adjList[u])
						if(res[u][v] > 0 && dist[v] > dist[u] + cost[u][v])
						{
							dist[v] = dist[u] + cost[u][v];
							p[v] = u;
						}
							
			if(p[V - 1] == -1)
				break;
			aug(V - 1, INF);
		}
		return totalCost;
	}
	
	static int aug(int v, int flow)
	{
		if(v == 0)
			return flow;
		int u = p[v];
		flow = aug(u, Math.min(flow, res[u][v]));
		res[u][v] -= flow;
		res[v][u] += flow;
		totalCost += flow * cost[u][v];
		return flow;
	}
	
	static void addEdge(int u, int v, double w)
	{
		res[u][v] = 1;
		cost[u][v] = w;
		cost[v][u] = -w;
		adjList[u].add(v);
		adjList[v].add(u);
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int N = sc.nextInt();
			int M = sc.nextInt();
			if(N + M == 0)
				break;
			V = N + M + 2;
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Integer>();
			res = new int[V][V];
			cost = new double[V][V];
			for(int i = 1; i <= M; ++i)
				addEdge(0, i, 0);
			for(int i = 1; i <= N; ++i)
			{
				addEdge(M + i, V - 1, 0);
				for(int j = 1; j <= M; ++j)
					addEdge(j, M + i, sc.nextDouble());
			}
			out.printf("%.2f\n", minCost() / N);
		}
		out.flush();
		out.close();
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