package v009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class JumpingChampion_UVa914 {

	static final int upperBound = 10000000;
	static int[] primes = new int[664579];
	
	static void sieve()
	{
		boolean[] notPrime = new boolean[upperBound + 1];
		int nxt = 0;
		for(int i = 2; i <= upperBound; i++)
			if(!notPrime[i])
			{
				primes[nxt++] = i;
				if((long) i * i <= upperBound)
					for(int j = i * i; j <= upperBound; j += i)
						notPrime[j] = true;
			}
	}
	
	static int bs(int t, boolean lower)
	{
		int ans = -1, lo = 0, hi = 664578;
		while(lo <= hi)
		{
			int mid = lo + ((hi - lo)>>1);
			if(lower)
			{
				if(primes[mid] >= t)
				{
					ans = mid;
					hi = mid - 1;
				}
				else
					lo = mid + 1;
			}
			else
			{
				if(primes[mid] <= t)
				{
					ans = mid;
					lo = mid + 1;
				}
				else
					hi = mid - 1;
			}
		}
		return ans;
		
	}
	
	public static void main(String[] args) throws IOException {
		
		sieve();
	
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int L = sc.nextInt(), U = sc.nextInt();
			
			L = bs(L, true); U = bs(U, false);
			if(L == -1 || U == -1 || L == U)
				out.print("No jumping champion\n");
			else
			{
				int[] f = new int[160];
				for(int i = L; i < U; i++)
					f[primes[i+1] - primes[i]]++;
				int max = 0, count = 0;
				for(int i = 0; i < 160; i++)
					if(f[i] > f[max])
					{
						max = i;
						count = 1;
					}
					else
						if(f[i] == f[max])
							count++;
				if(count != 1)
					out.print("No jumping champion\n");
				else
					out.printf("The jumping champion is %d\n", max);
			}
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

		public boolean ready() throws IOException {return br.ready();}


	}
}
