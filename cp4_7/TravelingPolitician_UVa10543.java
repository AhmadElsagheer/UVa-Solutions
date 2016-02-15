package cp4_7;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TravelingPolitician_UVa10543 {
	
	static final int INF = (int)1e7;
	static int N, K, memo[][];
	static ArrayList<Integer>[] adjList;
	
	static int dp(int city, int k)
	{
		if(k == 20)
			return INF;
		if(city == N - 1 && k >= K - 1)
			return 1;
		
		if(memo[city][k] != -1)
			return memo[city][k];
		int min = INF;
		for(int i = 0; i < adjList[city].size(); ++i)
			min = Math.min(min, 1 + dp(adjList[city].get(i), k + 1));
		return memo[city][k] = min;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			N = sc.nextInt();
			if(N == 0)
				break;
			int E = sc.nextInt();
			K = sc.nextInt();
			memo = new int[N][20];
			for(int i = 0; i < N; ++i)
				Arrays.fill(memo[i], -1);
			adjList = new ArrayList[N];
			for(int i = 0; i < N; ++i)
				adjList[i] = new ArrayList<Integer>();
			while(E-->0)
			{
				int u = sc.nextInt(), v = sc.nextInt();
				if(u != v)
					adjList[u].add(v);
			}
			int ans = dp(0, 0);
			out.println(ans == INF ? "LOSER" : ans);
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
