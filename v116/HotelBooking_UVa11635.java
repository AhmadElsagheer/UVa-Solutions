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


public class HotelBooking_UVa11635 {

	static final int INF = (int)1e9;

	static int dijkstra(ArrayList<Edge>[] adjList, boolean[] isHotel, int V, int S, int T)
	{
		int[][] dist = new int[601][V];
		for(int i = 0; i <= 600; ++i)
			Arrays.fill(dist[i], INF);
		PriorityQueue<State> pq = new PriorityQueue<State>();
		dist[600][S] = 0;
		pq.add(new State(S, 600, 0));
		while(!pq.isEmpty())
		{
			State cur = pq.remove();
			if(cur.city == T)
				return cur.cost;
			if(cur.cost > dist[cur.stamina][cur.city])
				continue;
			//Go to another city
			for(Edge nxt: adjList[cur.city])
			{
				int nxtStamina = cur.stamina - nxt.time;
				if(nxtStamina >= 0 && cur.cost < dist[nxtStamina][nxt.city])
					pq.add(new State(nxt.city, nxtStamina, dist[nxtStamina][nxt.city] = cur.cost));
			}
			
			//Stay here
			if(isHotel[cur.city] && cur.cost + 1 < dist[600][cur.city])
				pq.add(new State(cur.city, 600, dist[600][cur.city] = cur.cost + 1));
				
		}
		return -1;
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int C = sc.nextInt();
			if(C == 0)
				break;
			int H = sc.nextInt();
			boolean[] isHotel = new boolean[C];
			while(H-->0)
				isHotel[sc.nextInt() - 1] = true;
			ArrayList<Edge>[] adjList = new ArrayList[C];
			for(int i = 0; i < C; ++i)
				adjList[i] = new ArrayList<Edge>();
			int M = sc.nextInt();
			while(M-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1, t = sc.nextInt();
				adjList[u].add(new Edge(v, t));
				adjList[v].add(new Edge(u, t));
			}

			out.println(dijkstra(adjList, isHotel, C, 0, C - 1));
		}
		out.flush();
		out.close();
	}

	static class State implements Comparable<State>
	{
		int city, stamina, cost;

		State(int x, int y, int z) { city = x; stamina = y; cost = z; }

		public int compareTo(State s) { return cost - s.cost; }
	}
	
	static class Edge
	{
		int city, time;
		
		Edge(int x, int y) { city = x; time = y; }
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


	}
}