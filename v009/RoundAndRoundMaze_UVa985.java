package v009;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class RoundAndRoundMaze_UVa985 {
	
	static final int INF = (int)1e9;
	static int[] dx = new int[]{-1, 0, 1,0};
	static int[] dy = new int[]{0, -1, 0, 1};
	static int R, C, map[];
	static boolean[][][] grid;

	static void pre()
	{
		map = new int[26];
		map['N'-'A'] = 0;
		map['E'-'A'] = 3;
		map['S'-'A'] = 2;
		map['W'-'A'] = 1;
	}
	
	static boolean valid(int i, int j)
	{
		if(i == -1 || j == -1 || i == R || j == C)
			return false;
		return true;
	}
	
	static int bfs()
	{
		int[][][] dist = new int[R][C][4];
		for(int i = 0; i < R; i++)
			for(int j = 0; j < C; j++)
				Arrays.fill(dist[i][j], -1);
		dist[0][0][0] = 0;
		
		Queue<Triple> q = new LinkedList<Triple>();
		q.add(new Triple(0, 0, 0));
		while(!q.isEmpty())
		{
			Triple cur = q.remove();
			
			for(int k = 0; k < 4; k++)
			{
				int x = cur.i + dx[k], y = cur.j + dy[k];
				if(valid(x, y) && grid[cur.i][cur.j][(k + cur.t)%4] && dist[x][y][(cur.t + 1)%4] == -1)
				{
					dist[x][y][(cur.t + 1)%4] = dist[cur.i][cur.j][cur.t] + 1;
					q.add(new Triple(x, y, cur.t + 1));
				}
			}
		}
		
		int min = INF;
		for(int i = 0; i < 4; i++)
			if(dist[R-1][C-1][i] != -1)
				min = Math.min(min, dist[R-1][C-1][i]);
		return min;
	}
	public static void main(String[] args) throws IOException {
		pre();
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(sc.ready())
		{
			R = sc.nextInt();
			C = sc.nextInt();
			grid = new boolean[R][C][4];
			for(int k = 0, i = 0; i < R; i++)
				for(int j = 0; j < C && k < R * C - 1; j++, k++)
				{
					String line = sc.next();
					for(int c = 0; c < line.length(); c++)
						grid[i][j][map[line.charAt(c) - 'A']] = true;
				}
			int min = bfs();
			if(min != INF)
				out.println(min);
			else
				out.println("no path to exit");
		}
		out.flush();
		out.close();
	}
	
	static class Triple
	{
		int i, j, t;
		
		Triple(int x, int y, int z) { i = x; j = y; t = z%4; }
	}
	
	static class Scanner
	{
		BufferedReader br;
		StringTokenizer st;

		Scanner(InputStream s)
		{
			br = new BufferedReader(new InputStreamReader(s));
		}

		String next() throws IOException
		{
			while(st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		int nextInt() throws NumberFormatException, IOException
		{
			return Integer.parseInt(next());
		}

		double nextDouble() throws IOException
		{
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0.0, f = 1;
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
					if(dec) f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg ? -1 : 1);
		}
		
		boolean ready() throws IOException
		{
			return br.ready();
		}

	}

}
