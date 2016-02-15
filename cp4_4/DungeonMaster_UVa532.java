//package cp4_4;
//
//import java.util.*;
//import java.io.*;
//class Triple
//{
//	int i,j,k;
//	Triple(int x, int y, int z){i = x; j = y; k = z;}
//}
//public class DungeonMaster_UVa532 {
//
//	static int[][][] cube;
//	static int[] dx = new int[]{-1,1,0,0,0,0};
//	static int[] dy = new int[]{0,0,-1,1,0,0};
//	static int[] dz = new int[]{0,0,0,0,-1,1};
//	static boolean[][][] visited;
//	static int R,C,L;
//	static int sX, sY, sZ, eX, eY, eZ;
//	
//	public static int bfs()
//	{
//		visited[sX][sY][sZ] = true;
//		int[][][] dist = new int[R][C][L];
//		dist[sX][sY][sZ] = 0;
//		LinkedList<Triple> q = new LinkedList<Triple>();
//		q.add(new Triple(sX,sY,sZ));
//		while(!q.isEmpty())
//		{
//			Triple cur = q.remove();
//			if(cur.i==eX && cur.j == eY && cur.k == eZ)
//				return dist[eX][eY][eZ];
//			
//			for(int k = 0; k < 6; k++)
//			{
//				int x = cur.i + dx[k];
//				int y = cur.j + dy[k];
//				int z = cur.k + dz[k];
//				if(valid(x,y,z) && !visited[x][y][z] && cube[x][y][z]==1)
//				{
//					dist[x][y][z] = dist[cur.i][cur.j][cur.k] + 1;
//					visited[x][y][z] = true;
//					q.add(new Triple(x,y,z));
//				}
//			}		
//			
//		}
//		
//		return -1;
//	}
//	
//	public static boolean valid(int i, int j, int k)
//	{
//		if(i==-1 || j==-1 || k==-1 || i==R || j==C || k==L)
//			return false;
//		return true;
//	}
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(System.out);
//		
//		while(true)
//		{
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			L = Integer.parseInt(st.nextToken());
//			R = Integer.parseInt(st.nextToken());
//			C = Integer.parseInt(st.nextToken());
//			if(L==0 && R==0 && C==0)
//				break;
//			cube = new int[R][C][L];
//			for(int k = 0; k < L; k++)
//			{
//				for(int i = 0; i < R; i++)
//				{
//					String line = br.readLine();
//					for(int j = 0; j < C; j++)
//					{
//						char c = line.charAt(j);
//						switch(c)
//						{
//						case 'S': sX = i; sY = j; sZ = k;break;
//						case 'E': eX = i; eY = j; eZ = k;cube[i][j][k] = 1;break;
//						case '.': cube[i][j][k] = 1;
//						}
//					}
//				}
//				br.readLine();
//			
//			}
//			
//			visited = new boolean[R][C][L];
//			int ans = bfs();
//			if(ans==-1)
//				out.printf("Trapped!\n");
//			else
//				out.printf("Escaped in %d minute(s).\n",ans);
//			
//		}
//		
//		out.flush();
//		out.close();
//	}
//}
