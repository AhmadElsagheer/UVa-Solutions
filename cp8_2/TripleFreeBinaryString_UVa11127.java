package cp8_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TripleFreeBinaryString_UVa11127 {

	static char[] input;
	static int n;
	
	static int generate(int idx)
	{
		if(idx == n)
			return 1;
		char c = input[idx];
		int ret = 0;
		if(c != '0')
		{
			input[idx] = '1';
			if(possible(idx))
				ret += generate(idx + 1);
		}
		
		if(c != '1')
		{
			input[idx] = '0';
			if(possible(idx))
				ret += generate(idx + 1);
		}
		input[idx] = c;
		return ret;
	}
	
	static boolean possible(int idx)
	{
		for(int len = 1; idx + 1 >= len * 3; ++len)
		{
			boolean match = true;
			for(int i = idx - len + 1; match && i <= idx; ++i)
				if(input[i] != input[i-len] || input[i] != input[i-(len<<1)])
					match = false;
			if(match)
				return false;
		}
		return true;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = 1;
		while(true)
		{
			n = sc.nextInt();
			if(n == 0)
				break;
			input = sc.next().toCharArray();
			out.format("Case %d: %d\n", tc++, generate(0));
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
