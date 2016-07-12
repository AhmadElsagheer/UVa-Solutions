package v118;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class ComeAndGo_UVa11838 {
	
	static ArrayList<Integer>[] adjList;
	static int V, counter, SCC, dfs_low[], dfs_num[];
	static boolean[] inSCC;
	static Stack<Integer> stack;
	
	static void dfs(int u)
	{
		dfs_num[u] = dfs_low[u] = ++counter;
		stack.push(u);
		for(int v: adjList[u])
		{
			if(dfs_num[v] == 0)
				dfs(v);
			if(!inSCC[v])
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
		}
		if(dfs_low[u] == dfs_num[u])
		{	
			++SCC;
			while(true)
			{
				int v = stack.pop();
				inSCC[v] = true;
				if(v == u)
					break;
			}
		}
		
	}
	
	static int good()
	{
		dfs_num = new int[V];
		dfs_low = new int[V];
		inSCC = new boolean[V];
		stack = new Stack<Integer>();
		counter = SCC = 0;
		for(int i = 0; i < V; ++i)
			if(dfs_num[i] == 0)
				dfs(i);
		return SCC == 1 ? 1 : 0;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			V = sc.nextInt();
			if(V == 0)
				break;
			int E = sc.nextInt();
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Integer>();
			while(E-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1, p = sc.nextInt();
				adjList[u].add(v);
				if(p == 2)
					adjList[v].add(u);
			}
			out.println(good());
		}
		out.flush();
		out.close();
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}

	}
}