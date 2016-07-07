package v012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class NowhereMoney_UVa1258 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		long[] fib = new long[93];
		fib[0] = 0;
		fib[1] = 1;
		for(int i = 2; i < 93; ++i)
			fib[i] = fib[i-1] + fib[i-2];

		while(sc.ready())
		{
			long n = sc.nextLong(), m = n;
			ArrayList<Integer> s = new ArrayList<Integer>();
			int idx = 92;
			while(n > 0)
			{
				if(fib[idx] <= n)
				{
					n -= fib[idx];
					s.add(idx - 1);
				}
				--idx;
			}
			out.println(m);
			
			int size = s.size();
			for(int i = 0; i < size; ++i)
				out.print(s.get(i) + " ");
			out.println();
			
			for(int i = 0; i < size; ++i)
				out.print(fib[s.get(i)+1] + " ");
			out.println("\n");
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