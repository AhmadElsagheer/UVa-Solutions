package v003;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class RationalNumbersFromRepeatingFractions_UVa332 {
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			int j = sc.nextInt();
			if(j == -1)
				break;
			String s = sc.next();
			int k = s.length() - 2 - j;
			int num = Integer.parseInt(s.substring(2)) - (k == 0 ? 0 : Integer.parseInt(s.substring(2, 2 + k)));
			int den = (int) Math.pow(10	, k + j) - (int) Math.pow(10, k);
			if(j == 0)
			{
				num = Integer.parseInt(s.substring(2));
				den = (int) Math.pow(10, k);
			}
			
			int gcd = gcd(num, den);
			out.printf("Case %d: %d/%d\n", tc++, num / gcd, den / gcd);
		}
		out.flush();
		out.close();
	}
	
	static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a%b); }

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

		public boolean nextEmpty() throws IOException
		{
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}