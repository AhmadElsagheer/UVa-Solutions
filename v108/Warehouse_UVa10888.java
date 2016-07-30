package v108;
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


public class Warehouse_UVa10888 {

	static final int INF = (int)1e6;
	static final int[] dx = new int[] {0, 0, -1, 1};
	static final int[] dy = new int[] {-1, 1, 0, 0};
	static ArrayList<Integer>[] adjList;
	static int V, totalCost, res[][], cost[][], p[];


	static int minCost()
	{
		totalCost = 0;
		while(true)
		{
			int[] dist = new int[V];
			Arrays.fill(dist, INF);
			dist[0] = 0;
			p = new int[V];
			for(int k = 0; k < V - 1; ++k)
				for(int u = 0; u < V; ++u)
					for(int v: adjList[u])
						if(res[u][v] > 0 && dist[v] > dist[u] + cost[u][v])
						{
							dist[v] = dist[u] + cost[u][v];
							p[v] = u;
						}
			
			if(dist[V - 1] == INF)
				break;
			aug(V - 1, 15);
		}
		return totalCost;
	}
	
	static int aug(int v, int flow)
	{
		if(v == 0)
			return flow;
		int u = p[v];
		flow = aug(u, Math.min(flow, res[u][v]));
		res[u][v] -= flow;
		res[v][u] += flow;
		totalCost += flow * cost[u][v];
		return flow;
	}

	static void addEdge(int u, int v, int w)
	{
		res[u][v] = 1;
		cost[u][v] = w;
		cost[v][u] = -w;
		adjList[u].add(v);
		adjList[v].add(u);
	}

	static int R, C, grid[][];

	static boolean valid(int x, int y)
	{
		return x >= 0 && x < R && y >= 0 && y < C && grid[x][y] != -INF;
	}

	static void bfs(int x, int y, int N)
	{
		int s = grid[x][y];
		int[][] dist = new int[R][C];
		dist[x][y] = 1;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(x * C + y);
		while(!q.isEmpty())
		{
			int u = q.remove();
			x = u / C; y = u % C;
			if(grid[x][y] < 0)
				addEdge(s, N - grid[x][y], dist[x][y] - 1);
			for(int k = 0; k < 4; ++k)
			{
				int xx = x + dx[k], yy = y + dy[k];
				if(valid(xx, yy) && dist[xx][yy] == 0)
				{
					dist[xx][yy] = dist[x][y] + 1;
					q.add(xx * C + yy);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			R = sc.nextInt(); C = sc.nextInt(); V = 0;
			grid = new int[R][C];
			int x = 1, y = -1;
			for(int i = 0; i < R; ++i)
			{
				String s = sc.next();
				for(int j = 0; j < C; ++j)
					if(s.charAt(j) == 'B')
						grid[i][j] = x++;
					else if(s.charAt(j) == 'X')
						grid[i][j] = y--;
					else if(s.charAt(j) == '#')
						grid[i][j] = -INF;
			}
			V = x - y + 2;
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Integer>();
			res = new int[V][V];
			cost = new int[V][V];
			for(int i = 0; i < R; ++i)
				for(int j = 0; j < C; ++j)
					if(grid[i][j] > 0)
					{
						addEdge(0, grid[i][j], 0);
						bfs(i, j, x);
					}
					else if(grid[i][j] < 0 && grid[i][j] != -INF)
						addEdge(x - grid[i][j], V - 1, 0);
			out.println(minCost());
		}
		out.flush();
		out.close();
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