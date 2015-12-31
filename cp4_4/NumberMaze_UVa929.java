package cp4_4;

import java.util.*;
import java.io.*;
public class NumberMaze_UVa929 {

	static int[][] maze;
	static int R, C;
	static int[] dx = new int[]{-1,0,0,1};
	static int[] dy = new int[]{0,-1,1,0};
	
	public static int dijkstra()
	{
		int[][] cost = new int[R][C];
		for(int i = 0; i < R; i++)
			Arrays.fill(cost[i], 2000000000);
		
		cost[0][0] = maze[0][0];
		PriorityQueue<Triple> q = new PriorityQueue<Triple>();
		q.add(new Triple(0,0,cost[0][0]));
		while(!q.isEmpty())
		{
			Triple cur = q.remove();
			if(cur.cost > cost[cur.i][cur.j])
				continue;
			for(int k = 0; k < 4; k++)
			{
				int x = cur.i + dx[k];
				int y = cur.j + dy[k];
				if(valid(x,y) && cur.cost + maze[x][y] < cost[x][y])
				{
					cost[x][y] = cur.cost + maze[x][y];
					q.add(new Triple(x,y,cost[x][y]));
				}
			}
		}
		
		
		return cost[R-1][C-1];
	}
	
	public static boolean valid(int i, int j)
	{
		if(i==-1 || j==-1 || i==R || j==C)
			return false;
		return true;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int TC = Integer.parseInt(br.readLine());
		
		while(TC-->0)
		{
			R = Integer.parseInt(br.readLine());
			C = Integer.parseInt(br.readLine());
			maze = new int[R][C];
			for(int i = 0; i < R; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < C; j++)
					maze[i][j] = Integer.parseInt(st.nextToken());
			}
			
			int sol = dijkstra();
			out.println(sol);
		}
		out.flush();
		out.close();
	}
	
	static class Triple implements Comparable<Triple>
	{
		int i,j,cost;
		Triple(int x, int y, int z){i = x; j = y; cost = z;}
		
		public int compareTo(Triple t) {
			return this.cost - t.cost;
		}
		
	}
}
