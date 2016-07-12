package v110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TowardsZero_UVa11002 {

	static final int OFFSET = 3000;

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int n = sc.nextInt(), m = n*2-1;
			if(n == 0)
				break;
			int[][] board = new int[m][];
			for(int i = 0, k = 1, d = 1; i < m; ++i, k += d)
			{
				board[i] = new int[k];
				for(int j = 0; j < k; ++j)
					board[i][j] = sc.nextInt();
				if(i == n - 1)
					d = -1;
			}

			boolean[][][] dp = new boolean[m][n][OFFSET<<1|1];
			dp[m-1][0][v(board[m-1][0])] = true;
			for(int i = m - 2; i >= 0; --i)
				for(int j = 0, end = board[i].length; j < end; ++j)
				{
					int x = board[i][j];
					for(int sum = -OFFSET; sum <= OFFSET; ++sum)
					{
						boolean y = false;
						if(i < n - 1 && dp[i+1][j+1][v(sum)])
							y = true;

						if(dp[i+1][j][v(sum)])
							y = true;

						if(i >= n - 1 && j > 0 && dp[i+1][j-1][v(sum)])
							y = true;
						
						if(y)
							dp[i][j][v(sum+x)] = dp[i][j][v(sum-x)] = true;
					}
				}

			int ans = OFFSET;
			for(int i = -OFFSET; i <= OFFSET; ++i)
				if(dp[0][0][v(i)])
					ans = Math.min(ans, Math.abs(i));
			out.println(ans);
		}
		out.flush();
		out.close();
	}

	static int v(int x) { return x + OFFSET; }

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