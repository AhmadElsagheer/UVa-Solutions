package v111;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MonkeysInTheEmeiMountain_UVa11167 {

	static ArrayList<Edge>[] adjList;
	static int N;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = 1;
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			//1. Take the input
			int[][] in = new int[n][3];
			boolean[] valid = new boolean[50001];
			int[] endpoint = new int[50000];
			int exp = 0;					//expected max flow
			int m = sc.nextInt();
			for(int i = 0; i < n; ++i)
			{
				for(int j = 0; j < 3; ++j)
					in[i][j] = sc.nextInt();
				in[i][2]--;
				for(int j = in[i][1]; j <= in[i][2]; ++j)
					valid[j] = true;
				endpoint[in[i][1]] |= 1;		//start point
				endpoint[in[i][2]] |= 2;		//end point
				exp += in[i][0];
			}
			//2. Divide timeline into disjoint intervals where each interval is a subset of one or more thirsty interval
			ArrayList<Pair> intervals = new ArrayList<Pair>(n);
			for(int i = 0, start = -1; i <= 50000; ++i)
				if(valid[i])
				{
					if((endpoint[i] & 1) != 0 && start != -1)
					{
						intervals.add(new Pair(start, i - 1));
						start = -1;
					}
					if(start == -1)
						start = i;
					if((endpoint[i] & 2) != 0)
					{
						intervals.add(new Pair(start, i));
						start = -1;
					}
						
				}
			
			//3. Build the graph
			int size = intervals.size();
			N = n + size + 2;
			adjList = new ArrayList[N];
			for(int i = 0; i < n; ++i)
			{
				addEdge(0, i + 2, in[i][0]);
				for(int j = 0; j < size; ++j)
				{
					Pair p = intervals.get(j);
					if(p.a <= in[i][2] && p.a >= in[i][1])
						addEdge(i + 2, n + 2 + j, p.b - p.a + 1);
					
				}
			}
			for(int j = 0; j < size; ++j)
			{
				Pair p = intervals.get(j);
				addEdge(n + 2 + j, 1, (p.b - p.a + 1) * m);
			}
			//4. Run max flow
			int mf = dinic();
			if(mf != exp)
				out.format("Case %d: No\n", tc++);
			else
			{
				out.format("Case %d: Yes\n", tc++);
				//5. Print the solution
				int[] xx = new int[500000];
				Arrays.fill(xx, m);
				ArrayList<Integer>[] moms = new ArrayList[n];
				for(int i = 0; i < n; ++i)
					moms[i] = new ArrayList<Integer>();
				for(int j = 0; j < size; ++j)
				{
					Pair p = intervals.get(j);
					for(int i = 0; i < adjList[n + 2 + j].size() - 1; ++i)
					{
						Edge e = adjList[n + 2 + j].get(i);
						int monkey = e.to - 2;
						boolean[] used = new boolean[p.b - p.a + 1];
						for(int s = m; s > 0 && e.w > 0; s--)
						for(int k = p.a; k <= p.b && e.w > 0; ++k)
							if(!used[k - p.a] && xx[k] == s)
							{
								e.w--;
								moms[monkey].add(k);
								xx[k]--;
								used[k - p.a] = true;
							}
					}

				}
					
				for(int i = 0; i < n; ++i)
				{
					ArrayList<Pair> ans = new ArrayList<Pair>(moms[i].size());
					Collections.sort(moms[i]);
					for(int k : moms[i])
						if(ans.size() != 0 && ans.get(ans.size() - 1).b == k)
							ans.get(ans.size() - 1).b++;
						else
							ans.add(new Pair(k, k + 1));
					out.print(ans.size());
					for(Pair p : ans)
						out.format(" (%d,%d)", p.a, p.b);
					out.println();
				}
			}
		}
		out.flush();
		
	}
	
	static int[] dist, ptr;
	static final int INF = (int)1e8;
	static int dinic()
	{
		int mf = 0;
		while(bfs())
		{
			ptr = new int[N];
			int f;
			while((f = dfs(0, INF)) != 0)
				mf += f;
		}
		return mf;
	}
	
	static boolean bfs()
	{
		Queue<Integer> q = new LinkedList<Integer>();
		dist = new int[N];
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
				Edge e = adjList[u].get(i);
				if(dist[e.to] == -1 && e.w > 0)
				{
					dist[e.to] = dist[u] + 1;
					q.add(e.to);
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
			Edge e = adjList[u].get(i);
			if(dist[e.to] == dist[u] + 1 && e.w > 0)
			{
				int f = dfs(e.to, Math.min(flow, e.w));
				if(f > 0)
				{
					e.w -= f;
					e.rev.w += f;
					return f;
				}
			}
		}
		return 0;
	}
	static void addEdge(int u, int v, int w)
	{
		if(adjList[u] == null)
			adjList[u] = new ArrayList<Edge>();
		if(adjList[v] == null)
			adjList[v] = new ArrayList<Edge>();
		Edge e1 = new Edge(v, w), e2 = new Edge(u, 0);
		e1.rev = e2; e2.rev = e1;
		adjList[u].add(e1);	adjList[v].add(e2);
	}
	
	static class Edge
	{
		int to, w;
		Edge rev;
		
		Edge(int x, int y) { to = x; w = y; }
	}
	
	static class Pair
	{
		int a, b;
		
		Pair(int x, int y) { a = x; b = y; }
			
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
