package v109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class ThePartyPartI_UVa10959 {

	static ArrayList<Integer>[] adjList;
	static int[] dist;
	
	static void bfs(int P)
	{
		dist = new int[P];
		boolean[] visited = new boolean[P];
		dist[0] = 0;
		visited[0] = true;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(0);
		while(!q.isEmpty())
		{
			int u = q.remove();
			for(int i = 0, size = adjList[u].size(); i < size; i++)
			{
				int v = adjList[u].get(i);
				if(!visited[v])
				{
					dist[v] = dist[u] + 1;
					visited[v] = true;
					q.add(v);
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			br.readLine();
			TreeSet<Pair> set = new TreeSet<Pair>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int P = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[P];
			for(int i = 0; i < P; i++)
				adjList[i] = new ArrayList<Integer>();
			while(D-->0)
			{
				st = new StringTokenizer(br.readLine());
				int  u =Integer.parseInt(st.nextToken());
				int  v =Integer.parseInt(st.nextToken());
				Pair x = new Pair(u,v);
				if(set.contains(x))
					continue;
				adjList[u].add(v);
				adjList[v].add(u);
				set.add(x);
			}
			bfs(P);
			for(int i = 1; i < P; i++)
				sb.append(dist[i]).append("\n");
			if(TC!=0)
				sb.append("\n");
		}
		System.out.print(sb);
	
	
	}
	
	static class Pair implements Comparable<Pair>
	{
		int x, y;
		Pair(int i, int j) 
		{
			if(i > j)
			{
				int tmp = i;
				i = j;
				j = tmp;
			}
			x = i; y = j;
		}
		@Override
		public int compareTo(Pair o) {
			if(x!=o.x)
				return x - o.x;
			return y - o.y;
		}
	}
}