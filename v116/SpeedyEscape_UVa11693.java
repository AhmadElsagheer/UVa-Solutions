package v116;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class SpeedyEscape_UVa11693 {

	static final double INF = 1e9, EPS = 1e-15;
	static ArrayList<Edge>[] adjList;
	static int V;
	
	static double[] dijkstra(int S, double v, double[] limit)
	{
		double[] dist = new double[V];
		Arrays.fill(dist, INF);
		dist[S] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(S, 0));
		while(!pq.isEmpty())
		{
			Edge cur = pq.remove();
			if(cur.cost > dist[cur.node] + EPS)
				continue;
			for(Edge nxt: adjList[cur.node])
			{
				double cost = cur.cost + nxt.cost / v;
				if(cost + EPS < limit[nxt.node] && cost + EPS < dist[nxt.node])
					pq.add(new Edge(nxt.node, dist[nxt.node] = cost));
			}
		}
		return dist;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			V = sc.nextInt();
			int M = sc.nextInt(), E = sc.nextInt();;
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Edge>();
			while(M-->0)
			{
				int a = sc.nextInt() - 1, b = sc.nextInt() - 1;
				double cost = sc.nextDouble();
				adjList[a].add(new Edge(b, cost));
				adjList[b].add(new Edge(a, cost));
			}
			int[] exits = new int[E];
			for(int i = 0; i < E; ++i)
				exits[i] = sc.nextInt() - 1;
			int b = sc.nextInt() - 1, p = sc.nextInt() - 1;
			double[] limit = new double[V];
			Arrays.fill(limit, INF);
			limit = dijkstra(p, 160.0, limit);
			
			double ans = -10, lo = 0.0, hi = 1e7;
			for(int i = 0; i < 100; ++i)
			{
				boolean good = false;
				double v = (lo + hi) / 2;
				double[] dist = dijkstra(b, v, limit);
				for(int e: exits)
					if(dist[e] + EPS < INF)
						good = true;
				if(good)
					ans = hi = v;
				else
					lo = v;
			}
			if(ans < -5)
				out.println("IMPOSSIBLE");
			else
				out.println(ans);
		}
		
		out.flush();
		out.close();
	}
	
	static class Edge implements Comparable<Edge>
	{
		int node;
		double cost;
		
		Edge(int x, double y) { node = x; cost = y; }
		
		public int compareTo(Edge e)
		{
			if(Math.abs(cost - e.cost) > EPS)
				return cost > e.cost ? 1 : -1;
			return node - e.node;
		}
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

		public boolean ready() throws IOException {return br.ready();}

		public boolean nextEmpty() throws IOException
		{
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}