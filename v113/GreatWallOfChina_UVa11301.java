package v113;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class GreatWallOfChina_UVa11301 {

	static final int[] dx = new int[] {0, 0, -1, 1};
	static final int[] dy = new int[] {-1, 1, 0, 0};
	static ArrayList<Edge>[] adjList;
	static int V, totalCost, p[];
	static Edge[] pEdge;

	static int minCost()
	{
		totalCost = 0;
		int flow = 0;
		while(flow < 3)
		{
			int[] dist = new int[V];
			Arrays.fill(dist, (int)1e7);
			dist[0] = 0;
			p = new int[V];
			pEdge = new Edge[V];
			Queue<Integer> q = new LinkedList<Integer>();
			int[] countEnter = new int[V], inQueue = new int[V];
			q.add(0);
			inQueue[0] = countEnter[0] = 1;
			while(!q.isEmpty())
			{
				int u = q.remove();
				if(countEnter[u] >= V)
					continue;
				inQueue[u] = 0;
				for(Edge e: adjList[u])
					if(e.cap > 0 && dist[e.node] > dist[u] + e.cost)
					{
						dist[e.node] = dist[u] + e.cost;
						p[e.node] = u;
						pEdge[e.node] = e;
						if(inQueue[e.node] == 0)
						{
							countEnter[e.node] += inQueue[e.node] = 1;
							q.add(e.node);
						}
						
					}
			}

			if(pEdge[1] == null)
				break;
			flow += aug(1, 3 - flow);
		}
		return totalCost;
	}

	static int aug(int v, int flow)
	{
		if(v == 0)
			return flow;
		int u = p[v];
		Edge e = pEdge[v];
		flow = aug(u, Math.min(flow, e.cap));
		e.cap -= flow;
		e.rev.cap += flow;
		totalCost += flow * e.cost;
		return flow;
	}

	static void addEdge(int u, int v, int c, int w)
	{
		Edge e1 = new Edge(v, c, w), e2 = new Edge(u, 0, -w);
		e1.rev = e2;
		e2.rev = e1;
		adjList[u].add(e1);
		adjList[v].add(e2);
	}

	static int Vin(int v) { return (v << 1) + 2; }

	static int Vout(int v) { return (v << 1 | 1) + 2; }

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int C = sc.nextInt();
			if(C == 0)
				break;
			V = C * 5 * 2 + 2;
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Edge>();

			char[][] grid = new char[5][];
			for(int i = 0; i < 5; ++i)
			{
				grid[i] = sc.next().toCharArray();
				if(grid[i][0] == '@')
					addEdge(0, Vin(i * C), 1, 0);
				addEdge(Vin(i * C), Vout(i * C), 1, 0);
				for(int j = 1; j < C; ++j)
					addEdge(Vin(i * C + j), Vout(i * C + j), 1, grid[i][j] - '0');
			}

			for(int i = 0; i < 5; ++i)
				for(int j = 0; j < C; ++j)
				{
					for(int k = 0; k < 4; ++k)
					{
						int x = i + dx[k], y = j + dy[k];
						if(x >= 0 && x < 5 && y >= 0 && y < C)
							addEdge(Vout(i * C + j), Vin(x * C + y), 1, 0);
					}
					if(j == C - 1)
						addEdge(Vout(i * C + j), 1, 1, 0);
				}
			out.println(minCost());
		}
		out.flush();
		out.close();
	}

	static class Edge
	{
		int node, cap, cost;
		Edge rev;

		Edge(int a, int b, int c) { node = a; cap = b; cost = c; }
	}
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){    br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){    br = new BufferedReader(r);}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}
	}
}