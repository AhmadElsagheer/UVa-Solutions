package v109;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class EnchantedForest_UVa10977 {

	static int R, C;
	static boolean[][] blocked;
	static int[] dx = new int[]{0, 0, -1, 1, -1, -1, 1, 1};
	static int[] dy = new int[]{-1, 1, 0, 0, -1, 1, 1, -1};

	static int bfs()
	{
		int[][] dist = new int[R][C];
		for(int i = 0; i < R; ++i)
			Arrays.fill(dist[i], -1);
		dist[0][0] = 0;
		Queue<Cell> q = new LinkedList<Cell>();
		q.add(new Cell(0, 0));
		while(!q.isEmpty())
		{
			Cell cur = q.remove();
			if(cur.x == R - 1 && cur.y == C - 1)
				return dist[R-1][C-1];
			for(int k = 0; k < 4; ++k)
			{
				int x = cur.x + dx[k], y = cur.y + dy[k];
				if(valid(x, y) && dist[x][y] == -1)
				{
					dist[x][y] = dist[cur.x][cur.y] + 1;
					q.add(new Cell(x, y));
				}
			}
		}
		return -1;
	}

	static boolean valid(int x, int y)
	{
		return x >= 0 && y >= 0 && x < R && y < C && !blocked[x][y];
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
			blocked = new boolean[R][C];
			int m = sc.nextInt();
			while(m-->0)
				blocked[sc.nextInt() - 1][sc.nextInt() - 1] = true;
			m = sc.nextInt();
			while(m-->0)
			{
				int x = sc.nextInt() - 1, y = sc.nextInt() - 1, L = sc.nextInt();
				
				for(int i = 0; i < R; ++i)
					for(int j = 0; j < C; ++j)
						if(sq(x - i) + sq(y - j) <= L * L)
							blocked[i][j] = true;
			}
			int ans = bfs();
			if(ans == -1)
				out.println("Impossible.");
			else
				out.println(ans);
		}
		out.flush();
		out.close();
	}
	
	static int sq(int x)
	{
		return x * x;
	}

	static class Cell
	{
		int x, y;

		Cell(int a, int b)
		{
			x = a; y = b;
		}

	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine(), ",| ");
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready();}

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


	}
}
