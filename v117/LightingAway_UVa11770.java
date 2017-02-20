package v117;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;


public class LightingAway_UVa11770 {

	static int N, counter, SCC;
	static ArrayList<Integer>[] adjList;
	static Stack<Integer> stack;
	static int[] dfs_num, dfs_low, findSCC;
	static boolean[] visited;
	
	public static void tarjanSCC(int u)
	{
		dfs_num[u] = dfs_low[u] = ++counter;
		stack.push(u);
		visited[u] = true;
		for(int i = 0; i <adjList[u].size(); i++)
		{
			int v = adjList[u].get(i);
			if(dfs_num[v]==0)	//to distinguish between not visited at all and not visited in the context of SCC
				tarjanSCC(v);
			if(visited[v])
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
		}
		if(dfs_num[u]==dfs_low[u])
		{
			//Discovered an SCC
			while(true)		//vertices in the discovered SCC
			{	
				int v = stack.pop();
				visited[v] = false;
				findSCC[v] = SCC;
				if(v==u)
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
		
		for(int i = 0; i < N ; i++)
			for(int j = 0 ,size = adjList[i].size(); j < size; j++)
			{
				int to = adjList[i].get(j);
				if(findSCC[i] != findSCC[to])
					root[findSCC[to]] = false;
			}
		
		int count = 0;
		for(int i = 0; i < SCC; i++)
			if(root[i])
				count++;
		return count;
		
	}
	public static void main(String[] args) throws IOException {
		
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = in.nextInt();
		for(int k = 1; k <= tc; k++)
		{
			N = in.nextInt();
			int m = in.nextInt();
			
			adjList = new ArrayList[N];
			for(int i = 0; i < N; i++)
				adjList[i] = new ArrayList<Integer>();

			while(m-->0)
			{
				int a = in.nextInt() - 1, b = in.nextInt() - 1;
				adjList[a].add(b);
			}
			out.printf("Case %d: %d\n",k,go());
		}
		out.flush();
	}
	static class InputReader {
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
}

