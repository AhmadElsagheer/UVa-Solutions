package cp5_4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ExpressionBracketing_UVa10312 {
	
	static long[][] f;
	
	static long dp(int idx, int n)
	{
		if(n - idx == 1 || idx == n || n <= 2)
			return 1;
		if(f[idx][n] != -1)
			return f[idx][n];
		long c = 0;
		for(int k = 1; idx + k < n; ++k)
			c += dp(0, k) * dp(idx + k, n);
		if(idx != 0)
			c += dp(0, n - idx);
		return f[idx][n] = c;
		
	}
	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		f = new long[27][27];
		for(int i = 0; i < 27; ++i)
			Arrays.fill(f[i], -1);
		
		
		long[] cat = new long[27];
		cat[0] = 1;
		for(int i = 1; i <= 26; ++i)
			cat[i] = cat[i-1] * (i<<1) * ((i<<1) - 1) / (i * (i + 1));

		while(sc.ready())
		{
			int n = sc.nextInt();		
			out.println(dp(0, n) - cat[n-1]);
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
