<<<<<<< HEAD
package cp5_5;


import java.io.*;
import java.util.*;

public class GoldbachConjectureII_UVa686{
	
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
			int count = 0;
			int i = 0;
			while(true)
			{
				int prime = primes.get(i++);
				if(prime * 2 > N)
					break;
				int other = N - prime;
				if(!notPrime[other])
					count++;
			}
			sb.append(count+"\n");
		}
		
		
		System.out.print(sb);
	}
}
=======
package cp5_5;


import java.io.*;
import java.util.*;

public class GoldbachConjectureII_UVa686{
	
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
			int count = 0;
			int i = 0;
			while(true)
			{
				int prime = primes.get(i++);
				if(prime * 2 > N)
					break;
				int other = N - prime;
				if(!notPrime[other])
					count++;
			}
			sb.append(count+"\n");
		}
		
		
		System.out.print(sb);
	}
}
>>>>>>> 39f869b7a0b14772c795150d9bb52127d820fece
