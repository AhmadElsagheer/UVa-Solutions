package cp4_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class  Network_UVa315 {

	
	static int N;
	
	static ArrayList<Integer>[] adjMatrix;
	static int root;
	static int rootChildren;
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
				if(root==u)
					rootChildren++;
				dfs(v);
				if(dfs_low[v]>=dfs_num[u])
					aPoints[u] = true;
				dfs_low[u] = Math.min(dfs_low[u],dfs_low[v]);
			}
			else
			{
				if(v!=parent[u])
					dfs_low[u] = Math.min(dfs_low[u],dfs_num[v]);
			}
				
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		while(true)
		{
			N = Integer.parseInt(br.readLine());
			if(N==0)
				break;
			
			adjMatrix = new ArrayList[N];
			for(int i = 0; i < N; i++)
				adjMatrix[i] = new ArrayList<Integer>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int place;
			while((place = Integer.parseInt(st.nextToken()))!=0)
			{
				place--;
				while(st.hasMoreTokens())
				{
					int other = Integer.parseInt(st.nextToken()) - 1;
					
					if(!adjMatrix[place].contains(other))
						adjMatrix[place].add(other);
					if(!adjMatrix[other].contains(place))
						adjMatrix[other].add(place);
				}
				
				st = new StringTokenizer(br.readLine());
			}
			
			dfs_num = new int[N];
			dfs_low = new int[N];
			parent = new int[N];
			visited = new boolean[N];
			aPoints = new boolean[N];
						
			for(int i = 0; i < N; i++)
			{
				if(!visited[i])
				{
					root = i;
					rootChildren = 0;
					dfs(i);
					if(rootChildren<=1)
						aPoints[root] = false;
				}
			}
			
			int critical = 0;
			for(int i = 0; i < N; i++)
				if(aPoints[i])
					critical++;
			sb.append(critical+"\n");
				
			
			
			
		}
		
		
		
		
		
		System.out.print(sb);
	}
	

}	