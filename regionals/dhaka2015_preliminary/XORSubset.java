package regionals.dhaka2015_preliminary;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;


public class XORSubset {
	
	static final int mod = (int)1e9 + 7;
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
			out.printf("Case %d: %d\n", t, pow(new BigInteger(sc.next())));
		out.flush();
		out.close();
	}
	
	static int pow(BigInteger e)
	{
	
		long res = 1;
		long b = 3;
		while(e.compareTo(BigInteger.ZERO) > 0)
		{
			if(e.testBit(0))
				res = res * b % mod;
			b = b * b % mod;
			e = e.shiftRight(1);
		}
		res = (res + 1) % mod;
		res = res * 500000004 % mod;
		return (int) res;
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){    br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){    br = new BufferedReader(r);}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine(), " |(|)|:");
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		boolean ready() throws IOException { return br.ready(); }
		
		boolean hasNext() throws IOException
		{
			while (br.ready() && (st == null || !st.hasMoreTokens())) 
				st = new StringTokenizer(br.readLine(), " |(|)|:");
			return st != null && st.hasMoreTokens();
		}
	}
}