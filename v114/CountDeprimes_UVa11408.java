package v114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CountDeprimes_UVa11408 {

	static final int upperBound = 5000001;
	static int[] dePrimePrefix;
	
	static void sieve(int N)
	{
		int[] pf = new int[N];
		dePrimePrefix = new int[N];
		
		for(int i = 2; i < N; ++i)
		{
			dePrimePrefix[i] += dePrimePrefix[i-1];
			if(pf[i] == 0)
				for(int j = i; j < N; j += i)
					pf[j] += i;
			
			if(pf[pf[i]] == pf[i])
				dePrimePrefix[i]++;			
		}
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		sieve(upperBound);
		while(true)
		{
			int a = sc.nextInt();
			if(a == 0)
				break;
			int b = sc.nextInt();
			out.println(dePrimePrefix[b] - dePrimePrefix[a-1]);
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