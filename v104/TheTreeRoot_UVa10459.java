package v104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TheTreeRoot_UVa10459 {
	
	static ArrayList<Integer>[] adjList;
	static int N, dp_down[][], dp_up[];
	static boolean[] visited;
	
	static void dfs1(int u)
	{
		visited[u] = true;
		for(int i = 0; i < adjList[u].size(); ++i)
		{
			int v = adjList[u].get(i);
			if(!visited[v])
			{
				dfs1(v);
				if(dp_down[v][0] + 1> dp_down[u][1])
					dp_down[u][1] = dp_down[v][0] + 1;
				if(dp_down[u][1] > dp_down[u][0])
					swap(u);
			}
		}
	}
	
	static void dfs2(int u, int h)
	{
		visited[u] = true;
		dp_up[u] = h;
		for(int i = 0; i < adjList[u].size(); ++i)
		{
			int v = adjList[u].get(i);
			if(!visited[v])
			{
				int h_nxt;
				if(dp_down[v][0] + 1 == dp_down[u][0])
					h_nxt = dp_down[u][1] + 1;
				else
					h_nxt = dp_down[u][0] + 1;
				dfs2(v, Math.max(h + 1, h_nxt));
			}
		}
	}
	
	static void swap(int u)
	{
		int x = dp_down[u][0], y = dp_down[u][1];
		dp_down[u][0] = y;	dp_down[u][1] = x;
	}
	
	static void go()
	{
		dp_down = new int[N][2];
		dp_up = new int[N];
		visited = new boolean[N];
		dfs1(0);
		visited = new boolean[N];
		dfs2(0, 0);
	}
	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
//		while(sc.ready())
		{
			N = sc.nextInt();
			adjList = new ArrayList[N];
			for(int i = 0; i < N; ++i)
			{
				int k = sc.nextInt();
				adjList[i] = new ArrayList<Integer>(k);
				while(k-->0)
					adjList[i].add(sc.nextInt() - 1);
			}
			go();
			int[] h = new int[N];
			int min = N, max = 0;
			for(int i = 0; i < N; ++i)
			{
				h[i] = Math.max(dp_down[i][0], dp_up[i]);
				min = Math.min(min, h[i]);
				max = Math.max(max, h[i]);
			}
			
			out.print("Best Roots  :");
			for(int i = 0; i < N; ++i)
				if(h[i] == min)
					out.format(" %d", i + 1);
			out.print("\nWorst Roots :");
			for(int i = 0; i < N; ++i)
				if(h[i] == max)
					out.format(" %d", i + 1);
			out.println();
		}
		
		out.flush();
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
