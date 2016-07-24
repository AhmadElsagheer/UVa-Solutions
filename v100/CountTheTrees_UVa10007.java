package v100;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class CountTheTrees_UVa10007 {
	

	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		BigInteger[] fac = new BigInteger[301];
		fac[0] = fac[1] = BigInteger.ONE;
		for(int i = 2; i <= 300; ++i)
			fac[i] = fac[i-1].multiply(BigInteger.valueOf(i));
		BigInteger[] cat = new BigInteger[301];
		cat[0] = cat[1] = BigInteger.ONE;
		for(int i = 2; i <= 300; ++i)
			cat[i] = cat[i-1].multiply(BigInteger.valueOf((i<<1) * ((i<<1) - 1))).divide(BigInteger.valueOf(i * (i + 1)));
		int n;
		while((n = sc.nextInt()) != 0)
			out.println(fac[n].multiply(cat[n]));
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
