package cp4_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class ProjectScheduling_UVa452 {

	static ArrayList<Integer>[] adjList;
	static int[] time;
	static boolean[] root, visited;
	static Stack<Integer> stack;
	
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
		
		int tc = Integer.parseInt(br.readLine());
		br.readLine();
		while(tc-->0)
		{
			adjList = new ArrayList[26];
			for(int i = 0; i < 26; i++)
				adjList[i] = new ArrayList<Integer>();
			time = new int[26];
			root = new boolean[26];
			
			while(true)
			{
				String line = br.readLine();
				if(line == null|| line.isEmpty())
					break;
				StringTokenizer st = new StringTokenizer(line);
				int u = st.nextToken().charAt(0) - 'A';
				time[u] = -Integer.parseInt(st.nextToken());
				if(!st.hasMoreTokens())
				{
					root[u] = true;
					continue;
				}
				String pre = st.nextToken();
				for(int i = 0; i < pre.length(); i++)
				{
					int s = pre.charAt(i) -'A';
					adjList[s].add(u);
				}
			}
			
			stack = new Stack<Integer>();
			visited = new boolean[26];
			int[] dist = new int[26];
			
			for(int i = 0; i < 26; i++)
				if(root[i])
				{
					dist[i] = time[i];
					toposort(i);
				}
			
			int max = 0;
			while(!stack.isEmpty())
			{
				int u = stack.pop();
				
				if(adjList[u].size()==0)
					max = Math.min(max, dist[u]);
				else
					for(int i = 0; i < adjList[u].size(); i++)
					{
						int v = adjList[u].get(i);
						dist[v] = Math.min(dist[v], dist[u] + time[v]);
					}
			}
			
			max *= -1;
			sb.append(max+"\n");
			if(tc!=0)
				sb.append("\n");
		}
		System.out.print(sb);
	}
}
