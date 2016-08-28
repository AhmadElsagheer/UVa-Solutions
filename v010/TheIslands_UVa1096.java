package v010;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;


public class TheIslands_UVa1096 {

	static final double EPS = 1e-9;
	static int N, b1, b2;
	static double[][] dist;
	static double[][][][] memo;
	
	static double dp(int v, int lst1, int lst2, int cur)
	{
		if(cur == N - 1)
			return dist[cur][lst1] + dist[cur][lst2];
		if(memo[v][lst1][lst2][cur] > -5)
			return memo[v][lst1][lst2][cur];
		double ret = 1e16;
		//visit in 1st or 2nd only
		if(cur == b1 || cur == b2)
		{
			if((v & 1) == 0)
				ret = dp(v | 1, cur, lst2, cur + 1) + dist[lst1][cur];
			if((v & 2) == 0)
				ret = Math.min(ret, dp(v | 2, lst1, cur, cur + 1) + dist[lst2][cur]);
		}
		else
			ret = Math.min(dp(v, cur, lst2, cur + 1) + dist[lst1][cur], dp(v, lst1, cur, cur + 1) + dist[lst2][cur]);
		return memo[v][lst1][lst2][cur] = ret;
	}
	
	static Stack<Integer> s1, s2;
	
	static void print(int v, int lst1, int lst2, int cur)
	{
		if(cur == N - 1)
		{
			s1.push(cur);
			return;
		}
		double opt = dp(v, lst1, lst2, cur);
		if(cur == b1 || cur == b2)
		{
			if((v & 1) == 0 && Math.abs(dp(v | 1, cur, lst2, cur + 1) + dist[lst1][cur] - opt) < EPS)
			{
				s1.push(cur);
				print(v | 1, cur, lst2, cur + 1);
			}
			else
			{
				s2.push(cur);
				print(v | 2, lst1, cur, cur + 1);
			}
		}
		else if(Math.abs(dp(v, cur, lst2, cur + 1) + dist[lst1][cur] - opt) < EPS)
		{
			s1.push(cur);
			print(v, cur, lst2, cur + 1);
		}
		else
		{
			s2.push(cur);
			print(v, lst1, cur, cur + 1);
		}
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = 1;
		while(true)
		{
			N = sc.nextInt();
			b1 = sc.nextInt();
			b2 = sc.nextInt();
			if(N == 0)
				break;
			dist = new double[N][N];
			int[] x = new int[N], y = new int[N];
			for(int i = 0; i < N; ++i)
			{
				x[i] = sc.nextInt();
				y[i] = sc.nextInt();
			}
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					dist[i][j] = dist(x[i], y[i], x[j], y[j]);
			memo = new double[4][N][N][N];
			for(int i = 0; i < 4; ++i)
				for(int j = 0; j < N; ++j)
					for(int k = 0; k < N; ++k)
						Arrays.fill(memo[i][j][k], -10);
			out.printf("Case %d: %.2f\n", tc++, dp(0, 0, 0, 1));
			s1 = new Stack<Integer>();
			s2 = new Stack<Integer>();
			s1.push(0);
			s2.push(0);
			print(0, 0, 0, 1);
			while(!s1.isEmpty())
				s2.push(s1.pop());
			while(!s2.isEmpty())
				out.printf("%d%c", s2.pop(), s2.isEmpty() ? '\n' : ' ');
		}
		out.flush();
		out.close();
	}
	
	static double dist(int x1, int y1, int x2, int y2)
	{
		return Math.sqrt(sq(x1 - x2) + sq(y1 - y2));
	}
	
	static int sq(int x) { return x * x; }
	
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