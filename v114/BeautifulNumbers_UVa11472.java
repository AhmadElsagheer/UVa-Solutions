package v114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BeautifulNumbers_UVa11472 {

	static final int mod = (int)1e9 + 7;
	static int[][][][] memo;
	
	static int dp(int base, int lst, int digits, int used)
	{
		int valid = 0;
		if(lst != 0 && used == (1<<base) - 1)
			valid = 1;
		if(digits == 0)
			return valid;
		if(memo[base][lst][digits][used] != -1)
			return memo[base][lst][digits][used];
		int lower = lst == 0 ? 0 : dp(base, lst - 1, digits - 1, used | 1<<lst-1);
		int higher = lst == base - 1 ? 0 : dp(base, lst + 1, digits - 1, used | 1<<lst+1);
		return memo[base][lst][digits][used] = (valid + lower + higher)%mod;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		memo = new int[11][10][101][1<<10];
		for(int i = 2; i <= 10; ++i)
			for(int j = 0; j < 10; ++j)
				for(int k = 0; k <= 100; ++k)
					Arrays.fill(memo[i][j][k], -1);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int N = sc.nextInt(), M = sc.nextInt();
			if(M == 0)
				out.println(0);
			else
			{
				int ans = 0;
				for(int i = 0; i < N; ++i)
					ans = (ans + dp(N, i, M - 1, 1<<i))%mod;
				out.println(ans);
			}
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
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}


		public boolean ready() throws IOException {return br.ready();}

	}
}