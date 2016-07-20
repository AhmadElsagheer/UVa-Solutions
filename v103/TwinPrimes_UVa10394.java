package v103;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class TwinPrimes_UVa10394 {
	
	static int[] isPrime;
	static int[][] twins = new int[1000001][2];
	static int counter = 1;
	
	public static void sieve(int N) {          			//generates all primes [2,N]
		
		isPrime = new int[N+1];			// zero means is prime
		isPrime[0] = isPrime[1] = 1;
	                                       
	    for (long i = 2; i <= N; i++) 					//to test primality of N, loop till i*i <= N
	    	if (isPrime[(int)i] == 0) 
	    	{
	    		if(isPrime[(int) (i-2)]==0)
	    		{
	    			twins[counter][1] = (int)i;
	    			twins[counter++][0] = (int) (i - 2);
	    		}
	    		for (long j = i * i; j <= N; j += i) 	//to test primality of N, loop till j*j <= N
	    			isPrime[(int)j] = 1;
	    	}   
	} 													//for N > 10^6 change int N to long N 
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		sieve(20000000);
		
		while(br.ready())
		{
			int S = Integer.parseInt(br.readLine());
			
			sb.append("("+twins[S][0]+", "+twins[S][1]+")\n");
			
		}
		
		System.out.print(sb);
	}
	
}
