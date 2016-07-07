package v121;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Brothers_UVa12187 {

	static final int[] dx = new int[] {0, 0, -1, 1};
	static final int[] dy = new int[] {-1, 1, 0, 0};
	
	static boolean valid(int x, int y, int R, int C)
	{
		return x != -1 && y != -1 && x != R && y != C;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int N = sc.nextInt(), R = sc.nextInt(), C = sc.nextInt(), K = sc.nextInt();
			if(N == 0)
				break;
			int[][] grid = new int[R][C];
			for(int i = 0; i < R; ++i)
				for(int j = 0; j < C; ++j)
					grid[i][j] = sc.nextInt();
			while(K-->0)
			{
				int[][] nxt = new int[R][C];
				for(int i = 0; i < R; ++i)
					for(int j = 0; j < C; ++j)
					{
						int att = (grid[i][j]-1+N)%N;
						boolean conquered = false;
						for(int k = 0; k < 4; ++k)
						{
							int x = i + dx[k], y = j + dy[k];
							if(valid(x, y, R, C) && grid[x][y] == att)
								conquered = true;
						}
						nxt[i][j] = conquered ? att : grid[i][j];
					}
				grid = nxt;
			}
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < R; ++i)
			{
				for(int j = 0; j < C - 1; ++j)
					sb.append(grid[i][j] + " ");
				sb.append(grid[i][C-1] + "\n");	
			}
			out.print(sb);
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}