package v118;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class CheerLeaders_UVa11806 {
	
	static final int mod = (int)1e6 + 7;
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int[][] comb = new int[401][401];
		comb[0][0] = 1;
		for(int i = 1; i <= 400; ++i)
		{
			comb[i][0] = 1;
			for(int j = 1; j <= i; ++j)
				comb[i][j] = (comb[i-1][j] + comb[i-1][j-1])%mod;
		}
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int N = sc.nextInt(), M = sc.nextInt(), K = sc.nextInt();
			if(K > 400)
			{
				out.printf("Case %d: 0\n", t);
				continue;
			}
			int ans = 0;
			int[] c = new int[] { N, M, N, M };
			for(int i = 0; i < 1<<4; ++i)
			{
				int excluded = 0, ver = 0, hor = 0;
				for(int j = 0; j < 4; ++j)
					if((i & 1<<j) != 0)
					{
						if(j%2 == 0)
							++ver;
						else
							++hor;
						excluded += c[j];
					}
				excluded -= ver * hor;
				int cur = comb[N * M - excluded][K];
				if(Integer.bitCount(i)%2 == 0)
					ans = add(ans, cur);
				else
					ans = add(ans, -cur);
			}
			out.printf("Case %d: %d\n", t, ans);			
		}
		out.flush();
		out.close();
	}
	
	static int add(int x, int y) { return (x + y + mod) % mod; }

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