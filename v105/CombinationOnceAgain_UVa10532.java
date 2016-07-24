package v105;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class CombinationOnceAgain_UVa10532 {

	static BigInteger[][] memo;
	static int N, f[];
	
	static BigInteger dp(int idx, int r)
	{
		if(r == 0)
			return BigInteger.ONE;
		if(idx == N)
			return BigInteger.ZERO;
		if(memo[idx][r] != null)
			return memo[idx][r];
		BigInteger ret = BigInteger.ZERO;
		for(int k = 0; k <= f[idx] && k <= r; ++k)
			ret = ret.add(dp(idx + 1, r - k));
		return memo[idx][r] = ret;
			
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = 1;
		while(true)
		{
			N = sc.nextInt();
			int q = sc.nextInt();
			if(N == 0)
				break;
			f = new int[N];
			for(int i = 0; i < N; ++i)
				f[sc.nextInt() - 1]++;
			memo = new BigInteger[N][N + 1];
			out.format("Case %d:\n", tc++);
			while(q-->0)
				out.println(dp(0, sc.nextInt()));
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

		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
