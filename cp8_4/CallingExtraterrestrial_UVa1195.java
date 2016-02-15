package cp8_4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CallingExtraterrestrial_UVa1195 {
	
	public static void main(String[] args) throws IOException {
	
		sieve(100001);
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(true)
		{
			int m = sc.nextInt(), a = sc.nextInt(), b = sc.nextInt();
			if(m + a + b == 0)
				break;
			
			int end = -1, lo = 0, hi = primes.size() - 1;
			while(lo <= hi)
			{
				int mid = lo + (hi - lo) / 2;
				int p = primes.get(mid);
				if((long) p <= m)
				{
					end = mid;
					lo = mid + 1;
				}
				else
					hi = mid - 1;
			}
			
			int max = -1, P = -1, Q = -1;
			for(int i = 0; i <= end; ++i)
			{
				int q = primes.get(i);
				int ans = -1;
				lo = 0; hi = i;
				while(lo <= hi)
				{
					int mid = lo + (hi - lo) / 2;
					int p = primes.get(mid);
					if((long)p * q <= m)
					{
						if((long)p * b >= (long)a * q)
							ans = p;			
						lo = mid + 1;
					}
					else
						hi = mid - 1;
					
				}
				
				if(ans != -1 && max < ans * q)
				{
					max = ans * q;
					Q = q;
					P = ans;
				}
			}
			out.printf("%d %d\n", P, Q);
			
		}
		out.flush();
		out.close();
		
		
	}
	
	static ArrayList<Integer> primes;
	
	static void sieve(int upperBound)
	{
		boolean[] isPrime = new boolean[upperBound];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		primes = new ArrayList<Integer>(upperBound/10);
		for(int i = 2; i < upperBound; ++i)
			if(isPrime[i])
			{
				primes.add(i);
				if((long) i * i < upperBound)
					for(int j = i * i; j < upperBound; j += i)
						isPrime[j] = false;
			}
	}
	
	
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(FileReader fileReader) throws FileNotFoundException{	br = new BufferedReader(fileReader);}
		
		public Scanner(InputStream s) throws FileNotFoundException{	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}
		
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


		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready(); }


	}
	
}


