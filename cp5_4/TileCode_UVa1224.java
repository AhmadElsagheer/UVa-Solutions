package cp5_4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TileCode_UVa1224 {

	static int[] memo;
	
	static int dp(int n)
	{
		if(n == 0)
			return 1;
		if(memo[n] != -1)
			return memo[n];
		return memo[n] = dp(n - 1) + (n > 1 ? dp(n - 2)<<1 : 0);
	}
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		memo = new int[31];
		Arrays.fill(memo, -1);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt();
			out.println((dp(n) + (n%2 == 0 ? dp((n>>1) + 1): dp(n>>1)))>>1);
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
