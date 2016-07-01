package v120;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Divisors_UVa12043 {

	static int[] numDiv, sumDiv;
	
	static void modifiedSieve(int N)
	{
		numDiv = new int[N];
		sumDiv = new int[N];
		Arrays.fill(numDiv, 1);
		Arrays.fill(sumDiv, 1);
		for(int i = 2; i < N; ++i)
			if(numDiv[i] == 1)
				for(int j = i; j < N; j += i)
				{
					int e = 0, k = j;
					while(k % i == 0) { k /= i; ++e; }
					numDiv[j] *= e + 1;
					sumDiv[j] *= (int)((pow(i, e + 1) - 1) / (i - 1));
				}
	}
	
	static long pow(long p, int e)
	{
		long res = 1;
		while(e > 0)
		{
			if((e & 1) == 1)
				res *= p;
			p *= p;
			e >>= 1;
		}
		return res;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		modifiedSieve(100001);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int a = sc.nextInt(), b = sc.nextInt(), k = sc.nextInt();
			long x = 0, y = 0;
			while(a <= b)
			{
				if(a % k == 0)
				{
					x += numDiv[a];
					y += sumDiv[a];
				}
				++a;
			}
			out.println(x + " " + y);
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