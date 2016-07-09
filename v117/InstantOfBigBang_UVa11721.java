package v117;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class InstantOfBigBang_UVa11721 {

	static final int INF = (int)1e9;
	static boolean[] reach;
	static ArrayList<Integer>[] adjList;

	static void dfs(int u)
	{
		reach[u] = true;
		for(int v: adjList[u])
			if(!reach[v])
				dfs(v);
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int V = sc.nextInt(), E = sc.nextInt();
			Edge[] edgeList = new Edge[E];
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Integer>();
			for(int i = 0; i < E; ++i)
			{
				int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
				edgeList[i] = new Edge(v, u, w);		// work on reverse graph
				adjList[v].add(u);						// work on reverse graph
			}

			//Bellman-Ford
			int[] dist = new int[V];
			Arrays.fill(dist, INF);
			dist[0] = 0;
			for(int k = 0; k < V - 1; ++k)
				for(Edge e: edgeList)
					dist[e.v] = Math.min(dist[e.v], dist[e.u] + e.t);

			reach = new boolean[V];
			for(Edge e: edgeList)
				if(dist[e.v] > dist[e.u] + e.t && !reach[e.v])
					dfs(e.v);

			boolean possible = false;
			sb.append("Case "+t+":");
			for(int i = 0; i < V; ++i)
				if(reach[i])
				{
					sb.append(" " + i);
					possible = true;
				}
			if(!possible)
				sb.append(" impossible");
			sb.append("\n");
		}
		out.print(sb);
		out.flush();
		out.close();
	}

	static class Edge 
	{
		int u, v, t;

		Edge(int a, int b, int c) { u = a; v = b; t = c; }
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}