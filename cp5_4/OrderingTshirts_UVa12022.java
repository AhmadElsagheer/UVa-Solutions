package cp5_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class OrderingTshirts_UVa12022 {
	
	static int[][][][] memo;
	static int N;
	
	static int dp(int idx, int msk, int grp, int valid)
	{
		if(msk == 0)
			return 1;
		if(grp == N)
			return 0;
		if(idx == N)
			return valid == 1 ? dp(0, msk, grp + 1, 0): 0;
		
		if(memo[valid][idx][grp][msk] != -1)
			return memo[valid][idx][grp][msk];
		
		int ret = dp(idx + 1, msk, grp, valid);
		if((1<<idx & msk) != 0)
			ret += dp(idx + 1, msk & ~(1<<idx), grp, 1);
		
		return memo[valid][idx][grp][msk] = ret;
	}
	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			N = sc.nextInt();
			memo = new int[2][N][N][1<<N];
			for(int i = 0; i < 2; ++i)
				for(int j = 0; j < N; ++j)
					for(int k = 0; k < N; ++k)
						Arrays.fill(memo[i][j][k], -1);
			out.println(dp(0, (1<<N) - 1, 0, 0));
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
