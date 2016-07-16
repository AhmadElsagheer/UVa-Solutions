package regionals.dhaka2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SumOfMSLCM {

	static final int upperBound = (int)2e7;
	static long[] sumDiv;
	
	static void modifiedSieve(int N)
	{
		sumDiv = new long[N];
		Arrays.fill(sumDiv, 1);
		sumDiv[1] = 0;
		for(int i = 2; i < N; ++i)
		{
			if(sumDiv[i] == 1)
				for(int j = i; j < N; j += i)
				{	
					int e = 0, k = j;
					while(k % i == 0) { k /= i; ++e; }
					
					sumDiv[j] *= (pow(i, e + 1) - 1) / (i - 1);			
				}
			sumDiv[i] += sumDiv[i-1];
		}
	}
	
	static long pow(long p, long e)
	{
		long res = 1;
		while(e > 0)
		{
			if((e & 1) == 1)
				res *= p;
			p *= p;
			e >>= 1;
		}
		return res;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		modifiedSieve(upperBound + 1);
		
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			out.println(sumDiv[n]);
		}
		out.flush();
		out.close();
	}
	
	static class Scanner
	{
		BufferedReader br;
		StringTokenizer st;
		
		Scanner(InputStream s) { br = new BufferedReader(new InputStreamReader(s)); }
		
		String next() throws IOException
		{
			while(st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();	
		}
		
		int nextInt() throws NumberFormatException, IOException { return Integer.parseInt(next()); }
	}
}
