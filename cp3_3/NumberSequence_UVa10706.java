package cp3_3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class NumberSequence_UVa10706 {

	static final int upperBound = 100000;
	
	static int countDigits(int x)
	{
		int ans = 0;
		while(x > 0)
		{
			++ans;
			x /= 10;
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int[] sumDigits = new int[upperBound];
		for(int i = 1; i < upperBound; ++i)
			sumDigits[i] = sumDigits[i-1] + countDigits(i);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int s = sc.nextInt(), i = 1;
			while(s > sumDigits[i])
			{
				s -= sumDigits[i];
				++i;
			}
			
			for(i = 1; true; ++i)
			{
				int x = countDigits(i);
				if(s <= x)
					break;
				s -= x;
			}
			
			int j = countDigits(i) - s;
			int ans = 0;
			while(j >= 0)
			{
				ans = i % 10;
				i /= 10;
				--j;
			}
			out.println(ans);
				
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