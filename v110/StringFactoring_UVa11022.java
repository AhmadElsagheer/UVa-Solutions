package v110;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class StringFactoring_UVa11022 {

	static final int INF = (int)1e9;
	static char[] s;
	static int[][] memo;

	static int dp(int i, int j)
	{
		if(i > j)
			return 0;
		if(i == j)
			return 1;
		if(memo[i][j] != -1)
			return memo[i][j];
		int ret = j - i + 1;
		for(int k = i; k <= j; ++k)
			for(int len = 1; len < k - i + 1 || len == k - i + 1 && k != j; ++len)
				if(compress(i, k, len))
					ret = Math.min(ret, dp(i, i + len - 1) + dp(k + 1, j));
		return memo[i][j] = ret;			
	}

	static boolean compress(int i, int j, int len)
	{
		if((j - i + 1) % len != 0)
			return false;
		for(int k = 0, b = i; i <= j; ++i, k = (k + 1) % len)
			if(s[i] != s[b + k])
				return false;
		return true;
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			s = sc.next().toCharArray();
			if(s[0] == '*')
				break;
			memo = new int[s.length][s.length];
			for(int i = 0; i < s.length; ++i)
				Arrays.fill(memo[i], -1);
			out.println(dp(0, s.length - 1));
		}
		out.flush();
		out.close();
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){    br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){    br = new BufferedReader(r);}

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