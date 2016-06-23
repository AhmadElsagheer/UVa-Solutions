package v118;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Shopping_UVa11813 {

	static class Edge implements Comparable<Edge>
	{
		int to;
		long cost;
		
		Edge(int x, long y) { to = x; cost = y; }
		
		public int compareTo(Edge e)
		{
			if(cost != e.cost)
				return to - e.to;
			return cost > e.cost ? 1 : -1;
		}
	}
	static final long INF = (long)1e16; 	
	static int N, S, stores[];
	static long[][] dist;
	static ArrayList<Edge>[] adjList;
	
	static long[] dijkstra(int s)
	{
		long[] dist = new long[N];
		Arrays.fill(dist, INF);
		dist[s] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(s, 0));
		while(!pq.isEmpty())
		{
			Edge cur = pq.remove();
			if(cur.cost > dist[cur.to])
				continue;
			for(Edge nxt : adjList[cur.to])
			{
				long totalCost = cur.cost + nxt.cost;
				if(totalCost < dist[nxt.to])
					pq.add(new Edge(nxt.to, dist[nxt.to] = totalCost));
			}
		}
		
		long[] ret = new long[S + 1];
		for(int i = 0; i < S + 1; ++i)
			ret[i] = dist[stores[i]];
		return ret;
	}
	
	static long[][] memo;
	
	static long dp(int store, int msk)
	{
		if(msk == ((1<<S) - 1))
			return dist[store][S];
		if(memo[store][msk] != -1)
			return memo[store][msk];
		long min = INF;
		for(int i = 0; i < S; ++i)
			if((msk & (1<<i)) == 0)
				min = Math.min(min, dist[store][i] + dp(i, msk | (1<<i)));
		return memo[store][msk] = min;
	}
	
	public static void main(String[] args) throws IOException {
	
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			N = sc.nextInt();
			int E = sc.nextInt();
			adjList = new ArrayList[N];
			for(int i = 0; i < N; ++i)
				adjList[i] = new ArrayList<Edge>();
			while(E-->0)
			{
				int u = sc.nextInt(), v = sc.nextInt();
				long cost = sc.nextLong();
				adjList[u].add(new Edge(v, cost));
				adjList[v].add(new Edge(u, cost));
			}
			
			S = sc.nextInt();
			stores = new int[S + 1];
			for(int i = 0; i < S; ++i)
				stores[i] = sc.nextInt();
			
			dist = new long[S + 1][S + 1];
			for(int i = 0; i < S + 1; ++i)
				dist[i] = dijkstra(stores[i]);
			
			memo = new long[S + 1][1<<S];
			for(int i = 0; i < S + 1; ++i)
				Arrays.fill(memo[i], -1);
			out.println(dp(S, 0));
		}

		
		out.flush();
		out.close();
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


		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready(); }


	}
}


