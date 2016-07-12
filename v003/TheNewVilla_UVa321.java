package v003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class TheNewVilla_UVa321 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();

		int tc = 1;
		while(true)
		{
			int N = sc.nextInt(), M = sc.nextInt(), S = sc.nextInt();
			if(N == 0)
				break;
			int[][] adjMat = new int[N][N];
			while(M-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				adjMat[u][v] = adjMat[v][u] = 1;
			}
			while(S-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				adjMat[u][v] |= 2;
			}
			int[][] dist = new int[N][1<<N], p = new int[N][1<<N];
			dist[0][1] = 1;
			p[0][1] = -1;
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(N);
			while(!q.isEmpty())
			{
				int cur = q.remove();
				int u = cur % N, s = cur / N, d = dist[u][s];
				if(u == N - 1 && s == 1<<N-1)
					break;
				for(int v = 0; v < N; ++v)
					if(u != v)
					{
						//try to go there
						if((adjMat[u][v] & 1) == 1 && (s & 1<<v) != 0 && dist[v][s] == 0)
						{
							dist[v][s] = d + 1;
							p[v][s] = cur;
							q.add(s * N + v);
						}

						//try to switch the light there
						if((adjMat[u][v] & 2) == 2 && dist[u][s ^ 1<<v] == 0)
						{
							int ss = s ^ 1<<v; 
							dist[u][ss] = d + 1;
							p[u][ss] = cur;
							q.add(ss * N + u);
						}
					}
			}

			sb.append("Villa #" + tc++ +"\n");
			if(--dist[N-1][1<<N-1] == -1)
				sb.append("The problem cannot be solved.\n");
			else
			{
				sb.append("The problem can be solved in "+ dist[N-1][1<<N-1] +" steps:\n");
				Stack<String> stack = new Stack<String>();
				int cur = (1<<N-1) * N + N - 1;
				while(true)
				{
					int v = cur % N, sv = cur / N;
					int parent = p[v][sv];
					if(parent == -1)
						break;
					int u = parent % N, su = parent / N;
					if(u != v)
						stack.push("- Move to room "+ (v+1) +".\n");
					else
					{
						int s = su ^ sv, r = 0;
						while(s != 1<<r)
							++r;
						if((sv & 1<<r) == 0)
							stack.push("- Switch off light in room "+ (r+1) +".\n");
						else
							stack.push("- Switch on light in room "+ (r+1) +".\n");
					}
					cur = parent;
				}
				while(!stack.isEmpty())
					sb.append(stack.pop());
			}
			sb.append("\n");
		}
		out.print(sb);
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