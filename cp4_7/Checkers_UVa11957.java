package cp4_7;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Checkers_UVa11957 {

	static final int mod = (int)1e6 + 7;
	static int N, memo[][];
	static char[][] board;
	static int dp(int x, int y)
	{
		if(y == N - 1)
			return 1;
		if(memo[x][y] != -1)
			return memo[x][y];
		int ll = 0, rr = 0;
		if(x + 1 < N)
		{
			if(board[x+1][y+1] == '.')
				rr = dp(x + 1, y + 1);
			else
				if(y + 2 < N && x + 2 < N && board[x+2][y+2] == '.')
					rr = dp(x + 2, y + 2);
		}
		if(x - 1 >= 0)
		{
			if(board[x-1][y+1] == '.')
				ll = dp(x - 1, y + 1);
			else
				if(y + 2 < N && x - 2 >= 0 && board[x-2][y+2] == '.')
					ll = dp(x - 2, y + 2);
		}
		return memo[x][y] = (ll + rr)%mod;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			N = sc.nextInt();
			memo = new int[N][N];
			for(int i = 0; i < N; ++i)
				Arrays.fill(memo[i], -1);
			board = new char[N][N];
			int x = -1, y = -1;
			for(int i = 0; i < N; ++i)
			{
				String line = sc.next();
				for(int j = 0; j < N; ++j)
				{
					board[j][N - i - 1] = line.charAt(j);
					if(line.charAt(j) == 'W')
					{
						y = N - i - 1;
						x = j;				
					}
				}
			}
			
			out.format("Case %d: %d\n", t, dp(x, y));
		}
		out.flush();

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
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
