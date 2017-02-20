package v124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ForwardingEmails_UVa12442 {

	static int N, counter, dfs_num[], next[], reachable[];
	
	static int dfs(int u)
	{
		dfs_num[u] = ++counter;
		reachable[u] = 1;
		int cycleHead = -1;
		
		int v = next[u];
		if(dfs_num[v] == 0)
		{
			cycleHead = dfs(v);
			if(cycleHead != -1)
				reachable[u] = reachable[v];
			else
				reachable[u] += reachable[v];
		}
		else
			if(dfs_num[v] == -1)
				reachable[u] += reachable[v];
			else
			{
				reachable[u] = dfs_num[u] - dfs_num[v] + 1;
				cycleHead = v;
			}
		dfs_num[u] = -1;
		return cycleHead == u ? -1 : cycleHead;
	}

	static int go()
	{
		dfs_num = new int[N];
		reachable = new int[N];
		
		for(int i = 0; i < N; ++i)
			if(dfs_num[i] == 0)
				dfs(i);

		int ans = 0;
		for(int i = 1; i < N; ++i)
			if(reachable[i] > reachable[ans])
				ans = i;
		return ans;
	}


	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			N = sc.nextInt();
			next = new int[N];
			
			for(int i = 0; i < N; ++i)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				next[u] = v;
			}
			out.format("Case %d: %d\n", t, go() + 1);
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
				st = new StringTokenizer(br.readLine(), ",| ");
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready();}

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


	}
}
