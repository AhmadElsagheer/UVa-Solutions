package cp4_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Wormholes_UVa558 {

	
	static ArrayList<Pair>[] adjList;
	static int N;
	
	static boolean possible()
	{
		int[] dist = new int[N];
		Arrays.fill(dist, 500000000);
		dist[0] = 0;
		boolean modified = true;
		for(int i = 0; i < N - 1 && modified; i++)
		{
			modified = false;
			for(int u = 0; u < N; u++)
				for(int j = 0; j < adjList[u].size(); j++)
				{
					Pair next = adjList[u].get(j);
					if(next.cost + dist[u] < dist[next.node])
					{
						dist[next.node] = next.cost + dist[u];
						modified = true;
					}
				}
		}
		
		for(int u = 0; u < N; u++)
			for(int j = 0; j < adjList[u].size(); j++)
			{
				Pair next = adjList[u].get(j);
				if(next.cost + dist[u] < dist[next.node])
					return true;
			}
		return false;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			adjList = new ArrayList[N];
			for(int i = 0; i < N; i++)
				adjList[i] = new ArrayList<Pair>();
			while(E-->0)
			{
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				adjList[x].add(new Pair(y,t));
			}

			sb.append(possible()?"possible":"not possible").append("\n");
		}
		System.out.print(sb);
		
	}
	
	static class Pair
	{
		int node, cost;
		
		Pair(int x, int y)
		{
			node = x;
			cost = y;
		}
	}
}
