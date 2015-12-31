package cp4_7;
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

public class GuardianOfDecency_UVa12083 {

	static int V, n, m, match[];
	static ArrayList<Integer>[] adjList;	//size = n, idx = [0, n-1], val = [0, m-1]
	
	static int[] pair_U, pair_V, dist;
	static final int NIL = 0, INF = (int)1e9;
	static int hopcroftKarp()
	{
		pair_U = new int[n + 1];
		pair_V = new int[m + 1];
		dist = new int[n + 1];
		Arrays.fill(pair_U, NIL);
		Arrays.fill(pair_V, NIL);
		
		int matching = 0;
		while(bfs())
			for(int u = 1; u <= n; ++u)
				if(pair_U[u] == NIL && dfs(u))
					++matching;
		
		return matching;
	}
	
	static boolean bfs()
	{
		Queue<Integer> q = new LinkedList<Integer>();
		for(int u = 1; u <= n; ++u)
			if(pair_U[u] == NIL)
			{
				dist[u] = 0;
				q.add(u);
			}
			else
				dist[u] = INF;
		dist[NIL] = INF;
		while(!q.isEmpty())
		{
			int u = q.remove();
			if(dist[u] < dist[NIL])
				for(int v : adjList[u])
					if(dist[pair_V[v]] == INF)
					{
						dist[pair_V[v]] = dist[u] + 1;
						q.add(pair_V[v]);
					}
		}
		return dist[NIL] != INF;
	}
	
	static boolean dfs(int u)
	{
		if(u == NIL)
			return true;
		
		for(int v : adjList[u])
			if(dist[pair_V[v]] == dist[u] + 1 && dfs(pair_V[v]))
			{
				pair_U[u] = v;
				pair_V[v] = u;
				return true;
			}
		dist[u] = INF;
		return false;
		
					
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			n = 0; m = 0;
			V = sc.nextInt();
			int[] h = new int[V];
			String[] music = new String[V], sport = new String[V];
			while(n + m < V)
			{
				int hh = sc.nextInt(), idx;
				if(sc.next().charAt(0) == 'M')
					idx = n++;
				else
					idx = V - 1 - m++;
				h[idx] = hh;
				music[idx] = sc.next();
				sport[idx] = sc.next();
			}
			adjList = new ArrayList[n + 1];
			for(int i = 0; i < n; ++i)
			{
				adjList[i + 1] = new ArrayList<Integer>();
				for(int j = 0; j < m; ++j)
					if(Math.abs(h[i] - h[n + j]) <= 40 && music[i].equals(music[n + j]) && !sport[i].equals(sport[n + j]))
						adjList[i + 1].add(j + 1);
			}
			out.println(V - hopcroftKarp());
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

		public boolean ready() throws IOException {return br.ready(); }


	}
}
