package v121;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class AlmostShortestPath_UVa12144 {

	static final int INF = 50000000;
	static ArrayList<Pair>[] adjList;
	static int N;
	static Queue<Pair>[] edges;
	static Queue<Integer>[] parents;
	
	static int dijkstra(int S, int T, boolean removingShortest)
	{
		int[] dist = new int[N];
		Arrays.fill(dist, INF);
		dist[S] = 0;
		if(removingShortest)
		{
			edges = new LinkedList[N];
			parents = new LinkedList[N];
		}
		
		PriorityQueue<Pair> q = new PriorityQueue<Pair>();
		q.add(new Pair(S,0));
		while(!q.isEmpty())
		{
			Pair cur = q.remove();
			if(cur.cost > dist[cur.point])
				continue;
			for(int i = 0, size = adjList[cur.point].size(); i < size; i++)
			{
				Pair next = adjList[cur.point].get(i);
				if(next.point==-1)
					continue;
				int totalCost = cur.cost + next.cost;
				if(removingShortest && totalCost == dist[next.point])
				{
					edges[next.point].add(next);
					parents[next.point].add(cur.point);
				}
					
				if(totalCost < dist[next.point])
				{
					dist[next.point] = totalCost;
					q.add(new Pair(next.point, totalCost));
					if(removingShortest)
					{
						edges[next.point] = new LinkedList<Pair>();
						edges[next.point].add(next);
						parents[next.point] = new LinkedList<Integer>();
						parents[next.point].add(cur.point);
					}
				}
			}
		}
		if(removingShortest)
			removeShortest(T);
		return dist[T];
	}
	
	static void removeShortest(int u)
	{
		if(edges[u]==null)
			return;
		while(!edges[u].isEmpty())
			edges[u].remove().point = -1;
		while(!parents[u].isEmpty())
			removeShortest(parents[u].remove());
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if(N == 0)
				break;
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			adjList = new ArrayList[N];
			for(int i = 0; i < N; i++)
				adjList[i] = new ArrayList<Pair>(N);
			while(M-->0)
			{
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				adjList[u].add(new Pair(v,cost));
			}
			dijkstra(S,T,true);
			int sol = dijkstra(S,T,false);
			if(sol==INF)
				sb.append(-1).append("\n");
			else
				sb.append(sol).append("\n");
		}
		System.out.print(sb);
	}
	
	static class Pair implements Comparable<Pair>
	{
		int point, cost;
		Pair(int x, int y) {point = x; cost = y;}
		
		public int compareTo(Pair x)
		{
			return cost - x.cost;
		}
	}
}
