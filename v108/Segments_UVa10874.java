package v108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Segments_UVa10874 {
	
	//dp[j][i]: length of shortest path from start to row i and will drop from row i at end j
//				j = 0 for left end, j = 1 for right end
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int N = sc.nextInt(), L[] = new int[N], R[] = new int[N];
			if(N == 0)
				break;
			for(int i = 0; i < N; ++i)
			{
				L[i] = sc.nextInt() - 1;
				R[i] = sc.nextInt() - 1;
			}
			int[][] dp = new int[2][N];
			dp[0][0] = L[0] + 2 * (R[0] - L[0]);
			dp[1][0] = R[0];
			for(int i = 1; i < N; ++i)
				for(int j = 0; j < 2; ++j)
				{
					int x = L[i-1], y = R[i-1];
					int z = j == 1 ? L[i] : R[i], d = Math.abs(L[i] - R[i]); 
					
					int w1 = Math.abs(x - z) + d + dp[0][i-1];
					int w2 = Math.abs(y - z) + d + dp[1][i-1];
					dp[j][i] = Math.min(w1, w2);	
				}
			int ans = Math.min(N - 1 - L[N-1] + dp[0][N-1], N - 1 - R[N-1] + dp[1][N-1]) + N - 1;
			out.println(ans);
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