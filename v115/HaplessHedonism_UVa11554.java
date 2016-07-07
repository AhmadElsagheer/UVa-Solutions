package v115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class HaplessHedonism_UVa11554 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		long[] ans = new long[1000001];
		for(int i = 4, j = 2; i <= 1000000; ++i)
		{
			while(j * 2 + 1 <= i)
				++j;
			long x = i%2 == 1 ? (1l * (i - j) * (i - j - 1) / 2) : (1l * (i - j - 1) * (i - j - 2) / 2);
			ans[i] = ans[i-1] + x +  1l * (i / 2 - 1) * (i / 2) / 2;
		}
		
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