package v002;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CatAndMouse_UVa274 {
	
	
	static boolean[] bfs(ArrayList<Integer>[] graph, int N, int s)
	{
		boolean[] vis = new boolean[N];
		Queue<Integer> q = new LinkedList<Integer>();
		vis[s] = true;
		q.add(s);
		while(!q.isEmpty())
		{
			int u = q.remove();
			for(int i = 0; i < graph[u].size(); ++i)
			{
				int v = graph[u].get(i);
				if(!vis[v])
				{
					vis[v] = true;
					q.add(v);
				}
			}
				
		}
		return vis;
	}
	
	static boolean bfs2(boolean[] cat, ArrayList<Integer>[] graph, int N, int s)
	{
		if(cat[s])
			return false;
		boolean[] vis = new boolean[N];
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);
		while(!q.isEmpty())
		{
			int u = q.remove();
			for(int i = 0; i < graph[u].size(); ++i)
			{
				int v = graph[u].get(i);
				if(!cat[v] && !vis[v])
				{
					if(v == s)
						return true;
					vis[v] = true;
					q.add(v);
				}
			}
		}
		return false;
	}
	
	static boolean canMeet(boolean[] vis1, boolean[] vis2, int N)
	{
		for(int i = 0; i < N; ++i)
			if(vis1[i] && vis2[i])
				return true;
		return false;
	}
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int N = sc.nextInt(), s1 = sc.nextInt() - 1, s2 = sc.nextInt() - 1;
			ArrayList<Integer>[] cat = new ArrayList[N], mouse = new ArrayList[N];
			for(int i = 0; i < N; ++i)
			{
				cat[i] = new ArrayList<Integer>();
				mouse[i] = new ArrayList<Integer>();
			}
			while(true)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				if(u == -2)
					break;
				cat[u].add(v);
			}
			while(!sc.nxtEmpty())
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				mouse[u].add(v);
			}
			boolean[] vis1 = bfs(cat, N, s1), vis2 = bfs(mouse, N, s2);
			out.format("%c %c\n", canMeet(vis1, vis2, N)?'Y':'N', bfs2(vis1, mouse, N, s2) ? 'Y' :'N');
			if(tc != 0)
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

		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line == null || line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
