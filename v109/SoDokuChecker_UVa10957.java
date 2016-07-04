package v109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class SoDokuChecker_UVa10957 {

	static int n, m, board[][];
	static int sols;

	static boolean bt(int i, int j)
	{
		if(i == n)
		{	
			if(sols == 0)
			{
				sols = 1;
				return false;
			}
			sols = 2;
			return true;
		}
		if(j == n)
			return bt(i + 1, 0);
		if(board[i][j] != 0)
			return bt(i, j + 1);
		for(int k = 1; k <= n; ++k)
			if(canPut(i, j, k))
			{
				board[i][j] = k;
				if(bt(i, j + 1))
					return true;
			}
		board[i][j] = 0;
		return false;
	}

	static boolean canPut(int i, int j, int val)
	{
		boolean possible = true;
		for(int k = 0; possible && k < n; ++k)
		{
			int x = i/m * m + k/m, y = j / m * m + k%m;
			possible = board[i][k] != val && board[k][j] != val && board[x][y] != val;
		}
		return possible;
	}

	static boolean legal()
	{
		for(int i = 0; i < n; ++i)
			for(int j = 0; j < n; ++j)
				if(board[i][j] != 0)
				{
					int c = board[i][j];
					for(int k = 0; k < n; ++k)
					{
						int x = i / m * m + k / m, y = j / m * m + k % m;
						if(k != j && board[i][k] == c)
							return false;
						if(k != i && board[k][j] == c)
							return false;
						if((x != i || y != j) && board[x][y] == c)
							return false;
					}
				}
		return true;
	}


	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		m = 3; n = 9;
		int tc = 1;

		while(sc.ready())
		{
			sols = 0;
			board = new int[n][n];
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j)
					board[i][j] = sc.nextInt();
			String ans = "Impossible";
			if(!legal())
				ans = "Illegal";
			else
			{
				bt(0, 0);
				if(sols == 1)
					ans = "Unique";
				else if(sols == 2)
					ans = "Ambiguous";				
			}
			out.printf("Case %d: %s.\n", tc++, ans);
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
			return Double.parseDouble(next());
		}

		public boolean ready() throws IOException {return br.ready();}


	}
}