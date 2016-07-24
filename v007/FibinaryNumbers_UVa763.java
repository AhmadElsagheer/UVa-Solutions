package v007;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class FibinaryNumbers_UVa763 {
	
	static BigInteger[] fib;
	
	static void compute()
	{
		fib = new BigInteger[500];
		fib[0] = BigInteger.ONE;
		fib[1] = fib[0].add(fib[0]);
		for(int i = 2; i < 500; ++i)
			fib[i] = fib[i-1].add(fib[i-2]);
	}
	
	static int closestFib(BigInteger t)
	{
		int ans = -1, lo = 0, hi = fib.length - 1;
		while(lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;
			if(fib[mid].compareTo(t) <= 0)
			{
				ans = mid;
				lo = mid + 1;
			}
			else
				hi = mid - 1;
		}
		return ans;
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		compute();
		boolean first = true;
		
		while(sc.ready())
		{
			if(first)
				first = false;
			else
				out.println();
			String x = sc.next(), y = sc.next();
			BigInteger op1 = BigInteger.ZERO, op2 = BigInteger.ZERO;
			for(int i = 0; i < x.length(); ++i)
				if(x.charAt(i) == '1')
					op1 = op1.add(fib[x.length() - 1 - i]);
			for(int i = 0; i < y.length(); ++i)
				if(y.charAt(i) == '1')
					op2 = op2.add(fib[y.length() - 1 - i]);
			BigInteger res = op1.add(op2);
			
			byte[] ans = new byte[500]; int end = 0;
			while(!res.equals(BigInteger.ZERO))
			{
				int k = closestFib(res);
				if(end == 0)
					end = k;
				ans[k] = 1;
				res = res.subtract(fib[k]);
			}
			
			StringBuilder sb = new StringBuilder();
			for(int i = end; i >= 0; --i)
				sb.append(ans[i]);
			
			out.println(sb);
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
