package regionals.dhaka2015_preliminary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MarblesInJars2 {

	static final int mod = (int)1e9 + 7;
	static int N, M[];

	public static void main(String[] args) throws NumberFormatException, IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			N = sc.nextInt();
			M = new int[N];
			for(int i = 0; i < N; ++i)
				M[i] = sc.nextInt();
			Arrays.sort(M);
			long res = 1;
			for(int i = 0; i < N; ++i)
				res = (res * (M[i] - i)) % mod;
			out.printf("Case %d: %d\n", t, res);
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
