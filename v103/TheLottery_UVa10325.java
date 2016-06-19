package v103;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class TheLottery_UVa10325 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			int N = sc.nextInt(), M = sc.nextInt();
			int[] a = new int[M];
			for(int i = 0; i < M; ++i)
				a[i] = sc.nextInt();
			long ans = N;
			for(int i = 1; i < 1<<M; ++i)
			{
				long p = 1;
				for(int j = 0; p <= N && j < M; ++j)
					if((i & 1<<j) != 0)
						p = lcm(p, a[j]);
				if(Integer.bitCount(i)%2 == 1)
					ans -= N / p;
				else
					ans += N / p;
			}
			out.println(ans);
		}
		out.flush();
		out.close();
	}
	
	static long gcd(long a, long b) { return b == 0 ? a : gcd(b, a%b); }
	
	static long lcm(long a, long b) { return a / gcd(a, b) * b; }

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){ br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){ br = new BufferedReader(r);}

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