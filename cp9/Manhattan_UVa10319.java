package cp9;
	import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Manhattan_UVa10319 {

	static int V, dfs_low[], dfs_num[], findSCC[], counter, SCC;
	static ArrayList<Integer>[] adjList;
	static Stack<Integer> stack;
	
	static void tarjanSCC()
	{
		counter = 0; SCC = 0;
		dfs_low = new int[V];
		dfs_num = new int[V];
		findSCC = new int[V];
		stack = new Stack<Integer>();
		for(int i = 0; i < V; ++i)
			if(dfs_num[i] == 0)
				tarjanSCC(i);
	}
	
	static void tarjanSCC(int u)
	{
		dfs_num[u] = dfs_low[u] = ++counter;
		stack.push(u);
		for(int v: adjList[u])
		{
			if(dfs_num[v] == 0)
				tarjanSCC(v);
			if(findSCC[v] == 0)
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
		}
		if(dfs_low[u] == dfs_num[u])
		{
			SCC++;
			while(true)
			{
				int v = stack.pop();
				findSCC[v] = SCC;
				if(u == v)
					break;
			}
		}
	}
	
	static boolean possible(int n)
	{
		tarjanSCC();
		for(int i = 0; i < n; ++i)
			if(findSCC[i<<1] == findSCC[(i<<1) + 1])
				return false;
		return true;
	}
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int R = sc.nextInt(), C = sc.nextInt(), S = sc.nextInt();
			V = R + C << 1;
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Integer>();
			while(S-->0)
			{
				int x1 = sc.nextInt() - 1, y1 = sc.nextInt() - 1, x2 = sc.nextInt() - 1, y2 = sc.nextInt() - 1;
				if(x1 == x2 && y1 == y2)
					continue;
				if(x1 == x2)
					if(y1 < y2)
						adjList[(x1<<1)^1].add(x1<<1);
					else
						adjList[x1<<1].add((x1<<1)^1);
				else
					if(y1 == y2)
						if(x1 < x2)
							adjList[(y1 + R<<1)^1].add(y1 + R<<1);
						else
							adjList[y1 + R<<1].add((y1 + R<<1)^1);
					else
					{
						int a, b, c, d;
						if(y1 < y2) { a = x1<<1; c = x2<<1;}
						else { a = (x1<<1)^1; c = (x2<<1)^1;}
						
						if(x1 < x2) { b = y1+R<<1; d = y2+R<<1;}
						else { b = (y1+R<<1)^1; d = (y2+R<<1)^1;}
						addEdge(a, c); addEdge(a, b); addEdge(d, c); addEdge(d, b);
					}
			}
			out.println(possible(R + C)?"Yes":"No");
		}
		out.flush();
		out.close();
	}
	
	static void addEdge(int u, int v)
	{
		adjList[u^1].add(v);
		adjList[v^1].add(u);
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

		public boolean ready() throws IOException {return br.ready();}

		public double nextDouble() throws IOException 
		{
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}


	}
}
