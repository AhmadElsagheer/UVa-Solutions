package cp5_4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BinomialTheorem_UVa11955 {
	

	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		long[][] comb = new long[51][51];
		comb[0][0] = 1;
		for(int i = 1; i <= 50; ++i)
		{
			comb[i][0] = 1;
			for(int j = 1; j <= i; ++j)
				comb[i][j] = comb[i-1][j-1] + comb[i-1][j];
		}
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			out.format("Case %d: ", t);
			String[] comp = sc.next().split("\\^");
			comp[0] = comp[0].substring(1, comp[0].length() - 1);
			String[] ops = comp[0].split("\\+");
			
			int n = Integer.parseInt(comp[1]);
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i <= n; ++i)
			{
				long coff = comb[n][i];
				if(coff != 1)
					sb.append(coff).append("*");
				if(i != n)
					sb.append(ops[0]);
				if(i != n && n - i > 1)
					sb.append("^").append(n-i);
				if(i != n && i != 0)
					sb.append("*");
				if(i != 0)
					sb.append(ops[1]);
				if(i != 0 && i > 1)
					sb.append("^").append(i);
				if(i != n)
					sb.append("+");
			}
			out.println(sb);
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
