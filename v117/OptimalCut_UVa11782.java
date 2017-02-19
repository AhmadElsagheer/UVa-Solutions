package v117;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class OptimalCut_UVa11782 {

	static Scanner sc = new Scanner(System.in);
	static PrintWriter out = new PrintWriter(System.out);
	
	static int[][] memo;
	static int H, w[], level[];
	
	
	static void build(int node, int lvl) throws IOException
	{
		w[node] = sc.nextInt();
		level[node] = lvl;
		if(lvl < H)
		{
			build(node<<1, lvl + 1);
			build((node<<1) + 1, lvl + 1);
		}
	}
	
	static int dp(int node, int remK)
	{
		if(level[node] == H || remK == 1)
			return w[node];
		if(memo[node][remK] != -1)
			return memo[node][remK];
		int max = w[node];
		for(int i = 1; i < remK; i++)
			max = Math.max(max, dp(node<<1, i) + dp((node<<1) + 1, remK - i));
		return memo[node][remK] = max;
	}
	
	public static void main(String[] args) throws IOException {
		
		while(true)
		{
			H = sc.nextInt();
			if(H == -1)
				break;
			int k = sc.nextInt();
			w = new int[1<<H+1];
			level = new int[1<<H+1];
			build(1, 0);
			memo = new int[1<<H+1][k + 1];
			for(int i = 0; i < 1<<H+1; i++)
				Arrays.fill(memo[i], -1);
			out.println(dp(1, k));
		}
		out.flush();
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
