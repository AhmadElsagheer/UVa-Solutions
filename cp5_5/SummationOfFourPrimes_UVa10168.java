package cp5_5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class SummationOfFourPrimes_UVa10168 {

	static int[] primes;
	static boolean[] isPrime;
	
	static void sieve(int N)
	{
		int nxt = 0;
		primes = new int[664579];
		isPrime = new boolean[N + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for(int i = 2; i <= N; i++)
			if(isPrime[i])
			{
				primes[nxt++] = i;
				if((long) i * i <= N)
					for(int j = i * i; j <= N; j += i)
						isPrime[j] = false;
			}
	}
	
	static int find3(int n)
	{
		for(int p : primes)
			if(isPrime[p] && isPrime[n-p])
				return p;
		return -1;
	}
	public static void main(String[] args) throws IOException {
		
		sieve(10000000);
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		while(sc.ready())
		{
			int n = sc.nextInt();
			if(n < 8)
				sb.append("Impossible.\n");
			else
				if(n%2 == 0)
				{
					int third = find3(n - 4);
					sb.append("2 2 "+third+" "+(n-4-third)+"\n");
				}
				else
				{
					int third = find3(n - 5);
					sb.append("2 3 "+third+" "+(n-5-third)+"\n");
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
