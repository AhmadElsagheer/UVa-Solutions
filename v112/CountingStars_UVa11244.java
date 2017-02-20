package v112;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CountingStars_UVa11244 {
	
	static int[] dx = new int[]{0, 0, 1, -1, 1, -1, 1, -1};
	static int[] dy = new int[]{-1, 1, 0, 0, 1, 1, -1, -1};
	static char[][] grid;
	static int R, C;
	
	static boolean valid(int i, int j)
	{
		if(i == -1 || j == -1 || i == R || j == C)
			return false;
		return true;
	}
	
	static boolean isStar(int i, int j)
	{
		for(int k = 0; k < 8; ++k)
		{
			int x = i + dx[k], y = j + dy[k];
			if(valid(x, y) && grid[x][y] == '*')
				return false;
		}
		return true;
	}
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			R = sc.nextInt();
			C = sc.nextInt();
			if(R == 0)
				break;
			grid = new char[R][];
			for(int i = 0; i < R; ++i)
				grid[i] = sc.next().toCharArray();
			int c = 0;
			for(int i = 0; i < R; ++i)
				for(int j = 0; j < C; ++j)
					if(grid[i][j] == '*' && isStar(i, j))
						++c;
			out.println(c);
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
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
