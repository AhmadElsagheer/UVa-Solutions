package cp3_2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AntsShoppingMall_UVa12498 {

	static int R, C;
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			R = sc.nextInt(); C = sc.nextInt();
			int[][] grid = new int[R][C];
			boolean possible = true;
			for(int i = 0; i < R; ++i)
			{
				String line = sc.next();
				int sum = 0;
				for(int j = 0; j < C; ++j)
					sum += grid[i][j] = line.charAt(j) - '0';
				if(sum == C)
					possible = false;
			}
			if(!possible)
				out.format("Case %d: %d\n", t, -1);
			else
			{
				int ans = R * C;
				for(int j = 0; j < C; ++j)
				{
					int min = 0;
					for(int i = 0; i < R; ++i)
						min += minMoves(grid, i, j);
					ans = Math.min(ans, min);
				}
				out.format("Case %d: %d\n", t, ans);
			}
		}
		out.flush();
	}
	
	static int minMoves(int[][] grid, int r, int c)
	{
		int s1 = -1, s2 = -1;
		for(int j = c; j < C && s1 == -1; ++j)
			if(grid[r][j] == 0)
				s1 = j - c;
		for(int j = c; j >= 0 && s2 == -1; --j)
			if(grid[r][j] == 0)
				s2 = c - j;
		if(s1 == -1)
			return s2;
		if(s2 == -1)
			return s1;
		return Math.min(s1, s2);
		
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
