package cp4_6;


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

public class Sabotage_UVa10480 {
	
	static final int INF = (int)1e9;
	static ArrayList<Integer>[] adjList;
	static int V, res[][], dist[], ptr[];
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			V = sc.nextInt();
			if(V == 0)
				break;
			int E = sc.nextInt();
			res = new int[V][V];
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Integer>();
			while(E-->0)
				addEdge(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt());
			
			dinic();
			
			//print the solution
			Queue<Integer> q = new LinkedList<Integer>();
			int[] vis = new int[V];
			q.add(0);
			vis[0] = 1;
			while(!q.isEmpty())
			{
				int u = q.remove();
				for(int i = 0; i < adjList[u].size(); ++i)
				{
					int v = adjList[u].get(i);
					if(vis[v] == 0 && res[u][v] > 0)
					{
						vis[v] = 1;
						q.add(v);
					}
				}
			}
			for(int u = 0; u < V; ++u)
				for(int i = 0; i < adjList[u].size(); ++i)
				{
					int v = adjList[u].get(i);
					if(u > v && (vis[u] ^ vis[v]) == 1)
						out.format("%d %d\n", u + 1, v + 1);
				}
				
			out.println();
		}
		out.flush();
	}
	
	
	static void dinic()
	{
		while(bfs())
		{
			ptr = new int[V];
			while(dfs(0, INF) != 0);
		}	
	}
	
	static boolean bfs()
	{
		Queue<Integer> q = new LinkedList<Integer>();
		dist = new int[V];
		Arrays.fill(dist, -1);
		dist[0] = 0;
		q.add(0);
		while(!q.isEmpty())
		{
			int u = q.remove();
			if(u == 1)
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
		if(u == 1)
			return flow;
		
		for(int i = ptr[u]; i < adjList[u].size(); i = ++ptr[u])
		{
			int v = adjList[u].get(i);
			if(dist[v] == dist[u] + 1 && res[u][v] > 0)
			{
				
				int f;
				if(res[u][v] < flow)
					f = dfs(v, res[u][v]);
				else
					f = dfs(v, flow);
					
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

	static void addEdge(int u, int v, int w)
	{
		adjList[u].add(v);
		adjList[v].add(u);
		res[u][v] = res[v][u] = w;
	}
	
	
	static class Pair
	{
		int u, v;
		
		Pair(int x, int y) { u = x; v = y; }
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
		
		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
