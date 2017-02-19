package v110;

import java.util.*;
import java.io.*;

public class BasicWallMaze_UVa11049 {

	static Pair[][] parent;
	static int[][] dist;
	static boolean[][][] blocked;
	static int[] dx = new int[]{-1,0,1,0};
	static int[] dy = new int[]{0,1,0,-1};
	static char[] dir = new char[]{'N','E','S','W'};
	static StringBuilder sb = new StringBuilder();
	
	static void bfs(int a, int b)
	{
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(a,b));
		dist[a][b] = 1;
		while(!q.isEmpty())
		{
			Pair cur = q.remove();
			
			for(int k = 0; k < 4; k++)
			{
				
				int x = cur.i +dx[k];
				int y = cur.j +dy[k];
				if(valid(x,y) && !blocked[cur.i][cur.j][k] && dist[x][y]==0)
				{
					dist[x][y] = 1 + dist[cur.i][cur.j];
					parent[x][y] = cur;
					q.add(new Pair(x,y));
				}
			}
		}
		
	}
	
	static boolean valid(int i, int j)
	{
		if(i==0 || j==0 || i==7 || j==7)
			return false;
		return true;
	}
	
	static void print(int c, int d)
	{
		if(dist[c][d]==1)
			return;
		Pair p = parent[c][d];
		print(p.i, p.j);
		for(int k = 0; k < 4; k++)
			if(p.i + dx[k] == c && p.j + dy[k] == d)
			{
				sb.append(dir[k]);
				return;
			}
	}
	

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int b = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			if(a==0 && b==0)
				break;
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			blocked = new boolean[8][8][4];
			for(int i = 0; i < 3; i++)
			{
				st = new StringTokenizer(br.readLine());
				int startY = Integer.parseInt(st.nextToken());
				int startX = Integer.parseInt(st.nextToken());
				int endY = Integer.parseInt(st.nextToken());
				int endX = Integer.parseInt(st.nextToken());
				if(startX == endX)
				{
					//horizontal
					for(int k = startY + 1; k <= endY; k++)
					{
						blocked[startX][k][2] = true;
						blocked[startX+1][k][0] = true;
					}
				}
				else
				{
					//vertical
					for(int k = startX + 1; k <= endX; k++)
					{
						blocked[k][startY][1] = true;
						blocked[k][startY+1][3] = true;
					}
				}
			}
			parent = new Pair[7][7];
			dist = new int[7][7];
			bfs(a,b);
			print(c,d);
			sb.append("\n");
			
		}
		
		System.out.print(sb);
	}
	
	static class Pair implements Comparable<Pair>
	{
		int i,j;
		Pair(int x, int y){i = x; j = y;}
		@Override
		public int compareTo(Pair o) {
			if(i!=o.i)
				return i - o.i;
			return j - o.j;
		}	
	}
}