package cp4_5;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Arbitrage_UVa104 {
	
	static final double EPS = 1e-9;
	static int N;
	static double[][] conv, memo[];
	
	static double dp(int start, int lst, int convs)
	{
		if(convs == 0)
			return lst == start ? 1.0 : 0.0;
		if(memo[start][lst][convs] > -5)
			return memo[start][lst][convs];
		double ret = 0.0;
		for(int i = 0; i < N; ++i)
			ret = Math.max(ret, conv[lst][i] * dp(start, i, convs - 1));
		return memo[start][lst][convs] = ret;
	}
	
	static StringBuilder sb;
	
	static void print(int start, int lst, int convs)
	{
		if(convs == 0)
			return;
		double opt = dp(start, lst, convs);
		for(int i = 0; i < N; ++i)
			if(Math.abs(opt - conv[lst][i] * dp(start, i, convs - 1)) < EPS)
			{
				sb.append(" " + (i+1));
				print(start, i, convs - 1);
				return;
			}
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			N = sc.nextInt();
			conv = new double[N][N];
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					if(i == j)
						conv[i][j] = 1.0;
					else
						conv[i][j] = sc.nextDouble();
			memo = new double[N][N][N + 1];
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					Arrays.fill(memo[i][j], -10);
			int start = -1, convs = -1;
			for(int i = 1; start == -1 && i <= N; ++i)
				for(int j = 0; j < N; ++j)
				{
					double cur = dp(j, j, i);
					if(cur > 1.01 + EPS)
					{
						start = j;
						convs = i;
						break;
					}
				}
			if(start == -1)
				out.println("no arbitrage sequence exists");
			else
			{
				sb = new StringBuilder();
				sb.append(start + 1);
				print(start, start, convs);
				out.println(sb);
			}
			
		}
		out.flush();
		out.close();

	}
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}
		
		public Scanner(FileReader r) {  br = new BufferedReader(r); }

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