package v012;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PalindromicPaths_UVa1244 {

	static final int INF = (int)1e9;
	static char[][] adjMat;
	static int[][] memo;			// memo table can store the best string instead of just length

	static int dp(int i, int j)
	{
		if(i == j)
			return 0;
		if(memo[i][j] != -1)
			return memo[i][j];
		int ret = adjMat[i][j] == '*' ? -INF : 1;
		for(int k = i + 1; k < j; ++k)
			for(int w = k; w < j; ++w)
			{
				char x = adjMat[i][k], y = adjMat[w][j];
				if(x != '*' && x == y)
					ret = Math.max(ret, 2 + dp(k, w));
			}
		return memo[i][j] = ret;
	}

	static String ans;

	static void print(int i, int j, String s)
	{
		if(i == j)
		{
			if(ans == null || s.compareTo(ans) < 0)
				ans = s;
			return;
		}
		int optimal = dp(i, j);
		if(optimal == 1)
		{
			s += adjMat[i][j];
			if(ans == null || s.compareTo(ans) < 0)
				ans = s;
		}
		else
			for(int k = i + 1; k < j; ++k)
				for(int w = k; w < j; ++w)
				{
					char x = adjMat[i][k], y = adjMat[w][j];
					if(x != '*' && x == y && optimal == 2 + dp(k, w))
						print(k, w, s + x);
				}
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int N = sc.nextInt();
			adjMat = new char[N][];
			memo = new int[N][N];
			for(int i = 0; i < N; ++i)
			{
				Arrays.fill(memo[i], -1);
				adjMat[i] = sc.next().toCharArray();
			}
			ans = null;
			print(0, N - 1, "");
			if(ans == null)
				ans = "NO PALINDROMIC PATH";
			else
			{
				int len = dp(0, N - 1);
				StringBuilder sb = new StringBuilder(ans.substring(0, len / 2));
				ans += sb.reverse().toString();
			}
			out.println(ans);
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