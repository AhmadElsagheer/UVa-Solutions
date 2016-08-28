package v116;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class FlightPlanning_UVa11695 {
	
	static ArrayList<Integer>[] adjList;
	static int N, dp_down[][], dp_up[], dp_downX[][], dp_upX[];
	static int uOld, vOld, uNew, vNew, minDia;
	
	static void dfs1(int u, int p)
	{
		for(int i = 0; i < adjList[u].size(); ++i)
		{
			int v = adjList[u].get(i);
			if(v == p) 
				continue;
			dfs1(v, u);
			
			if(dp_down[v][0] + 1 > dp_down[u][2])
			{
				dp_down[u][2] = dp_down[v][0] + 1;
				if(dp_down[u][2] > dp_down[u][1])
					swap(dp_down, u, 1, 2);
				if(dp_down[u][1] > dp_down[u][0])
					swap(dp_down, u, 0, 1);
			}
			
			int x = Math.max(dp_downX[v][0], dp_down[v][0] + dp_down[v][1]);
			if(x > dp_downX[u][1])
			{
				dp_downX[u][1] = x;
				if(dp_downX[u][1] > dp_downX[u][0])
					swap(dp_downX, u, 0, 1);
			}
		}
	}
	
	static void dfs2(int u, int p, int up, int upX)
	{
		dp_up[u] = up;
		dp_upX[u] = upX;
		for(int i = 0; i < adjList[u].size(); ++i)
		{
			int v = adjList[u].get(i);
			if(v == p)
				continue;
			
			int nxt_up = up + 1, nxt_upX = upX;
			if(dp_down[v][0] + 1 == dp_down[u][0])
			{
				nxt_up = Math.max(nxt_up, 1 + dp_down[u][1]);
				nxt_upX = Math.max(nxt_upX, Math.max(dp_up[u] + dp_down[u][1], dp_down[u][1] + dp_down[u][2]));
			}
			else
			{
				nxt_up = Math.max(nxt_up, 1 + dp_down[u][0]);
				nxt_upX = Math.max(nxt_upX, dp_up[u] + dp_down[u][0]);
				if(dp_down[v][0] + 1 == dp_down[u][1])
					nxt_upX = Math.max(nxt_upX, dp_down[u][0] + dp_down[u][2]);
				else
					nxt_upX = Math.max(nxt_upX, dp_down[u][0] + dp_down[u][1]);
			}
			if(Math.max(dp_downX[v][0], dp_down[v][0] + dp_down[v][1]) == dp_downX[u][0])
				nxt_upX = Math.max(nxt_upX, dp_downX[u][1]);
			else
				nxt_upX = Math.max(nxt_upX, dp_downX[u][0]);
			dfs2(v, u, nxt_up, nxt_upX);
		}
	}
	
	static void swap(int[][] x, int u, int i, int j)
	{
		int tmp = x[u][i];
		x[u][i] = x[u][j];
		x[u][j] = tmp;
	}
	
	static void dfs3(int u, int p)
	{
		for(int i = 0; i < adjList[u].size(); ++i)
		{
			int v = adjList[u].get(i);
			if(v == p)
				continue;
			dfs3(v, u);
			int d1 = Math.max(dp_downX[v][0], dp_down[v][0] + dp_down[v][1]);
			int d2 = dp_upX[u];
			if(dp_down[v][0] + 1 == dp_down[u][0])
				d2 = Math.max(d2, Math.max(dp_up[u] + dp_down[u][1], dp_down[u][1] + dp_down[u][2]));
			else
			{
				d2 = Math.max(d2, dp_up[u] + dp_down[u][0]);
				if(dp_down[v][0] + 1 == dp_down[u][1])
					d2 = Math.max(d2, dp_down[u][0] + dp_down[u][2]);
				else
					d2 = Math.max(d2, dp_down[u][0] + dp_down[u][1]);
			}
			if(Math.max(dp_downX[v][0], dp_down[v][0] + dp_down[v][1]) == dp_downX[u][0])
				d2 = Math.max(d2, dp_downX[u][1]);
			else
				d2 = Math.max(d2, dp_downX[u][0]);
			int d = Math.max((d1 + 1) / 2 + (d2 + 1) / 2 + 1, Math.max(d1, d2));
			if(d < minDia)
			{
				uOld = u;
				vOld = v;
				minDia = d;
			}
		}
	}
	
	static void link()
	{
		int[] subtree = new int[N]; // 1 for u tree, 0 for v tree
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(uOld);
		while(!q.isEmpty())
		{
			int u = q.remove();
			subtree[u] = 1;
			for(int i = 0; i < adjList[u].size(); ++i)
			{
				int v = adjList[u].get(i);
				if(v != vOld && subtree[v] == 0)
					q.add(v);
				
			}
		}
		uNew = findRoot(subtree, 1);
		vNew = findRoot(subtree, 0);
	}
	
	static int findRoot(int[] subtree, int x)
	{
		int[] deg = new int[N];
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i = 0; i < N; ++i)
		if(subtree[i] == x)
		{
			deg[i] = adjList[i].size();
			if(i == uOld || i == vOld)
				deg[i]--;
			if(deg[i] <= 1)
				q.add(i);
		}
		
		int root = -1;
		while(!q.isEmpty())
		{
			root = q.remove();
			for(int i = 0; i < adjList[root].size(); ++i)
			{
				int v = adjList[root].get(i);
				if(subtree[v] != x)
					continue;
				if(--deg[v] == 1)
					q.add(v);
			}
		}
		return root;
		
	}

	static void go()
	{
		dp_down = new int[N][3]; dp_up = new int[N]; dp_downX = new int[N][2]; dp_upX = new int[N];
		minDia = N; dfs1(0, -1); dfs2(0, -1, 0, 0);	dfs3(0, -1); link();
	}
	
	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			N = sc.nextInt();
			adjList = new ArrayList[N];
			for(int i = 0; i < N; ++i)
				adjList[i] = new ArrayList<Integer>();
			for(int i = 1; i < N; ++i)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				adjList[u].add(v);
				adjList[v].add(u);
			}
			go();
			out.format("%d\n%d %d\n%d %d\n", minDia, uOld + 1, vOld + 1, uNew + 1, vNew + 1);
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
