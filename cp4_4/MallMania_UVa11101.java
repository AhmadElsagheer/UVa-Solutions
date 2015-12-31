//package cp4_4;
//import java.util.*;
//import java.io.*;
//
//public class MallMania_UVa11101 {
//
//	static int[][] city;	
//	static final int INF = 10000000;
//	static int[] dx = new int[]{0,0,1,-1};
//	static int[] dy = new int[]{1,-1,0,0};
//	static int R, C;
//	public static int dijkstra(int iS, int jS, int iT, int jT)
//	{
//		int[][] dist = new int[R+1][C+1];
//		for(int i = 0; i <= R; i++)
//			Arrays.fill(dist[i], INF);
//		dist[iS][jS] = 0;
//		PriorityQueue<Triple> q = new PriorityQueue<Triple>();
//		q.add(new Triple(iS, jS, 0));
//		while(!q.isEmpty())
//		{
//			Triple cur = q.remove();
//			if(cur.distance > dist[cur.i][cur.j])
//				continue;
//			for(int k = 0; k < 4; k++)
//			{
//				int x = cur.i + dx[k];
//				int y = cur.j + dy[k];
//				if(valid(x,y))
//				{
//					int totalDist;
//					if(city[x][y]==0)
//						totalDist = 1 + dist[cur.i][cur.j];
//					else
//						if(city[x][y]==1)
//							totalDist = dist[cur.i][cur.j];
//						else
//							if(city[cur.i][cur.j]==2)
//								totalDist = dist[cur.i][cur.j];
//							else
//								totalDist = 1 + dist[cur.i][cur.j];
//					
//					if(totalDist<dist[x][y])
//					{
//						dist[x][y] = totalDist;
//						q.add(new Triple(x,y,dist[x][y]));
//					}
//				}
//			}
//		}
//		return dist[iT][jT];
//	}
//	public static boolean valid(int i, int j)
//	{
//		if (i == - 1 || j == - 1 || i == R+1 || j == C+1)
//			return false;
//		return true;
//	}
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
////		Scanner sc = new Scanner(System.in);
//		PrintWriter out = new PrintWriter(System.out);
//		StringTokenizer st;
//		while(true)
//		{
//			int x = Integer.parseInt(br.readLine());
////			int x = sc.nextInt();
//			if(x==0)
//				break;
//			city = new int[2001][2001];
//			R = 0; C = 0;
//			st = new StringTokenizer(br.readLine());
//			int a = -1,b = -1,c = -1,d = -1;
//			while(x-->0)
//			{
//				int i,j;
//				while(!st.hasMoreTokens())
//					st = new StringTokenizer(br.readLine());
//				i = Integer.parseInt(st.nextToken());
////				i = sc.nextInt();
//				while(!st.hasMoreTokens())
//					st = new StringTokenizer(br.readLine());
//				j = Integer.parseInt(st.nextToken());
////				j = sc.nextInt();
//				city[i][j] = 1;
//				if(x==0)
//				{
//					a = i;
//					b = j;
//				}
//				R = Math.max(R, i);
//				C = Math.max(C, j);
//			}
//			int y = Integer.parseInt(br.readLine());
////			int y = sc.nextInt();
//			while(y-->0)
//			{
//				int i,j;
//				while(!st.hasMoreTokens())
//					st = new StringTokenizer(br.readLine());
//				i = Integer.parseInt(st.nextToken());
////				i = sc.nextInt();
//				while(!st.hasMoreTokens())
//					st = new StringTokenizer(br.readLine());
//				j = Integer.parseInt(st.nextToken());
////				j = sc.nextInt();
//				city[i][j] = 2;
//				if(y==0)
//				{
//					c = i;
//					d = j;
//				}
//				R = Math.max(R, i);
//				C = Math.max(C, j);
//			}
//			out.println(dijkstra(a,b,c,d));
//		}
//		out.flush();
//		out.close();
//	}
//}
//
//class Triple implements Comparable<Triple>
//{
//	int i,j,distance;
//	Triple(int x, int y, int z) {i =  x; j = y; distance = z;}
//	@Override
//	public int compareTo(Triple o) {
//		// TODO Auto-generated method stub
//		return this.distance - o.distance;
//	}
//	
//}