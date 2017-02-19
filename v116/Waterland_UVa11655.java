package v116;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Waterland_UVa11655 {
	
	static Pair[] memo;
	static ArrayList<Integer>[] adjList;
	static int N;
	
	static Pair dp(int city)
	{
		if(city == N - 1)
			return new Pair(1, 1);
		if(memo[city] != null)
			return memo[city];
		Pair ans = new Pair(0, 0);
		for(int i = 0; i < adjList[city].size(); ++i)
		{
			int v = adjList[city].get(i);
			Pair nxt = dp(v);
			ans.total = (ans.total + nxt.total + nxt.teams * (city == 0 ? 0 : 1))%100000;
			ans.teams = (ans.teams + nxt.teams)%100000;
		}
		return memo[city] = ans;
	}
	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			N = sc.nextInt();
			int E = sc.nextInt();
			adjList = new ArrayList[N];
			for(int i = 0; i < N; ++i)
				adjList[i] = new ArrayList<Integer>();
			while(E-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				adjList[u].add(v);
			}
			memo = new Pair[N];
			Pair ans = dp(0);
			
			out.format("Case %d: %d %d\n", t, ans.total, ans.teams);
		}
		
		out.flush();
	}
	
	
	static class Pair
	{
		int total, teams;
		
		Pair(int x, int y)
		{
			total = x;
			teams = y;
		}
		
		public String toString()
		{
			return total + " " + teams;
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
