package regionals.latinAmerica2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class GalacticTaxes {

	static final double EPS = 1e-11;
	
	static ArrayList<Edge>[] adjList;
	static int N;
	
	static double dijkstra(int S, int T, double t)
	{
		double[] dist = new double[N];
		Arrays.fill(dist, 1e16);
		dist[S] = 0;
		PriorityQueue<State> pq = new PriorityQueue<State>();
		pq.add(new State(S, 0));
		while(!pq.isEmpty())
		{
			State cur = pq.remove();
			if(cur.node == T)
				return cur.cost;
			if(cur.cost > dist[cur.node] + EPS)
				continue;
			for(Edge e: adjList[cur.node])
			{
				double cost = cur.cost + (e.A * t + e.B);
				if(cost + EPS < dist[e.node])
					pq.add(new State(e.node, dist[e.node] = cost));
			}
		}
		return -1;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			N = sc.nextInt();
			int M = sc.nextInt();
			adjList = new ArrayList[N];
			for(int i = 0; i < N; ++i)
				adjList[i] = new ArrayList<Edge>();
			while(M-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1, A = sc.nextInt(), B = sc.nextInt();
				adjList[u].add(new Edge(v, A, B));
				adjList[v].add(new Edge(u, A, B));
			}
			
			double lo = 0, hi = 24 * 60;
			while(Math.abs(hi - lo) > EPS)
			{
				double d = (hi - lo) / 3;
				double m1 = dijkstra(0, N - 1, lo + d);
				double m2 = dijkstra(0, N - 1, hi - d);

				if(Math.abs(m1 - m2) < EPS)
				{
					lo += d; hi -= d;
				}
				else if(m1 > m2)
					hi -= d;
				else
					lo += d;
			}
			out.printf("%.5f\n", dijkstra(0, N - 1, lo));
		}
		out.flush();
		out.close();
		
	}
	
	static class Edge { int node, A, B; Edge(int a, int b, int c) { node = a; A = b; B = c; } }

	static class State implements Comparable<State>
	{
		int node;
		double cost;
		
		State(int x, double y) { node = x; cost = y; }
		
		public int compareTo(State s)
		{
			if(Math.abs(cost - s.cost) < EPS)
				return 0;
			return cost > s.cost ? 1 : -1;
		}
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
		
		boolean ready() throws IOException { return br.ready(); }
	}
}
