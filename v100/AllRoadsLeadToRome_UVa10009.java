package v100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class AllRoadsLeadToRome_UVa10009 {

	static ArrayList<Integer>[] adjList;
	static Stack<Integer> stack;
	static boolean[] visited;
	
	static boolean dfs(int u, int v)
	{
		if(u==v)
		{
			stack.push(u);
			return true;
		}
		visited[u] = true;
		for(int i = 0; i < adjList[u].size(); i++)
		{
			int nxt = adjList[u].get(i);
			if(!visited[nxt] && dfs(nxt,v))
			{
				stack.push(u);
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		while(tc-->0)
		{
			br.readLine();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int E = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			int V = E + 1;
			String[] unmap = new String[V];
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			adjList = new ArrayList[V];
			int next = 0;
			while(E-->0)
			{
				st = new StringTokenizer(br.readLine());
				String x = st.nextToken();
				String y = st.nextToken();
				if(!map.containsKey(x))
				{
					map.put(x, next);
					unmap[next] = x;
					adjList[next++] = new ArrayList<Integer>();
				}
				if(!map.containsKey(y))
				{
					map.put(y, next);
					unmap[next] = y;
					adjList[next++] = new ArrayList<Integer>();
				}
				int u = map.get(x), v = map.get(y);
				adjList[u].add(v);
				adjList[v].add(u);
			}
			while(Q-->0)
			{
				st = new StringTokenizer(br.readLine());
				int u = map.get(st.nextToken()), v = map.get(st.nextToken());
				stack = new Stack<Integer>();
				visited = new boolean[V];
				dfs(u,v);
				while(!stack.isEmpty())
					sb.append(unmap[stack.pop()].charAt(0));
				sb.append("\n");
			}
			if(tc!=0)
				sb.append("\n");
		}
		System.out.print(sb);
	}
}
