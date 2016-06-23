package v005;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class HeavyCargo_UVa544 {

	static final int INF = (int)1e9;
	static ArrayList<Edge>[] adjList;
	static boolean[] visited;
	static int V;
	
	static int mst()
	{
		boolean[] visited = new boolean[V];
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(0, 0));
		int mst = 0;
		while(!pq.isEmpty())
		{
			Edge cur = pq.remove();
			if(visited[cur.node])
				continue;
			if(cur.rev != null)
				cur.use = cur.rev.use = true;
			visited[cur.node] = true;
			for(Edge e: adjList[cur.node])
				if(!visited[e.node])
					pq.add(e);
		}
		return mst;
	}
	
	static int dfs(int u, int t)
	{
		visited[u] = true;
		if(u == t)
			return INF;

		for(Edge e: adjList[u])
			if(e.use && !visited[e.node])
			{
				int d = dfs(e.node, t);
				if(d != -1)
					return Math.min(d, e.weight);
			}
		return -1;
	}
	
	static int findMaxWeight(int s, int t)
	{
		mst();
		visited = new boolean[V];
		int ans = dfs(s, t);
		if(ans == -1)
			ans = 0;
		return ans;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			int N = sc.nextInt();
			int E = sc.nextInt();
			if(N == 0)
				break;
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			adjList = new ArrayList[N];
			for(int i = 0; i < N; ++i)
				adjList[i] = new ArrayList<Edge>();
			V = 0;
			while(E-->0)
			{
				String s1 = sc.next(), s2 = sc.next();
				int w = sc.nextInt();
				Integer u = map.get(s1), v = map.get(s2);
				if(u == null)
					map.put(s1, u = V++);
				if(v == null)
					map.put(s2, v = V++);
				Edge e1 = new Edge(v, w), e2 = new Edge(u, w);
				e1.rev = e2; e2.rev = e1;
				adjList[u].add(e1);
				adjList[v].add(e2);
			}
			int s = map.get(sc.next()), t = map.get(sc.next());
			out.printf("Scenario #%d\n%d tons\n\n", tc++, findMaxWeight(s, t));
		}
		out.flush();
		out.close();
	}

	static class Edge implements Comparable<Edge>
	{
		int node, weight;
		Edge rev;
		boolean use;
		
		Edge(int x, int y) { node = x; weight = y; }
		
		public int compareTo(Edge e) { return e.weight - weight; }
		
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