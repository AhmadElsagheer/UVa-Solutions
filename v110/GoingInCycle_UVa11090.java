package v110;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GoingInCycle_UVa11090 {
	
	
	static final int INF = (int)1e9;
	static int[][] adjMat, memo;
	static int V, S;
	
	static int dp(int node, int remEdges)
	{
		if(remEdges == 0)
			return node == S ? 0 : INF;
		if(memo[node][remEdges] != -1)
			return memo[node][remEdges];
		int min = INF;
		
		for(int i = 0; i < V; ++i)
			min = Math.min(min, adjMat[node][i] + dp(i, remEdges - 1));
		return memo[node][remEdges] = min;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			V = sc.nextInt();
			adjMat = new int[V][V];
			for(int i = 0; i < V; ++i)
				Arrays.fill(adjMat[i], INF);
			int E = sc.nextInt();
			while(E-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1, w = sc.nextInt();
				adjMat[u][v] = Math.min(w, adjMat[u][v]);
			}
			double ans = 1e9;
			for(int i = 0; i < V; ++i)
			{
				S = i;
				memo = new int[V][V + 1];
				for(int j = 0; j < V; ++j)
					Arrays.fill(memo[j], -1);
				for(int k = 1; k <= V; ++k)
				{
					int ss = dp(i, k);
					if(ss < INF)
						ans = Math.min(ans, (double)ss/k);
				}
				
			}
			if(ans + 0.5 > 1e9)
				out.format("Case #%d: No cycle found.\n", t);
			else
				out.format("Case #%d: %.2f\n", t, ans);
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
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
