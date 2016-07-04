package v113;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class NumberTheoryForNewbies_UVa11371 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			int n = sc.nextInt();
			long a = getLargest(freq(n)), b = getSmallest(freq(n));
			out.printf("%d - %d = %d = 9 * %d\n", a, b, a - b, (a - b) / 9);
		}
		
		out.flush();
		out.close();
	}
	
	static int[] freq(int x)
	{
		int[] f = new int[10];
		while(x > 0)
		{
			f[x%10]++;
			x /= 10;
		}
		return f;
	}
	
	static long getLargest(int[] f)
	{
		long x = 0;
		for(int i = 9; i >= 0; --i)
			while(f[i]-->0)
				x = x * 10 + i;
		return x;
	}
	
	static long getSmallest(int[] f)
	{
		long x = 0;
		for(int i = 1; i <= 9; ++i)
			if(f[i] > 0)
			{
				f[i]--;	x = i; break;
			}
		
		for(int i = 0; i <= 9; ++i)
			while(f[i]-->0)
				x = x * 10 + i;
		return x;
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