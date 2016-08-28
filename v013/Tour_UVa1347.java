package v013;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Tour_UVa1347 {
	
	static double[][] dist, memo;
	static int N;
	
	static double dp(int p1, int p2)
	{
		int cur = Math.max(p1, p2) + 1;
		if(cur == N - 1)
			return dist[p1][cur] + dist[p2][cur];
		if(memo[p1][p2] > -5)
			return memo[p1][p2];
		return memo[p1][p2] = Math.min(dist[p1][cur] + dp(cur, p2), dist[p2][cur] + dp(p1, cur));
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			N = sc.nextInt();
			dist = new double[N][N];
			int[] x = new int[N], y = new int[N];
			for(int i = 0; i < N; ++i)
			{
				x[i] = sc.nextInt();
				y[i] = sc.nextInt();
			}
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					dist[i][j] = dist(x[i], x[j], y[i], y[j]);
			memo = new double[N][N];
			for(int i = 0; i < N; ++i)
				Arrays.fill(memo[i], -10);
			out.printf("%.2f\n", dp(0, 0));
		}
		
		out.flush();
		out.close();
	}
	
	static double dist(int x1, int x2, int y1, int y2)
	{
		return Math.sqrt(sq(x1 - x2) + sq(y1 - y2));
	}
	
	static double sq(double x) { return x * x; }

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){	br = new BufferedReader(r);}

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