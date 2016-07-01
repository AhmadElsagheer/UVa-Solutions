package v108;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HeadmastersHeadache_UVa10817 {

	static final int INF = 1<<30;
	static int S, M, N;
	static boolean[][] teach;
	static int[] cost, memo[];
	
	static int dp(int idx, int msk)
	{
		if(msk == 0)
			return 0;
		if(idx == M)
			return INF;
		if(memo[idx][msk] != -1)
			return memo[idx][msk];
		int msk2 = msk;
		for(int s = 0; s < S; ++s)
			if(teach[idx][s])
			{
				if(((1<<s) & msk2) != 0)
					msk2 &= ~(1<<s); 
				else
					if(((1<<s + S) & msk2) != 0)
						msk2 &= ~(1<<s + S);
			}
		int take = dp(idx + 1, msk2) + cost[idx];
		int leave = dp(idx + 1, msk);
		return memo[idx][msk] = Math.min(take, leave);
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			S = sc.nextInt();
			if(S == 0)
				break;
			N = sc.nextInt();
			M = sc.nextInt();
			int msk = (1<<(S<<1)) - 1, ini = 0;
			while(N-->0)
			{
				StringTokenizer st = new StringTokenizer(sc.nextLine());
				int c = Integer.parseInt(st.nextToken());
				ini += c;
				while(st.hasMoreTokens())
				{
					int s = Integer.parseInt(st.nextToken()) - 1;
					if(((1<<s) & msk) != 0)
						msk &= ~(1<<s); 
					else
						if(((1<<s + S) & msk) != 0)
							msk &= ~(1<<s + S);
				}
			}
			teach = new boolean[M][S];
			cost = new int[M];
			for(int i = 0; i < M; ++i)
			{
				StringTokenizer st = new StringTokenizer(sc.nextLine());
				cost[i] = Integer.parseInt(st.nextToken());
				while(st.hasMoreTokens())
					teach[i][Integer.parseInt(st.nextToken()) - 1] = true;
			}
			memo = new int[M][1<<(S<<1)];
			for(int i = 0; i < M; ++i)
				Arrays.fill(memo[i], -1);
			
			out.println(ini + dp(0, msk));
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
