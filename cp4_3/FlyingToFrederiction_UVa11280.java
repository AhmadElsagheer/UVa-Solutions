package cp4_3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class FlyingToFrederiction_UVa11280 {

	static final int INF = (int)1e9;
	public static void main(String[] args) throws IOException 
	{
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; t++)
		{
			out.format("Scenario #%d\n", t);
			
			int n = sc.nextInt();
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			int[][] adjMat = new int[n][n];
			for(int i = 0; i < n; i++)
			{
				Arrays.fill(adjMat[i], INF);
				adjMat[i][i] = 0;
				map.put(sc.next(), i);
			}
			
			int m = sc.nextInt();
			while(m-->0)
			{
				int u = map.get(sc.next()), v = map.get(sc.next()), cost = sc.nextInt();
				adjMat[u][v] = Math.min(adjMat[u][v], cost);
			}
			
			int[][] dp = new int[n][n+1];
			for(int i = 0; i < n; i++)
				Arrays.fill(dp[i], INF);
			dp[0][1] = 0;
			for(int i = 0; i < n; i++)
				for(int k = 1; k < n; k++)
					for(int j = 0; j < n; j++)
						if(j != i)
							dp[j][k + 1] = Math.min(dp[j][k + 1], dp[i][k] + adjMat[i][j]);
			
			for(int i = 3; i <= n; i++)
				dp[n-1][i] = Math.min(dp[n-1][i], dp[n-1][i-1]);
			
			int q = sc.nextInt();
			while(q-->0)
			{
				int stops = sc.nextInt();
				if(stops > n - 2) stops = n - 2;
				if(dp[n - 1][stops + 2] == INF)
					out.println("No satisfactory flights");
				else
					out.format("Total cost of flight(s) is $%d\n", dp[n - 1][stops + 2]);
			}
			if(t != tc)
				out.println();
		}
		out.flush();
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

		int countTokens() throws IOException
		{
			st = new StringTokenizer(br.readLine());
			return st.countTokens();
		}
	}
}
