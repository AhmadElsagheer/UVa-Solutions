package cp3_4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class FillTheSquare_UVa11520 {

	static int[] dx = new int[] { 0, 0, -1, 1};
	static int[] dy = new int[] { -1, 1, 0, 0};

	static boolean possible(char c, int x, int y, char[][] grid)
	{
		int N = grid.length;
		for(int k = 0; k < 4; ++k)
		{
			int i = x + dx[k], j = y + dy[k];
			if(valid(i, j, N) && grid[i][j] == c)
				return false;
		}
		return true;
	}

	static boolean valid(int i, int j, int N)
	{
		return i != -1 && j != -1 && i != N && j != N;
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int n = sc.nextInt();
			char[][] grid = new char[n][];
			for(int i = 0; i < n; ++i)
				grid[i] = sc.next().toCharArray();

			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j)
					if(grid[i][j] == '.')
						for(char c = 'A'; c <= 'E'; ++c)
							if(possible(c, i, j, grid))
							{
								grid[i][j] = c;
								break;
							}
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < n; ++i)
				sb.append(grid[i]).append("\n");
			out.printf("Case %d:\n%s", t, sb.toString());
		}
		out.flush();
		out.close();

	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r) {  br = new BufferedReader(r); }

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