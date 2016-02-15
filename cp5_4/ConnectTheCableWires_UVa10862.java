package cp5_4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class ConnectTheCableWires_UVa10862 {

	static int N;
	static BigInteger[][] memo;
	
	static BigInteger dp(int idx, int connected)
	{
		if(idx == N)
			return connected == 1 ? BigInteger.ONE : BigInteger.ZERO;
		if(memo[idx][connected] != null)
			return memo[idx][connected];
		BigInteger ans = dp(idx + 1, 1);
		if(idx != 0)
			ans = ans.add(dp(idx + 1, connected));
		if(connected == 1)
			ans = ans.add(dp(idx + 1, 0));
		return memo[idx][connected] = ans;
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			N = sc.nextInt();
			if(N == 0)
				break;
			memo = new BigInteger[N][2];
			
			out.println(dp(0, 1));
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
