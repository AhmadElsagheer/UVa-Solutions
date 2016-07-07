package v002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BudgetTravel_UVa222 {

	static final int INF = (int)1e9;
	static final double EPS = 1e-9;
	static double D, C, MPG;
	static double[] pos, price;
	static int S, memo[];
	
	static int dp(int curStop)	// 0 (org), 1, 2, 3, ...., S
	{
		if(memo[curStop] != -1)
			return memo[curStop];
		
		double minDist = pos[curStop] + C * 0.5 * MPG;
		double maxDist = pos[curStop] + C * MPG;
		if(D < maxDist + EPS)
			return memo[curStop] = 0;
		
		int ret = INF;
		for(int nxtStop = S; nxtStop > curStop; --nxtStop)
		{
			double d = pos[nxtStop];
			if(minDist < d + EPS && d + EPS < maxDist || ret == INF && d + EPS < minDist)
			{
				d -= pos[curStop];
				int cost = (int) (200 + Math.round(d / MPG * price[nxtStop]));
				ret = Math.min(ret, cost + dp(nxtStop));
			}
		}
		return memo[curStop] = ret;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			D = sc.nextDouble();
			if(D < 0)
				break;
			C = sc.nextDouble();
			MPG = sc.nextDouble();
			int cost = (int) (sc.nextDouble() * 100);
			S = sc.nextInt();
			pos = new double[S + 1];
			price = new double[S + 1];
			for(int i = 1; i <= S; ++i)
			{
				pos[i] = sc.nextDouble();
				price[i] = sc.nextDouble();
			}
			memo = new int[S + 1];
			Arrays.fill(memo, -1);
			out.printf("Data Set #%d\nminimum cost = $%.2f\n", tc++, (dp(0) + cost)/100.0);
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}