package v008;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class AnagramaticPrimes_UVa897 {

	static final int upperBound = 10000000;
	static int[] fac = new int[9];
	static int[] permutations = new int[upperBound + 1];
	static int[] anagrams = new int[upperBound + 1];
	static ArrayList<Integer> anagPrimes = new ArrayList<Integer>();
	static ArrayList<Integer> primes = new ArrayList<Integer>();
	
	
	static int fac(int n)
	{
		if(n == 0) return fac[0] = 1;
		
		return fac[n] = n * fac(n - 1);
	}
	
	static int allPermutations(int n)
	{
		if(permutations[n] != 0)
			return permutations[n]; 
		int k = n;
		int[] a = new int[10];
		while(n > 0)
		{
			a[n%10]++;
			n /= 10;
		}

		
		int res = fac[len(k)];
		for(int i = 1; i < 10; i++)
			res /= fac[a[i]];

		return permutations[k] = res;
	}
	
	static int findAnagram(int n)
	{
		int[] a = new int[10];
		while(n > 0)
		{
			a[n%10]++;
			n /= 10;
		}
		
		int res = 0;
		for(int i = 1; i < 10; i++)
			while(a[i]-->0)
				res = res * 10 + i;
		
		return res;
	}
	
	static boolean containsZero(int n)
	{
		while(n > 0)
		{
			if(n%10 == 0)
				return true;
			n /= 10;
		}
		return false;
	}
	
	static int nextPower(int n)
	{
		int f = 1;
		while(n > 0)
		{
			f *= 10;
			n /= 10;
		}
		return f;
	}
	
	static int len(int n)
	{
		return (n + "").length();
	}
	
	static void sieve()
	{
		boolean[] isPrime = new boolean[upperBound + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for(int i = 2; i <= upperBound; i++)
			if(isPrime[i])
			{
				if(!containsZero(i))
				{
					primes.add(i);
					anagrams[findAnagram(i)]++;
				}
				if((long) i * i <= upperBound)
					for(int j = i * i; j <= upperBound; j += i)
						isPrime[j] = false;
				
			}
	}
	static void compute()
	{
		fac(8);
		sieve();
		for(int i = 0; i < primes.size(); i++)
		{
			int x = findAnagram(primes.get(i));
			if(anagrams[x] == allPermutations(x))
				anagPrimes.add(primes.get(i));
		}
	}
	
	static int bs(int n)
	{
		int ans = -1, lo = 0, hi =anagPrimes.size() - 1;
		while(lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;
			if(anagPrimes.get(mid) > n)
			{
				ans = mid;
				hi = mid - 1;
			}
			else
				lo = mid + 1;
		}
		if(ans == -1 || anagPrimes.get(ans) >= nextPower(n))
			return 0;
		return anagPrimes.get(ans);
	}
	public static void main(String[] args) throws IOException {
		
		compute();
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) break;
			sb.append(bs(n)+"\n");
		}
		System.out.print(sb);
		
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

		public boolean ready() throws IOException {return br.ready();}


	}
}
