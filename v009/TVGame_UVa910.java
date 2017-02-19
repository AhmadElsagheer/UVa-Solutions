package v009;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TVGame_UVa910 {

	static boolean[] special;
	static int[][] next, memo;
	
	static int dp(int node, int moves)
	{
		if(moves == 0)
			return special[node] ? 1 : 0;
		if(memo[node][moves] != -1)
			return memo[node][moves];
		return memo[node][moves] = dp(next[node][0], moves - 1) + dp(next[node][1], moves - 1); 
	}
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(sc.ready())
		{
			int n = sc.nextInt();
			next = new int[26][2];
			special = new boolean[26];
			for(int i = 0; i < n; ++i)
			{
				int cur = sc.next().charAt(0) - 'A';
				next[cur][0] = sc.next().charAt(0) - 'A';
				next[cur][1] = sc.next().charAt(0) - 'A';
				special[cur] = sc.next().charAt(0) == 'x' ? true : false;
			}
			memo = new int[26][31];
			for(int i = 0; i < 26; ++i)
				Arrays.fill(memo[i], -1);
			out.println(dp(0, sc.nextInt()));
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
