package v120;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * DP with state(x1, y1, x2, y2, t) => TLE, Complexity: O(T * N^4) per test case
 * Dijkstra (Solution below) => AC, Complexity: O(F * T * N^2 log (T * N^2)) per test case
 */
public class InviteYourFriends_UVa12070 {

	static final int INF = (int)1e8;

	static int[] dx = new int[] {0, 0, -1, 1};
	static int[] dy = new int[] {-1, 1, 0, 0};
	
	static boolean valid(int x, int y, int N) { return x != -1 && y != -1 && x != N && y != N; }
	
	static void dijkstra(int N, int T, int[][] grid, int Sx, int Sy, int[][] cost)
	{
		int[][][] dist = new int[T + 1][N][N];
		for(int i = 0; i <= T; ++i)
			for(int j = 0; j < N; ++j)
			Arrays.fill(dist[i][j], INF);
		PriorityQueue<State> pq = new PriorityQueue<State>();
		dist[0][Sx][Sy] = 0;
		pq.add(new State(Sx, Sy, 0, 0));
		
		while(!pq.isEmpty())
		{
			State cur = pq.remove();
			if(cur.t == T || cur.w > dist[cur.t][cur.x][cur.y])
				continue;
			int totalCost = cur.w + grid[cur.x][cur.y], t = cur.t + 1;
			for(int k = 0; k < 4; ++k)
			{
				int x = cur.x + dx[k], y = cur.y + dy[k];
				if(valid(x, y, N) && totalCost < dist[t][x][y])
					pq.add(new State(x, y, t, dist[t][x][y] = totalCost));
			}
		}
		
		for(int i = 0; i < N; ++i)
			for(int j = 0; j < N; ++j)
			{
				int min = INF;
				for(int k = 0; k <= T; ++k)
					min = Math.min(min, dist[k][i][j]);
				cost[i][j] += min;
			}
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			int N = sc.nextInt(), F = sc.nextInt(), T = sc.nextInt();
			if(N == 0)
				break;
			int[][] grid = new int[N][N];
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					grid[i][j] = sc.nextInt();
			int[] xF = new int[F], yF = new int[F];
			for(int i = 0; i < F; ++i)
			{
				xF[i] = sc.nextInt();
				yF[i] = sc.nextInt();
			}
			
			int[][] cost = new int[N][N];
			for(int i = 0; i < F; ++i)
				dijkstra(N, T, grid, xF[i], yF[i], cost);
			int min = INF, x = -1, y = -1;
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					if(cost[i][j] < min)
						min = cost[x = i][y = j];
			if(min == INF)
				out.printf("Case #%d: Impossible.\n", tc++);
			else
				out.printf("Case #%d: Selected city (%d,%d) with minimum cost %d.\n", tc++, x, y, min);
		}
		out.flush();
		out.close();
	}

	static class State implements Comparable<State>
	{
		int x, y, t, w;
		
		State(int a, int b, int c, int d) { x = a; y = b; t = c; w = d; }
		
		public int compareTo(State s) { return w - s.w; }
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