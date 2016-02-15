package cp4_2;


import java.io.*; 
import java.util.*;

@SuppressWarnings("rawtypes")
class Station implements Comparable{
	
	int stNumber;
	int pValue;
	
	public Station(int n, int p)
	{
		stNumber = n;
		pValue = p;
	}
	
	public int compareTo(Object o)
	{
		Station other = (Station)o;
		
		if(pValue>other.pValue)
			return -1;
		if(pValue<other.pValue)
			return 1;
		return stNumber-other.stNumber;
	}
}

public class  DovesAndBombs_UVa10765 {

	
	static int N;
	static int M;
	static ArrayList<Station> stations;
	
	static ArrayList<Integer>[] adjMatrix;
	static int root;
	static int rootChildren;
	static int counter;
	
	static int[] dfs_num;
	static int[] dfs_low;
	static int[] parent;
	static boolean[] visited;
//	static boolean[] aPoints;
	
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
				{
//					aPoints[u] = true;
					stations.get(u).pValue++;
				}
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
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if(N==0)
				break;
			stations = new ArrayList<Station>();
			adjMatrix = new ArrayList[N];
			for(int i = 0; i < N; i++)
			{
				adjMatrix[i] = new ArrayList<Integer>();
				stations.add(new Station(i,1));
			}
			st = new StringTokenizer(br.readLine());
			
			
			int x;
			while((x = Integer.parseInt(st.nextToken()))!=-1)
			{
				int y = Integer.parseInt(st.nextToken());
				
				if(!adjMatrix[x].contains(y))
					adjMatrix[x].add(y);
				if(!adjMatrix[y].contains(x))
					adjMatrix[y].add(x);	
				
				
				st = new StringTokenizer(br.readLine());
			}
			
			dfs_num = new int[N];
			dfs_low = new int[N];
			parent = new int[N];
			visited = new boolean[N];
//			aPoints = new boolean[N];
						
			for(int i = 0; i < N; i++)
			{
				if(!visited[i])
				{
					root = i;
					rootChildren = 0;
					dfs(i);
					if(rootChildren<=1)
					{		
//						aPoints[root] = false;
						stations.get(root).pValue--;
					
					}
				}
			}
			
			
			Collections.sort(stations);
				
			for(int i = 0; i < M; i++)
			{
				Station station = stations.get(i);
				sb.append(station.stNumber+" "+station.pValue+"\n");
			}
			sb.append("\n");
			
			
		}
		
		
		
		
		
		System.out.print(sb);
	}
	

}	