package v107;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class RiemannVsMertens_UVa10738 {
	
	static final int upperBound = (int)1e6;

	static int[] modifiedSieve(int N)
	{
		boolean[] squareFree = new boolean[N];
		Arrays.fill(squareFree, true);
		int[] pf = new int[N];
		for(int i = 2; i < N; ++i)
			if(pf[i] == 0)
			{
				pf[i] = 1;
				for(int j = 2; i * j < N; ++j)
				{
					if(j % i == 0)
						squareFree[i * j] = false;
					int e = 0, k = i * j;
					while(k % i == 0) { k /= i; ++e; }
					pf[i * j] += e;
				}
			}
		int[] mu = new int[N];
		mu[1] = 1;
		for(int i = 2; i < N; ++i)
			if(squareFree[i])
				if((pf[i] & 1) == 0)
					mu[i] = 1;
				else
					mu[i] = -1;
		return mu;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int[] mu = modifiedSieve(upperBound + 1);
		int[] M = new int[upperBound + 1];
		for(int i = 1; i <= upperBound; ++i)
			M[i] = M[i-1] + mu[i];
		while(true)
		{
			int N = sc.nextInt();
			if(N == 0)
				break;
			out.printf("%8d%8d%8d\n", N, mu[N], M[N]);
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