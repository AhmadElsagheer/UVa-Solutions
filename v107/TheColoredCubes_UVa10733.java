package v107;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class TheColoredCubes_UVa10733 {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) break;
			BigInteger ans = BigInteger.valueOf(n);
			ans = ans.pow(6).add(ans.pow(4).multiply(BigInteger.valueOf(3))).add(ans.pow(3).multiply(BigInteger.valueOf(12))).add(ans.pow(2).multiply(BigInteger.valueOf(8))).divide(BigInteger.valueOf(24));
			out.println(ans);
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
