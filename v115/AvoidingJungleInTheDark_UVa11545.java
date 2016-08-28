package v115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class AvoidingJungleInTheDark_UVa11545 {

	static final int INF = (int)1e9;
	static int N;
	static char[] path;
	static int[][][][] memo;
	
	static int dp(int rest, int stamina, int hour, int pos)
	{
		if(pos == N - 1)
			return 0;
		if(memo[rest][stamina][hour][pos] != -1)
			return memo[rest][stamina][hour][pos];
		int ret = INF;
		//1. move
		if(stamina > 0 && ((hour + 1)%24 < 11 || path[pos + 1] != '*'))
			ret = Math.min(ret, 1 + dp(0, stamina - 1, (hour + 1)%24, pos + 1));
	
		//2. take rest
		if(rest < 2 && ((hour + 8)%24 < 11 || path[pos] != '*'))
			ret = Math.min(ret, 8 + dp(rest + 1, 16, (hour + 8)%24, pos));
			
		return memo[rest][stamina][hour][pos] = ret;
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			path = sc.next().toCharArray();
			memo = new int[3][17][24][N = path.length];
			for(int k = 0; k < 3; ++k)
			for(int i = 0; i <= 16; ++i)
				for(int j = 0; j < 24; ++j)
					Arrays.fill(memo[k][i][j], -1);
			int ans = dp(0, 16, 23, 0);
			if(ans == INF)
				ans = -1;
			out.printf("Case #%d: %d\n", t, ans);
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
			return Double.parseDouble(next());
		}

		public boolean ready() throws IOException {return br.ready();}


	}
}