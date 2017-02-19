package v009;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class WalkingAroundWisely_UVa926 {

	static long[][] memo;
	static boolean[][][] blocked;		//1st dim => 0 for north, 1 for east
	static int N, Tx, Ty;
	static long dp(int x, int y)
	{
		if(x == Tx && y == Ty)
			return 1;
		if(memo[x][y] != -1)
			return memo[x][y];
		long ret = 0;
		if(x < N - 1 && !blocked[1][x][y])
			ret += dp(x + 1, y);
		if(y < N - 1 && !blocked[0][x][y])
			ret += dp(x, y + 1);
		return memo[x][y] = ret;
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			N = sc.nextInt();
			memo = new long[N][N];
			for(int i = 0; i < N; ++i)
				Arrays.fill(memo[i], -1);
			blocked = new boolean[2][N][N];
			int Sx = sc.nextInt() - 1, Sy = sc.nextInt() - 1;
			Tx = sc.nextInt() - 1; Ty = sc.nextInt() - 1;
			int W = sc.nextInt();
			while(W-->0)
			{
				int x = sc.nextInt() - 1, y = sc.nextInt() - 1, dir = 0;
				char d = sc.next().charAt(0);
				if(d == 'S')
					--y;
				else if(d == 'E')
					dir = 1;
				else if(d == 'W')
				{
					--x; dir = 1;
				}
				blocked[dir][x][y] = true;	
			}
			out.println(dp(Sx, Sy));
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