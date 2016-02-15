package cp5_5;

import java.io.*;
import java.util.*;

public class DistancePrimes_UVa10140 {
	
	static boolean[] isPrime;
	static int[] primeRange;
	static long U;
	static long L;
	static long start;
	
	public static void sieve(int N) {          			
	    												
		isPrime = new boolean[N+1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
	                               
	    for (long i = 2; i <= N; i++) 				
	    	if (isPrime[(int) i]) 
	    	{
	    		for (long j = i * i; j <= N; j += i) 	
	    			isPrime[(int)j] = false;
	    		
	
	   			for(long j = L%i==0?L:L +(i - L%i); j <= U; j += i)
	    				primeRange[(int) (j-start)] = 1;			//set as NOT prime number
	    			
	    	}  
	} 													
	
	
	public static boolean isPrime(long N)
	{
		if(N<=50000) return isPrime[(int)N];


		return primeRange[(int) (N-start)] == 0;
				
		
	}
	
	
	public static void main(String[] args) throws IOException {
		

//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext())
		{
			
//			String line = br.readLine();
//			if(line==null || line.equals(""))
//				break;
//			StringTokenizer st = new StringTokenizer(line);
//			if(!st.hasMoreTokens())
//				break;
//			L = Long.parseLong(st.nextToken());
//			U = Long.parseLong(st.nextToken());
//			if(L == 0 || U == 0)
//				break;
			L = sc.nextLong();
			U = sc.nextLong();
			primeRange = new int[1000001];				// 0 indicates a prime number
			start = L;
			sieve(50000);
			
			boolean found = false;
			long minDiff = Integer.MAX_VALUE;
			long maxDiff = 0;
			long last = 0;
			long c1, c2, d1, d2;
			c1 = c2 = d1 = d2 = 0;
			
			long current = L;
			while(current <= U)
			{
				if(isPrime(current))
					if(last==0)
						last = current;
					else
					{
						found = true;
						long localDiff = current - last;
						if(localDiff<minDiff)
						{
							minDiff = localDiff;
							c1 = last;
							c2 = current;
						}
						if(localDiff>maxDiff)
						{
							maxDiff = localDiff;
							d1 = last;
							d2 = current;
						}
						last = current;	
					}
				current ++ ;
						
			}
			if(!found)
				sb.append("There are no adjacent primes.\n");
			else
				sb.append(c1+","+c2+" are closest, "+d1+","+d2+" are most distant.\n");
						
		}
		System.out.print(sb);
		
		sc.close();
	}
}
