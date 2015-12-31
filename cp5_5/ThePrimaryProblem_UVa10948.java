package cp5_5;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ThePrimaryProblem_UVa10948 {

	static boolean isPrime[];
	
	static void sieve(int N)
	{
		isPrime = new boolean[N + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for(int i = 2; i <= N; ++i)
			if(isPrime[i] && (long) i * i <= N)
				for(int j = i * i; j <= N; j += i)
					isPrime[j] = false;
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		sieve(1000000);
		int N;
		while((N = sc.nextInt()) != 0)
		{
			out.format("%d:\n", N);
			if((N & 1) == 1)
				if(isPrime[N - 2])
					out.format("%d+%d\n", 2, N - 2);
				else
					out.println("NO WAY!");
			else
			{
				boolean done = false;
				for(int i = 2; !done && i <= N - i; ++i)
					if(isPrime[i] && isPrime[N - i])
					{
						out.format("%d+%d\n", i, N - i);
						done = true;
					}
				if(!done)
					out.println("NO WAY!");
			}
				
		}
		out.flush();
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
