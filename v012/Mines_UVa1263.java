package v012;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Mines_UVa1263 {

	static int N, counter, SCC, dfs_num[], dfs_low[], findSCC[];
	static ArrayList<Integer>[] adjList;
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
		
		if(dfs_low[u] == dfs_num[u])
		{
			++SCC;
			while(true)
			{
				int v = stack.pop();
				findSCC[v] = SCC;
				if(u == v)
					break;
			}
		}
	}
	
	static int go()
	{
		counter = SCC = 0;
		dfs_num = new int[N];
		dfs_low = new int[N];
		findSCC = new int[N];
		stack = new Stack<Integer>();
		
		for(int i = 0; i < N; ++i)
			if(dfs_num[i] == 0)
				dfs(i);
		
		boolean[] notRoot = new boolean[SCC];
		for(int u = 0; u < N; ++u)
		{
			int curSCC = findSCC[u] - 1;
			for(int v: adjList[u])
			{
				int nxtSCC = findSCC[v] - 1;
				if(curSCC != nxtSCC)
					notRoot[nxtSCC] = true;
			}
		}
		
		int ans = 0;
		for(boolean b: notRoot)
			if(!b)
				++ans;
		return ans;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			N = sc.nextInt();
			Mine[] mines = new Mine[N];
			for(int i = 0; i < N; ++i)
				mines[i] = new Mine(sc.nextInt(), sc.nextInt(), sc.nextInt());
			adjList = new ArrayList[N];
			for(int i = 0; i < N; ++i)
				adjList[i] = new ArrayList<Integer>();
			for(int i = 0; i < N; ++i)
			{
				Mine cur = mines[i];
				for(int j = 0; j < N; ++j)	
					if(cur.contains(mines[j]))
						adjList[i].add(j);
			}
			out.println(go());
		}
		out.flush();
		out.close();
	}
	
	static class Mine
	{
		int x, y, d;
		
		Mine(int a, int b, int c) { x = a; y = b; d = c;}
		
		boolean contains(Mine m)
		{
			return 2 * x - d <= 2 * m.x && 2 * m.x <= 2 * x + d && 2 * y - d <= 2 * m.y && 2 * m.y <= 2 * y + d; 
		}
		
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

		public boolean ready() throws IOException {return br.ready();}
	}
}