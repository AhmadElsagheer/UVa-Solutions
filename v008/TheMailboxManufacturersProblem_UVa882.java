package v008;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TheMailboxManufacturersProblem_UVa882 {

	static final int INF = (int)1e9;
	static int[][][] memo;
	
	static int minCrackers(int k, int minS, int maxS)	// (mailboxes, minStrength, maxStrength)
	{
		if(minS == maxS)
			return 0;
		if(k == 0)
			return INF;
		if(memo[k][minS][maxS] != -1)
			return memo[k][minS][maxS];
		int ret = INF;
		for(int i = minS + 1; i <= maxS; ++i)
			ret = Math.min(ret, i + Math.max(minCrackers(k - 1, minS, i - 1), minCrackers(k, i, maxS)));
		return memo[k][minS][maxS] = ret;
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		memo = new int[11][101][101];
		for(int i = 0; i <= 10; ++i)
			for(int j = 0; j <= 100; ++j)
				Arrays.fill(memo[i][j], -1);
		int tc = sc.nextInt();
		while(tc-->0)
			out.println(minCrackers(sc.nextInt(), 0, sc.nextInt()));
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