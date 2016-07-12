package v114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SAMIAM_UVa11419 {

	static int n, m, match[];
	static boolean[] visited;
	static ArrayList<Integer>[] adjList;

	static int maxMatches()
	{
		int matches = 0;
		match = new int[n + m];
		Arrays.fill(match, -1);
		for(int i = 0; i < n; ++i)
		{
			visited = new boolean[n];
			matches += aug(i);
		}
		return matches;
	}

	static int aug(int u)
	{
		visited[u] = true;
		for(int v: adjList[u])
		{
			int ux = match[v];
			if(ux == -1 || !visited[ux] && aug(ux) == 1)
			{
				match[v] = u;
				match[u] = v;
				return 1;
			}
		}
		return 0;
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();

		while(true)
		{
			n = sc.nextInt(); m = sc.nextInt();
			int e = sc.nextInt();
			if(n == 0)
				break;
			adjList = new ArrayList[n + m];
			for(int i = 0; i < n + m; ++i)
				adjList[i] = new ArrayList<Integer>();
			while(e-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1 + n;
				adjList[u].add(v);
				adjList[v].add(u);
			}
			int matches = maxMatches(), mvc[] = new int[matches], idx = 0;
			boolean[] visited = new boolean[n + m];
			Queue<Integer> unmatched = new LinkedList<Integer>();
			Queue<Integer> mvcCandidate = new LinkedList<Integer>();
			for(int i = 0; i < n + m; ++i)
				if(match[i] == -1)
					unmatched.add(i);
				else
					mvcCandidate.add(i);
			while(!unmatched.isEmpty() || !mvcCandidate.isEmpty())
			{
				while(!unmatched.isEmpty())
				{
					int u = unmatched.remove();
					for(int v: adjList[u])
						if(!visited[v])
						{
							mvc[idx++] = v;
							visited[v] = true;
							int w = match[v];
							visited[w] = true;
							unmatched.add(w);
						}
				}
				
				while(!mvcCandidate.isEmpty())
				{
					int u = mvcCandidate.remove();
					if(!visited[u])
					{
						mvc[idx++] = u;
						visited[u] = true;
						int w = match[u];
						visited[w] = true;
						unmatched.add(w);
						break;
					}
				}
			}
			sb.append(matches);
			for(int u: mvc)
				if(u < n)
					sb.append(" r"+(u+1));
				else
					sb.append(" c"+(u+1-n));
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