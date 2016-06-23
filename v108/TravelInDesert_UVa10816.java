package v108;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class TravelInDesert_UVa10816 {
	
	
	static final int INF = (int)1e9;
	static ArrayList<Edge>[] adjList;
	static int N, parent[];
	
	static int dijkstra(int S, int T, double maxTemp)
	{
		int[] dist = new int[N];
		parent = new int[N];
		Arrays.fill(dist, INF);
		dist[S] = 0; parent[S] = -1;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(S, 0, -1));
		while(!pq.isEmpty())
		{
			Edge cur = pq.remove();
			
			if(cur.to == T)
				return cur.dist;
			if(cur.dist > dist[cur.to])
				continue;
			for(Edge nxt: adjList[cur.to])
				if(nxt.temp <= maxTemp && nxt.dist + cur.dist < dist[nxt.to])
				{
					pq.add(new Edge(nxt.to, dist[nxt.to] = nxt.dist + cur.dist, -1));
					parent[nxt.to] = cur.to;
				}
		}
		return -10;
	}
	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
//		long s = System.currentTimeMillis();
//		while(System.currentTimeMillis() - s < 2000)
		while(sc.ready())
		{
			N = sc.nextInt();
			int E = sc.nextInt(), S = sc.nextInt() - 1, T = sc.nextInt() - 1;
			adjList = new ArrayList[N];
			for(int i = 0; i < N; ++i)
				adjList[i] = new ArrayList<Edge>();
			while(E-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				int t = (int)(sc.nextDouble() * 100), d = (int)(sc.nextDouble() * 100);
				adjList[u].add(new Edge(v, d, t));
				adjList[v].add(new Edge(u, d, t));
			}
			int minTemp = -1, minDist = -1, lo = 0, hi = 8000;
			int[] p = null;
			while(lo <= hi)
			{
				int mid = (lo + hi) / 2;
				int dist = dijkstra(S, T, mid);
				if(dist != -10)
				{
					hi = mid - 1;
					minDist = dist;
					minTemp = mid;
					p = parent;
				}
				else
					lo = mid + 1;
			}
//			if(p == null)
//				continue;
			
			Stack<Integer> route = new Stack<Integer>();
			while(T != -1)
			{
				route.push(T);
				T = p[T];
			}
			
			boolean first = true;
			while(!route.isEmpty())
			{
				if(first)
				{
					first = false;
					out.print(route.pop() + 1);
				}
				else
					out.printf(" %d", route.pop() + 1);
			}
			out.printf("\n%.1f %.1f\n", minDist / 100.0, minTemp / 100.0);
		}
		out.flush();
		out.close();
	}
	
	static class Edge implements Comparable<Edge>
	{
		int to;
		int temp, dist;
		
		Edge(int x, int y, int z)
		{
			to = x;
			dist = y;
			temp = z;
		}
		
		public int compareTo(Edge e)
		{
			if(dist != e.dist)
				return dist > e.dist ? 1 : -1;
				if(temp != e.temp)
					return temp > e.temp ? 1 : -1;
			return to - e.to;
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
				st = new StringTokenizer(br.readLine(), ",| ");
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready();}

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


	}
}
