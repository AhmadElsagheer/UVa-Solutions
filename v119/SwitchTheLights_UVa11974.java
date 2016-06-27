package v119;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SwitchTheLights_UVa11974 {

	static final int INF = (int)1e9;
	static int[][] memo;
	static int[] lights;
	static int M;
	
	static int dp(int idx, int on)
	{
		if(idx == M)
			return on == 0 ? 0 : INF;
		if(memo[idx][on] != -1)
			return memo[idx][on];
		return memo[idx][on] = Math.min(dp(idx + 1, on), 1 + dp(idx + 1, on ^ lights[idx]));
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int N = sc.nextInt();
			M = sc.nextInt();
			lights = new int[M];
			for(int i = 0; i < M; ++i)
			{
				int x = 0;
				for(int j = 0; j < N; ++j)
					x |= sc.nextInt()<<j;
				lights[i] = x;
			}
			memo = new int[M][1<<N];
			for(int i = 0; i < M; ++i)
				Arrays.fill(memo[i], -1);
			int ans = dp(0, (1<<N) - 1);
			if(ans == INF)
				out.printf("Case %d: IMPOSSIBLE\n", t);
			else
				out.printf("Case %d: %d\n", t, ans);
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