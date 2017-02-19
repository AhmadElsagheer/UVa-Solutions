package v113;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SternBrocotTree_UVa11350 {
	
	public static void main(String[] args) throws IOException {
		
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		Frac begin = new Frac(0, 1), end = new Frac(1, 0);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			String line = sc.next();
			Frac left = begin, right = end;
			for(int i = 0; i < line.length(); ++i)
			{
				Frac mid = mid(left, right);
				if(line.charAt(i) == 'L')
					right = mid;
				else
					left = mid;
			}
			out.println(mid(left, right));
		}
		out.flush();

	}
	
	static Frac mid(Frac x, Frac y)
	{
		long a = x.num + y.num, b = x.den + y.den, gcd = gcd(a, b);
		return new Frac(a / gcd, b / gcd);
	}
	
	static long gcd(long a, long b) { return b == 0 ? a : gcd(b, a%b); }
	static class Frac
	{
		long num, den;
		
		Frac(long x, long y) { num = x; den = y; }
		
		public String toString()
		{
			return num + "/" + den;
		}
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
