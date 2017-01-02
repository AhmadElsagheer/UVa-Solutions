package v102;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class HippityHopscotch_UVa10259 {

	static int[][] grid;
	static int[][] memo;
	static int N, K;
	static int[] dx = new int[]{0, 0, -1, 1};
	static int[] dy = new int[]{-1, 1, 0, 0};
	
	static int dfs(int i, int j)
	{
		if(memo[i][j] != -1)
			return memo[i][j];
		
		int val = 0;
		for(int k = 0; k < 4; k++)
			for(int f = 1; f <= K; f++)
			{
				int x = i + dx[k] * f;
				int y = j + dy[k] * f;
				if(!valid(x, y))
					break;
				if(grid[x][y] > grid[i][j])
					val = Math.max(val, dfs(x, y));
			}
		return memo[i][j] = val + grid[i][j];
	}
	
	static boolean valid(int i, int j)
	{
		if(i < 0 || j < 0 || i >= N || j >= N)
			return false;
		return true;
	}
	public static void main(String[] args) throws IOException 
	{
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			N = sc.nextInt();
			K = sc.nextInt();
			grid = new int[N][N];
			memo = new int[N][N];
			for(int i = 0; i < N; i++)
			{
				Arrays.fill(memo[i], -1);
				for(int j = 0; j < N; j++)
					grid[i][j] = sc.nextInt();
			}
				
			
			out.println(dfs(0, 0));
			if(tc != 0)
				out.println();
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
