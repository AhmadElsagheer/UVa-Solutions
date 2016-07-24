package v009;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BrickWallPatterns_UVa900 {

	static long[] memo = new long[51];
	
	static long dp(int n)
	{
		if(n == 0)
			return 1;
		if(memo[n] != -1)
			return memo[n];
		return memo[n] = dp(n - 1) + (n > 1 ? dp(n - 2) : 0);
	}
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		Arrays.fill(memo, -1);
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			out.println(dp(n));
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
			
		public boolean ready() throws IOException {return br.ready(); }


	}
}
