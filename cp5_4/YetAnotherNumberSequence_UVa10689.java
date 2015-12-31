package cp5_4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class YetAnotherNumberSequence_UVa10689 {
	
	static BigInteger[] fib = new BigInteger[15001];
	
	static void compute()
	{
		fib[0] = BigInteger.ZERO;
		fib[1] = BigInteger.ONE;
		for(int i = 2; i <= 15000; i++)
			fib[i] = fib[i-1].add(fib[i-2]);
	}
	public static void main(String[] args) throws IOException {

		compute();
		int[] period = new int[]{60, 300, 1500, 15000};
		int[] mod = new int[]{10, 100, 1000, 10000};
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int a = sc.nextInt(), b = sc.nextInt(), n = sc.nextInt(), m = sc.nextInt() - 1;
			BigInteger ans = fib[(n-1)%period[m]].multiply(BigInteger.valueOf(a%mod[m])).add(fib[n%period[m]].multiply(BigInteger.valueOf(b%mod[m])));
			out.println(ans.mod(BigInteger.valueOf(mod[m])));
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
