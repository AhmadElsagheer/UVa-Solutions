package v104;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Marcus_UVa10452 {
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt(), m = sc.nextInt();
			char[][] grid = new char[n][];
			int x = -1, y = -1;
			for(int i = 0; i < n; ++i)
			{
				grid[i] = sc.next().toCharArray();
				if(i == n - 1)
					for(int j = 0; j < m; ++j)
						if(grid[i][j] == '@')
						{
							x = i; y = j;
						}
			}
			char[] seq = "IEHOVA#".toCharArray();
			for(int k = 0; k < seq.length; ++k)
			{
				if(k != 0)
					sb.append(" ");
				if(y > 0 && grid[x][y - 1] == seq[k])
				{
					sb.append("left");
					--y;
				}
				else if(y < m - 1 && grid[x][y + 1] == seq[k])
				{
					sb.append("right");
					++y;
				}
				else
				{
					sb.append("forth");
					--x;
				}
			}
			sb.append("\n");
		}
		out.print(sb);
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

		public boolean nextEmpty() throws IOException
		{
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}