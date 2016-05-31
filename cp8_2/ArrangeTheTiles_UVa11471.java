package cp8_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class ArrangeTheTiles_UVa11471 {
	
	static int[][] memo;
	static int[][] tiles;
	
	static int dp(int used, int edges)
	{
		if(used == (1<<12)-1)
			return 1;
		if(memo[used][edges] != -1)
			return memo[used][edges];
		int count = 0, cell = Integer.bitCount(used), col = cell%3;
		int upEdge = cell / 3 == 0 ? -1 : get(edges, col), leftEdge = col == 0 ? -1 : get(edges, 3);
		for(int i = 0; i < 12; ++i)
			if((used & 1<<i) == 0)
			{
				boolean valid = true;
				if(upEdge != -1 && upEdge != tiles[i][0])
					valid = false;
				if(leftEdge != -1 && leftEdge != tiles[i][3])
					valid = false;
				if(valid)
					count += dp(used | 1<<i, set(edges, i, col));
			}
		return memo[used][edges] = count;
	}
	
	static int get(int x, int i)
	{
		return x>>i*2 & 3;
	}
	
	static int set(int edges, int i, int col)
	{
		edges &= ~(3<<col*2 | 3<<6);
		return edges | tiles[i][2]<<col*2 | tiles[i][1]<<6;
	}
	
	static int mapColor(char c)
	{
		if(c == 'R')
			return 0;
		else if(c == 'G')
			return 1;
		else if(c == 'Y')
			return 2;
		return 3;
	}
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			tiles = new int[12][4];
			for(int i = 0; i < 12; ++i)
			{
				String s = sc.next();
				for(int j = 0; j < 4; ++j)
					tiles[i][j] = mapColor(s.charAt(j));
			}
			memo = new int[1<<12][1<<8];
			for(int i = 0; i < 1<<12; ++i)
				Arrays.fill(memo[i], -1);
			out.printf("Case %d: %d\n", t, dp(0, 0));
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