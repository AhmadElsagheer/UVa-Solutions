package v113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AlternativeArborescence_UVa11307 {

	static final int INF = (int)1e9;
	static int [][] children;
	static int[][] memo;
	
	static int dp(int u, int c)
	{
		if(memo[c][u] != -1)
			return memo[c][u];
		int ret = INF;
		for(int i = 0; i < 6; ++i)
			if(i != c)
			{
				int cur = i + 1;
				for(int v: children[u])
					cur += dp(v, i);
				ret = Math.min(ret, cur);
			}
		return memo[c][u] = ret;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			children = new int[n][];
			boolean[] root = new boolean[n];
			Arrays.fill(root, true);
			for(int i = 0; i < n; ++i)
			{
				StringTokenizer st = new StringTokenizer(sc.nextLine());
				String s = st.nextToken();
				int u = Integer.parseInt(s.substring(0, s.length() - 1));
				int k = st.countTokens();
				children[u] = new int[k];
				for(int j = 0; j < k; ++j)
				{	
					int v = children[u][j] = Integer.parseInt(st.nextToken());
					root[v] = false;
				}
			}
			memo = new int[7][n];
			for(int i = 0; i < 7; ++i)
				Arrays.fill(memo[i], -1);
			int s = -1;
			for(int i = 0; i < n; ++i)
				if(root[i])
				{
					s = i;
					break;
				}
			out.println(dp(s, 6));
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