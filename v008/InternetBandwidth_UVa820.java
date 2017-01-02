package v008;


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

public class InternetBandwidth_UVa820 {
	
	static final int INF = (int)1e9;
	static int V, s, t;
	static ArrayList<Integer>[] adjList;
	static int[] dist, ptr, res[];
	
	static boolean bfs()
	{
		Queue<Integer> q = new LinkedList<Integer>();
		dist = new int[V];
		Arrays.fill(dist, -1);
		dist[s] = 0;
		q.add(s);
		while(!q.isEmpty())
		{
			int u = q.remove();
			if(u == t)
				return true;
			for(int i = 0; i < adjList[u].size(); ++i)
			{
				int v = adjList[u].get(i);
				if(dist[v] == -1 && res[u][v] > 0)
				{
					dist[v] = dist[u] + 1;
					q.add(v);
				}
			}
		}
		return false;
	}
	
	static int dfs(int u, int flow)
	{
		if(u == t)
			return flow;
		for(int i = ptr[u]; i < adjList[u].size(); i = ++ptr[u])
		{
			int v = adjList[u].get(i);
			if(dist[v] == dist[u] + 1 && res[u][v] > 0)
			{
				int f = dfs(v, Math.min(flow, res[u][v]));
				if(f > 0)
				{
					res[u][v] -= f;
					res[v][u] += f;
					return f;
				}
			}
		}
		return 0;
	}
	static int dinic()
	{
		int mf = 0;
		while(bfs())
		{
			ptr = new int[V];
			int f;
			while((f = dfs(s, INF)) != 0)
				mf += f;
		}
		
		return mf;
	}
	
	public static void main(String[] args) throws IOException {
		
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = 1;
		while(true)
		{
			V = sc.nextInt();
			if(V == 0)
				break;
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Integer>();
			res = new int[V][V];
			s = sc.nextInt() - 1;
			t = sc.nextInt() - 1;
			int E = sc.nextInt();
			while(E-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				adjList[u].add(v);
				adjList[v].add(u);
				int bw = sc.nextInt();
				res[u][v] += bw;
				res[v][u] += bw;
			}
			out.format("Network %d\nThe bandwidth is %d.\n\n", tc++, dinic());
		}
		out.flush();

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
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
