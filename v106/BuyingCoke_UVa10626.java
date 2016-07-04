package v106;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BuyingCoke_UVa10626 {

	static final int INF = (int)1e9;
	static int[] memo;

	static int dp(int n1, int n5, int n10, int C)
	{
		int idx = n10 * 151 * 151 + n5 * 151 + C; 
		if(C == 0)
			return 0;
		if(memo[idx] != -1)
			return memo[idx];

		int ret = INF;
		if(n10 >= 1)
		{
			// - 10 + 2(1)
			ret = 1 + dp(n1 + 2, n5, n10 - 1, C - 1);
			// - 10 - (3)1 + 5
			if(n1 >= 3)
				ret = Math.min(ret, 4 + dp(n1 - 3, n5 + 1, n10 - 1, C - 1));
		}

		// - (2)5 + (2)1
		if(n5 >= 2)
			ret = Math.min(ret, 2 + dp(n1 + 2, n5 - 2, n10, C - 1));
		
		// - 5 - (3)1
		if(n5 >= 1 && n1 >= 3)
			ret = Math.min(ret, 4 + dp(n1 - 3, n5 - 1, n10, C - 1));

		// - 8(1)
		if(n1 >= 8)
			ret = Math.min(ret, 8 + dp(n1 - 8, n5, n10, C - 1));

		return memo[idx] = ret;
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int C = sc.nextInt(), n1 = sc.nextInt(), n5 = sc.nextInt(), n10 = sc.nextInt();

			memo = new int[51*151*151];
			Arrays.fill(memo, -1);
			out.println(dp(n1, n5, n10, C));
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