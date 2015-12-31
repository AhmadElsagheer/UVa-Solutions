package cp5_4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Stripe_UVa10541 {
	
	
	static BigInteger[][][] memo;
	static int N, K, code[];
	
	static BigInteger dp(int idx, int num, int acc)
	{
		if(num == K) return BigInteger.ONE;
		if(idx >= N) return BigInteger.ZERO;
		if(memo[idx][num][acc] != null) return memo[idx][num][acc];
		
		BigInteger take = acc + 1 == code[num] ? dp(idx + 2, num + 1, 0) : dp(idx + 1, num, acc + 1);
		BigInteger leave = acc == 0 ? dp(idx + 1, num , acc) : BigInteger.ZERO;
		return memo[idx][num][acc] = take.add(leave);
	}
	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			N = sc.nextInt();
			K = sc.nextInt();
			code = new int[K];
			for(int i = 0; i < K; ++i)
				code[i] = sc.nextInt();
			memo = new BigInteger[N][K][N];
			out.println(dp(0, 0, 0));
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
