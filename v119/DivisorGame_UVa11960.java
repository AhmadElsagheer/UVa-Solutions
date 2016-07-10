package v119;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DivisorGame_UVa11960 {
	
	static final int upperBound = (int)1e6 + 1;
	
	static int[] modifiedSieve(int N)
	{
		int[] numDiv = new int[N];
		Arrays.fill(numDiv, 1);
		for(int i = 2; i < N; ++i)
			if(numDiv[i] == 1)
				for(int j = i; j < N; j += i)
				{
					int e = 0, k = j;
					while(k % i == 0) { k /= i; ++e; }
					numDiv[j] *= e + 1;
				}
		return numDiv;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int[] ans = new int[upperBound], numDiv = modifiedSieve(upperBound); 
		ans[1] = 1;
		for(int i = 2; i < upperBound; ++i)
			if(numDiv[i] >= numDiv[ans[i-1]])
				ans[i] = i;
			else
				ans[i] = ans[i-1];
		int tc = sc.nextInt();
		while(tc-->0)
			out.println(ans[sc.nextInt()]);
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