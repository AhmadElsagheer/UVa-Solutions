package v103;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class OptimalBinarySearchTree_UVa10304 {

	static final int INF = (int)1e9;
	static int[] f, prefix;
	static int[][] memo;
	
	static int dp(int l, int r)
	{
		if(l >= r)
			return 0;
		if(memo[l][r] != -1)
			return memo[l][r];
		int ret = INF;
		for(int k = l; k <= r; ++k)
			ret = Math.min(ret, getSum(l, k, r) + dp(l, k-1) + dp(k + 1, r));
		return memo[l][r] = ret;
	}
	
	static int getSum(int l, int k, int r)
	{
		int sum = prefix[r] - f[k];
		if(l > 0)
			sum -= prefix[l-1];
		return sum;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			int N = sc.nextInt();
			f = new int[N];
			prefix = new int[N];
			for(int i = 0; i < N; ++i)
			{
				prefix[i] = f[i] = sc.nextInt();
				if(i > 0)
					prefix[i] += prefix[i-1];
			}
			memo = new int[N][N];
			for(int i = 0; i < N; ++i)
				Arrays.fill(memo[i], -1);
			out.println(dp(0, N - 1));
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