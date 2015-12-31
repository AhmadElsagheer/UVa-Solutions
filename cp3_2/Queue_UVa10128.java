package cp3_2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Queue_UVa10128 {
	
	static int[][] comb;
	static long[][][] memo;
	static void compute()
	{
		comb = new int[13][13];
		comb[0][0] = 1;
		for(int i = 1; i < 13; ++i)
		{
			comb[i][0] = 1;
			for(int j = 1; j <= i; ++j)
				comb[i][j] = comb[i-1][j] + comb[i-1][j-1];
		}
		
		memo = new long[1<<13][13][13];
		for(int i = 0; i < 1<<13; ++i)
			for(int j = 0; j < 13; ++j)
				Arrays.fill(memo[i][j], -1);
		
	}
	
	static long dp(int msk, int lst, int remL)
	{
		if(remL < 0)
			return 0;
		if(msk == 0)
			return remL == 0 ? 1 : 0;
		if(memo[msk][lst][remL] != -1)
			return memo[msk][lst][remL];
		int count = 0;
		for(int i = 0; i < 13; ++i)
			if(((1<<i) & msk) != 0)
				if(i + 1 > lst)
					count += dp(msk & ~(1<<i), i + 1, remL - 1);
				else
					count += dp(msk & ~(1<<i), lst, remL);
		return memo[msk][lst][remL] = count;
	}
	
	static long go(int N, int L, int R)
	{
		long c = 0;
		for(int k = L; k + R <= N; ++k)
			c += (long)comb[N][k] * dp((1<<k) - 1, 0, L) * dp((1<<N - k) - 1, 0, R);
		return c;
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		compute();
		
		int tc = sc.nextInt();
		while(tc-->0)
			out.println(go(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt() - 1));
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
