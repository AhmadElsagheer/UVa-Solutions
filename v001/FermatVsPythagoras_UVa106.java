package v001;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FermatVsPythagoras_UVa106 {

	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			int N = sc.nextInt(), sqrt = (int)Math.sqrt(N), t = 0, p = 0;
			boolean[] used = new boolean[N + 1];
			for(int n = 1; n <= sqrt; ++n)
				for(int m = n + 1; m <= sqrt; ++m)
				{
					int x = 2 * n * m;
					int y = m * m - n * n;
					int z = m * m + n * n;
					if(x > N || y > N || z > N)
						continue;

					for(int k = 1; k * x <= N && k * y <= N && k * z <= N; ++k)
						used[k * x] = used[k * y] = used[k * z] = true;
					if(gcd(n, m) == 1 && (m - n)%2 == 1)
						++t;
				}
			
			for(int i = 1; i <= N; ++i)
				if(!used[i])
					++p;
			out.println(t + " " + p);
		}
		out.flush();
		out.close();
	}

	static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a%b); }

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