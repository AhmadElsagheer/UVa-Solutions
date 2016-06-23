package cp5_4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Miles2Km_UVa11780 {
	
	static int[] fib;
	static double[] memo;
	
	static double findBest(int N)
	{
		if(N == 1)
			return 2;
		if(memo[N] > -5)
			return memo[N];
		double exact = 1.6 * N, best = 0;
		for(int i = 2; fib[i] <= N; ++i)
		{
			double cur = (fib[i + 1] + findBest(N - fib[i]));
			if(Math.abs(exact - cur) < Math.abs(exact - best))
				best = cur;
		}
		return memo[N] = best;
				
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		fib = new int[21];
		fib[0] = 0;
		fib[1] = 1;
		for(int i = 2; i <= 20; ++i)
			fib[i] = fib[i-1] + fib[i-2];
		memo = new double[1001];
		Arrays.fill(memo, -10);
		while(true)
		{
			int N = sc.nextInt();
			if(N == 0)
				break;
			out.printf("%.2f\n", Math.abs(1.6 * N - findBest(N)));
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

		public boolean nextEmpty() throws IOException
		{
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}