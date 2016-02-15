package cp4_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class ManyPathsOneDestination_UVa988 {

	static ArrayList<Integer>[] adjList;
	static Stack<Integer> stack;
	static boolean[] visited;
	
	static void toposort(int u)
	{
		visited[u] = true;
		for(int i = 0; i < adjList[u].size(); i++)
		{
			int v = adjList[u].get(i);
			if(!visited[v])
				toposort(v);
		}
		stack.push(u);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		while(br.ready())
		{
			if(first)
				first = false;
			else
				sb.append("\n");
			int e = Integer.parseInt(br.readLine());

			adjList = new ArrayList[e];
			for(int i = 0; i < e; i++)
			{
				adjList[i] = new ArrayList<Integer>();
				StringTokenizer st = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st.nextToken());
				while(n-->0)
				{
					int v = Integer.parseInt(st.nextToken());
					adjList[i].add(v);
				}
			}
			visited = new boolean[e];
			stack = new Stack<Integer>();
			toposort(0);
			int[] dp = new int[e];
			dp[0] = 1;
			int ways = 0;
			while(!stack.isEmpty())
			{
				int u = stack.pop();
				if(adjList[u].size() == 0)
					ways += dp[u];
				else
					for(int i = 0; i < adjList[u].size(); i++)
					{
						int v = adjList[u].get(i);
						dp[v] += dp[u];
					}
			}
			
			sb.append(ways+"\n");
			br.readLine();
		}
		System.out.print(sb);
	}
}
