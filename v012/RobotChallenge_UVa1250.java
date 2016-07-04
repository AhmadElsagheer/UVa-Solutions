package v012;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RobotChallenge_UVa1250 {

	static Target[] targets;
	static int N;
	static double[][] memo;
	
	static double dp(int lst, int cur)
	{
		if(cur == N + 1)
			return targets[lst].dist(targets[N + 1]) + 1;
		if(memo[lst][cur] > -5)
			return memo[lst][cur];
		
		double take = targets[cur].dist(targets[lst]) + 1 + dp(cur, cur + 1);
		double leave = targets[cur].p + dp(lst, cur + 1);
		return memo[lst][cur] = Math.min(take, leave);
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(true)
		{
			N = sc.nextInt();
			if(N == 0)
				break;
			targets = new Target[N + 2];
			targets[0] = new Target(0, 0, 0);
			targets[N + 1] = new Target(100, 100, 0);
			for(int i = 1; i <= N; ++i)
				targets[i] = new Target(sc.nextInt(), sc.nextInt(), sc.nextInt());
			
			memo = new double[N + 1][N + 1];
			for(int i = 0; i < N; ++i)
				Arrays.fill(memo[i], -10);
			out.printf("%.3f\n", dp(0, 1));
		}
		out.flush();
		out.close();
	}
	
	static class Target
	{
		int x, y, p;
		
		Target(int a, int b, int c) { x = a; y = b; p = c; } 
		
		double dist(Target t) { return Math.sqrt(sq(x - t.x) + sq(y - t.y)); }
		
		double sq(double x) { return x * x; }
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