package v109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BuyOneGetTheRestFree_UVa10983 {

	static final int INF = (int)1e9;
	static int V, s, t, f[][], c[][], p[];
	static ArrayList<Edge>[] adjList;
	
	static boolean possible(int maxCost, int totalPassengers)
	{
		f = new int[V][V];
		int maxFlow = 0;
		while(true)
		{
			p = new int[V];
			Arrays.fill(p, -1);
			p[s] = s;
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(s);
			while(!q.isEmpty())
			{
				int u = q.remove();
				if(u == t)
					break;
				for(Edge e: adjList[u])
					if(e.w <= maxCost && p[e.v] == -1 && f[u][e.v] < c[u][e.v])
					{
						p[e.v] = u;
						q.add(e.v);
					}
			}
			if(p[t] == -1)
				break;
			maxFlow += aug(t, INF);
		}
		return maxFlow == totalPassengers;
	}
	
	static int aug(int v, int flow)
	{
		if(v == s)
			return flow;
		int u = p[v];
		flow = aug(u, Math.min(flow, c[u][v] - f[u][v]));
		f[u][v] += flow;
		f[v][u] -= flow;
		return flow;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int tt = 1; tt <= tc; ++tt)
		{
			int n = sc.nextInt(), d = sc.nextInt(), m = sc.nextInt();
			V = n * (d + 1) + 1;
			c = new int[V][V];
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Edge>();
			
			// Add flights
			while(m-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1, p = sc.nextInt(),
						w = sc.nextInt(), e = sc.nextInt();
				int x = u * (d + 1) + e, y = v * (d + 1) + e + 1;
				adjList[x].add(new Edge(y, w));
				adjList[y].add(new Edge(x, w));
				c[x][y] = p;
			}
			
			// Add stays
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < d; ++j)
				{
					int u = i * (d + 1) + j, v = u + 1;
					adjList[u].add(new Edge(v, 0));
					adjList[v].add(new Edge(u, 0));
					c[u][v] = INF;
				}
			
			// Add virtual source
			int totalPassengers = 0;
			for(int i = 0; i < n; ++i)
			{
				int p = sc.nextInt();
				totalPassengers += p;
				adjList[V-1].add(new Edge(i * (d + 1), 0));
				adjList[i * (d + 1)].add(new Edge(V-1, 0));
				c[V-1][i * (d + 1)] = p;
			}
			
			//Source and Sink
			s = V - 1; t = V - 2;		// t = (n - 1) * (d + 1) + d
			
			int ans = -1, lo = 0, hi = 100000;
			while(lo <= hi)
			{
				int maxCost = lo + hi >> 1;
				if(possible(maxCost, totalPassengers))
				{
					ans  = maxCost;
					hi = maxCost - 1;
				}
				else
					lo = maxCost + 1;
			}
			if(ans == -1)
				out.printf("Case #%d: Impossible\n", tt);
			else
				out.printf("Case #%d: %d\n", tt, ans);
		}
		out.flush();
		out.close();
	}
	
	static class Edge { int v, w; Edge(int a, int b) { v = a; w = b; } }
	
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}

	}
}