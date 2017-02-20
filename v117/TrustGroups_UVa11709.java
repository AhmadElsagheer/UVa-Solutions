package v117;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;



public class TrustGroups_UVa11709 {

	static ArrayList<Integer>[] adjList;
	static int[] dfs_num, dfs_low;
	static boolean[] visited;
	static int SCC;
	static int counter;
	static Stack<Integer> stack;
	
	public static void tarjanSCC(int u)
	{
		dfs_num[u] = dfs_low[u] = ++counter;
		visited[u] = true;
		stack.push(u);
		for(int i = 0, size = adjList[u].size(); i < size;i++)
		{
			int v = adjList[u].get(i);
			if(dfs_num[v]==0)
				tarjanSCC(v);
			if(visited[v])
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
		}
		if(dfs_low[u]==dfs_num[u])
		{
			SCC++;
			while(true)
			{
				int v = stack.pop();
				visited[v] = false;
				if(v==u)
					break;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			TreeMap<String,Integer> map = new TreeMap<String,Integer>();
			int P = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			if(P==0&& T==0)
				break;
			adjList = new ArrayList[P];
			for(int i = 0; i < P; i++)
			{
				adjList[i] = new ArrayList<Integer>(1000);
				map.put(br.readLine(), i);
			}
			while(T-->0)
			{
				int u = map.get(br.readLine());
				int v = map.get(br.readLine());
				adjList[u].add(v);
			}
			SCC = 0;
			counter  = 0;
			stack = new Stack<Integer>();
			dfs_low = new int[P];
			dfs_num = new int[P];
			visited = new boolean[P];
			for(int i = 0; i < P; i++)
				if(dfs_num[i]==0)
					tarjanSCC(i);
			sb.append(SCC).append("\n");
		}
		
		System.out.print(sb);
	}
}
