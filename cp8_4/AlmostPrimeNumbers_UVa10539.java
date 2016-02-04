package cp8_4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AlmostPrimeNumbers_UVa10539 {

	static final long upperBound = (long)1e12;
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		sieve(1000000);
		long[] almostPrime = new long[1011537];
		int nxt = 0;
		
		for(int p: primes)
		{
			long pp = (long) p * p;
			while(pp <= upperBound)
			{
				almostPrime[nxt++] = pp;
				pp *= p;
			}
		}
		Arrays.sort(almostPrime);
		
		int n = sc.nextInt();
		while(n-->0)
		{
			long lb = sc.nextLong(), ub = sc.nextLong();
			int lo = 0, hi = 1011536, ans1 = 1011537, ans2 = -1;
			while(lo <= hi)
			{
				int mid = lo + (hi - lo) / 2;
				if(almostPrime[mid] >= lb)
				{
					ans1 = mid;
					hi = mid - 1;
				}
				else
					lo = mid + 1;
			}
			
			lo = 0; hi = 1011536;
			while(lo <= hi)
			{
				int mid = lo + (hi - lo) / 2;
				if(almostPrime[mid] <= ub)
				{
					ans2 = mid;
					lo = mid + 1;
				}
				else
					hi = mid - 1;
			}
			out.println(ans2 - ans1 + 1);
		}
		
		out.flush();
		out.close();
	}	

	static ArrayList<Integer> primes;
	
	static void sieve(int N)
	{
		primes = new ArrayList<Integer>(3000);
		boolean[] isComposite = new boolean[N];
		for(int i = 2; i < N; ++i)
			if(!isComposite[i])
			{
				primes.add(i);
				if((long) i * i < N)
					for(int j = i * i; j < N; j += i)
						isComposite[j] = true;
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
		
		public double nextDouble(String x) 
		{

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
