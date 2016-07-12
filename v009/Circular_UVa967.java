package v009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Circular_UVa967 {

	static final int upperBound = (int)1e6 + 1;
	static boolean[] isPrime;
	
	static void sieve(int N)
	{
		isPrime = new boolean[N];
		Arrays.fill(isPrime, true);
		for(int i = 2; i < N; ++i)
			if(isPrime[i] && 1l * i * i < N)
				for(int j = i * i; j < N; j += i)
					isPrime[j] = false;
	}
	
	static boolean isCircular(int x)
	{
		int z = x;
		while(z > 0)
		{
			if(z%10 == 0)
				return false;
			z /= 10;
		}
		
		z = x;
		while(true)
		{
			if(!isPrime[z])
				return false;
			z = circulate(z);
			if(x == z)
				break;
		}
		return true;
	}
	
	static int circulate(int x)
	{
		int f = 1;
		while(f * 10 < x)
			f *= 10;
		return (x % f) * 10 + (x / f); 
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		
		sieve(upperBound);
		int[] prefix = new int[upperBound];
		for(int i = 100; i < upperBound; ++i)
			prefix[i] = prefix[i-1] + (isCircular(i) ? 1 : 0);
		
		while(true)
		{
			int l = sc.nextInt();
			if(l == -1)
				break;
			int r = sc.nextInt();
			int ans = prefix[r] - prefix[l-1];
			if(ans == 0)
				sb.append("No Circular Primes.\n");
			else if(ans == 1)
				sb.append("1 Circular Prime.\n");
			else
				sb.append(ans + " Circular Primes.\n");				
		}
		out.print(sb);
		out.flush();
		out.close();
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}

	}
}