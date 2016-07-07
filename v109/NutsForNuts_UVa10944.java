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

public class NutsForNuts_UVa10944 {

	static final int[] dx = new int[] {0, 0, -1, -1, -1, 1, 1, 1};
	static final int[] dy = new int[] {-1, 1, -1, 1, 0, -1, 1, 0};
	static int[][] grid, dist;
	static int R, C, N;

	static boolean valid(int x, int y)
	{
		return x != -1 && y != -1 && x != R && y != C;
	}

	static int[] bfs(int x, int y)
	{
		int[][] dist = new int[R][C];
		int[] ret = new int[N];
		dist[x][y] = 1;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(x * C + y);
		while(!q.isEmpty())
		{
			int cell = q.remove();
			x = cell / C; y = cell % C;
			int d = dist[x][y];
			if(grid[x][y] < N)
				ret[grid[x][y]] = d - 1;
			for(int k = 0; k < 8; ++k)
			{
				int i = x + dx[k], j = y + dy[k];
				if(valid(i, j) && dist[i][j] == 0)
				{
					dist[i][j] = d + 1;
					q.add(i * C + j);
				}
			}
		}
		return ret;
	}
	
	static int[][] memo;
	
	static int tsp(int pos, int vis)
	{
		if(vis == (1<<N-1) - 1)
			return dist[pos][N-1];
		if(memo[pos][vis] != -1)
			return memo[pos][vis];
		int ret = (int)1e6;
		for(int i = 0; i < N - 1; ++i)
			if((vis & 1<<i) == 0)
				ret = Math.min(ret, dist[pos][i] + tsp(i, vis | 1<<i));
		return memo[pos][vis] = ret;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			R = sc.nextInt(); C = sc.nextInt();
			N = 0;
			grid = new int[R][C];
			int Sx = -1, Sy = -1;
			for(int i = 0; i < R; ++i)
			{
				char[] s = sc.next().toCharArray();
				for(int j = 0; j < C; ++j)
				{
					if(s[j] == 'L') 
					{ 
						Sx = i; Sy = j; 
					}
					else if(s[j] == '#')
						grid[i][j] = N++;
					else
						grid[i][j] = 20;
				}
			}
			grid[Sx][Sy] = N++;
			dist = new int[N][N];
			for(int i = 0; i < R; ++i)
				for(int j = 0; j < C; ++j)
					if(grid[i][j] < N)
						dist[grid[i][j]] = bfs(i, j);
			memo = new int[N][1<<N-1];
			for(int i = 0; i < N; ++i)
				Arrays.fill(memo[i], -1);
			out.println(tsp(N-1, 0));
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