package v117;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Cantor_UVa11701 {

	static final int factor = (int)1e6;
	static Boolean[] memo = new Boolean[factor * 10];
	
	static boolean hasOne(int x)
	{
		if(memo[x] != null)
			return memo[x];
		memo[x] = false;
		int y = x * 3;
		return memo[x] = (y / factor) == 1 || hasOne(y % factor);
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			String s = sc.next();
			if(s.equals("END"))
				break;
			if(s.length() == 1)
				out.println("MEMBER");
			else
			{
				s = s.substring(2);
				int x = 0, i;
				for(i = 0; i < s.length(); ++i)
					x = x * 10 + s.charAt(i) - '0';
				for(; i < 6; ++i)
					x *= 10;
				out.println(hasOne(x) ? "NON-MEMBER":"MEMBER");
			}
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