//package cp4_4;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.PriorityQueue;
//import java.util.StringTokenizer;
//
//public class RoughRoads_UVa10356 {
//
//	static ArrayList<Pair>[] adjList;
//	static int N;
//	static final int INF = 1000000;
//	
//	static int dijkstra()
//	{
//		int[][] dist = new int[N][2];
//		for(int i = 0; i < N; i++)
//			Arrays.fill(dist[i], INF);
//		dist[0][0] = 0;
//		PriorityQueue<Triple> q = new PriorityQueue<Triple>();
//		q.add(new Triple(0,0,0));
//		while(!q.isEmpty())
//		{
//			Triple cur = q.remove();
//			if(cur.walking==1 && cur.distance>dist[cur.junc][1] || cur.walking==0 && cur.distance>dist[cur.junc][0])
//				continue;
//			
//			for(int i = 0, size = adjList[cur.junc].size(); i < size; i++)
//			{
//				Pair next = adjList[cur.junc].get(i);
//				int totalDistance = cur.distance + next.distance;
//				if(totalDistance < dist[next.junc][(cur.walking+1)%2])
//				{
//					dist[next.junc][(cur.walking+1)%2] = totalDistance;
//					q.add(new Triple(next.junc,totalDistance,(cur.walking+1)%2));
//				}
//			}	
//				
//		}
//		return dist[N-1][0];
//	}
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		int k = 1;
//		
//		
//		while(br.ready())
//		{
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			N = Integer.parseInt(st.nextToken());
//			int E = Integer.parseInt(st.nextToken());
//			adjList = new ArrayList[N];
//			for(int i = 0; i < N; i++)
//				adjList[i] = new ArrayList<Pair>(500);
//			int back = INF;
//			while(E-->0)
//			{
//				st = new StringTokenizer(br.readLine());
//				int u = Integer.parseInt(st.nextToken());
//				int v = Integer.parseInt(st.nextToken());
//				int d = Integer.parseInt(st.nextToken());
//				if(u == 0 || v == 0)
//					back = Math.min(back, d);
//				adjList[u].add(new Pair(v,d));
//				adjList[v].add(new Pair(u,d));
//			}
//			int min = dijkstra();
//			sb.append("Set #"+k+++"\n");
//			if(min==INF)
//				sb.append("?\n");
//			else
//				sb.append(min+"\n");
//		}
//		System.out.print(sb);
//	}
//}
//
//class Triple implements Comparable<Triple>
//{
//	int junc, distance, walking;	//1 for walking 0, for riding
//	Triple(int x, int y, int z) {junc = x; distance = y; walking = z;}
//	@Override
//	public int compareTo(Triple o) {
//			return distance - o.distance;
//	}
//}
//
//class Pair{
//	int junc,distance;
//	Pair(int x, int y) {junc = x; distance = y;}
//}