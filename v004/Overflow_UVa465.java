package v004;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Overflow_UVa465 {
	
	static final BigInteger INF = BigInteger.valueOf(Integer.MAX_VALUE);
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			String line = sc.nextLine();
			out.println(line);
			StringTokenizer st = new StringTokenizer(line);
			String x1 = st.nextToken(), op = st.nextToken(), x2 = st.nextToken();
			BigInteger bx1 = new BigInteger(x1), bx2 = new BigInteger(x2);
			BigInteger r = op.equals("+") ? bx1.add(bx2) : bx1.multiply(bx2);
			if(bx1.compareTo(INF) > 0)
				out.println("first number too big");
			if(bx2.compareTo(INF) > 0)
				out.println("second number too big");
			if(r.compareTo(INF) > 0)
				out.println("result too big");
		}
		out.flush();
		out.close();
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();} 
	}
} 