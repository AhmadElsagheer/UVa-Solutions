//package cp4_4;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.PriorityQueue;
//import java.util.StringTokenizer;
//import java.util.TreeSet;
//
//public class Getaway_UVa949 {
//
//	static final int INF = 50000000;
//	static int[][] monitor;
//	static int V,H;
//	static int[] dx = new int[]{0,0,-1,1};
//	static int[] dy = new int[]{-1,1,0,0};
//	static TreeSet<Restriction> map;
//	
//	static int dijkstra()
//	{
//		int[][] dist = new int[H][V];
//		for(int i = 0; i < H; i++)
//			Arrays.fill(dist[i], INF);
//		dist[0][0] = 0;
//		PriorityQueue<Triple> q = new PriorityQueue<Triple>();
//		q.add(new Triple(0,0,0));
//		while(!q.isEmpty())
//		{
//			Triple cur = q.remove();
//			if(cur.time > dist[cur.i][cur.j])
//				continue;
//			int x,y;
//			for(int k = 0; k < 4; k++)
//			{
//				x = cur.i + dx[k];
//				y = cur.j + dy[k];
//				if(valid(x,y) && !map.contains(new Restriction(cur.i, cur.j, x, y)))
//				{
//					int z = 1;
//					while(cur.time + z<= 500 && monitor[cur.time+z][0]==x && monitor[cur.time+z][1]==y)z++;
//					
//					int totalTime = cur.time + z;
//					if(totalTime < dist[x][y])
//					{
//						dist[x][y] = totalTime;
//						q.add(new Triple(x,y,totalTime));
//					}
//				}
//			}
//		}
//		return dist[H-1][V-1];
//	}
//	
//	static boolean valid(int i, int j)
//	{
//		if(i==-1 || j==-1 || i== H || j==V)
//			return false;
//		return true;
//	}
//	
//	public static void main(String[] args) throws IOException {
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		StringTokenizer st;
//		int R,M,x1,x2,y1,y2,x,y,t;
//		while(br.ready())
//		{
//			st = new StringTokenizer(br.readLine());
//			V = Integer.parseInt(st.nextToken());
//			H = Integer.parseInt(st.nextToken());
//			R = Integer.parseInt(br.readLine());
//			map = new TreeSet<Restriction>();
//			while(R-->0)
//			{
//				st = new StringTokenizer(br.readLine());
//				x1 = Integer.parseInt(st.nextToken());
//				y1 = Integer.parseInt(st.nextToken());
//				x2 = Integer.parseInt(st.nextToken());
//				y2 = Integer.parseInt(st.nextToken());
//				map.add(new Restriction(y1,x1,y2,x2));
//			}
//			M = Integer.parseInt(br.readLine());
//			monitor = new int[501][2];
//			while(M-->0)
//			{
//				st = new StringTokenizer(br.readLine());
//				t = Integer.parseInt(st.nextToken());
//				x = Integer.parseInt(st.nextToken());
//				y = Integer.parseInt(st.nextToken());
//				monitor[t][0] = y;
//				monitor[t][1] = x;
//			}
//			sb.append(dijkstra()).append("\n");
//		}
//		
//		System.out.print(sb);
//	}
//	
//}
//class Triple implements Comparable<Triple>
//{
//	int i, j, time;
//	Triple(int x, int y, int t){i = x; j = y; time = t;}
//	@Override
//	public int compareTo(Triple o) {
//		// TODO Auto-generated method stub
//		return time - o.time;
//	}
//	
//}
//class Restriction implements Comparable<Restriction>
//{
//	int iFrom, jFrom, iTo, jTo;
//	Restriction(int x, int y, int a, int b)
//	{
//		iFrom = x;
//		jFrom = y;
//		iTo = a;
//		jTo = b;
//	}
//	@Override
//	public int compareTo(Restriction o) {
//		if(iFrom!=o.iFrom)
//			return iFrom - o.iFrom;
//		if(jFrom!=o.jFrom)
//			return jFrom - o.jFrom;
//		if(iTo!=o.iTo)
//			return iTo - o.iTo;
//		return jTo - o.jTo;
//	}
//	public boolean equals(Restriction i)
//	{
//		return this.compareTo(i) == 0;
//	}
//}
