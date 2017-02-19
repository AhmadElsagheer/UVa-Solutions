package v101;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class Travel_UVa10166 {

	static final int INF = (int)1e9;
	static int V, S, T;
	static ArrayList<Edge>[] adjList, adjListR;
	
	static int dijkstra(int S, int T, int minStart, ArrayList<Edge>[] adjList)
	{
		int[] dist = new int[V];
		Arrays.fill(dist, INF);
		dist[S] = minStart;
		
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(S, -1, minStart));

		while(!pq.isEmpty())
		{
			Edge cur = pq.remove();
			if(cur.city == T)
				return cur.arr;
			if(cur.arr > dist[cur.city])
				continue;
			for(Edge nxt: adjList[cur.city])
				if(cur.arr <= nxt.dept && nxt.arr < dist[nxt.city])
				{
					dist[nxt.city] = nxt.arr;
					pq.add(nxt);
				}
		}
		return -1;
	}
	
	static int[] go(int S, int T, int start)
	{
		//find earliest time to arrive
		int arr = dijkstra(S, T, start, adjList);
		
		if(arr == -1)
			return null;
		
		//find latest time to depart
		int dept = 2359 - dijkstra(T, S, 2359 - arr, adjListR);
		return new int[] { dept, arr };
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			V = sc.nextInt();
			if(V == 0)
				break;
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			for(int i = 0; i < V; ++i)
				map.put(sc.next(), i);
			adjList = new ArrayList[V];
			adjListR = new ArrayList[V];
			for(int i = 0; i < V; ++i)
			{
				adjList[i] = new ArrayList<Edge>();
				adjListR[i] = new ArrayList<Edge>();
			}
			int trains = sc.nextInt();
			while(trains-->0)
			{
				int t = sc.nextInt(), lst = -1, lstTime = -1;
				while(t-->0)
				{
					int curTime = sc.nextInt(), cur = map.get(sc.next());
					if(lst != -1)
					{
						adjList[lst].add(new Edge(cur, lstTime, curTime));
						adjListR[cur].add(new Edge(lst, 2359 - curTime, 2359 - lstTime));
					}
					lst = cur;
					lstTime = curTime;
				}	
			}
			int start = sc.nextInt();
			S = map.get(sc.next()); T = map.get(sc.next());
			int[] ans = go(S, T, start);
			if(ans == null)
				out.println("No connection");
			else
				out.printf("%04d %04d\n", ans[0], ans[1]);
		}
		
		out.flush();
		out.close();
	}
	
	static class Edge implements Comparable<Edge>
	{
		int city, dept, arr;
		
		Edge(int a, int b, int c) { city = a; dept = b; arr = c; }
		
		public int compareTo(Edge e) { return arr - e.arr; }
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