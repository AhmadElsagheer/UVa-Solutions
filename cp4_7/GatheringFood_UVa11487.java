package cp4_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class GatheringFood_UVa11487 {

	static final int mod = 20437;

	static int[] dx = new int[] { 0, 0, -1, 1};
	static int[] dy = new int[] { -1, 1, 0, 0};
	static char[][] grid;
	static int N;

	static boolean valid(int x, int y, int i)
	{
		return x != -1 && y != -1 && x != N && y != N && (grid[x][y] == '.' || grid[x][y] - 'A' == i);
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = 1;
		while(true)
		{
			N = sc.nextInt();
			int x = -1, y = -1;
			if(N == 0)
				break;
			grid = new char[N][N];
			int n = 0;
			for(int i = 0; i < N; ++i)
			{
				String s = sc.next();
				for(int j = 0; j < N; ++j)
				{
					if(s.charAt(j) == 'A') { x = i; y = j;}
					grid[i][j] = s.charAt(j);
					if(s.charAt(j) >= 'A' && s.charAt(j) <= 'Z')
						n = Math.max(n, s.charAt(j) - 'A');
				}
			}

			int count = 1, min = 0;
			for(int i = 1; i <= n; ++i)
			{
				int[][] dist = new int[N][N];
				int[][] paths = new int[N][N];
				dist[x][y] = paths[x][y] = 1;
				Queue<Cell> q = new LinkedList<Cell>();
				q.add(new Cell(x, y));
				grid[x][y] = '.';
				x = -1; y = -1;
				while(!q.isEmpty())
				{
					Cell cur = q.remove();
					if(grid[cur.x][cur.y] - 'A' == i)
					{
						x = cur.x; y = cur.y;
					}

					for(int k = 0; k < 4; ++k)
					{
						int nxtX = cur.x + dx[k], nxtY = cur.y + dy[k];
						if(valid(nxtX, nxtY, i))
						{
							if(dist[nxtX][nxtY] == 0)
							{
								dist[nxtX][nxtY] = dist[cur.x][cur.y] + 1;
								paths[nxtX][nxtY] = paths[cur.x][cur.y];
								q.add(new Cell(nxtX, nxtY));
							}
							else if(dist[nxtX][nxtY] == dist[cur.x][cur.y] + 1)
								paths[nxtX][nxtY] = (paths[nxtX][nxtY] + paths[cur.x][cur.y])%mod;
						}
					}
				}
				if(x == -1)
				{
					count = -1;
					break;
				}

				min += dist[x][y] - 1;
				count = (count * paths[x][y]) % mod;
			}

			if(count == -1)
				out.printf("Case %d: Impossible\n", tc++);
			else
				out.printf("Case %d: %d %d\n", tc++, min, count);
		}
		out.flush();
		out.close();

	}

	static class Cell
	{
		int x, y;

		Cell(int a, int b) { x = a; y = b; }
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
			return Double.parseDouble(next());
		}

		public boolean ready() throws IOException {return br.ready();}


	}
}