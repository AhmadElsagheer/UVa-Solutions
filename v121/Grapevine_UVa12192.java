package v121;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Grapevine_UVa12192 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			int N = sc.nextInt(), M = sc.nextInt();
			if(N + M == 0)
				break;
			int[][] grid = new int[N][M];
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < M; ++j)
					grid[i][j] = sc.nextInt();
			int Q = sc.nextInt();
			while(Q-->0)
			{
				int L = sc.nextInt(), R = sc.nextInt(), ans = 0;
				for(int i = 0; i < N; ++i)
				{
					int j = -1, lo = 0, hi = M - 1, cur = -1;
					while(lo <= hi)
					{
						int mid = lo + hi >> 1;
						if(grid[i][mid] >= L)
						{
							j = mid;
							hi = mid - 1;
						}
						else
							lo = mid + 1;
					}
					
					if(j != -1)
					{
						lo = 1; hi = Math.min(N - i, M - j);
						while(lo <= hi)
						{
							int mid = lo + hi >> 1;
							if(grid[i + mid - 1][j + mid - 1] <= R)
							{
								cur = mid;
								lo = mid + 1;
							}
							else
								hi = mid - 1;
						}
						ans = Math.max(ans, cur);
					}
				}
				sb.append(ans + "\n");
			}
			sb.append("-\n");
		}
		out.print(sb);
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