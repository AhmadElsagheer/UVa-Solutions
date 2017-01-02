package v109;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
 
 
public class PrimeWords_UVa10924 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		boolean[] isPrime = new boolean[2000];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;				// 1 is considered in this problem as a prime number
		for(int i = 2; i * i < 2000; ++i)
			if(isPrime[i])
				for(int j = i * i; j < 2000; j += i)
					isPrime[j] = false;
		while(sc.ready())
		{
			char[] s = sc.next().toCharArray();
			int sum = 0;
			for(char c: s)
				if(c >= 'a' && c <= 'z')
					sum += 1 + c - 'a';
				else
					sum += 27 + c - 'A';
			if(isPrime[sum])
				out.println("It is a prime word.");
			else
				out.println("It is not a prime word.");
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