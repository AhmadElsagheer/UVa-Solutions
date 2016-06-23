package v118;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class AccountBook_UVa11832 {

	static int[][] dp;
	static long[][] canPlus, canMinus;
	static int N, F, OFFSET, T[];

	static int solve()
	{
		dp = new int[N + 1][OFFSET<<1|1];
		canPlus = new long[N + 1][OFFSET<<1|1];
		canMinus = new long[N + 1][OFFSET<<1|1];
		dp[N][F + OFFSET] = 1;
		
		for(int idx = N - 1; idx >= 0; --idx)
		{
			int x = T[idx];
			for(int sum = -OFFSET; sum <= OFFSET; ++sum)
			{
				int ret = 0;
				long p = 0, m = 0;
				if(sum - x >= -OFFSET)
				{
					int y = dp[idx + 1][sum - x + OFFSET];
					if(y == 1)
					{
						ret = 1;
						p |= canPlus[idx + 1][sum - x + OFFSET];
						m |= canMinus[idx + 1][sum - x + OFFSET] | 1l<<idx;
					}
				}
				if(sum + x <= OFFSET)
				{
					int y = dp[idx + 1][sum + x + OFFSET];
					if(y == 1)
					{
						ret = 1;
						p |= canPlus[idx + 1][sum + x + OFFSET] | 1l<<idx;
						m |= canMinus[idx + 1][sum + x + OFFSET];
					}
				}
				canPlus[idx][sum + OFFSET] = p;
				canMinus[idx][sum + OFFSET] = m;
				dp[idx][sum + OFFSET] = ret;
			}
		}
		return dp[0][OFFSET];
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			N = sc.nextInt(); F = sc.nextInt();
			if(N == 0)
				break;
			OFFSET = 1000 * N;
			T = new int[N];
			for(int i = 0; i < N; ++i)
				T[i] = sc.nextInt();
			
			if(solve() != 1)
				out.println("*");
			else
			{
				
				StringBuilder sb = new StringBuilder();
				long x = canPlus[0][OFFSET], y = canMinus[0][OFFSET];
				for(int i = 0; i < N; ++i)
					if((x & 1l<<i) == (y & 1l<<i))
						sb.append("?");
					else if((x & 1l<<i) != 0)
						sb.append("+");
					else
						sb.append("-");
				out.println(sb);
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

		public boolean nextEmpty() throws IOException
		{
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}