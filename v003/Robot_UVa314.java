package v003;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Robot_UVa314 {
	
	static int[][] grid;
	static int[] dx = new int[]{-1,0,1,0};
	static int[] dy = new int[]{0,1,0,-1};
	static int R, C;
	
	static class State
	{
		int x, y, dir;
		
		State(int a, int b, int c) { x = a; y = b; dir = c; }
	}
	static boolean valid(int x, int y)
	{
		return x >= 0 && y >= 0 && x < R && y < C && grid[x][y] == 0;
	}
	
	static int bfs(int Bx, int By, int Ex, int Ey, int dir)
	{
		Queue<State> q = new LinkedList<State>();
		int[][][] dist = new int[R][C][4];
		for(int i = 0; i < R; ++i)
			for(int j = 0; j < C; ++j)
				Arrays.fill(dist[i][j], -1);
		q.add(new State(Bx, By, dir));
		dist[Bx][By][dir] = 0;
		while(!q.isEmpty())
		{
			State cur = q.remove();
			if(cur.x == Ex && cur.y == Ey)
				return dist[cur.x][cur.y][cur.dir];
			
			for(int k = 1; k <= 3; ++k)
			{
				int xx = cur.x + dx[cur.dir] * k, yy = cur.y + dy[cur.dir] * k;
				if(!valid(xx, yy))
					break;
				if(dist[xx][yy][cur.dir] == -1)
				{
					dist[xx][yy][cur.dir] = dist[cur.x][cur.y][cur.dir] + 1;
					q.add(new State(xx, yy, cur.dir));
				}
			}
			
			int dd = (cur.dir + 1)%4;
			if(dist[cur.x][cur.y][dd] == -1)
			{
				dist[cur.x][cur.y][dd] = dist[cur.x][cur.y][cur.dir] + 1;
				q.add(new State(cur.x, cur.y, dd));
			}
			
			dd = (cur.dir + 3)%4;
			if(dist[cur.x][cur.y][dd] == -1)
			{
				dist[cur.x][cur.y][dd] = dist[cur.x][cur.y][cur.dir] + 1;
				q.add(new State(cur.x, cur.y, dd));
			}
		}
		return -1;
	}
	
	static void block(int x, int y)
	{
		if(x < R && y < C)
			grid[x][y] = 1;
		if(x > 0 && y < C)
			grid[x-1][y] = 1;
		if(x < R && y > 0)
			grid[x][y-1] = 1;
		if(x > 0 && y > 0)
			grid[x-1][y-1] = 1;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			int n = sc.nextInt(), m = sc.nextInt();
			if(n == 0)
				break;
			R = n - 1;
			C = m - 1;
			grid = new int[R][C];
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < m; ++j)
					if(sc.nextInt() == 1)
						block(i, j);
			int Bx = sc.nextInt() - 1, By = sc.nextInt() - 1, Ex = sc.nextInt() - 1, Ey = sc.nextInt() - 1, dir;
			switch(sc.next().toLowerCase().charAt(0))
			{
			case 'n': dir = 0;break;
			case 'e': dir = 1;break;
			case 's': dir = 2;break;
				default: dir = 3;
			}

			out.println(bfs(Bx, By, Ex, Ey, dir));
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
			
		public boolean ready() throws IOException {return br.ready(); }


	}
}
