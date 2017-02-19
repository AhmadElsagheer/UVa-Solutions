package v011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TheMysertiousXNetwork_UVa1148 {

	static int[][] adjList;
	static int N;
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			N = sc.nextInt();
			adjList = new int[N][];
			for(int i = 0; i < N; ++i)
			{
				int u = sc.nextInt(), k = sc.nextInt();
				adjList[u] = new int[k];
				for(int j = 0; j < k; ++j)
					adjList[u][j] = sc.nextInt();
			}
			int c1 = sc.nextInt(), c2 = sc.nextInt();
			out.format("%d %d %d\n",c1, c2, bfs(c1, c2));
			if(tc != 0)
				out.println();
		}
		out.flush();
		
	}
	
	static int bfs(int s, int t)
	{
		int[] dist = new int[N];
		Queue<Integer> q =  new LinkedList<Integer>();
		dist[s] = 1;
		q.add(s);
		while(!q.isEmpty())
		{
			int u = q.remove();
			if(u == t)
				break;
			for(int i = 0; i < adjList[u].length; ++i)
			{
				int v = adjList[u][i];
				if(dist[v] == 0)
				{
					dist[v] = dist[u] + 1;
					q.add(v);
				}
			}
		}
		return dist[t] - 2;
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
		
		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
