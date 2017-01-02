package v100;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
 
/*
 *  Brute force Solution will pass. Complexity O(n log n) as a^n is calculated in O(log n)
 *  Further Optimizations:- 
 *  1. Generate prime numbers with sieve to exclude them from the check
 *  2. Generate square-free numbers with sieve to exclude others from the check
 *  3. A simple run for all numbers [3, 65000] will terminate quickly resulting in only 15 numbers! (hardcode them)
 */
public class CarmichaelNumbers_UVa10006 {
	
	static boolean[] isComposite, sf;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		sieve(65000);
		while(true)
		{
			int N = sc.nextInt();
			if(N == 0)
				break;
			if(isComposite[N] && sf[N])
			{
				boolean normal = false;
				for(int i = 2; i < N; ++i)
					if(modPow(i, N, N) != i)
					{
						normal = true;
						break;
					}
				if(normal)
					out.printf("%d is normal.\n", N);
				else
					out.printf("The number %d is a Carmichael number.\n", N);
			}
			else
				out.printf("%d is normal.\n", N);
		}
		out.flush();
		out.close();
	}
	
	static int modPow(long b, int e, int mod)
	{
		long res = 1;
		while(e > 0)
		{
			if((e & 1) == 1)
				res = res * b % mod;
			b = b * b % mod;
			e >>= 1;
		}
		return (int)res;
	}
	
	static void sieve(int N)
	{
		sf = new boolean[N];
		isComposite = new boolean[N];
		Arrays.fill(sf, true);
		for(int i = 2; i < N; ++i)
			if(!isComposite[i])
				for(int j = 2; i * j < N; ++j)
				{
					isComposite[i * j] = true;
					if(j % i == 0)
						sf[i * j] = false;
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