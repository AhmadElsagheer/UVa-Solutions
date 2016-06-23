package cp5_5;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class GoldbachConjecture_UVa543{
	
	static ArrayList<Integer> primes;
	static boolean[] notPrime;
	public static void sieve(int N) {          			
	    												
		notPrime = new boolean[N+1];
		notPrime[0] = notPrime[1] = true;
		primes = new ArrayList<Integer>();
	                               
	    for (int i = 2; i <= N; i++) 				
	    	if (!notPrime[i]) 
	    	{
	    		primes.add(i);
	    		for (int j = i * i; j <= N; j += i) 	
	    			notPrime[j] = true;
	    	}   
	} 													
	
	
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			primes = new ArrayList<Integer>();
			String line = br.readLine();
			StringTokenizer st = new StringTokenizer(line);
			int N = Integer.parseInt(st.nextToken());
			if(N==0)
				break;
			sieve(N);
			int i = 1;
			while(true)
			{
				int prime = primes.get(i++);
				int other = N - prime;
				if(!notPrime[other])
				{
					sb.append(N+" = "+prime+" + "+other+"\n");
					break;
				}
			}
			
		}
		
		
		System.out.print(sb);
	}
}
