package cp8_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Sudoko_UVa989 {

	static int n, m, board[][];
	
	static boolean bt(int i, int j)
	{
		if(i == n)
			return true;
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
	
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		boolean first = true;
		
		while(sc.ready())
		{
			if(first)
				first = false;
			else
				out.println();
			m = sc.nextInt(); n = m * m;
			board = new int[n][n];
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j)
					board[i][j] = sc.nextInt();
	
			if(!bt(0, 0))
				out.println("NO SOLUTION");
			else
				for(int i = 0; i < n; ++i)
				{
					for(int j = 0; j < n - 1; ++j)
						out.print(board[i][j] + " ");
					out.print(board[i][n-1] + "\n");
				}
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