package v012;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SumOfDifferentPrimes_UVa1213 {

	static int[] isPrime;
	static LinkedList<Integer> primes;
	static int[][][] memo;
	static final int UNCAL = -1;
	
	public static void sieve(int N)
	{
		isPrime = new int[N+1];
		primes = new LinkedList<Integer>();
		Arrays.fill(isPrime, 1);
		isPrime[0] = isPrime[1] = 0;
		for(int i = 2; i <= N; i++)
		{
			if(isPrime[i]==1)
			{
				primes.add(i);
				for(int j = i * i; j <= N; j += i)
					isPrime[j] = 0;
			}
					
		}
				
	}
	
	public static int dp(int i, int k, int n)
	{
		if(k==0)
			if(n==0)
				return 1;
			else
				return 0;
		if(i==primes.size() || primes.get(i) > n)
			return 0;
		if(memo[i][k][n]!=UNCAL)
			return memo[i][k][n];
		
		int take = dp(i+1,k-1,n-primes.get(i));
		int ignore = dp(i+1,k,n);
		
		return memo[i][k][n] = take + ignore;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		sieve(2000);
		memo = new int[primes.size()][15][1150];
		for(int i = 0, size = primes.size(); i < size; i++)
			for(int j = 0; j < 15; j++)
				Arrays.fill(memo[i][j], UNCAL);
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			if(N==0 && K == 0)
				break;
			
			sb.append(dp(0,K,N)+"\n");
		}
		System.out.print(sb);
	}
}
