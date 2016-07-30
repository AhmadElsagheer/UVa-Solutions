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
	static final BigInteger bigMod = BigInteger.valueOf(mod);
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{	
			long res = BigInteger.valueOf(3).modPow(new BigInteger(sc.next()), bigMod).longValue();
			out.printf("Case %d: %d\n", t, (res + 1) % mod * 500000004 % mod);	
		}
		out.flush();
		out.close();
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