package v104;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class TheCandymanCan_UVa10482 {

	static int[][][] memo;
	static int N, a[];
	
	static int dp(int idx, int x, int y, int z)
	{
		if(idx == N)
			return Math.max(Math.max(x, y), z) - Math.min(Math.min(x, y), z);
		if(memo[idx][x][y] != -1)
			return memo[idx][x][y];
		int t1 = dp(idx + 1, x + a[idx], y, z);
		int t2 = dp(idx + 1, x, y + a[idx], z);
		int t3 = dp(idx + 1, x, y, z + a[idx]);
		return memo[idx][x][y] = Math.min(t1, Math.min(t2, t3));
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			N = sc.nextInt();
			memo = new int[N][650][650];
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < 650; ++j)
					Arrays.fill(memo[i][j], -1);
			a = new int[N];
			for(int i = 0; i < N; ++i)
				a[i] = sc.nextInt();
			out.printf("Case %d: %d\n", t, dp(0, 0, 0, 0));
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