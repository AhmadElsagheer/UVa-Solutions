package cp4_2;


	
import java.io.*; 
import java.util.*;

public class  CriticalLinks_UVa796{

	
	static int N;
	static int M;
	static int bridges;
	static ArrayList<ArrayList<Integer>> edges;
	
	
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
				dfs(v);

				
				if(dfs_low[v]>dfs_num[u])
				{
					bridges++;
					if(u>v)
						edges.get(v).add(u);
					else
						edges.get(u).add(v);
				}
				
					
				
				dfs_low[u] = Math.min(dfs_low[u],dfs_low[v]);
			}
			else
			{
				if(v!=parent[u])
				{
					dfs_low[u] = Math.min(dfs_low[u],dfs_num[v]);
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
		

		while(br.ready())
		{
			
			String line = br.readLine();
			if(line.length()==0)
				continue;
			StringTokenizer st = new StringTokenizer(line);
			N = Integer.parseInt(st.nextToken());
			if(N==0)
			{
				sb.append("0 critical links\n\n");
				continue;
			}
			
			
			edges = new ArrayList<ArrayList<Integer>>();
			adjMatrix = new ArrayList[N];
			for(int i = 0; i < N; i++)
			{
				adjMatrix[i] = new ArrayList<Integer>();
				edges.add(new ArrayList<Integer>());
				
			}
			
			for(int k = 0; k < N; k++)
			{
				st = new StringTokenizer(br.readLine());
				int server = Integer.parseInt(st.nextToken());
				int connections = Integer.parseInt(""+st.nextToken().charAt(1));
				while(connections-->0)
				{
					int other = Integer.parseInt(st.nextToken());
					if(!adjMatrix[server].contains(other))
						adjMatrix[server].add(other);
					if(!adjMatrix[other].contains(server))
						adjMatrix[other].add(server);
					
				}
				
			}
			
			bridges = 0;
			dfs_num = new int[N];
			dfs_low = new int[N];
			parent = new int[N];
			visited = new boolean[N];

			
			for(int i = 0; i < N; i++)
			{
				if(!visited[i])
					dfs(i);
			}
			
			sb.append(bridges+" critical links\n");
			for(int i = 0; i < edges.size(); i++)
			{
				Collections.sort(edges.get(i));
				for(int j = 0; j < edges.get(i).size(); j++)
					sb.append(i +" - "+ edges.get(i).get(j)+"\n");
			}
					
			
			
			sb.append("\n");
			
			
		}
		
		
		
		
		
		System.out.print(sb);
	}
	

}	