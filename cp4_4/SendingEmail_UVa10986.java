package cp4_4;
//
//import java.util.*;
//import java.io.*;
//
//import cp1_4.Pair;
//
//class Pair implements Comparable<Pair>
//{
//	int v; long d;
//	Pair(int v, long d){this.v = v; this.d = d;}
//	
//	public String toString()
//	{
//		return v + " "+ d;
//	}
//
//	@Override
//	public int compareTo(Pair x) {
//		
//		if(this.d <= x.d)
//			return -1;
//		else
//			return 1;
//	}
//}
//
//public class SendingEmai {
//
//	static LinkedList<Pair>[] adjList;
//	static long[] dist;
//	static final long INF = 10000000000000L;
//	
//	public static long dijkstra(int S, int T)
//	{
//		Arrays.fill(dist, INF);
//		dist[S]  = 0;
//		PriorityQueue<Pair> x = new PriorityQueue<Pair>();
//		x.add(new Pair(S,0));
//		while(!x.isEmpty())
//		{
//			Pair cur = x.remove();
//			if(cur.d > dist[cur.v])
//				continue;
//			for(int i = 0, size = adjList[cur.v].size(); i < size; i++)
//			{
//				Pair next = adjList[cur.v].get(i);
//				if(cur.d +next.d < dist[next.v])
//				{
//					dist[next.v] = cur.d +next.d;
//					x.add(new Pair(next.v,dist[next.v]));
//				}
//			}
//		}
//		return dist[T];
//	}
//	
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		
//		int TC = Integer.parseInt(br.readLine());
//		for(int i = 1; i <= TC; i++)
//		{
//			
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			int N = Integer.parseInt(st.nextToken());
//			int M = Integer.parseInt(st.nextToken());
//			int S = Integer.parseInt(st.nextToken());
//			int T = Integer.parseInt(st.nextToken());
//			
//			adjList = new LinkedList[N];
//			for(int j = 0; j < N; j++)
//				adjList[j] = new LinkedList<Pair>();
//			
//			dist = new long[N];
//			while(M-->0)
//			{
//				st = new StringTokenizer(br.readLine());
//				int u = Integer.parseInt(st.nextToken());
//				int v = Integer.parseInt(st.nextToken());
//				int w = Integer.parseInt(st.nextToken());
//				adjList[u].add(new Pair(v,w));
//				adjList[v].add(new Pair(u,w));
//			}
//			
//			sb.append("Case #"+i+": ");
//			long ans = dijkstra(S,T);
//			if(ans==INF)
//				sb.append("unreachable\n");
//			else
//				sb.append(ans+"\n");
//			
//			
//		}
//		System.out.print(sb);
//	}
//}
