package v107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TheNewRuleOfEuphomia_UVa10742 {

	static final int upperBound = (int)1e6;
	static ArrayList<Integer> primes;
	static boolean[] isComposite;
	static int[] countPrimes;

	static void sieve(int N)
	{
		isComposite = new boolean[N];
		countPrimes = new int[N];
		primes = new ArrayList<Integer>(N / 10);

		for(int i = 2; i < N; ++i)
		{
			countPrimes[i] = countPrimes[i-1];
			if(!isComposite[i])
			{
				countPrimes[i]++;
				primes.add(i);
				if(1l * i * i < N)
					for(int j = i * i; j < N; j += i)
						isComposite[j] = true;
			}
		}
	}


	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		sieve(upperBound + 1);
		int tc = 1;
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			long ans = 0;
			for(int i = 0; i < primes.size(); ++i)
			{
				int p = primes.get(i);
				if(p > n)
					break;
				int c = countPrimes[n - p];
				if(n - p >= p)
					--c;
				ans += c;
			}
			out.printf("Case %d: %d\n", tc++, ans / 2);
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}