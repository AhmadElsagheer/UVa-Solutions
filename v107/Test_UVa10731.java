package v107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Test_UVa10731 {

	static int[] dfs_num;
	static int[] dfs_low;
	static boolean[] valid;
	static boolean[] visited;
	static int counter;
	static Stack<Integer> stack;
	static ArrayList<Component> scc;
	static ArrayList<Integer>[] adjList;
	public static void tarjanSCC(int u)
	{
		dfs_num[u] = dfs_low[u] = ++counter;
		stack.push(u);
		visited[u] = true;
		for(int i = 0, size = adjList[u].size(); i < size; i++)
		{
			int v = adjList[u].get(i);
			if(dfs_num[v]==0)
				tarjanSCC(v);
			if(visited[v])
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
		}
		if(dfs_num[u]==dfs_low[u])
		{
			ArrayList<Integer> x = new ArrayList<Integer>(50);
			while(true)
			{
				int v = stack.pop();
				x.add(v);
				visited[v] = false;
				if(v==u)
				{
					Collections.sort(x);
					scc.add(new Component(x,x.get(0)));
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;boolean first = true;
		while(true)
		{
			
			int N = Integer.parseInt(br.readLine());
			if(N==0)
				break;
			if(first)
				first = false;
			else
				out.println();
			adjList = new ArrayList[26];
			for(int i = 0; i < 26; i++)
				adjList[i] = new ArrayList<Integer>(30);
			valid = new boolean[26];
			visited = new boolean[26];
			dfs_num = new int[26];
			dfs_low = new int[26];
			counter = 0;
			stack = new Stack<Integer>();
			scc = new ArrayList<Component>(30);
			while(N-->0)
			{
				st = new StringTokenizer(br.readLine());
				int[] next = new int[5];
				for(int i = 0; i < 5; i++)
				{
					next[i] = st.nextToken().charAt(0) -'A';valid[next[i]] = true;
				}
				int u = st.nextToken().charAt(0) -'A';valid[u] = true;
				for(int i = 0; i < 5; i++)
					if(!adjList[u].contains(next[i]))
						adjList[u].add(next[i]);
			}
			
			for(int i = 0; i < 26; i++)
				if(valid[i] && dfs_num[i]==0)
					tarjanSCC(i);
			Collections.sort(scc);
			for(int i = 0; i < scc.size(); i++)
			{
				ArrayList<Integer> x = scc.get(i).nodes;
				for(int j = 0; j < x.size() - 1; j++)
					out.printf("%c ",(char)(x.get(j)+'A'));
				out.printf("%c\n",(char)(x.get(x.size()-1)+'A'));
			}
		}
		
		out.flush();
		out.close();
	}
}

class Component implements Comparable<Component>
{
	ArrayList<Integer> nodes;
	int head;
	Component(ArrayList<Integer> x, int u){nodes = x; head = u;}
	@Override
	public int compareTo(Component o) {
		return this.head - o.head;
	}
	
	
}