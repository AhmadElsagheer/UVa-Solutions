package v104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SumupThePrimes_UVa10419 {
	
	static ArrayList<Integer> primes;
	
	static void sieve(int N)
	{
		boolean[] isComposite = new boolean[N];
		primes = new ArrayList<Integer>();
		for(int i = 2; i < N; ++i)
			if(!isComposite[i])
			{
				primes.add(i);
				for(int j = i * i; j < N; j += i)
					isComposite[j] = true;
			}
	}
	
	static Boolean[][][] memo;
	
	static boolean dp(int t, int p, int n)
	{
		if(n == 0)
			return t == 0;
		if(p == 62 || t == 0)
			return false;
		if(memo[t][p][n] != null)
			return memo[t][p][n];
		boolean ret = dp(t, p + 1, n);
		int prime = primes.get(p);
		if(prime <= n)
		{
			ret |= dp(t - 1, p + 1, n - prime);
			if(prime != 2 && t > 1 && prime<<1 <= n)
				ret |= dp(t - 2, p + 1, n - (prime<<1));
		}
		return memo[t][p][n] = ret;
	}
	
	static ArrayList<Integer> sol;
	
	static void print(int t, int p, int n)
	{
		if(n == 0)
			return;
		int prime = primes.get(p);
		
		if(prime != 2 && t > 1 && prime<<1 <= n && dp(t - 2, p + 1, n - (prime<<1)))
		{
			sol.add(prime); sol.add(prime);
			print(t - 2, p + 1, n - (prime<<1));
			return;
		}
		
		if(prime <= n && dp(t - 1, p + 1, n - prime))
		{
			sol.add(prime);
			print(t - 1, p + 1, n - prime);
			return;
		}
		
		print(t, p + 1, n);
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		sieve(300);
		Collections.sort(primes, new Sorter());
		memo = new Boolean[15][62][1001];
		
		int tc = 1;

		while(true)
		{
			int n = sc.nextInt(), t = sc.nextInt();
			if(n == 0)
				break;
			out.printf("CASE %d:\n", tc++);
			if(!dp(t, 0, n))
				out.println("No Solution.");
			else
			{
				sol = new ArrayList<Integer>(t);
				print(t, 0, n);
				StringBuilder sb = new StringBuilder();
				sb.append(sol.get(0));
				for(int i = 1; i < t; ++i)
					sb.append("+"+sol.get(i));
				out.println(sb);
			}
		}
		out.flush();
		out.close();
	}
	
	static class Sorter implements Comparator<Integer>
	{
		public int compare(Integer a, Integer b)
		{
			return a.toString().compareTo(b.toString());
		}
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