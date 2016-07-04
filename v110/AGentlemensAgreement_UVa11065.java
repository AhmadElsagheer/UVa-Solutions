package v110;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class AGentlemensAgreement_UVa11065 {

	static long[] adjMat;
	static int N, ans, max;

	static void dfs(int u, int cur, long msk)
	{
		if(u == N)
		{
			if(Long.bitCount(msk) == N)
			{
				++ans;
				max = Math.max(max, cur);				
			}
			return;
		}
		
		if((msk & 1l<<u) == 0)
			dfs(u + 1, cur + 1, msk | adjMat[u]);
		dfs(u + 1, cur, msk);
		
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			N = sc.nextInt();
			adjMat = new long[N];
			for(int i = 0; i < N; ++i)
				adjMat[i] = 1l<<i;
			int E = sc.nextInt();
			while(E-->0)
			{
				int u = sc.nextInt(), v = sc.nextInt();
				adjMat[u] |= 1l<<v;
				adjMat[v] |= 1l<<u;
			}
			ans = 0; max = 0;
			dfs(0, 0, 0);
			out.printf("%d\n%d\n", ans, max);
		}
		out.flush();
		out.close();
	}

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