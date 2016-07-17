package v115;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class FewestFlops_UVa11552 {
	
	static final int INF = (int)1e9;
	static int[] chunks, f[], memo[];
	static int N;
	
	static int dp(int c, int idx)
	{
		if(idx == N)
			return 0;
		if(memo[c][idx] != -1)
			return memo[c][idx];
		int ret = INF, minus = f[c][idx] != 0 ? 1 : 0, chs = chunks[idx];
		for(int i = 0; i < 26; ++i)
			if(f[i][idx] != 0)
				ret = Math.min(ret, chs - (chs > 1 && i == c || chs == 1 && i != c ? 0 : minus) + dp(i, idx + 1));
		return memo[c][idx] = ret;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			 int k = sc.nextInt();
			 String s = sc.next();
			 N = s.length() / k;
			 f = new int[27][N];
			 for(int i = 0; i < N; ++i)
				 for(int j = 0; j < k; ++j)
					 f[s.charAt(i * k + j)-'a'][i]++;
			 chunks = new int[N];
			 for(int i = 0; i < N; ++i)
			 {
				 int count = 0;
				 for(int j = 0; j < 26; ++j)
					 if(f[j][i] != 0)
						 ++count;
				 chunks[i] = count;
			 }
			 
			 memo = new int[27][N];
			 for(int i = 0; i < 27; ++i)
				 Arrays.fill(memo[i], -1);
			 out.println(dp(26, 0));
		}
		out.flush();
		out.close();
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){    br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){    br = new BufferedReader(r);}

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