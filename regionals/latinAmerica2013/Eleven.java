package regionals.latinAmerica2013;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Eleven {
	
	static final int MOD = (int)1e9 + 7;
	static int[] rep;
	static long[][] comb;
	static long memo[][][];
	static int N, maxPos, maxNeg;
	
	static long dp(int idx, int remPos, int remNeg, int mod)
	{
		if(idx == 10)
			return mod == 0 ? 1 : 0;
		if(memo[idx][remPos][mod] != -1)
			return memo[idx][remPos][mod];
		long count = 0;
		for(int k = 0; k <= rep[idx]; ++k)
			if(k <= remPos && rep[idx] - k <= remNeg)
			{
				int nk = rep[idx] - k;
				if(idx == 0)
					count = (count + (remPos == 0 ? 0 : (comb[remPos - 1][k] * comb[remNeg][nk] %MOD * dp(idx + 1, remPos - k, remNeg - nk, fix(mod + (k - nk) * idx, 11))%MOD)))%MOD;
				else
					count = (count + comb[remPos][k] * comb[remNeg][nk] %MOD * dp(idx + 1, remPos - k, remNeg - nk, fix(mod + (k - nk) * idx, 11))%MOD)%MOD;
			}
		
		return memo[idx][remPos][mod] = count;
		
	}
	
	static int fix(int x, int mod)
	{
		while(x < 0)
			x += mod;
		return x%mod;
	}
	static void compute()
	{
		comb = new long[101][101];
		comb[0][0] = 1;
		for(int i = 1; i <= 100; ++i)
		{
			comb[i][0] = 1;
			for(int j = 1; j <= i; ++j)
				comb[i][j] = (comb[i-1][j-1] + comb[i-1][j])%MOD;
		}
				
	}
	
	public static void main(String[] args) throws IOException {
		
		compute();
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(sc.ready())
		{
			String line = sc.next();
			N = line.length();
			rep = new int[10];
			for(int i = 0; i < N; ++i)
				rep[line.charAt(i) - '0']++;
			maxPos = (N + 1)>>1;
			maxNeg = N>>1;
		memo = new long[10][maxPos + 1][11];
		for(int i = 0; i < 10; ++i)		
			for(int j = 0; j < maxPos + 1; ++j)
					Arrays.fill(memo[i][j], -1);
		
		out.println(dp(0, maxPos, maxNeg, 0));
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
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
		
		public boolean ready() throws IOException {return br.ready();}


	}

}
