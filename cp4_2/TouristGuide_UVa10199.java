
package cp4_2;
	
import java.io.*; 
import java.util.*;
public class TouristGuide_UVa10199{

	static ArrayList<String> cities;
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
		
		int k = 1;
		while(true)
		{
			N = Integer.parseInt(br.readLine());
			if(N==0)
				break;
			if(k!=1)
				sb.append("\n");
			cities = new ArrayList<String>();
			for(int i = 0; i < N; i++)
				cities.add(br.readLine());
			Collections.sort(cities);
			adjMatrix = new ArrayList[N];
			for(int i = 0; i < N; i++)
				adjMatrix[i] = new ArrayList<Integer>();
			
			int R = Integer.parseInt(br.readLine());
			
			while(R-->0)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int city1 = cities.indexOf(st.nextToken());
				int city2 = cities.indexOf((st.nextToken()));
				
				if(!adjMatrix[city1].contains(city2))
					adjMatrix[city1].add(city2);
				
				if(!adjMatrix[city2].contains(city1))
					adjMatrix[city2].add(city1);
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
			
			PriorityQueue<String> cameraCities = new PriorityQueue<String>();
			for(int i = 0; i < N; i++)
				if(aPoints[i])
					cameraCities.add(cities.get(i));
			sb.append("City map #"+k+++": "+cameraCities.size()+" camera(s) found\n");
			while(!cameraCities.isEmpty())
				sb.append(cameraCities.remove()+"\n");
			
				
			
			
			
		}
		
		
		
		
		
		System.out.print(sb);
	}
	

}	