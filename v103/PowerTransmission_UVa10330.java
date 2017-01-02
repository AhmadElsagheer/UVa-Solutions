package v103;


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

public class PowerTransmission_UVa10330 {
	
	static final int INF = (int)1e9;
	static int V, res[][], ptr[], dist[];
	static ArrayList<Integer>[] adjList;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(sc.ready())
		{
			int N = sc.nextInt();
			V = (N<<1) + 2;
			res = new int[V][V];
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Integer>();
			for(int i = 0; i < N; ++i)
				addEdge(Vin(i), Vout(i), sc.nextInt());
			int E = sc.nextInt();
			while(E-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1, w = sc.nextInt();
				addEdge(Vout(u), Vin(v), w);
			}
			int B = sc.nextInt(), D = sc.nextInt();
			while(B-->0)
				addEdge(0, Vin(sc.nextInt() - 1), INF);
			
			while(D-->0)
				addEdge(Vout(sc.nextInt() - 1), 1, INF);
			out.println(dinic());
			
		}
		out.flush();

	}
	
	static int dinic()		//O(V^2E)
	{
		int mf = 0;
		while(bfs())
		{
			ptr = new int[V];
			int f;
			while((f = dfs(0, INF)) != 0)
				mf += f;
		}
		
		return mf;
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

	
	static int Vin(int x) { return 2 + (x<<1); }
	
	static int Vout(int x) { return 3 + (x<<1); }
	
	static void addEdge(int u, int v, int w)
	{
		adjList[u].add(v);
		adjList[v].add(u);
		res[u][v] = w;
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
