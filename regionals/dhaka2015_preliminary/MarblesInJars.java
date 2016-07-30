package regionals.dhaka2015_preliminary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MarblesInJars {

	static final int mod = (int)1e9 + 7;
	static int N, M[], memo[][];
	
	static int dp(int m, int taken)
	{
		if(m == 0)
			return taken == N ? 1 : 0;
		if(memo[m][taken] != -1)
			return memo[m][taken];
		int ret = dp(m - 1, taken);
		if(taken < N)
			ret = (ret + mult(M[m] - taken, dp(m - 1, taken + 1)))%mod;
		return memo[m][taken] = ret;
	}

	static int mult(long x, long y) { return (int) ( x * y % mod);  }

	public static void main(String[] args) throws NumberFormatException, IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			N = sc.nextInt();
			M = new int[101];
			while(N-->0)
				++M[sc.nextInt()];
			for(int i = 99; i >= 0; --i)
				M[i] += M[i + 1];
			memo = new int[101][101];
			for(int i = 0; i <= 100; ++i)
				Arrays.fill(memo[i], -1);
			out.printf("Case %d: %d\n", t, dp(100, 0));
		}

		out.flush();
		out.close();
	}

	static class Scanner
	{
		BufferedReader br;
		StringTokenizer st;

		Scanner(InputStream s) { br = new BufferedReader(new InputStreamReader(s)); }

		String next() throws IOException
		{
			while(st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		int nextInt() throws NumberFormatException, IOException { return Integer.parseInt(next()); }
	}
}
