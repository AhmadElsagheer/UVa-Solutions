package cp5_4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TilingDominoes_UVa11270 {

	static long[][][] memo;
	static int R, C;
	
	static long dp(int r, int c, int msk)
	{
		if(r == R)
			return msk == 0 ? 1 : 0;
		if(c == C)
			return dp(r + 1, 0, msk ^ ((1<<C) - 1));
		if(memo[r][c][msk] != -1)
			return memo[r][c][msk];
		long ver = dp(r, c + 1, msk), hor = 0;			
		if(c < C - 1 && (msk & (3<<c)) == 0)
			hor = dp(r, c + 2, msk | (3<<c));
		return memo[r][c][msk] = ver + hor;
	}
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			R = sc.nextInt();
			C = sc.nextInt();
			if((R & 1) == 1 && (C & 1) == 1)
			{
				out.println(0);
				continue;
			}
			if(R < C) { R ^= C; C ^= R; R ^= C; }
			memo = new long[R][C][1<<C];
			for(int i = 0; i < R; ++i)
				for(int j = 0; j < C; ++j)
					Arrays.fill(memo[i][j], -1);
			out.println(dp(0, 0, 0));
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

		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
