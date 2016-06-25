package v104;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MrAzadAndHisSon_UVa10490 {

	static final int upperBound = (int)1e6;
	static boolean[] isComposite;
	static ArrayList<Integer> primes;
	
	static void sieve(int N) 
	{
		isComposite = new boolean[N];
		primes = new ArrayList<Integer>(N / 10);
		isComposite[1] = true;
		for(int i = 2; i < N; ++i)
			if(!isComposite[i])
			{
				primes.add(i);
				if(1l* i * i < N)
					for(int j = i * i; j < N; j += i)
						isComposite[j] = true;
			}
	}
	
	static boolean isPrime(int x)
	{
		if(x < upperBound)
			return !isComposite[x];
		for(int p: primes)
			if(p * p > x)
				break;
			else if(x % p == 0)
				return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		sieve(upperBound);
		while(true)
		{
			int N = sc.nextInt();
			if(N == 0)
				break;
			boolean nPrime = isPrime(N), fPrime = isPrime((1<<N)-1);
			if(nPrime && fPrime)
				out.printf("Perfect: %d!\n", 1l* (1<<N-1) * ((1<<N) - 1));
			else if(nPrime)
				out.println("Given number is prime. But, NO perfect number is available.");
			else
				out.println("Given number is NOT prime! NO perfect number is available.");
				
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
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}

		public boolean ready() throws IOException {return br.ready();}

		public boolean nextEmpty() throws IOException
		{
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}