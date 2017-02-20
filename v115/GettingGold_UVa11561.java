package v115;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class GettingGold_UVa11561 {
	
	static int R, C;
	static char[][] grid;
	static int[] dx = new int[] { -1, 1, 0, 0 };
	static int[] dy = new int[] { 0, 0, -1, 1 };

	static int dfs(int i, int j)
	{
		int ret = 0;
		if(grid[i][j] == 'G')
			++ret;
		grid[i][j] = '#';
		if(!nearTrap(i, j))
			for(int k = 0; k < 4; ++k)
			{
				int x = i + dx[k], y = j + dy[k];
				if(grid[x][y] != '#')
					ret += dfs(x, y);
			}
		return ret;
	}
	
	static boolean nearTrap(int i, int j)
	{
		for(int k = 0; k < 4; ++k)
			if(grid[i + dx[k]][j + dy[k]] == 'T')
				return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			C = sc.nextInt();
			R = sc.nextInt();
			grid = new char[R][C];
			int x = -1, y = -1;
			for(int i = 0; i < R; ++i)
			{
				String s = sc.next();
				for(int j = 0; j < C; ++j)
				{
					if(s.charAt(j) == 'P')
					{
						x = i; y = j;
					}
					grid[i][j] = s.charAt(j);
				}
			}
			out.println(dfs(x, y));
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