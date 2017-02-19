package v120;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class HighestPaidToll_UVa12047 {

	static class Edge implements Comparable<Edge>
	{
		int to, cost;
		
		Edge(int x, int y) { to = x; cost = y; }
		
		public int compareTo(Edge e)
		{
			return cost - e.cost; 
		}
	}
	static final int INF = (int)1e8;
	static ArrayList<Edge>[] adjList1, adjList2;
	static int N, S, T, P;
	
	static int[] dijkstra(int S, ArrayList<Edge>[] adjList)
	{
		int[] dist = new int[N];
		Arrays.fill(dist, INF);
		dist[S] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(S, 0));
		while(!pq.isEmpty())
		{
			Edge cur = pq.remove();
			
			if(cur.cost > dist[cur.to])
				continue;
			for(Edge nxt : adjList[cur.to])
			{
				int totalCost = cur.cost + nxt.cost;
				if(totalCost < dist[nxt.to])
				{
					dist[nxt.to] = totalCost;
					pq.add(new Edge(nxt.to, totalCost));
				}
			}
			
		}
		return dist;
	}
		
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			N = sc.nextInt();
			int E = sc.nextInt();
			S = sc.nextInt() - 1;
			T = sc.nextInt() - 1;
			P = sc.nextInt();
			adjList1 = new ArrayList[N];
			adjList2 = new ArrayList[N];
			for(int i = 0; i < N; ++i)
			{
				adjList1[i] = new ArrayList<Edge>();
				adjList2[i] = new ArrayList<Edge>();
			}
			
			while(E-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1, c = sc.nextInt();
				adjList1[u].add(new Edge(v, c));
				adjList2[v].add(new Edge(u, c));
			}
			int[] dist1 = dijkstra(S, adjList1), dist2 = dijkstra(T, adjList2);
			int maxToll = S == T ? 0 : -1;
			
			for(int i = 0; i < N; ++i)
				for(Edge e : adjList1[i])
					if(dist1[i] + dist2[e.to] + e.cost <= P)
						maxToll = Math.max(maxToll, e.cost);
			out.println(maxToll);	
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


