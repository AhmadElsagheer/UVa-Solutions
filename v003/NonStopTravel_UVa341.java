package v003;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class NonStopTravel_UVa341 {

	static final int INF = (int)1e9;
	static Edge[][] adjList;
	static int N, p[];
	static Stack<Integer> stack;
	static int dijkstra(int s, int t)
	{
		int[] dist = new int[N];
		p = new int[N];
		Arrays.fill(dist, INF);
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		dist[s] = 0;
		pq.add(new Edge(-1, s, 0));
		while(!pq.isEmpty())
		{
			Edge cur = pq.remove();
			if(cur.cost > dist[cur.to])
				continue;
			p[cur.to] = cur.from;
			if(cur.to == t)
				break;
			for(int i = 0; i < adjList[cur.to].length; ++i)
			{
				Edge nxt = adjList[cur.to][i];
				int cost = cur.cost + nxt.cost;
				if(cost < dist[nxt.to])
					pq.add(new Edge(cur.to, nxt.to, dist[nxt.to] = cost));
			}
		}
		stack = new Stack<Integer>();
		int u = t;
		while(u != -1)
		{
			stack.push(u + 1);
			u = p[u];
		}
		return dist[t];
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = 1;
		while(true)
		{
			N = sc.nextInt();
			if(N == 0)
				break;
			adjList = new Edge[N][];
			for(int i = 0; i < N; ++i)
			{
				int k = sc.nextInt();
				adjList[i] = new Edge[k];
				for(int j = 0; j < k; ++j)
					adjList[i][j] = new Edge(i, sc.nextInt() - 1, sc.nextInt());
			}
			int ans = dijkstra(sc.nextInt() - 1, sc.nextInt() - 1);
			StringBuilder sb = new StringBuilder();
			while(!stack.isEmpty())
				sb.append(" ").append(stack.pop());
			out.format("Case %d: Path =%s; %d second delay\n", tc++, sb.toString(), ans);
				
		}
		out.flush();

	}
	
	static class Edge implements Comparable<Edge>
	{
		int from, to, cost;
		
		Edge(int x, int y, int z) { from = x; to = y; cost = z; }
		
		public int compareTo(Edge e)
		{
			return cost - e.cost;
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

		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
