package cp4_4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class FullTank_UVa11367 {

	static final int INF = 100000000;
	static ArrayList<Edge>[] adjList;
	static int N, price[];
	
	static int dijkstra(int S, int E, int C)
	{
		int[][] dist = new int[N][C + 1];
		for(int i = 0; i < N; i++) Arrays.fill(dist[i], INF);
		
		dist[S][0] = 0;
		PriorityQueue<Triple> q = new PriorityQueue<Triple>();
		q.add(new Triple(S, 0, 0));
		while(!q.isEmpty())
		{
			Triple cur = q.remove();
<<<<<<< HEAD
			if(cur.city == E)
				return cur.cost;

			if(cur.cost > dist[cur.city][cur.fuel])
				continue;
			
=======
			if(cur.cost > dist[cur.city][cur.fuel])
				continue;
				
>>>>>>> 39f869b7a0b14772c795150d9bb52127d820fece
			for(int i = 0; i < adjList[cur.city].size(); i++)
			{
				Edge nxt = adjList[cur.city].get(i);
				if(nxt.distance <= cur.fuel && cur.cost < dist[nxt.city][cur.fuel - nxt.distance])
				{
					dist[nxt.city][cur.fuel - nxt.distance] = cur.cost;
					q.add(new Triple(nxt.city, cur.fuel - nxt.distance, cur.cost));
				}
			}
			if(cur.fuel < C && cur.cost + price[cur.city] < dist[cur.city][cur.fuel + 1])
			{
				dist[cur.city][cur.fuel + 1] = cur.cost + price[cur.city];
				q.add(new Triple(cur.city, cur.fuel + 1, cur.cost + price[cur.city])); 
			}
			
		}
<<<<<<< HEAD
		return INF;
=======
		return dist[E][0];
>>>>>>> 39f869b7a0b14772c795150d9bb52127d820fece
	}
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		N = sc.nextInt();
		int M = sc.nextInt();
		
		price = new int[N];
		for(int i = 0; i < N; i++) price[i] = sc.nextInt();
		
		adjList = new ArrayList[N];
		for(int i = 0; i < N; i++) adjList[i] = new ArrayList<Edge>();
		
		while(M-->0)
		{
			int u = sc.nextInt(), v = sc.nextInt(), d = sc.nextInt();
			adjList[u].add(new Edge(v, d));
			adjList[v].add(new Edge(u, d));
		}
		
		int Q = sc.nextInt();
		while(Q-->0)
		{
			int C = sc.nextInt(), S = sc.nextInt(), E = sc.nextInt();
			int ans = dijkstra(S, E, C);
			if(ans == INF)
				sb.append("impossible\n");
			else
				sb.append(ans+"\n");
		}
		System.out.print(sb);
	}
	
	static class Triple implements Comparable<Triple>
	{
		int city, fuel ,cost;

		Triple(int x, int y, int z) { city = x; fuel = y; cost = z; }
		
		public int compareTo(Triple o) { return cost - o.cost; }
		
	}
	
	static class Edge
	{
		int city, distance;
		
		Edge(int x, int y){ city = x; distance  = y; }
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

		public boolean ready() throws IOException {return br.ready();}


	}

}
