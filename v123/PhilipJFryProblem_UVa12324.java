package v123;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PhilipJFryProblem_UVa12324 {
		
	static final int INF = (int)1e8;
	static int N, t[], b[], memo[][];
	
	static int dp(int idx, int spheres)
	{
		if(idx == N)
			return 0;
		if(memo[idx][spheres] != -1)
			return memo[idx][spheres];
		int leave = t[idx] + dp(idx + 1, Math.min(N, spheres + b[idx])), put = INF;
		if(spheres > 0)
			put = (t[idx]>>1) + dp(idx + 1, Math.min(N, spheres + b[idx] - 1));
		return memo[idx][spheres] = Math.min(leave, put);
	}
	public static void main(String[] args) throws IOException {

		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while((N = sc.nextInt()) != 0)
		{
			t = new int[N];
			b = new int[N];
			for(int i = 0; i < N; ++i)
			{
				t[i] = sc.nextInt();
				b[i] = sc.nextInt();
			}
			memo = new int[N][N + 1];
			for(int i = 0; i < N; ++i)
				Arrays.fill(memo[i], -1);
			out.println(dp(0, 0));
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
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
