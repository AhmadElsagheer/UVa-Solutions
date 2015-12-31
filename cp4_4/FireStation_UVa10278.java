package cp4_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class FireStation_UVa10278 {
	
	static int N,F;
	static int[] stations;
	static ArrayList<Pair>[] adjList;
	
	
	static int dijkstra(int trial)
	{
		PriorityQueue<Pair> q = new PriorityQueue<Pair>();

		int[] dist = new int[N];
		Arrays.fill(dist, 500000000);
		dist[trial] = 0;
		q.add(new Pair(trial,0));
		for(int i = 0; i < F; i++)
		{
			dist[stations[i]] = 0;
			q.add(new Pair(stations[i],0));
		}
		
		while(!q.isEmpty())
		{
			Pair cur = q.remove();
			if(cur.cost > dist[cur.vertex])
				continue;
			for(int i = 0, size = adjList[cur.vertex].size(); i < size; i++)
			{
				Pair next = adjList[cur.vertex].get(i);
				int totalCost = cur.cost + next.cost;
				if(totalCost < dist[next.vertex])
				{
					dist[next.vertex] = totalCost;
					q.add(new Pair(next.vertex,totalCost));
				}
			}
		}
		int max = 0;
		for(int i = 0; i < N; i++)
			max = Math.max(max, dist[i]);
		return max;
			
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		br.readLine();
		while(TC-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			F = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			stations = new int[F];
			for(int i = 0; i < F; i++)
				stations[i] = Integer.parseInt(br.readLine()) - 1;
			
			adjList = new ArrayList[N];
			for(int i = 0; i < N; i++)
				adjList[i] = new ArrayList<Pair>();
			while(br.ready())
			{
				String line = br.readLine();
				if(line.isEmpty())
					break;
				st = new StringTokenizer(line);
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				int cost = Integer.parseInt(st.nextToken());
				adjList[u].add(new Pair(v,cost));
				adjList[v].add(new Pair(u,cost));
			}
			
			int max = 50000000;
			int vertex = -1;
			for(int i = 0; i < N; i++)
			{
				int cur = dijkstra(i);
				if(cur < max)
				{
					max = cur;
					vertex = i + 1;
				}
			}
			sb.append(vertex).append("\n");
			
			if(TC!=0)
				sb.append("\n");
			
		}
		System.out.print(sb);
	}
}

class Pair implements Comparable<Pair>
{
	int vertex,cost;
	Pair(int x, int y){vertex = x; cost = y;}
	@Override
	public int compareTo(Pair o) {
		// TODO Auto-generated method stub
		return cost - o.cost;
	}
}