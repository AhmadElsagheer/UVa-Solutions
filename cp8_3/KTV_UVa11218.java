package cp8_3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KTV_UVa11218 {
	
	static final int INF = 1<<30;
	static int N, score[], comb[], memo[][];
	
	static int dp(int idx, int msk)
	{
		if((1<<9)-1 == msk)
			return 0;
		if(idx == N)
			return -INF;
		if(memo[idx][msk] != -1)
			return memo[idx][msk];
		int take = -INF, leave = dp(idx + 1, msk);
		if((msk & comb[idx]) == 0)
			take = score[idx] + dp(idx + 1, msk | comb[idx]);
		return memo[idx][msk] = Math.max(take, leave);
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int k = 1;
		while(true)
		{
			N = sc.nextInt();
			if(N == 0)
				break;
			score = new int[N];
			comb = new int[N];
			for(int i = 0; i < N; ++i)
			{
				for(int j = 0; j < 3; ++j)
					comb[i] |= 1<<sc.nextInt() - 1;
				score[i] = sc.nextInt();
			}
			memo = new int[N][1<<9];
			for(int i = 0; i < N; ++i)
				Arrays.fill(memo[i], -1);
			int ans = dp(0, 0);
			if(ans < 0)
				ans = -1;
			out.printf("Case %d: %d\n", k++, ans);
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
