package v102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LongestRunOnASnowboard_UVa10285 {

	static int[][] memo;
	static int[][] grid;
	static int R, C;
	static int[] dx = new int[] {0, 0, -1, 1};
	static int[] dy = new int[] {-1, 1, 0, 0};
	
	static boolean valid(int x, int y) { return x != -1 && y != -1 && x != R && y != C; }
	
	static int longest(int x, int y)
	{
		if(memo[x][y] != -1)
			return memo[x][y];
		int ret = 1, val = grid[x][y];
		for(int k = 0; k < 4; ++k)
		{
			int i = x + dx[k], j = y + dy[k];
			if(valid(i, j) && grid[i][j] < val)
				ret = Math.max(ret, 1 + longest(i, j));
		}
		return memo[x][y] = ret;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			String s = sc.next();
			R = sc.nextInt(); C = sc.nextInt();
			grid = new int[R][C];
			for(int i = 0; i < R; ++i)
				for(int j = 0; j < C; ++j)
					grid[i][j] = sc.nextInt();
			memo = new int[R][C];
			for(int i = 0; i < R; ++i)
				Arrays.fill(memo[i], -1);
			int max = 0;
			for(int i = 0; i < R; ++i)
				for(int j = 0; j < C; ++j)
					max = Math.max(max, longest(i, j));
			out.printf("%s: %d\n", s, max);
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