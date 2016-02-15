package cp4_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Dominoes2_UVa11518 {

	static ArrayList<Integer>[] adjList;
	static boolean[] visited;
	static int c;
	
	static void dfs(int u)
	{
		visited[u] = true;
		c++;
		for(int i = 0; i < adjList[u].size(); i++)
		{
			int v = adjList[u].get(i);
			if(!visited[v])
				dfs(v);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		while(tc-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			adjList = new ArrayList[n];
			for(int i = 0; i < n; i++)
				adjList[i] = new ArrayList<Integer>();
			while(m-->0)
			{
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				adjList[u].add(v);
			}
			visited = new boolean[n];
			c = 0;
			while(l-->0)
			{
				int s = Integer.parseInt(br.readLine()) - 1;
				if(!visited[s])
					dfs(s);
			}
			sb.append(c+"\n");
		}
		System.out.print(sb);
	}
}
