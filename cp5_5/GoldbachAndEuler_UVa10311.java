package cp5_5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class GoldbachAndEuler_UVa10311 {

	static int[] primes = new int[5761455];
	static final int upperBound = 100000000;
	static boolean[] isPrime = new boolean[upperBound + 1];
	
	static void sieve()
	{
		int[] lp = new int[upperBound + 1];	
		int nxt = 0;
		
		for(int i = 2; i <= upperBound; i++)
		{
			if(lp[i] == 0)
			{
				lp[i] = i;
				primes[nxt++] = i;
				isPrime[i] = true;
				
			}
			for(int j = 0; j < nxt && primes[j] <= lp[i] && (long)i * primes[j] <= upperBound; j++)
				lp[i * primes[j]] = primes[j];
		}
	}
	
	static int bs(int t)
	{
		int lo = 0, hi = 5761455, ans = -1;
		while(lo <= hi)
		{
			int mid = lo + ((hi - lo) >> 1);
			if(primes[mid] >= t)
			{
				ans = mid;
				hi = mid - 1;
			}
			else
				lo = mid + 1;
		}
		return ans;
	}
	public static void main(String[] args) throws IOException {
		
		sieve();
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		while(sc.ready())
		{
			int n = sc.nextInt();
			if(n <= 4)
				sb.append(n+" is not the sum of two primes!\n");
			else
				if(n%2 == 1)
					if(isPrime[n - 2])
						sb.append(n).append(" is the sum of "+2+" and "+(n-2)+".\n");
					else
						sb.append(n+" is not the sum of two primes!\n");
				else
					{

						int idx = bs(n>>1);
						if(primes[idx] == n>>1)
							idx++;
						while(idx < 5761455 && n - primes[idx] > 0 && !isPrime[n - primes[idx]]) idx++;
						if(idx < 5761455 && n - primes[idx] > 0)
							sb.append(n).append(" is the sum of "+(n-primes[idx])+" and "+primes[idx]+".\n");
						else
							sb.append(n+" is not the sum of two primes!\n");
					}
				
				
		}
		System.out.print(sb);
		
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
