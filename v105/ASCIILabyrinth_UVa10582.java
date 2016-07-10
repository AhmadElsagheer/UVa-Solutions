package v105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ASCIILabyrinth_UVa10582 {

	static int[] dx = new int[] {0, -1, 0, 1};
	static int[] dy = new int[] {-1, 0, 1, 0};
	static boolean[][] vis;
	static int[][] t;
	static int n, m;

	static int bt(int x, int y, int d)
	{
		if(x == n - 1 && y == m - 1)
			return 1;

		if(!valid(x, y) || vis[x][y] || t[x][y] == 0) 
			return 0;

		vis[x][y] = true;
		int ans = 0;
		if(t[x][y] == 1)
			ans = bt(x + dx[d], y + dy[d], d);
		else
		{
			int dd = d ^ 1;
			ans = bt(x + dx[dd], y + dy[dd], dd) + bt(x + dx[(dd+2)%4], y + dy[(dd+2)%4], (dd+2)%4);
		}
		vis[x][y] = false;
		return ans;
	}

	static boolean valid(int x, int y) { return x != -1 && y != -1 && x != n && y != m; }

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			n = sc.nextInt(); m = sc.nextInt();
			t = new int[n][m];
			vis = new boolean[n][m];
			for(int i = 0; i < n; ++i)
			{
				sc.nextLine();
				String s1 = sc.nextLine(), s2 = sc.nextLine(), s3 = sc.nextLine();
				for(int j = 0; j < m; ++j)
				{
					int x = 0, y = 0;
					if(s1.charAt(j * 4 + 2) == '*')
						++x;
					if(s3.charAt(j * 4 + 2) == '*')
						++x;
					if(s2.charAt(j * 4 + 1) == '*')
						++y;
					if(s2.charAt(j * 4 + 2) == '*')
					{	
						++x; ++y;
					}
					if(s2.charAt(j * 4 + 3) == '*')
						++y;
					if(x == 3 || y == 3)
						t[i][j] = 1;
					else if(x == 2 && y == 2)
						t[i][j] = 2;
				}
			}
			sc.nextLine();		
			out.println("Number of solutions: " + (bt(0, 0, 2) + bt(0, 0, 3)));
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}

	}
}