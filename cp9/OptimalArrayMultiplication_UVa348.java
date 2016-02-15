package cp9;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class OptimalArrayMultiplication_UVa348 {
	
	static int[][] dim;
	static long[][] memo;
	static StringBuilder sb = new StringBuilder();
	
	static long dp(int i, int j)
	{
		if(i == j)
			return 0;
		if(memo[i][j] != -1)
			return memo[i][j];
		long min = Long.MAX_VALUE;
		for(int k = i; k < j; ++k)
			min = Math.min(min, dp(i, k) + dp(k + 1, j) + dim[i][0] * dim[k][1] * dim[j][1]);
		return memo[i][j] = min;
	}
	
	static void print(int i, int j)
	{
		if(i == j)
			sb.append("A").append(i + 1);
		else
		{
			long optimal = dp(i, j);
			for(int k = i; k < j; ++k)
				if(optimal == dp(i, k) + dp(k + 1, j) + dim[i][0] * dim[k][1] * dim[j][1])
				{
					sb.append("(");
					print(i, k);
					sb.append(" x ");
					print(k + 1, j);
					sb.append(")");
					return;
				}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		
		int k = 1;
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			dim = new int[n][2];
			for(int i = 0; i < n; i++)
			{
				dim[i][0] = sc.nextInt();
				dim[i][1] = sc.nextInt();
			}
			memo = new long[n][n];
			for(int i = 0; i < n; ++i)
				Arrays.fill(memo[i], -1);
			sb.append("Case ").append(k++).append(": ");
			print(0, n - 1);
			sb.append("\n");
		}
		System.out.print(sb);
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
