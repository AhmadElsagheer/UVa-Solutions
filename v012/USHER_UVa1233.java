package v012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class USHER_UVa1233 {
	
	static final int INF = (int)1e9;
	
	static int dijkstra(ArrayList<Edge>[] adjList, int N, int[] q)
	{
		int[] dist = new int[N];
		Arrays.fill(dist, INF);
		dist[0] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(0, 0));
		while(!pq.isEmpty())
		{
			Edge cur = pq.remove();
			if(cur.cost > dist[cur.node])
				continue;
			for(Edge nxt: adjList[cur.node])
				if(cur.cost + nxt.cost < dist[nxt.node])
					pq.add(new Edge(nxt.node, dist[nxt.node] = cur.cost + nxt.cost));
		}
		int min = INF;
		for(int x: q)
			min = Math.min(min, dist[x]);
		return min;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int b = sc.nextInt(), p = sc.nextInt();
			ArrayList<Edge>[] adjList = new ArrayList[p + 1];
			for(int i = 0; i <= p; ++i)
				adjList[i] = new ArrayList<Edge>();
			int k = sc.nextInt(), q[] = new int[k];
			for(int i = 0; i < k; ++i)
				q[i] = sc.nextInt();
			for(int i = 1; i <= p; ++i)
			{
				k = sc.nextInt();
				while(k-->0)
				{
					int w = sc.nextInt(), j = sc .nextInt();
					adjList[j].add(new Edge(i, w));
				}
			}
			int minDons = dijkstra(adjList, p + 1, q), ans = 0;
			if(b - 1 >= minDons)
				ans = (b - 1 - minDons) / (minDons - 1) + 1;
			out.println(ans);
		}
		out.flush();
		out.close();
	}
	
	static class Edge implements Comparable<Edge>
	{
		int node, cost;
		
		Edge(int a, int b) { node = a; cost = b; }
		
		public int compareTo(Edge e) { return cost - e.cost; }
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