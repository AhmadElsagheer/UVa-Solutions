package v003;

import java.util.*;
import java.io.*;



public class ANodeTooFar_UVa336 {

	
	static int[][] adjMatrix;
	static int N;
	static final int INF = 10000000;
	public static int bfs(int S, int max)
	{
		int[] dist = new int[N];
		boolean[] visited = new boolean[N];
		Arrays.fill(dist, INF);
		LinkedList<Integer> x = new LinkedList<Integer>();
		x.add(S);
		visited[S] = true;
		dist[S] = 0;
				
		while(!x.isEmpty())
		{
			int u = x.remove();
			for(int i = 0; i < N; i ++)
				if(adjMatrix[u][i]==1 && !visited[i])
				{
					visited[i] = true;
					dist[i] = dist[u] + 1;
					x.add(i);
				}
		}
		int count = 0;
		for(int i = 0; i < N; i++)
			if(dist[i]>max)
				count++;
		return count;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int k = 1;
		while(true)
		{
			int E = sc.nextInt();
			if(E == 0)
				break;
			adjMatrix = new int[30][30];
			N = 0;
			HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
			while(E-->0)
			{
				int x = sc.nextInt();
				int y = sc.nextInt();
				
				int u,v;
				if(map.containsKey(x))
					u = map.get(x);
				else
				{
					u = N++;
					map.put(x, u);
				}
				if(map.containsKey(y))
					v = map.get(y);
				else
				{
					v = N++;
					map.put(y, v);
				}
				adjMatrix[u][v] = adjMatrix[v][u] = 1;
			}
			
			while(true)
			{
				
				int S = sc.nextInt(); int max = sc.nextInt();
				if(S==0 && max==0)
					break;
				int count = bfs(map.get(S),max);
				sb.append("Case "+k+++": "+count+" nodes not reachable from node "+S+" with TTL = "+max+".\n");
			}
			
		}
		System.out.print(sb);
	}
	
}
