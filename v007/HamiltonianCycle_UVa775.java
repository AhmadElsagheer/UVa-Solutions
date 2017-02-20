package v007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class HamiltonianCycle_UVa775 {

	static boolean[] visited;
	static ArrayList<Integer>[] adjList;
	static int N, sol[], trial[];
	
	static boolean dfs(int u, int n, int root)
	{
		
		trial[n] = u;
		if(n==N && u==root)
		{
			for(int i = 0; i < N; i++)
				sol[i] = trial[i];
			return true;
		}
		if(visited[u])
			return false;
		visited[u] = true;
		for(int i = 0; i < adjList[u].size(); i++)
		{
			int v = adjList[u].get(i);
			if(dfs(v,n+1,root))
				return true;
				
		}
		visited[u] = false;
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(br.ready())
		{
			N = Integer.parseInt(br.readLine());
			adjList = new ArrayList[N];
			for(int i = 0; i < N; i++)
				adjList[i] = new ArrayList<Integer>(N);
			sol = new int[N+1];
			trial = new int[N+1];
			while(true)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				String x = st.nextToken();
				if(x.equals("%"))
					break;
				int u = Integer.parseInt(x) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				adjList[u].add(v);
				adjList[v].add(u);
			}
			visited = new boolean[N];
			int i = 0;
			for(; i < N; i++)
				if(dfs(i,0,i))
					break;
			if(i==N)
				sb.append("N\n");
			else
			{
				for(int j = 0; j < N; j++)
					sb.append(sol[j]+1+" ");
				sb.append(sol[N]+1).append("\n");
			}
		}
		System.out.print(sb);
	}
	
}
