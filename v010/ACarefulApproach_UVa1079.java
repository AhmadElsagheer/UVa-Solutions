package v010;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ACarefulApproach_UVa1079 {
	
	static final double EPS = 1e-9;
	
	static int n, a[], b[];
	
	static boolean test(int landed, double time, double gap)
	{
		if(landed == (1<<n) - 1)
			return true;
		for(int i = 0; i < n; ++i)
			if((landed & 1<<i) == 0 && time < b[i] + EPS)
				if(test(landed | 1 << i, Math.max(time, a[i]) + gap, gap))
					return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			n = sc.nextInt();
			if(n == 0)
				break;
			a = new int[n]; b = new int[n];
			for(int i = 0; i < n; ++i)
			{
				a[i] = sc.nextInt();
				b[i] = sc.nextInt();
			}
			
			double ans = -1, lo = 0, hi = 200000.0;
			for(int i = 0; i < 100; ++i)
			{
				double gap = (lo + hi) / 2;
				if(test(0, 0, gap / 60.0))
					ans = lo = gap;
				else
					hi = gap;
			}
			int ss = (int) Math.round(ans), mm = ss / 60;
			ss %= 60;
			out.printf("Case %d: %d:%02d\n", tc++, mm, ss);
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