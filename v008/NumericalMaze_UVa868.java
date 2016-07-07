package v008;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class NumericalMaze_UVa868 {

	static final int[] dx = new int[] {0, 0, -1, 1};
	static final int[] dy = new int[] {-1, 1, 0, 0};
	static int[][] grid;
	static int R, C, M;

	static boolean valid(int x, int y)
	{
		return x != -1 && y != -1 && x != R && y != C;
	}

	static boolean[] exit, visited[];

	static void canReach(int x, int y, int a, int b)
	{
		int cell = x * C + y, state = a * M + b;
		if(x == R - 1)
		{	
			exit[y] = true;
			return;
		}

		for(int k = 0; k < 4; ++k)
		{
			int i = x + dx[k], j = y + dy[k];
			if(valid(i, j) && grid[i][j] == a && !visited[cell][state])
				canReach(i, j, (a + 1) % b, a + 1 == b ? b + 1 : b);
		}
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			R = sc.nextInt();
			C = sc.nextInt();
			M = Math.max(R, C);
			grid = new int[R][C];
			for(int i = 0; i < R; ++i)
				for(int j = 0; j < C; ++j)
					grid[i][j] = sc.nextInt() - 1;

			int s = C, t = C;
			for(int i = 0; i < s; ++i)
				if(grid[0][i] == 0)
				{
					visited = new boolean[R * C][M * M];
					exit = new boolean[C];
					canReach(0, i, 0, 2);
					for(int j = 0; j < t; ++j)
						if(exit[j])
						{
							s = i;
							t = j;
						}
				}
			out.println("1 " + (s + 1));
			out.println(R + " " + (t + 1));
			if(tc != 0)
				out.println();
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