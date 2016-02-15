package cp4_4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class AirportSetup_UVa11377 {

	static final int INF = 10000000;
	static boolean[] hasAirport;
	static int n;
	static ArrayList<Integer>[] adjList;
	
	static class Pair implements Comparable<Pair>
	{
		int city, val;
		
		Pair(int x, int y) { city = x; val = y; }
		
		public int compareTo(Pair p)
		{
			return val - p.val;
		}
	}
	static int dijkstra(int s, int t)
	{
		int[] dist = new int[n];
		Arrays.fill(dist, INF);
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		dist[s] = hasAirport[s] ? 0 : 1;
		pq.add(new Pair(s, dist[s]));
		while(!pq.isEmpty())
		{
			Pair cur = pq.remove();
			if(cur.val > dist[cur.city])
				continue;

			if(cur.city == t) break;
				
			for(int i = 0; i < adjList[cur.city].size(); i++)
			{
				int nxt = adjList[cur.city].get(i);
				int total = cur.val + (hasAirport[nxt] ? 0 : 1);
				if(total < dist[nxt])
				{
					dist[nxt] = total;
					pq.add(new Pair(nxt, total));
				}
			}				
			
			
		}
		if(dist[t] == INF)
			return -1;
		return dist[t];
		
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; t++)
		{
			n = sc.nextInt();
			int m = sc.nextInt(), k = sc.nextInt();
			
			hasAirport = new boolean[n];
			while(k-->0) hasAirport[sc.nextInt() - 1] = true;
			adjList = new ArrayList[n];
			for(int i = 0; i < n; i++) adjList[i] = new ArrayList<Integer>();
			
			while(m-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				adjList[u].add(v);
				adjList[v].add(u);
			}
			
			out.format("Case %d:\n", t);
			int q = sc.nextInt();
			while(q-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				if(u == v)
					out.println(0);
				else
					out.println(dijkstra(u, v));
			}
			out.println();
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
