package v002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//1.DP Solution 	2.Bipartite Matching 
public class SoftwareAllocation_UVa259 {

	//1. DP Solution 
	static int n, app[], memo[][], sol[] = new int[10];
	static boolean[][] coms;
	
	static int dp(int idx, int msk)
	{
		if(idx == n)
			return 1;
		if(memo[idx][msk] != -1)
			return memo[idx][msk];
		for(int i = 0; i < 10; ++i)
			if(coms[app[idx]][i] && ((1<<i) & msk) == 0 && dp(idx + 1, msk | (1<<i)) == 1)
				return memo[idx][msk] = 1;
		return memo[idx][msk] = 0;
	}
	
	static void print(int idx, int msk)
	{
		if(idx == n)
			return;
		
		for(int i = 0; i < 10; ++i)
			if(coms[app[idx]][i] && ((1<<i) & msk) == 0 && dp(idx + 1, msk | (1<<i)) == 1)
			{
				sol[i] = app[idx];
				print(idx + 1, msk | (1<<i));
				return;
			}
	}
	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			app = new int[260];
			coms = new boolean[26][10];
			n = 0;
			while(sc.ready() && !sc.nxtEmpty())
			{
				String s = sc.next();
				int users = s.charAt(1) - '0', c = s.charAt(0) - 'A';
				for(int i = 0; i < users; ++i)
					app[n++] = c;
				s = sc.next();
				for(int i = 0; i < s.length() - 1; ++i)
					coms[c][s.charAt(i) - '0'] = true;
			}
			if(n > 10)
				out.println('!');
			else
			{
				memo = new int[n][1<<10];
				for(int i = 0; i < n; ++i)
					Arrays.fill(memo[i], -1);
				if(dp(0, 0) == 0)
					out.println('!');
				else
				{
					Arrays.fill(sol, -1);
					print(0, 0);
					StringBuilder sb = new StringBuilder();
					for(int i = 0; i < 10; ++i)
						if(sol[i] == -1)
							sb.append("_");
						else
							sb.append((char)(sol[i] + 'A'));
					out.println(sb);
							
				}
			}
			
		}
		out.flush();
	}
	
	//2.Bipartite Matching
	static final int INF = (int)1e9;
	static int V = 38, res[][], p[] = new int[V];
	static int s, t;
	
	static int go(int source, int sink)
	{
		s = source; t = sink;
		int mf = 0;
		while(true)
		{
			Queue<Integer> q = new LinkedList<Integer>();
			p = new int[V];
			Arrays.fill(p, -1);
			q.add(s);
			p[s] = s;
			while(!q.isEmpty())
			{
				int u = q.remove();
				if(u == t)
					break;
				for(int v = 0; v < V; ++v)
					if(res[u][v] > 0 && p[v] == -1)
					{
						p[v] = u;
						q.add(v);
					}					
			}
			if(p[t] == -1)
				break;
			mf += augment(t, INF);
		}
		return mf;
	}
	
	static int augment(int v, int flow)
	{
		if(v == s)
			return flow;
		flow = augment(p[v], Math.min(flow, res[p[v]][v]));
		res[p[v]][v] -= flow; res[v][p[v]] += flow;
		return flow;
	}
	
	public static void bipatite() throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			res = new int[V][V];
			int total = 0;
			while(sc.ready() && !sc.nxtEmpty())
			{
				String s = sc.next();
				int app = s.charAt(0) - 'A' + 1;
				total += res[0][app] = s.charAt(1) - '0';
				s = sc.next();
				for(int i = 0; i < s.length() - 1; ++i)
					res[app][s.charAt(i)-'0'+27] = INF;
			}
			for(int i = 27; i <= 36; ++i)
				res[i][37] = 1;
			int f = go(0, 37);
			
			if(f != total)
				out.println('!');
			else
			{
				StringBuilder sb = new StringBuilder();
				for(int i = 27; i <= 36; ++i)
				{
					boolean used = false;
					for(int j = 1; j <= 26 && !used; ++j)
						if(res[i][j] == 1)
						{
							sb.append((char)(j + 'A' -1));
							used = true;
						}
					if(!used)
						sb.append('_');
				}
				out.println(sb);
			}
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
		
		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
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
