package cp5_5;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class LeadingAndTrailing_UVa11029 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt(), k = sc.nextInt();
			double d = Math.log10(n) * k;
			int leading = (int)(Math.pow(10, (d - (int)d)) * 100);
			int trailing = fastExp(n, k, 1000);
			out.printf("%03d...%03d\n", leading, trailing);
		}
		out.flush();
		out.close();
	}
	
	static int fastExp(int b, int e, int m)
	{
		b %= m;
		int res = 1;
		for(int i = 0; i < 30; ++i)
			if((e & 1<<i) != 0)
				res = res * fastExp2(b, i, m) % m;
		return res;
	}
	
	static int fastExp2(int b, int i, int m)
	{
		if(i == 0)
			return b;
		b = fastExp2(b, i - 1, m);
		return b * b % m;
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){    br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){    br = new BufferedReader(r);}

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