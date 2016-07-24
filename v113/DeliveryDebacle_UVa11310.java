package v113;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DeliveryDebacle_UVa11310 {
	
	
	static long[][] memo;
	
	static long dp(int idx, int s)
	{
		if(idx == 0) return s == 0 ? 1 : 0;
		
		if(memo[idx][s] != - 1) return memo[idx][s];
		
		long ans = 0;
		switch(s)
		{
		case 0:
			for(int i = 0; i < 4; ++i)
				ans += dp(idx - 1, i)<<(i == 3 ? 1 : 0); break;
		case 1: case 2:
			ans = dp(idx - 1, 3);
		default:
			ans += dp(idx - 1, 0);	
		}
		return memo[idx][s] = ans;
	}
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		memo = new long[41][4];
		for(int i = 0; i < 41; ++i)
			Arrays.fill(memo[i], -1);
		int tc = sc.nextInt();
		while(tc-->0)
			out.println(dp(sc.nextInt(), 0));
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
