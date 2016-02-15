package cp8_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class HelpTheWinners_UVa12030 {
	
	static int[][] match;
	static long[][][] memo;
	static int N;
	
	static long dp(int dress, int shoes, int isSuper)
	{
		if(dress == N)
			return isSuper != 0 ? 1 : 0;
		if(memo[dress][shoes][isSuper] != -1)
			return memo[dress][shoes][isSuper];
		long c = 0;
		for(int i = 0; i < N; ++i)
			if((shoes & (1<<i)) == 0)
				c += dp(dress + 1, shoes | (1<<i), isSuper == 2 ? 2 : (match[dress][i] != 1 ? match[dress][i] : isSuper));
		return memo[dress][shoes][isSuper] = c;
	}
	
	public static void main(String[] args) throws IOException {
	
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			N = sc.nextInt();
			match = new int[N][N];
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					match[i][j] = sc.nextInt();
			memo = new long[N][1<<N][3];
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < 1<<N; ++j)
					Arrays.fill(memo[i][j], -1);
			out.printf("Case %d: %d\n", t, dp(0, 0, 1));
			
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


		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready(); }


	}
}


