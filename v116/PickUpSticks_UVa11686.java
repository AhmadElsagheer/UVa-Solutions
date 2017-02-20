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


public class PickUpSticks_UVa11686 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			int N = sc.nextInt(), M = sc.nextInt();
			if(N + M == 0)
				break;
			ArrayList<Integer>[] adjList = new ArrayList[N];
			for(int i = 0; i < N; ++i)
					adjList[i] = new ArrayList<Integer>();
			
			int[] deg = new int[N];
			while(M-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				adjList[u].add(v);
				deg[v]++;
			}

			Queue<Integer> q = new LinkedList<Integer>();
			ArrayList<Integer> ans = new ArrayList<Integer>(N);
			for(int i = 0; i < N; ++i)
				if(deg[i] == 0)
					q.add(i);
			while(!q.isEmpty())
			{
				int u = q.remove();
				ans.add(u);
				for(int v: adjList[u])
					if(--deg[v] == 0)
						q.add(v);
			}
			
			
			if(ans.size() != N)
				out.println("IMPOSSIBLE");
			else
			{
				StringBuilder sb = new StringBuilder();
				for(int x: ans)
					sb.append(x+1+"\n");
				out.print(sb);
			}
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