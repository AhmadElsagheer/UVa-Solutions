package v108;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GameOfSum_UVa10891 {

	static final int INF = (int)2e9;
	static int N, A[];
	static Integer memo[][][];
	
	static int dp(int t, int i, int j)
	{
		if(i == j)
			return A[i];
		if(i > j)
			return 0;
		if(memo[t][i][j] != null)
			return memo[t][i][j];
		int ret = -INF;
		for(int k = i, sum = 0; k <= j; ++k)
		{
			sum += A[k];
			ret = Math.max(ret, sum - dp(t ^ 1, k + 1, j));
		}
		for(int k = j, sum = 0; k >= i; --k)
		{
			sum += A[k];
			ret = Math.max(ret, sum - dp(t ^ 1, i, k - 1));
		}
		return memo[t][i][j] = ret;
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			N = sc.nextInt();
			if(N == 0)
				break;
			A = new int[N];
			for(int i = 0; i < N; ++i)
				A[i] = sc.nextInt();
			
			memo = new Integer[2][N][N];
			out.println(dp(0, 0, N - 1));
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
			String s = nextLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}
	}
}