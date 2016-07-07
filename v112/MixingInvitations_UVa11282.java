package v112;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MixingInvitations_UVa11282 {

	static int[][] comb;
	static long[] fact;
	
	static void preprocess(int N)
	{
		fact = new long[N];
		fact[0] = fact[1] = 1;
		for(int i = 2; i < N; ++i)
			fact[i] = i * fact[i-1];
		comb = new int[N][N];
		comb[0][0] = 1;
		for(int i = 1; i < N; ++i)
		{
			comb[i][0] = 1;
			for(int j = 1; j <= i; ++j)
				comb[i][j] = comb[i-1][j-1] + comb[i-1][j];
		}
	}
	
	static long fixedPoints(int N, int K)
	{
		long ans = 0;
		for(int r = 0, s = 1; r <= N - K; ++r, s *= -1)
			ans += s * comb[N][K + r] * fact[N - K - r];
		
		return ans;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		preprocess(21);
		
		while(sc.ready())
		{
			int N = sc.nextInt(), M = sc.nextInt();

			long ans = 0;
			for(int i = 0; i <= M; ++i)
				ans += comb[N][i] * fixedPoints(N - i, 0);
			
			out.println(ans);
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}