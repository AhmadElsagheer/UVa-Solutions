package v114;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class CountTheFactorials_UVa11415 {

	static final int upperBound = 2703663;		//countFact[upperBound + 1] > 1e7
	static int[] pf;

	static void modifiedSieve(int N)
	{
		pf = new int[N];
		for(int i = 2; i < N; ++i)
			if(pf[i] == 0)
				for(int j = i; j < N; j += i)
				{
					int k = j, e = 0;
					while(k % i == 0) { k /= i; ++e; }
					pf[j] += e;
				}
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		modifiedSieve(upperBound);
		int[] countFact = new int[upperBound];
		for(int i = 2; i < upperBound; ++i)
			countFact[i] = countFact[i-1] + pf[i];

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt(), ans = -1, lo = 0, hi = upperBound - 1;
			while(lo <= hi)
			{
				int mid = lo + hi >> 1;
				if(countFact[mid] > n)
				{
					ans = mid;
					hi = mid - 1;
				}
				else
					lo = mid + 1;
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