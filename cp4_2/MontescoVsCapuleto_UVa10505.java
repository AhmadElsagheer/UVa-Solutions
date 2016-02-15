package cp4_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MontescoVsCapuleto_UVa10505 {

	static ArrayList<Integer>[] adjList;
	static int[] visited;
	static int group1, group2;
	static boolean possible;
	
	static void dfs(int u)
	{
		if(visited[u]==1)
			group1++;
		else
			group2++;
		for(int i = 0; i < adjList[u].size(); i++)
		{
			int v = adjList[u].get(i);
			if(visited[v]==0)
			{
				visited[v] = -visited[u];
				dfs(v);
			}
			else
				if(visited[v]==visited[u])
					possible = false;
				
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		
		while(TC-->0)
		{
			
			br.readLine();
			int N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			adjList = new ArrayList[200];

			
			for(int i = 0; i < N; i++)
				adjList[i] = new ArrayList<Integer>();
			
			for(int i = 0; i < N; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int count = Integer.parseInt(st.nextToken());
				while(count-->0)
				{
					int v = Integer.parseInt(st.nextToken()) - 1;
					if(v>=N)
						continue;
					adjList[i].add(v);
					adjList[v].add(i);
				}
			}
			visited = new int[N];
			int max = 0;
			for(int i = 0; i < N; i++)
				if(visited[i]==0)
				{
					group1 = group2 = 0;
					possible = true;
					visited[i] = 1;
					dfs(i);
					if(!possible)
						group1 = group2  = 0;
					
					max += Math.max(group1, group2);
				}
			sb.append(max).append("\n");
		}
		System.out.print(sb);
	}

}
