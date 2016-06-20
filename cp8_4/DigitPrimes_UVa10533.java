package cp8_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class DigitPrimes_UVa10533 {

	static final int upperBound = (int)1e6;
	static int[] prefix;
	
	static void modifiedSieve(int N)
	{
		boolean[] isComposite = new boolean[N + 1];
		prefix = new int[N + 1];
		for(int i = 2; i <= N; ++i)
		{
			prefix[i] += prefix[i-1];
			if(!isComposite[i])
			{
				if(!isComposite[sumOfDigits(i)])
					++prefix[i];
				if(1l * i * i <= N)
					for(int j = i * i; j <= N; j += i)
						isComposite[j] = true;
			}
		}
		
	}
	
	static int sumOfDigits(int x)
	{
		int sum = 0;
		while(x > 0)
		{
			sum += x%10;
			x /= 10; 
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		
		modifiedSieve(upperBound);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int L = sc.nextInt(), R = sc.nextInt();
			sb.append(prefix[R] - prefix[L - 1] + "\n");
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

		public double nextDouble() throws IOException
		{
			return Double.parseDouble(next());
		}

		public boolean ready() throws IOException {return br.ready();}


	}
}