package v103;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class OptimalBinarySearchTree_UVa10304 {

	/*
	 * Knuth's Optimization
	 */
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			int n = sc.nextInt(), f[] = new int[n];
			for(int i = 0; i < n; ++i)
				f[i] = sc.nextInt();
			int[] prefix = new int[n];
			prefix[0] = f[0];
			for(int i = 1; i < n; ++i)
				prefix[i] = prefix[i - 1] + f[i];
			int[][] dp = new int[n][n], p = new int[n][n];
			for(int i = 0; i < n; ++i)
				p[i][i] = i;
			for(int len = 2; len <= n; ++len)
				for(int i = 0, j = i + len - 1; j < n; ++i, ++j)
				{
					int left = p[i][j - 1], right = p[i + 1][j], ret = (int)1e9;
					for(int k = left; k <= right; ++k)
					{
						int w = prefix[j] - f[k] - (i > 0 ? prefix[i - 1] : 0);
						if(k > i)
							w += dp[i][k - 1];
						if(k < j)
							w += dp[k + 1][j];
						if(w < ret)
						{
							ret = w;
							p[i][j] = k;
						}
					}
					dp[i][j] = ret;
				}
			out.println(dp[0][n-1]);
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