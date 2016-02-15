package cp8_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TheVindictiveCoach_UVa702 {
		
	static int N;
	static long[][] memo;
	
	static long dp(int n, int c, int shorter)
	{
		if(n == 1)
			return 1;
		if(memo[n][c] != -1)
			return memo[n][c];
		long ret = 0;
		int start = shorter == 1 ? 0 : (c + 1), end = shorter == 1 ? c : n;
		for(int i = start; i < end; ++i)
		{
			ret += dp(n - 1, i - (shorter == 0 ? 1 : 0), (shorter + 1) & 1);
			if(c == 0 && n == N && ret != 0)
				return ret;
		}
		
		return memo[n][c] = ret;
	}
	
	public static void main(String[] args) throws IOException {

		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			N = sc.nextInt();
			int c = sc.nextInt() - 1;
			memo = new long[N + 1][N];
			for(int i = 1; i <= N; ++i)
				Arrays.fill(memo[i], -1);
			if(c == 0)
				out.println(dp(N, c, 0));
			else
				out.println(dp(N, c, 1));
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
