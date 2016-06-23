package v102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class PrimeTime_UVa10200 {

	static final int upperBound = (int)1e6;
	static ArrayList<Integer> primes;
	static boolean[] isComposite;
	
	static void sieve(int N)
	{
		primes = new ArrayList<Integer>(N / 10);
		isComposite = new boolean[N + 1];
		for(int i = 2; i <= N; ++i)
		{
			primes.add(i);
			if(!isComposite[i] && 1l * i * i <= N)
				for(int j = i * i; j <= N; j += i)
					isComposite[j] = true;
		}
	}
	
	static boolean isPrime(int N)
	{
		if(N <= upperBound)
			return !isComposite[N];
		for(int p: primes)
			if(p * p > N)
				break;
			else if(N % p == 0)
				return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		sieve(upperBound);
		int[] prefix = new int[10001];
		prefix[0] = 1;
		for(int i = 1; i <= 10000; ++i)
		{
			prefix[i] = prefix[i-1];
			if(isPrime(i * i + i + 41))
				prefix[i]++;
		}

		while(sc.ready())
		{
			int a = sc.nextInt(), b = sc.nextInt();

			out.printf("%.2f\n", 100.0 * (prefix[b] - (a == 0 ? 0 :prefix[a-1])) / (b - a + 1));
		}
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

		public double nextDouble() throws IOException
		{
			return Double.parseDouble(next());
		}

		public boolean ready() throws IOException {return br.ready();}


	}
}