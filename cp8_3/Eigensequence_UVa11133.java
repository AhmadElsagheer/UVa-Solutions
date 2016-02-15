package cp8_3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Eigensequence_UVa11133 {
	
	static long[][] memo;
	static int end;
	
	static long dp(int last, int cur)
	{
		if(cur == end)
			return possible(last, cur) ? 1 : 0;
		if(memo[last][cur] != -1)
			return memo[last][cur];
		memo[last][cur] = dp(last, cur + 1);
		if(possible(last, cur))
			memo[last][cur] += dp(cur, cur + 1);
		return memo[last][cur];
	}
	
	static boolean possible(int x, int y)
	{
		int d = y - x;
		for(int i = x + 1; i < y; ++i)
			if(i%d == 0)
				return false;
		return y%d == 0;
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			int s = sc.nextInt(), e = sc.nextInt();
			if(s >= e)
				break;
			end = e;
			memo = new long[e][e];
			for(int i = 0; i < e; ++i)
				Arrays.fill(memo[i], -1);
			out.format("%d %d %d\n", s, e, dp(s, s + 1));
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

		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
