package v100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LongestPaths_UVa10000 {

	static ArrayList<Integer>[] adjList;
	static Pair[] memo;
	
	static Pair longestPath(int city)
	{
		if(memo[city] != null)
			return memo[city];
		
		Pair ret = new Pair(0, city);
		for(int v: adjList[city])
		{
			Pair nxt = longestPath(v);
			if(nxt.len + 1 > ret.len || nxt.len + 1 == ret.len && nxt.lst < ret.lst)
			{
				ret.len = nxt.len + 1;
				ret.lst = nxt.lst;
			}
		}
		return memo[city] = ret;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			int N = sc.nextInt();
			if(N == 0)
				break;
			adjList = new ArrayList[N];
			for(int i = 0; i < N; ++i)
				adjList[i] = new ArrayList<Integer>();
			int s = sc.nextInt() - 1;
			while(true)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				if(u == -1)
					break;
				adjList[u].add(v);
			}
			memo = new Pair[N];
			Pair ans = longestPath(s);
			out.printf("Case %d: The longest path from %d has length %d, finishing at %d.\n\n", tc++, s + 1, ans.len, ans.lst + 1);
		}

		out.flush();
		out.close();
	}
	
	static class Pair { int len, lst; Pair(int a, int b) { len = a; lst = b; } }

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