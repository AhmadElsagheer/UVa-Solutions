package v100;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;
 
 
public class LeapYearOrNotLeapYearAnd_UVa10070 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		BigInteger[] mods = {
			BigInteger.valueOf(4),
			BigInteger.valueOf(100),
			BigInteger.valueOf(400),
			BigInteger.valueOf(15),
			BigInteger.valueOf(55),
				
		};
		boolean first = true;
		while(sc.ready())
		{
			if(first)
				first = false;
			else
				out.println();
			BigInteger n = new BigInteger(sc.next());
			boolean ord = true, leap = false;
			if(n.mod(mods[0]).equals(BigInteger.ZERO) && !n.mod(mods[1]).equals(BigInteger.ZERO) 
					|| n.mod(mods[2]).equals(BigInteger.ZERO))
			{
				out.println("This is leap year.");
				leap = true;
				ord = false;
			}
			if(n.mod(mods[3]).equals(BigInteger.ZERO))
			{
				out.println("This is huluculu festival year.");
				ord = false;
			}
			if(leap && n.mod(mods[4]).equals(BigInteger.ZERO))
			{
				out.println("This is bulukulu festival year.");
				ord = false;
			}
			if(ord)
				out.println("This is an ordinary year.");
		}
		out.flush();
		out.close();
	}
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;
 
		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}
		
		public Scanner(FileReader s){	br = new BufferedReader(s);}
 
		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}
 
		public int nextInt() throws IOException {return Integer.parseInt(next());}
 
		public long nextLong() throws IOException {return Long.parseLong(next());}
 
		public String nextLine() throws IOException {return br.readLine();}
 
		public double nextDouble() throws IOException
		{
			return Double.parseDouble(next());
		}
 
		public boolean ready() throws IOException {return br.ready();}
 
 
	}
} 