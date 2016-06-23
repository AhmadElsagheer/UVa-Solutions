package v113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;


public class TheLargestClique_UVa11324 {

	static int V, SCC, counter;
	static int[] dfs_num, dfs_low, findSCC, sizeSCC, largest;
	static ArrayList<Integer>[] adjList, DAG;
	static Stack<Integer> stack;

	static void dfs(int u)
	{
		dfs_num[u] = dfs_low[u] = ++counter;
		stack.push(u);
		for(int v: adjList[u])
		{
			if(dfs_num[v] == 0)
				dfs(v);
			if(findSCC[v] == 0)
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
		}
		if(dfs_num[u] == dfs_low[u])
		{
			++SCC;
			while(true)
			{
				int v = stack.pop();
				findSCC[v] = SCC;
				if(v == u)
					break;
			}
		}
	}
	
	
	static int findLargest(int u)
	{
		if(largest[u] != 0)
			return largest[u];
		int ret = 0;
		for(int v: DAG[u])
			ret = Math.max(ret, findLargest(v));
		return largest[u] = ret + sizeSCC[u];
	}
	
	static int go()
	{
		dfs_num = new int[V];
		dfs_low = new int[V];
		findSCC = new int[V];
		stack = new Stack<Integer>();
		counter = SCC = 0;
		for(int i = 0; i < V; ++i)
			if(dfs_num[i] == 0)
				dfs(i);
		DAG = new ArrayList[SCC];
		sizeSCC = new int[SCC];
		largest = new int[SCC];
		for(int i = 0; i < SCC; ++i)
			DAG[i] = new ArrayList<Integer>();
		for(int u = 0; u < V; ++u)
		{
			int s1 = findSCC[u] - 1;
			sizeSCC[s1]++;
			for(int v: adjList[u])
			{
				int s2 = findSCC[v] - 1;
				if(s1 != s2)
					DAG[s1].add(s2); 
			}
		}
		
		int ans = 0;
		for(int u = 0; u < SCC; ++u)
			ans = Math.max(ans, findLargest(u));
		return ans;
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			V = sc.nextInt();
			int E = sc.nextInt();
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Integer>();
			while(E-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				adjList[u].add(v);
			}
			out.println(go());
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

		public double nextDouble() throws IOException
		{
			return Double.parseDouble(next());
		}

		public boolean ready() throws IOException {return br.ready();}


	}
}