package v002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Vertex_UVa280 {

	static LinkedList<Integer>[] adjList;
	static boolean visited[];
	
	public static void dfs(int u, boolean start)
	{
		if(!start)
			visited[u] = true;
		for(int i = 0, size = adjList[u].size(); i < size; i++)
		{
			int v =adjList[u].get(i);
			if(!visited[v])
				dfs(v,false);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			int N = Integer.parseInt(br.readLine());
			if(N==0)
				break;
			adjList = new LinkedList[N+1];
			for(int i = 1; i <= N; i++)
				adjList[i] = new LinkedList<Integer>();
			while(true)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				if(u==0)
					break;
				while(true)
				{
					int v = Integer.parseInt(st.nextToken());
					if(v==0)
						break;
					adjList[u].add(v);
				}
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			while(count-->0)
			{
				visited = new boolean[N+1];
				int start = Integer.parseInt(st.nextToken());
				dfs(start,true);
				int c = 0;
				StringBuilder res = new StringBuilder("");
				for(int i = 1; i <= N; i++)
					if(!visited[i])
					{
						c++; res.append(" "+i);
					}
				sb.append(c).append(res+"\n");
			}
		}
		System.out.print(sb);
	}
}
