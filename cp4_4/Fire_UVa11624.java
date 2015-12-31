//package cp4_4;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
//public class Fire_UVa11624 {
//
//	static int[][] dist;
//	static Queue<Pair> q;
//	static char[][] maze;
//	static int[] dx = new int[]{0,0,1,-1};
//	static int[] dy = new int[]{1,-1,0,0};
//	static int R,C;
//	
//	static int bfs()
//	{
//		
//		while(!q.isEmpty())
//		{
//			Pair cur = q.remove();
//			for(int k = 0; k < 4; k++)
//			{
//				int x = cur.i + dx[k];
//				int y = cur.j + dy[k];
//				if(valid(x,y))
//				{
//					if(maze[x][y] != '#' && maze[x][y] != 'J' && dist[x][y]==0)
//					{
//						dist[x][y] = dist[cur.i][cur.j] + 1;
//						if(maze[cur.i][cur.j]=='J')
//							maze[x][y] = 'J';
//						q.add(new Pair(x,y));
//					}
//				}
//				else
//					if(maze[cur.i][cur.j]=='J')
//						return dist[cur.i][cur.j];
//				
//			}
//		}
//		return -1;
//	}
//	
//	
//	static boolean valid(int i, int j)
//	{
//		if(i==-1 || j==-1 || i==R || j==C)
//			return false;
//		return true;
//	}
//	
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		int TC = Integer.parseInt(br.readLine());
//		StringTokenizer st;
//		while(TC-->0)
//		{
//			st = new StringTokenizer(br.readLine());
//			R = Integer.parseInt(st.nextToken());
//			C = Integer.parseInt(st.nextToken());
//			maze = new char[R][C];
//			dist = new int[R][C];
//			q = new LinkedList<Pair>();
//			int x = -1, y = -1;
//			for(int i = 0; i < R; i++)
//			{
//				String line = br.readLine();
//				for(int j = 0; j < C; j++)
//				{
//					maze[i][j] = line.charAt(j);
//					if(maze[i][j]=='F')
//					{
//						dist[i][j] = 1;
//						q.add(new Pair(i,j));
//					}
//					else
//						if(maze[i][j]=='J'){x = i;	y = j;}
//						
//				}
//				
//			}
//			dist[x][y] = 1;
//			q.add(new Pair(x,y));
//			int min = bfs();
//			if(min==-1)
//				sb.append("IMPOSSIBLE\n");
//			else
//				sb.append(min+"\n");
//		}
//		System.out.print(sb);
//	}
//	
//}
//
//class Pair
//{
//	int i,j;
//	Pair(int x, int y){i = x; j  = y;}
//}
