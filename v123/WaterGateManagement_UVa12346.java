package v123;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class WaterGateManagement_UVa12346 {

	static final int INF = (int) 1e9;
	static final double EPS = 1e-9;
	static int[] F, C;
	static int N;
	
	static long bt(int idx, double neededFlow)
	{
		if(idx == N)
			return neededFlow < EPS ? 0 : INF;
		return Math.min(bt(idx + 1, neededFlow), C[idx] + bt(idx + 1, neededFlow - F[idx]));
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		N = sc.nextInt();
		F = new int[N];
		C = new int[N];
		for(int i = 0; i < N; ++i)
		{
			F[i] = sc.nextInt();
			C[i] = sc.nextInt();
		}
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			double V = sc.nextInt(), T = sc.nextInt();
			long ans = bt(0, V / T);
			if(ans == INF)
				out.printf("Case %d: IMPOSSIBLE\n", t);
			else
				out.printf("Case %d: %d\n", t, ans);
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