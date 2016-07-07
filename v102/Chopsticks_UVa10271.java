package v102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Chopsticks_UVa10271 {

	static final int INF = (int)1e9;
	static int[][] memo;
	static int N, L[];

	static int dp(int idx, int guests)
	{
		if(guests == 0)
			return 0;

		if(idx >= N - 1 || N - idx < guests * 3)
			return INF;

		if(memo[idx][guests] != -1)
			return memo[idx][guests];

		return memo[idx][guests] = Math.min(dp(idx + 1, guests), sq(L[idx] - L[idx+1]) + dp(idx + 2, guests - 1));
	}

	static int sq(int x) { return x * x; }

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int K = sc.nextInt();
			N = sc.nextInt();
			L = new int[N];
			for(int i = 0; i < N; ++i)
				L[i] = sc.nextInt();
			memo = new int[N][K + 9];
			for(int i = 0; i < N; ++i)
				Arrays.fill(memo[i], -1);
			out.println(dp(0, K + 8));
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