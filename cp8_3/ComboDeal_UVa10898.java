package cp8_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ComboDeal_UVa10898 {

	static int[][] combo, memo;
	static int N, I, itemCost[];
	
	static int dp(int idx, int order)
	{
		if(idx == N)
		{
			int ret = 0;
			for(int i = 0; i < I; ++i)
			{
				ret += order%10 * itemCost[i];
				order /= 10;
			}
			return ret;
		}
		if(memo[idx][order] != -1)
			return memo[idx][order];

		int div = 1, minus = 0;
		for(int i = 0; minus != -1 && i < I; ++i)
			if(combo[idx][i] <= order/div%10)
			{
				minus += div * combo[idx][i];
				div *= 10;
			}
		int leave = dp(idx + 1, order), take = Integer.MAX_VALUE;
		if(minus > 0)
			take = dp(idx, order - minus) + combo[idx][I];
		return memo[idx][order] = Math.min(take, leave);
	}
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			I = sc.nextInt();
			itemCost = new int[I];
			for(int i = 0; i < I; ++i)
				itemCost[i] = sc.nextInt();
			N = sc.nextInt();
			combo = new int[N][I + 1];
			for(int i = 0; i < N; ++i)
				for(int j = 0; j <= I; ++j)
					combo[i][j] = sc.nextInt();
			
			memo = new int[N][1000000];
			for(int i = 0; i < N; ++i)
				Arrays.fill(memo[i], -1);
			int q = sc.nextInt();
			while(q-->0)
			{
				int order = 0;
				for(int i = 0; i < I; ++i)
					order = order * 10 + sc.nextInt();
				int tmp = 0;
				for(int i = 0; i < I; ++i)
				{
					tmp = tmp * 10 + order%10;
					order /= 10;
				}
				out.println(dp(0, tmp));
			}
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
				st = new StringTokenizer(br.readLine(), ",| ");
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready();}

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


	}
}
