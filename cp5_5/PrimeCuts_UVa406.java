package cp5_5;

import java.io.*;
import java.util.*;

public class PrimeCuts_UVa406 {
	
	static ArrayList<Integer> primes;
	
	public static void sieve(int N) {          			
	    												
		boolean[] notPrime = new boolean[N+1];			
		primes = new ArrayList<Integer>();
	    primes.add(1);                                   
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
		
		while(br.ready())
		{
			
			String line = br.readLine();
			StringTokenizer st = new StringTokenizer(line);
			int N = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			sieve(N);
			int size = primes.size();
			int count = 2*C;
			int start;
			if(size%2==1)
			{
				count--;
				start = size/2 - C + 1;
			}
			else
				start = size/2 - C;
			if(start<0)
				start = 0;
			sb.append(line+":");
			for(int i = start; i < size && count-->0; i++)
				sb.append(" " + primes.get(i));
			sb.append("\n\n");
			
		}
		
		
		System.out.print(sb);
	}
}
