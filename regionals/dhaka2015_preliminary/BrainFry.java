package regionals.dhaka2015_preliminary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BrainFry {

	static double[] P;
	static Pair[][] memo;
	static int[] dist;
	static ArrayList<Edge>[] adjList;
	static int[] neigh;
	static int T;

	static Pair dp(int pos, int curT)
	{
		if(curT >= 200)
			return new Pair(0.0, curT);
		if(memo[pos][curT] != null)
			return memo[pos][curT];

		Pair ret;
		if(pos == 0)
		{
			if(curT >= T || neigh[pos] == 0)
				ret = new Pair(0.0, curT);
			else
			{
				double prob = 0.0, exp = 0.0, count = neigh[pos];
				for(Edge e: adjList[pos])
				{
					Pair nxt = dp(e.node, curT + e.time);
					prob += nxt.prob / count;
					exp += nxt.exp / count;
				}
				ret = new Pair(prob, exp);
			}

		}
		else
		{
			double prob = P[pos], exp = (curT + dist[pos]) * P[pos], count = neigh[pos];

			if(curT >= T)
				exp += (1 - P[pos]) * (curT + dist[pos]);
			else if(neigh[pos] == 0)
			{
				Pair nxt = dp(0, curT + dist[pos]);
				prob += (1 - P[pos]) * nxt.prob;
				exp += (1 - P[pos]) * nxt.exp;
			}
			else
				for(Edge e: adjList[pos])
					if(e.node != 0)
					{
						Pair nxt = dp(e.node, curT + e.time);
						prob += (1 - P[pos]) * nxt.prob / count;
						exp += (1 - P[pos]) * nxt.exp / count;
					}
			ret = new Pair(prob, exp);
		}
		return memo[pos][curT] = ret;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int N = sc.nextInt(), M = sc.nextInt();
			T = sc.nextInt();
			adjList = new ArrayList[N + 5];
			for(int i = 0; i <= N; ++i)
				adjList[i] = new ArrayList<Edge>();
			P = new double[N + 5];
			for(int i = 1; i <= N; ++i)
				P[i] = sc.nextDouble();
			neigh = new int[N + 1];
			while(M-->0)
			{
				int u = sc.nextInt(), v = sc.nextInt(), tt = sc.nextInt();
				adjList[u].add(new Edge(v, tt));
				adjList[v].add(new Edge(u, tt));
				if(u == 0 || v != 0)
					neigh[u]++;
				if(v == 0 || u != 0)
					neigh[v]++;

			}
			dijkstra(N + 5);				

			memo = new Pair[N + 5][300];
			Pair ans = dp(0, 0);

			out.println("Case "+ t + ": " + ans.prob+ " " + ans.exp);
		}

		out.flush();
		out.close();
	}

	static final int INF = (int)1e9;

	static void dijkstra(int V)
	{
		dist = new int[V];
		Arrays.fill(dist, INF);
		dist[0] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(); 
		pq.add(new Edge(0, 0));
		while(!pq.isEmpty())
		{
			Edge cur = pq.remove();
			if(cur.time > dist[cur.node])
				continue;
			for(Edge nxt: adjList[cur.node])
				if(dist[nxt.node] > cur.time + nxt.time)
					pq.add(new Edge(nxt.node, dist[nxt.node] = cur.time + nxt.time));
		}
	}


	static class Pair { double prob, exp; Pair(double a, double b) { prob = a; exp = b; } }

	static class Edge implements Comparable<Edge>
	{
		int node, time; Edge(int a, int b) { node = a; time = b; } 

		public int compareTo(Edge e) { return time - e.time; }
	}

	static class Scanner
	{
		BufferedReader br;
		StringTokenizer st;

		Scanner(InputStream s) { br = new BufferedReader(new InputStreamReader(s)); }

		String next() throws IOException
		{
			while(st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		int nextInt() throws NumberFormatException, IOException { return Integer.parseInt(next()); }

		double nextDouble() throws NumberFormatException, IOException { return Double.parseDouble(next()); }
	}
}
