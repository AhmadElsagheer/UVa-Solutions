package cp8_3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class GameOfEuler_UVa10536 {

	static int[] memo;

	static int dp(int board)
	{
		if(Integer.bitCount(board) == 16)
			return 1;
		if(memo[board] != -1)
			return memo[board];
		int ret = 0, x1, x2, y1, y2;
		for(int i = 0; ret == 0 && i < 16; ++i)
			if((board & 1<<i) == 0)
			{
				//try single
				ret |= dp(board | 1<<i) ^ 1;
				//try right
				x1 = rightCell(i, 1);
				if(x1 != -1 && (board & 1<<x1) == 0)
				{
					// 1 right
					if(i%4 != 1)
						ret |= dp(board | 1<<i | 1<<x1) ^ 1;
					// 2 right
					x2 = rightCell(i, 2);
					if(x2 != -1 && (board & 1<<x2) == 0)
						ret |= dp(board | 1<<i | 1<<x1 | 1<<x2) ^ 1;
				}
				//try down
				y1 = downCell(i, 1);
				if(y1 != -1 && (board & 1<<y1) == 0)
				{
					// 1 down
					if(i / 4 != 1)
						ret |= dp(board | 1<<i | 1<<y1) ^ 1;
					// 2 down
					y2 = downCell(i, 2);
					if(y2 != -1 && (board & 1<<y2) == 0)
						ret |= dp(board | 1<<i | 1<<y1 | 1<<y2) ^ 1;
				}
			}

		return memo[board] = ret;
	}

	static int rightCell(int idx, int diff)
	{
		if(idx % 4 + diff >= 4)
			return -1;
		return idx + diff;
	}

	static int downCell(int idx, int diff)
	{
		if(idx / 4 + diff >= 4)
			return -1;
		return (idx / 4 + diff) * 4 + idx % 4;
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		memo = new int[1<<16];
		Arrays.fill(memo, -1);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int board = 0;
			for(int i = 0; i < 4; ++i)
			{
				char[] s = sc.next().toCharArray();
				for(int j = 0; j < 4; ++j)
					if(s[j] == 'X')
						board |= 1<<(i * 4 + j);
			}
			out.println(dp(board) == 1 ? "WINNING" : "LOSING");
		}
		out.flush();
		out.close();
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){    br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){    br = new BufferedReader(r);}

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