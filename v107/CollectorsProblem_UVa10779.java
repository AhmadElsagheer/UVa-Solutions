package v107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CollectorsProblem_UVa10779 {
	
	static int V, p[], res[][];
	
	static int maxFlow()
	{
		int mf = 0;
		while(true)
		{
			p = new int[V];
			Queue<Integer> q = new LinkedList<Integer>();
			Arrays.fill(p, -1);
			p[1] = 1;
			q.add(1);
			while(!q.isEmpty())
			{
				int u = q.remove();
				if(u == 0)
					break;
				for(int i = 0; i < V; ++i)
					if(p[i] == -1 && res[u][i] > 0)
					{
						p[i] = u;
						q.add(i);
					}
			}
			if(p[0] == -1)
				break;
			++mf;
			augment(0);
		}
		return mf;
	}
	
	static void augment(int v)
	{
		if(v == 1)
			return;
		res[p[v]][v]--;
		res[v][p[v]]++;
		augment(p[v]);
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int N = sc.nextInt(), M = sc.nextInt();
			V = N + M + 1;
			res = new int[V][V];
			for(int i = 1; i <= N; ++i)
			{
				int k = sc.nextInt();
				while(k-->0)
					res[i][sc.nextInt() + N]++;
				if(i != 1)
					for(int j = 1; j <= M; ++j)
						if(res[i][j + N] != 0)
							res[i][j + N]--;
						else
							res[j + N][i] = 1;
			}
			for(int i = 1; i <= M; ++i)
				res[i + N][0] = 1;
			out.format("Case #%d: %d\n", t, maxFlow());
			
		}
		out.flush();
	}
//	static final int INF = 1<<25;
//	static Node source, sink;
//	static Node[] lastCheck;
//	public static void main(String[] args) throws IOException {
//		
//		Scanner sc = new Scanner(System.in);
//		PrintWriter out = new PrintWriter(System.out);
//	
//		int tc = sc.nextInt();
//		for(int t = 1; t <= tc; ++t)
//		{
//			int N = sc.nextInt(), M = sc.nextInt();
//			int[][] stickers = new int[N][M];
//			for(int i = 0; i < N; ++i)
//			{
//				int k = sc.nextInt();
//				while(k-->0)
//					++stickers[i][sc.nextInt() -  1];
//			}
//			source = new Node();
//			sink = new Node();
//			lastCheck = new Node[M];
//			for(int i = 0; i < M; ++i)
//				addEdge(lastCheck[i] = new Node(), sink, 1);
//			Node[][][] nodes = new Node[N][2][M];
//			for(int i = 1; i < N; ++i)
//			{
//				Node center = new Node();
//				for(int j = 0; j < M; ++j)
//					if(stickers[i][j] == 0)
//					{
//						Node nn = new Node();
//						addEdge(nn, center, 1);
//						nodes[i][0][j] = nn;
//						
//					}
//					else
//						if(stickers[i][j] > 1)
//						{
//							Node nn = new Node();
//							addEdge(center, nn, stickers[i][j] - 1);
//							nodes[i][1][j] = nn;
//							addEdge(nn, lastCheck[j], INF);
//						}
//			}
//			for(int j = 0; j < M; ++j)
//			{
//				Node nn = new Node();
//				addEdge(source, nn, stickers[0][j]);
//				addEdge(nn, lastCheck[j], INF);
//				nodes[0][1][j] = nn;
//			}
//			
//			for(int i = 0; i < N; ++i)
//				for(int j = 0; j < N; ++j)
//					if(i != j)
//						for(int k = 0; k < M; ++k)
//							addEdge(nodes[i][1][k], nodes[j][0][k], INF);
//			out.format("Case #%d: %d\n", t, maxFlow());
//		}
//		out.flush();
//	}
//	
//	static void addEdge(Node x, Node y, int w)
//	{
//		if(x == null || y == null)
//			return;
//		Edge e1 = new Edge(y, w), e2 = new Edge(x, 0); 
//		x.neighbors.add(e1);
//		y.neighbors.add(e2);
//		e1.rev = e2;
//		e2.rev = e1;
//	}
//	
//	static Node[] p;
//	static Edge[] path;
//	
//	static int maxFlow()
//	{
//		int mf = 0;
//		while(true)
//		{
//			Queue<Node> q = new LinkedList<Node>();
//			p = new Node[Node.V];
//			path = new Edge[Node.V];
//			p[source.idx] = source;
//			q.add(source);
//			while(!q.isEmpty())
//			{
//				Node u = q.remove();
//				if(u == sink)
//					break;
//				
//				for(int i = 0; i < u.neighbors.size(); ++i)
//				{
//					Edge e = u.neighbors.get(i);
//					if(p[e.to.idx] == null && e.cost > 0)
//					{
//						path[e.to.idx] = e;
//						p[e.to.idx] = u;
//						q.add(e.to);
//					}
//				}
//			}
//			if(p[sink.idx] == null)
//				break;
//			++mf;
//			augment(sink);
//		}
//		return mf;
//	}
//	
//	static void augment(Node v)
//	{
//		if(v == source)
//			return;
//		Edge e = path[v.idx];
//		e.cost--;
//		e.rev.cost++;
//		augment(p[v.idx]);
//	}
//	
//	static class Node
//	{
//		static int V;
//		int idx;
//		int color, friend;
//		ArrayList<Edge> neighbors = new ArrayList<Edge>();
//		
//		Node() { idx = V++;}
//		
//		Node(int x, int y) { friend = x; color = y; idx = V++;}
//	
//	}
//	
//	static class Edge
//	{
//		Node to;
//		int cost;
//		Edge rev;
//		Edge(Node x, int y) { to = x; cost = y; }
//	}
//	
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
