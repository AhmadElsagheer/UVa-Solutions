package v007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class TheGame_UVa710 {

	static final int INF = (int)1e9;
	static int[] dx = new int[] {0, 0, -1, 1};
	static int[] dy = new int[] {-1, 1, 0, 0};
	static int W, H;
	static char[][] grid;
	
	static boolean valid(int x, int y)
	{
		return x != -1 && y != -1 && x != W && y != H && grid[y][x] != 'X';
	}
	
	static int dijkstra(int sx, int sy, int tx, int ty)
	{
		int[][][] dist = new int[5][H][W];
		for(int i = 0; i < 4; ++i)
			for(int j = 0; j < H; ++j)
				Arrays.fill(dist[i][j], INF);
		PriorityQueue<State> pq = new PriorityQueue<State>();
		dist[4][sy][sx] = 0;
		pq.add(new State(sx, sy, 4, 0));
		while(!pq.isEmpty())
		{
			State cur = pq.remove();
			int d = dist[cur.d][cur.y][cur.x];
			if(cur.x == tx && cur.y == ty)
				return d;
			if(cur.cost > d)
				continue;

			for(int k = 0; k < 4; ++k)
			{
				int x = cur.x + dx[k], y = cur.y + dy[k];
				int totalCost = cur.cost + (k == cur.d ? 0 : 1);
				if(valid(x, y) && dist[k][y][x] > totalCost)
					pq.add(new State(x, y, k, dist[k][y][x] = totalCost));
			}
		}
		return -1;
	}
	
	static class State implements Comparable<State>
	{
		int x, y, d, cost;
		
		State(int a, int b, int c, int w) { x = a; y = b; d = c; cost = w; }
		
		public int compareTo(State s) { return cost - s.cost; }
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = 1;
		while(true)
		{
			W = sc.nextInt(); H = sc.nextInt();
			if(W == 0 && H == 0)
				break;
			grid = new char[H + 2][W + 2];
			for(int i = 0; i < H; ++i)
			{
				String s = sc.nextLine();
				for(int j = 0; j < s.length(); ++j)
					grid[i + 1][j + 1] = s.charAt(j);
			}
			W += 2;
			H += 2;
			int q = 1;
			out.printf("Board #%d:\n", tc++);
			while(true)
			{
				int sx = sc.nextInt(), sy = sc.nextInt();
				int tx = sc.nextInt(), ty = sc.nextInt();
				if(sx + sy + tx + ty == 0)
					break;
				grid[ty][tx] = '.';
				int ans = dijkstra(sx, sy, tx, ty);
				if(ans == -1)
					out.printf("Pair %d: impossible.\n", q++);
				else
					out.printf("Pair %d: %d segments.\n", q++, ans);
				grid[ty][tx] = 'X';
			}
			out.println();
		}
		out.flush();
		out.close();
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}