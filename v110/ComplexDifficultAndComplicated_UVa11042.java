package v110;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class ComplexDifficultAndComplicated_UVa11042 {
	
	static final long limit = 1l<<60;
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			Complex c = new Complex(sc.nextInt(), sc.nextInt());
			int N = c.maxPower(), ans = 1;
			
			Complex cur = c;
			while(!cur.isReal() && ans < N)
			{
				++ans;
				cur = cur.mult(c);
			}
			if(cur.isReal())
				out.println(ans);
			else
				out.println("TOO COMPLICATED");
		}
		out.flush();
		out.close();
	}
	
	static class Complex
	{
		int a, b;
		
		Complex(int x, int y) { a = x; b = y; }
		
		boolean isReal() { return b == 0; }
		
		Complex mult(Complex c)
		{
			return new Complex(a * c.a - b * c.b, a * c.b + b * c.a);
		}
		
		int maxPower()
		{
			int abs2 = a * a + b * b, res = 0;
			if(abs2 <= 1)
				return 2;
			long p = 1;
			while(p <= limit / abs2)
			{
				p *= abs2;
				++res;
			}
			return res;
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

		public double nextDouble() throws IOException
		{
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}

		public boolean ready() throws IOException {return br.ready();}


	}
}