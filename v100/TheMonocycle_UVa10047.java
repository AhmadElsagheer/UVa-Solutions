package v100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TheMonocycle_UVa10047 {

	static int[] dx = new int[] { -1, 0, 1, 0};
	static int[] dy = new int[] { 0, 1, 0, -1};
	
	static char[][] grid;
	static int R, C;
	
	static boolean valid(int x, int y) 
	{ 
		return x != -1 && y != -1 && x != R && y != C && grid[x][y] != '#'; 
	}
	
	static int bfs(int sx, int sy, int tx, int ty)
	{
		int[][] dist = new int[20][R * C];
		dist[0][sx * C + sy] = 1;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add((sx * C + sy) * 20);
		while(!q.isEmpty())
		{
			int cur = q.remove();
			int cell = cur / 20, state = cur % 20;
			int x = cell / C, y = cell % C, color = state / 4, dir = state % 4;
			int d = dist[state][cell];
			if(x == tx && y == ty && color == 0)
				return d - 1;
		
			//turn left
			if(dist[color*4 + (dir+3)%4][cell] == 0)
			{
				dist[color*4 + (dir+3)%4][cell] = d + 1;
				q.add(cell * 20 + color*4 + (dir+3)%4);
			}
			
			//turn right
			if(dist[color*4 + (dir+1)%4][cell] == 0)
			{
				dist[color*4 + (dir+1)%4][cell] = d + 1;
				q.add(cell * 20 + color*4 + (dir+1)%4);
			}
			
			//move forward
			x += dx[dir];
			y += dy[dir];
			if(valid(x, y) && dist[(color+1)%5*4 + dir][x*C + y] == 0)
			{
				dist[(color+1)%5*4 + dir][x*C + y] = d + 1;
				q.add((x*C + y) * 20 + (color+1)%5*4 + dir);
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = 1;
		while(true)
		{
			R = sc.nextInt(); C = sc.nextInt();
			if(R == 0)
				break;
			if(tc != 1)
				out.println();
			grid = new char[R][C];
			int sx = -1, sy = -1, tx = -1, ty = -1;
			for(int i = 0; i < R; ++i)
			{
				String s = sc.next();
				for(int j = 0; j < C; ++j)
				{
					grid[i][j] = s.charAt(j);
					if(s.charAt(j) == 'S') { sx = i; sy = j; }
					else if(s.charAt(j) == 'T') { tx = i; ty = j; }
				}
			}
			out.printf("Case #%d\n", tc++);
			int res = bfs(sx, sy, tx, ty);
			if(res == -1)
				out.println("destination not reachable");
			else
				out.printf("minimum time = %d sec\n", res);
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