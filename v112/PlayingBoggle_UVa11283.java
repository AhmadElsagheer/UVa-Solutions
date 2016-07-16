package v112;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class PlayingBoggle_UVa11283 {

	static int[] dx = new int[] {0, 0, -1, -1, -1, 1, 1, 1};
	static int[] dy = new int[] {-1, 1, -1, 0, 1, -1, 0, 1};
	static char[][] grid;

	static boolean valid(int x, int y) { return x != -1 && y != -1 && x != 4 && y != 4 && grid[x][y] != '$'; }

	static boolean find(String s)
	{
		for(int i = 0; i < 4; ++i)
			for(int j = 0; j < 4; ++j)
				if(find(i, j, 0, s))
					return true;
		return false;
	}
	static boolean find(int x, int y, int idx, String s)
	{
		if(idx == s.length())
			return true;
		if(!valid(x, y) || grid[x][y] != s.charAt(idx))
			return false;
		char c = grid[x][y];
		grid[x][y] = '$';
		for(int k = 0; k < 8; ++k)
		{
			int i = x + dx[k], j = y + dy[k];
			if(find(i, j, idx + 1, s))
			{
				grid[x][y] = c;
				return true;
			}
		}
		grid[x][y] = c;
		return false;
	}
	
	static int[] score = new int[] {1, 1, 2, 3, 5};
	
	static int score(int x)
	{
		if(x >= 8)
			return 11;
		return score[x-3];
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb  = new StringBuilder();
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			grid = new char[4][];
			for(int i = 0; i < 4; ++i)
				grid[i] = sc.next().toCharArray();
			
			int w = sc.nextInt(), ans = 0;
			while(w-->0)
			{	
				String s = sc.next();
				if(find(s))
					ans += score(s.length());
			}
			out.printf("Score for Boggle game #%d: %d\n", t, ans);
		}
		out.print(sb);
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