package v108;

import java.util.*;
import java.io.*;

public class LiftHopping_UVa10801 {

	static int [][] adjMatrix;
	static int N;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			boolean [][] floors = new boolean[N][100];
			int[] time = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++)
				time[i] = Integer.parseInt(st.nextToken());
			
			adjMatrix = new int[N*100][N*100];
			
			for(int i = 0; i < N; i++)
			{
				st = new StringTokenizer(br.readLine());
				int last = -1;
				int shift = 100*i;
				while(st.hasMoreTokens())
				{
					int floor = Integer.parseInt(st.nextToken());
					floors[i][floor] = true;
					if(last!=-1)
						adjMatrix[last+shift][floor+shift] = adjMatrix[floor+shift][last+shift] = time[i]*(floor-last);
					
					
					last = floor;
				}
			}
			for(int i = 0; i < 100; i++)
			{
				ArrayList<Integer> nodes = new ArrayList<Integer>(N);
				for(int j = 0; j < N; j++)
					if(floors[j][i])
						nodes.add(i+100*j);
				link(nodes);
			}
			
			int min = INF;
			for(int i = 0; i < N; i++)
			{
				int cur = dijkstra(0+i*100,K);
				if(cur < min)
					min = cur;
			}
			
			if(min>=INF)
				sb.append("IMPOSSIBLE").append("\n");
			else
				sb.append(min).append("\n");
			
			
		}
		System.out.print(sb);
	}
	
	public static void link(ArrayList<Integer> nodes)
	{
		for(int i = 0; i < nodes.size(); i++)
			for(int j = 1; j < nodes.size(); j++)
				if(i!=j)
				{
					int u = nodes.get(i);
					int v = nodes.get(j);
					adjMatrix[u][v] = adjMatrix[v][u] = 60;
				}
	}
	
	static final int INF = 100000000;
	public static int dijkstra(int S, int T)
	{
		int[] dist = new int[adjMatrix.length];
		Arrays.fill(dist, INF);
		dist[S] = 0;
		PriorityQueue<Pair> x = new PriorityQueue<Pair>();
		x.add(new Pair(S,0));
		while(!x.isEmpty())
		{
			Pair cur = x.remove();
			if(cur.time > dist[cur.v])
				continue;
			
			for(int i = 0; i < adjMatrix.length; i++)
			{
				if(adjMatrix[cur.v][i]==0)
					continue;
				
				if(cur.time + adjMatrix[cur.v][i] < dist[i])
				{
					
					dist[i] = cur.time + adjMatrix[cur.v][i];
					x.add(new Pair(i,dist[i]));
				}
			}
		}
		int min = INF;
		for(int i = 0; i < N; i++)
			if(dist[T+i*100]<min)
				min = dist[T+i*100];
		return min;
		
	}
	
	static class Pair implements Comparable<Pair>
	{
		int v; int time;
		Pair(int x, int y) { v = x; time = y;}
		
		@Override
		public int compareTo(Pair x) {
			if(this.time - x.time <= 0)
				return - 1;
			return 1;
		}
	}
}