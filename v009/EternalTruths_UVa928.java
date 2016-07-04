package v009;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class EternalTruths_UVa928 {

	static final int INF = (int)1e9;
	static int[] dx = new int[] {0, 0, -1, 1};
	static int[] dy = new int[] {-1, 1, 0, 0};
	static char[][] grid;
	static int R, C;

	static boolean canReach(int x, int y, int k, int m)
	{
		while(m-->0)
		{
			x += dx[k];
			y += dy[k];
			if(x == -1 || y == -1 || x == R || y == C || grid[x][y] == '#')
				return false;
		}
		return true;
	}
	
	static int dijkstra(int Sx, int Sy, int Tx, int Ty)
	{
		int[][][] dist = new int[3][R][C];
		Queue<State> q = new LinkedList<State>();
		
		dist[0][Sx][Sy] = 1;
		q.add(new State(Sx, Sy, 0));
		
		while(!q.isEmpty())
		{
			State cur = q.remove();
			int d = dist[cur.m][cur.x][cur.y];
			if(cur.x == Tx && cur.y == Ty)
				return d - 1;
			
			int m = cur.m + 1;
			for(int k = 0; k < 4; ++k)
				if(canReach(cur.x, cur.y, k, m))
				{
					int i = cur.x + m * dx[k], j = cur.y + m * dy[k], n = m % 3;
					if(dist[n][i][j] == 0)
					{
						dist[n][i][j] = d + 1;
						q.add(new State(i, j, n));
					}
				}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			R = sc.nextInt(); C = sc.nextInt();
			int Sx = -1, Sy = -1, Tx = -1, Ty = -1;
			grid = new char[R][C];
			for(int i = 0; i < R; ++i)
			{
				char[] s = sc.next().toCharArray();
				for(int j = 0; j < C; ++j)
				{
					grid[i][j] = s[j];
					if(s[j] == 'S')
					{
						Sx = i; Sy = j;
					}
					else if(s[j] == 'E')
					{
						Tx = i; Ty = j;
					}
				}
			}
			int ans = dijkstra(Sx, Sy, Tx, Ty);
			out.println(ans == -1 ? "NO" : ans);
			
		}
		out.flush();
		out.close();
	}
	
	static class State
	{
		int x, y, m;
		
		State(int a, int b, int c) { x = a; y = b; m = c; }
		
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
	}
}