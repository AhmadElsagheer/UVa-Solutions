package cp3_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Firetruck_UVa208 {

	static ArrayList<Integer>[] adjList;
	static int target, routes;
	static int[] trial = new int[21];
	static boolean[] visited = new boolean[21];
	static boolean[] canVisit;
	static StringBuilder sb = new StringBuilder();
	
	static void mark(int u)
	{
		canVisit[u] = true;
		for(int i = 0, size = adjList[u].size(); i < size; i++)
		{
			int v = adjList[u].get(i);
			if(!canVisit[v])
				mark(v);
		}
	}
	
	static void dfs(int u, int corners)
	{
		trial[corners-1] = u;
		if(u==target)
		{
			routes++;
			for(int i = 0; i < corners - 1; i++)
				sb.append(trial[i]).append(" ");
			sb.append(trial[corners-1]).append("\n");
			return;
		}
		visited[u] = true;
		for(int i = 0, size = adjList[u].size(); i < size; i++)
		{
			int v = adjList[u].get(i);
			if(!visited[v] && canVisit[v])
				dfs(v,corners+1);
		}
		
		visited[u] = false;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;
		int k = 1;
		
		
		while(br.ready())
		{
			sb.append("CASE "+k+++":\n");
			adjList = new ArrayList[21];
			for(int i = 1; i < 21; i++)
				adjList[i] = new ArrayList<Integer>(20);
			target = Integer.parseInt(br.readLine());
			while(true)
			{
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				if(u==0 && v ==0)
					break;
				adjList[u].add(v);
				adjList[v].add(u);
			}
			for(int i = 1; i < 21; i++)
				Collections.sort(adjList[i]);
			
			routes  = 0;
			canVisit = new boolean[21];
			mark(target);
			dfs(1,1);
			sb.append("There are ").append(routes).append(" routes from the firestation to streetcorner ").append(target).append(".\n");
			
			
		}
		out.print(sb);
		out.flush();
	}
}
