package v100;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Marbles_UVa10090 {
	
	static long x, y, d;
	
	static void extendedEuclid(int a, int b)
	{
		if(b == 0) { x = 1; y = 0; d = a; return; }
		extendedEuclid(b, a%b);
		long x1 = y;
		long y1 = x - a / b * y;
		x = x1;	y = y1;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			int c1 = sc.nextInt(), n1 = sc.nextInt(), c2 = sc.nextInt(), n2 = sc.nextInt();
			boolean swapped = false;
			if(c1 < c2)
			{
				swapped = true;
				c1 ^= c2; c2 ^= c1; c1 ^= c2;
				n1 ^= n2; n2 ^= n1; n1 ^= n2;
			}
			
			extendedEuclid(n1, n2);
			if(n % d != 0)
				out.println("failed");
			else
			{
				x *= n / d;
				y *= n / d;
				long k1 = (int)Math.ceil(-x * d * 1.0/ n2), k2 = (int)Math.floor(y * d * 1.0 / n1);

				if(k1 > k2)
					out.println("failed");
				else
				{
					long x1 = x + k1 * n2 / d, y1 = y - k1 * n1 / d;
					long x2 = x + k2 * n2 / d, y2 = y - k2 * n1 / d;

					if(x1 * c1 + y1 * c2 > x2 * c1 + y2 * c2)
					{
						x1 = x2;
						y1 = y2;
					}
					if(swapped)
					{
						x1 ^= y1; y1 ^= x1; x1 ^= y1; 
					}
					out.println(x1 + " " + y1);
				}
			}
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