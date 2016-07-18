package cp4_7;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AtennaPlacement_UVa10349 {

	static char[][] grid;
	static int h, w, memo[][][];
	
	static int dp(int r, int c, int msk)
	{
		if(r == h)
			return 0;
		if(c == w)
			return dp(r + 1, 0, msk);
		if(memo[r][c][msk] != -1)
			return memo[r][c][msk];
		if(grid[r][c] == 'o' || (msk & (1<<c)) != 0)
			return memo[r][c][msk] = dp(r, c + 1, msk & ~(1<<c));
		int hor = dp(r, c + 1, msk & ~(1<<c) | (c == w - 1 ? 0 : (1<<c+1))) + 1;
		int ver = dp(r, c + 1, msk | (1<<c)) + 1;
		return memo[r][c][msk] = Math.min(hor, ver);
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			h = sc.nextInt();
			w = sc.nextInt();
			grid = new char[h][];
			for(int i = 0; i < h; ++i)
				grid[i] = sc.next().toCharArray();
			memo = new int[h][w][1<<w];
			for(int i = 0; i < h; ++i)
				for(int j = 0; j < w; ++j)
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

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready(); }


	}
}
