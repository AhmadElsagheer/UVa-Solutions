package v109;
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


public class WalkThroughTheForest_UVa10917 {
	
	static final int INF = (int)2e9;
	static ArrayList<Edge>[] adjList;
	static int V, dist[], paths[];
	
	static void dijkstra()
	{
		dist = new int[V];
		Arrays.fill(dist, INF);
		dist[1] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(1, 0));
		while(!pq.isEmpty())
		{
			Edge cur = pq.remove();
			if(cur.cost > dist[cur.node])
				continue;
			for(Edge nxt: adjList[cur.node])
			{
				int cost = cur.cost + nxt.cost;
				if(cost < dist[nxt.node])
					pq.add(new Edge(nxt.node, dist[nxt.node] = cost));
			}
		}	
	}
	
	static void convert()
	{
		for(int u = 0; u < V; ++u)
		{
			ArrayList<Edge> newEdges = new ArrayList<Edge>(adjList[u].size());
			int d = dist[u];
			for(Edge e: adjList[u])
				if(dist[e.node] < d)
					newEdges.add(e);
			adjList[u] = newEdges;
		}
	}
	
	static Stack<Integer> stack;
	static boolean[] visited;
	
	static void toposort(int u)
	{
		visited[u] = true;
		for(Edge e: adjList[u])
			if(!visited[e.node])
				toposort(e.node);
		stack.push(u);
	}
	
	static int countPaths()
	{
		paths = new int[V];
		paths[0] = 1;
		while(!stack.isEmpty())
		{
			int u = stack.pop(), p = paths[u];
			for(Edge e: adjList[u])
				paths[e.node] += p;
		}
		return paths[1];
	}
	
	static int go()
	{
		dijkstra();
		convert();
		visited = new boolean[V];
		stack = new Stack<Integer>();
		toposort(0);
		return countPaths();
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
			int E = sc.nextInt();
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Edge>();
			while(E-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1, w = sc.nextInt();
				adjList[u].add(new Edge(v, w));
				adjList[v].add(new Edge(u, w));
			}
			out.println(go());	
		}
		out.flush();
		out.close();
	}

	static class Edge implements Comparable<Edge>
	{
		int node, cost;
		
		Edge(int x, int y) { node = x; cost = y; }
		
		public int compareTo(Edge e) { return cost - e.cost; }
		
		public String toString() { return ":" + node; }
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