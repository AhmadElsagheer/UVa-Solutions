package v115;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;


public class Dominos_UVa11504 {

	static int[] dfs_num, dfs_low, findSCC;
	static int counter, N, SCC;
	static boolean[] visited;
	static ArrayList<Integer>[] adjList;
	static Stack<Integer> stack;
	
	static void tarjanSCC(int u)
	{
		visited[u] = true;
		dfs_num[u] = dfs_low[u] = ++counter;
		stack.push(u);
		for(int i = 0, size = adjList[u].size(); i < size; i++)
		{
			int v = adjList[u].get(i);
			if(dfs_num[v] == 0)
				tarjanSCC(v);
			if(visited[v])
				dfs_low[u] = Math.min(dfs_low[u],dfs_low[v]);
				
		}
		if(dfs_low[u] == dfs_num[u])
		{
			while(true)
			{
				int v = stack.pop();
				visited[v] = false;
				findSCC[v] = SCC;
				if(v == u)
					break;
			}
			SCC++;
		}
	}
	
	static int go()
	{
		counter = SCC = 0;
		dfs_num = new int[N];
		dfs_low = new int[N];
		findSCC = new int[N];
		visited = new boolean[N];
		stack = new Stack<Integer>();
		for(int i = 0; i < N; i++)
			if(dfs_num[i]==0)
				tarjanSCC(i);
		
		boolean[] root = new boolean[SCC];
		Arrays.fill(root, true);
		ArrayList<Integer>[] DAG = new ArrayList[SCC];
		for(int i = 0; i < SCC; i++)
			DAG[i] = new ArrayList<Integer>();
		
		for(int i = 0; i < N; i++)
			for(int j = 0, size = adjList[i].size(); j < size; j++)
			{
				int to = adjList[i].get(j);
				if(findSCC[i] != findSCC[to])
					root[findSCC[to]] = false;
			}
		int roots = 0;
		for(int i = 0; i < SCC; i++)
			if(root[i])
				roots++;
		return roots;
	}
	
	public static void main(String[] args) throws IOException {
		
		InputReader in = new InputReader(System.in);
		StringBuilder sb = new StringBuilder();
		
		int tc = in.nextInt();
		while(tc-->0)
		{
			N = in.nextInt();
			int m = in.nextInt();
			adjList = new ArrayList[N];
			for(int i = 0; i < N; i++)
				adjList[i] = new ArrayList<Integer>();
			while(m-->0)
			{
				int x = in.nextInt() - 1, y = in.nextInt() - 1;
				adjList[x].add(y);
			}
			sb.append(go()+"\n");
		}
		System.out.print(sb);
	}
	
	
	
	
}

class InputReader {
	StringTokenizer st;
	BufferedReader br;

	public InputReader(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

	public String next() throws IOException 
	{
		while (st == null || !st.hasMoreTokens()) 
			st = new StringTokenizer(br.readLine());
		return st.nextToken();
	}

	public int nextInt() throws IOException {return Integer.parseInt(next());}
	
	public String nextLine() throws IOException {return br.readLine();}

	public boolean ready() throws IOException {return br.ready();}

}