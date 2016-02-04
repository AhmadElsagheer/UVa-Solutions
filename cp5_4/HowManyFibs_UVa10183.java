package cp5_4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class HowManyFibs_UVa10183 {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		ArrayList<BigInteger> f = new ArrayList<BigInteger>(500);
		f.add(BigInteger.ONE); f.add(BigInteger.valueOf(2));

		int sz = 2;
		while(sz < 479)
		{
			BigInteger f1 = f.get(sz - 2), f2 = f.get(sz - 1);
			BigInteger ff = f1.add(f2);
			f.add(ff);
			sz++;
		}
		
		while(true)
		{
			String aa = sc.next(), bb = sc.next();
			if(aa.equals("0") && bb.equals("0"))
				break;
			BigInteger a = new BigInteger(aa), b = new BigInteger(bb);
			int lb = lowerBound(f, a), ub = upperBound(f, b);
			if(lb > ub)
				out.println(0);
			else
				out.println(ub - lb + 1);
		}
		out.flush();
		out.close();
	}

	static int lowerBound(ArrayList<BigInteger> f, BigInteger t)
	{
		int ans = f.size(), lo = 0, hi = f.size() - 1;
		while(lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;
			if(f.get(mid).compareTo(t) >= 0)
			{
				ans = mid;
				hi = mid - 1;
			}
			else
				lo = mid + 1;
		}
		return ans;
	}

	static int upperBound(ArrayList<BigInteger> f, BigInteger t)
	{
		int ans = -1, lo = 0, hi = f.size() - 1;
		while(lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;
			if(f.get(mid).compareTo(t) <= 0)
			{
				ans = mid;
				lo = mid + 1;
			}
			else
				hi = mid - 1;
		}
		return ans;
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine(), ",| ");
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready();}

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


	}
}
