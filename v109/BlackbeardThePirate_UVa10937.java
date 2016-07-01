package v109;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BlackbeardThePirate_UVa10937 {

	static final int INF = (int)1e9;
	static int R, C;
	static int[] dx = new int[] {0, 0, -1, 1, -1, 1, -1, 1};
	static int[] dy = new int[] {-1, 1, 0, 0, -1, 1, 1, -1};
	
	static boolean valid(int x, int y)
	{
		return x != -1 && y != -1 && x != R && y != C;
	}
	
	static int[] bfs(char[][] grid, Cell s, int t)
	{
		int[] ret = new int[t];
		Arrays.fill(ret, INF);
		int[][] dist = new int[R][C];
		dist[s.x][s.y] = 1;
		Queue<Cell> q = new LinkedList<Cell>();
		q.add(s);
		while(!q.isEmpty())
		{
			Cell cur = q.remove();
			char c = grid[cur.x][cur.y];
			int d = dist[cur.x][cur.y];
			if(c == '@')
				ret[0] = d - 1;
			else if(c >= '0' && c <= '9')
				ret[c-'0'+1] = d - 1;
			for(int k = 0; k < 4; ++k)
			{
				int x = cur.x + dx[k], y = cur.y + dy[k];
				if(valid(x, y) && grid[x][y] != 'X' && dist[x][y] == 0)
				{
					dist[x][y] = d + 1;
					q.add(new Cell(x, y));
				}
			}
		}

		return ret;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			R = sc.nextInt();
			C = sc.nextInt();
			if(R + C == 0)
				break;
			char[][] grid = new char[R][];
			for(int i = 0; i < R; ++i)
				grid[i] = sc.next().toCharArray();
			t = clear(grid) + 1;
			if(t == 0)
			{
				out.println(-1);
				continue;
			}
			Cell[] cells = new Cell[t];
			for(int i = 0; i < R; ++i)
				for(int j = 0; j < C; ++j)
				{
					char c = grid[i][j];
					if(c == '@')
						cells[0] = new Cell(i, j);
					else if(c >= '0' && c <= '9')
						cells[c - '0' + 1] = new Cell(i, j);
				}

			adjMat = new int[t][t]; 
			for(int i = 0; i < t; ++i)
				adjMat[i] = bfs(grid, cells[i], t);
			memo = new int[t][1<<t];
			for(int i = 0; i < t; ++i)
				Arrays.fill(memo[i], -1);
			int ans = dp(0, (1<<t) - 1);
			if(ans == INF)
				ans = -1;
			out.println(ans);
		}
		out.flush();
		out.close();
	}
	
	static int t;
	static int[][] adjMat;
	static int[][] memo;
	
	static int dp(int c, int msk)
	{
		if(msk == 0)
			return adjMat[c][0];
		if(memo[c][msk] != -1)
			return memo[c][msk];
		int ret = INF;
		for(int i = 0; i < t; ++i)
			if((msk & 1<<i) != 0)
				ret = Math.min(ret, adjMat[c][i] + dp(i, msk & ~(1<<i)));
		return memo[c][msk] = ret;
	}
	
	static int clear(char[][] grid)
	{
		char nxt = '0';
		for(int i = 0; i < R; ++i)
			for(int j = 0; j < C; ++j)
			{
				char c = grid[i][j];
				if(c == '~' || c == '#')
					c = 'X';
				else if(c == '*')
				{
					c = 'X';
					for(int k = 0; k < 8; ++k)
					{
						int x = i + dx[k], y = j + dy[k];
						if(valid(x, y))
						{
							if(grid[x][y] == '@' || grid[x][y] == '!')
								return -1;
							if(grid[x][y] != '*')
								grid[x][y] = c;
						}
					}
				}
				else if(c == '!')
					c = nxt++;
				grid[i][j] = c;
			}
		return nxt - '0';
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