package v007;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class LostInSpace_UVa736 {

	static String[] dirs = new String[] { "N", "NE", "incomp.E", "SE", "S", "SW", "W", "NW"};
	static int[] dx = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = new int[] {0, 1, 1, 1, 0, -1, -1, -1};

	static char[][] grid;
	static int N;

	static boolean valid(int x, int y) { return x != -1 && y != -1 && x != N && y != N; }

	static char[] s;

	static boolean can(int x, int y, int idx, int dir)
	{
		if(idx == s.length)
			return true;
		if(!valid(x, y))
			return false;
		char c = grid[x][y];
		if(c != ' ' && c != s[idx])
			return false;
		grid[x][y] = 0;
		int i = x + dx[dir], j = y + dy[dir];
		if(can(i, j, c == ' ' ? idx : idx + 1, dir))
		{
			grid[x][y] = c;
			return true;
		}

		grid[x][y] = c;
		return false;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		int tc = sc.nextInt();
		while(tc-->0)
		{
			N = sc.nextInt();
			grid = new char[N][];
			for(int i = 0; i < N; ++i)
				grid[i] = sc.nextLine().toCharArray();
			while(sc.ready())
			{
				String t = sc.nextLine();
				if(t.isEmpty())
					break;
				s = t.toCharArray();
				sb.append("\n"+t+"\n");
				int c = 0;
				for(int i = 0; i < N; ++i)
					for(int j = 0; j < N; ++j)
						for(int k = 0; k < 8; ++k)
							if(grid[i][j] == s[0] && can(i + dx[k], j + dy[k], 1, k))
							{
								sb.append("("+(i+1)+","+(j+1)+") - "+dirs[k]+"\n");
								++c;
							}
				if(c == 0)
					sb.append("not found\n");
			}
			if(tc != 0)
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