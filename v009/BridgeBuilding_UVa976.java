package v009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BridgeBuilding_UVa976 {

	static int[] dx = new int[] {0, 0, -1, 1};
	static int[] dy = new int[] {-1, 1, 0, 0};
	
	static void flood(char[][] grid, int R, int C, int sx, int sy, char l)
	{
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(sx * C + sy); 
		grid[sx][sy] = l;
	
		while(!q.isEmpty())
		{
			int u = q.remove(), x = u / C, y = u % C;
			for(int k = 0; k < 4; ++k)
			{
				int i = x + dx[k], j = y + dy[k];
				if(i != -1 && j != -1 && i != R && j != C && grid[i][j] == '#')
				{
					grid[i][j] = l;
					q.add(i * C + j);
				}
			}
		}
	}
	
	static final int INF = (int)1e9;
	static int D, cost[], memo[][];
	
	static int dp(int b, int col)
	{
		if(b == 0)
			return 0;
		if(col < 0)
			return INF;
		if(memo[b][col] != -1)
			return memo[b][col];
		return memo[b][col] = Math.min(dp(b, col - 1), cost[col] + dp(b - 1, col - D - 1));
	}
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(sc.ready())
		{
			int R = sc.nextInt(), C = sc.nextInt(), B = sc.nextInt();
			D = sc.nextInt();
			char[][] grid = new char[R][];
			for(int i = 0; i < R; ++i)
				grid[i] = sc.next().toCharArray();
			
			//1. Label terrain
			flood(grid, R, C, 0, 0, 'N');
			flood(grid, R, C, R - 1, 0, 'S');
			
			//2. DP
			cost = new int[C];
			for(int j = 0; j < C; ++j)
			{
				int maxN = 0, minS = R - 1;
				for(int i = 1; i < R - 1; ++i)
					if(grid[i][j] == 'N')
						maxN = i;
					else if(grid[i][j] == 'S' && minS == R - 1)
							minS = i;
				cost[j] = minS - maxN - 1;
			}

			memo = new int[B + 1][C];
			for(int i = 0; i <= B; ++i)
				Arrays.fill(memo[i], -1);
			out.println(dp(B, C - 1));
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