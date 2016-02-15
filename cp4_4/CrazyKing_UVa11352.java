//package cp4_4;
//import java.util.*;
//import java.io.*;
//public class CrazyKing_UVa11352 {
//
//	static char[][] forest;
//	static int R,C;
//	static int x,y,a,b;
//	static int[] di = new int[]{-1,-1,-2,-2,1,1,2,2};
//	static int[] dj = new int[]{-2,2,-1,1,-2,2,-1,1};
//	static int[] dx = new int[]{-1,-1,-1,0,0,1,1,1};
//	static int[] dy = new int[]{-1,0,1,-1,1,-1,0,1};
//	
//	public static int bfs()
//	{
//		Queue<Pair> q = new LinkedList<Pair>();
//		forest[x][y] = 'Z';
//		int[][] dist = new int[R][C];
//		q.add(new Pair(x,y));
//		while(!q.isEmpty())
//		{
//			Pair cur = q.remove();
//			for(int k = 0; k < 8; k++)
//			{
//				int x = cur.i + dx[k];
//				int y = cur.j + dy[k];
//				if(valid(x,y) && forest[x][y]!='Z')
//				{
//					forest[x][y] = 'Z';
//					dist[x][y] = dist[cur.i][cur.j] + 1;
//					q.add(new Pair(x,y));
//				}
//			}
//		}
//		if(dist[a][b]==0)
//			return -1;
//		return dist[a][b];
//	}
//	public static boolean valid(int i, int j)
//	{
//		if(i<0 || j<0 || i>=R || j>= C)
//			return false;
//		return true;
//	}
//	public static void setTraps(int i, int j)
//	{
//		for(int k = 0; k < 8; k++)
//			if(valid(i+di[k],j+dj[k]) && forest[i+di[k]][j+dj[k]]!='B')
//				forest[i+di[k]][j+dj[k]] ='Z';
//	}
//	
//	public static void main(String[] args) throws IOException {
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(System.out);
//		
//		int TC = Integer.parseInt(br.readLine());
//		StringTokenizer st;
//		while(TC-->0)
//		{
//			st = new StringTokenizer(br.readLine());
//			R = Integer.parseInt(st.nextToken());
//			C = Integer.parseInt(st.nextToken());
//			forest = new char[R][C];
//			for(int i = 0; i < R; i++)
//			{
//				String line = br.readLine();
//				for(int j = 0; j < C; j++)
//				{
//					char c = line.charAt(j);
//					switch(c)
//					{
//					case 'A':x = i; y = j;break;
//					case 'B':a = i; b = j;forest[i][j]='B';break;
//					case 'Z':forest[i][j]='Z';setTraps(i,j);break;
//					}
//				}
//			}
//			int min = bfs();
//			if(min==-1)
//				out.println("King Peter, you can't go now!");
//			else
//				out.printf("Minimal possible length of a trip is %d\n", min);
//		}
//		
//		
//		out.flush();
//		out.close();
//	}
//}
//
//class Pair
//{
//	int i,j;
//	Pair(int x, int y) {i = x; j  = y;};
//}
