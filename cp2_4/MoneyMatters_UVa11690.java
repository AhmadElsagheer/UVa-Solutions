package cp2_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MoneyMatters_UVa11690 {

	static ArrayList<Integer>[] adjList;
	static int[] money;
	static boolean[] visited;
	
	static int bfs(int u)
	{
		visited[u]  = true;
		int count = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(u);
		while(!q.isEmpty())
		{
			u = q.remove();
			count += money[u];
			
			for(int i = 0, size = adjList[u].size(); i < size; i++)
			{
				int v = adjList[u].get(i);
				if(!visited[v])
				{
					visited[v] = true;
					q.add(v);
				}
			}
		}
		return count;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			money = new int[N];
			adjList = new ArrayList[N];
			for(int i = 0; i < N; i++)
				adjList[i] = new ArrayList<Integer>();
			
			for(int i = 0; i < N; i++)
				money[i] = Integer.parseInt(br.readLine());
			
			while(M-->0)
			{
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				adjList[u].add(v);
				adjList[v].add(u);
			}
			
			
			visited = new boolean[N];
			boolean possible = true;
			for(int i = 0; i < N && possible; i++)
				if(!visited[i] && bfs(i)!=0)
					possible = false;
			if(possible)
				sb.append("POSSIBLE\n");
			else
				sb.append("IMPOSSIBLE\n");
			
		}
		
		System.out.print(sb);
		
	}
}

