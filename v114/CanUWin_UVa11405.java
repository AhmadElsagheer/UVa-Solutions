package v114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class CanUWin_UVa11405 {

	static int[] dx = new int[] { -1, -1, -2, -2, 1, 1, 2, 2};
	static int[] dy = new int[] { -2, 2, -1, 1, -2, 2, -1, 1};
	static int[][] dist;
	
	static void preprocess()
	{
		dist = new int[64][64];
		for(int i = 0; i < 64; ++i)
		{
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(i);
			while(!q.isEmpty())
			{
				int u = q.remove(), x = u / 8, y = u % 8;
				for(int k = 0; k < 8; ++k)
				{
					int nxtX = x + dx[k], nxtY = y + dy[k];
					if(valid(nxtX, nxtY))
					{
						int v = nxtX * 8 + nxtY;
						if(dist[i][v] == INF)
						{
							dist[i][v] = dist[i][u] + 1;
							q.add(v);
						}
					}
				}
			}
		}
		
	}
	
	static boolean valid(int x, int y)
	{
		return x >= 0 && y >= 0 && x < 8 && y < 8 && grid[x][y] != 'K' && grid[x][y] != 'p';
	}
	
	static final int INF = (int)1e9;
	static int m, pawns[], memo[][];
	static char[][] grid;

	static int dp(int pos, int alive)
	{
		if(alive == 0)
			return 0;
		if(memo[pos][alive] != -1)
			return memo[pos][alive];
		int ans = INF;
		for(int i = 0; i < m; ++i)
			if((alive & 1<<i) != 0)
				ans = Math.min(ans, dist[pos][pawns[i]] + dp(pawns[i], alive & ~(1<<i)));
		return memo[pos][alive] = ans;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt(), knight = -1;
			grid = new char[8][];
			pawns = new int[8];
			m = 0;
			for(int i = 0; i < 8; ++i)
			{
				char[] s = sc.next().toCharArray();
				grid[i] = s;
				for(int j = 0; j < 8; ++j)
					if(s[j] == 'k')
						knight = i * 8 + j;
					else if(s[j] == 'P')
						pawns[m++] = i * 8 + j;
			}
			preprocess();
			memo = new int[64][1<<m];
			for(int i = 0; i < 64; ++i)
				Arrays.fill(memo[i], -1);
			if(dp(knight, (1<<m) - 1) > n)
				out.println("No");
			else
				out.println("Yes");
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

		public double nextDouble() throws IOException
		{
			return Double.parseDouble(next());
		}

		public boolean ready() throws IOException {return br.ready();}


	}
}