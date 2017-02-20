package v006;


	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class StreetDirections_UVa610 {

	
	static int N;
	static int M;
	static int[][] edges;
	
	
	static ArrayList<Integer>[] adjMatrix;

	static int counter;
	
	static int[] dfs_num;
	static int[] dfs_low;
	static int[] parent;
	static boolean[] visited;
	static boolean[] aPoints;
	
	public static void dfs(int u)
	{
		visited[u] = true;
		dfs_num[u] = dfs_low[u] = counter++;
		for(int i = 0; i < adjMatrix[u].size(); i++)
		{
			int v = adjMatrix[u].get(i);
			if(!visited[v])
			{
				
				parent[v] = u;
				edges[u][v] = 1;
				dfs(v);

				
				if(dfs_low[v]>dfs_num[u])
					edges[v][u] = 1;
				
					
				
				dfs_low[u] = Math.min(dfs_low[u],dfs_low[v]);
			}
			else
			{
				if(v!=parent[u])
				{
					dfs_low[u] = Math.min(dfs_low[u],dfs_num[v]);
					edges[u][v] = 1;
					if(adjMatrix[v].contains(u))
						adjMatrix[v].remove(adjMatrix[v].indexOf(u));
				}
				
					
				
					
			}
				
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int k = 1;
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if(N==0)
				break;
			M = Integer.parseInt(st.nextToken());
			
			sb.append(k+++"\n\n");
			edges = new int[N][N];
			adjMatrix = new ArrayList[N];
			for(int i = 0; i < N; i++)
			{
				adjMatrix[i] = new ArrayList<Integer>();
				
			}
			
			while(M-->0)
			{
				st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken()) - 1;
				int j = Integer.parseInt(st.nextToken()) - 1;
				adjMatrix[i].add(j);
				adjMatrix[j].add(i);
			}
			
			for(int i = 0; i < N; i++)
				Collections.sort(adjMatrix[i]);
				
			
			
			
			
			dfs_num = new int[N];
			dfs_low = new int[N];
			parent = new int[N];
			visited = new boolean[N];

			dfs(0);
			for(int i = 0; i < edges.length; i++)
				for(int j = 0; j < edges.length; j++)
					if(edges[i][j]==1)
						sb.append((i+1)+" "+(j+1)+"\n");
			sb.append("#\n");
			
			
		}
		
		
		
		
		
		System.out.print(sb);
	}
	

}	