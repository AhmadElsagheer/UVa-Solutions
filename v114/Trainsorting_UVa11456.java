package v114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Trainsorting_UVa11456 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt(), a[] = new int[n];
			for(int i = 0; i < n; ++i)
				a[i] = sc.nextInt();

			int[] lis = lis(a, n, true), lds = lis(a, n, false);

			int ans = 0;
			for(int i = 0; i < n; ++i)
				ans = Math.max(ans, lis[i] + lds[i] - 1);
			out.println(ans);
		}
		out.flush();
		out.close();
	}

	static int[] lis(int[] a, int n, boolean inc)
	{
		int[] L = new int[n];
		for(int i = n - 1; i >= 0; --i)
		{
			int curL = 1;
			for(int j = n - 1; j > i; --j)
				if((inc && a[j] > a[i] || !inc && a[j] < a[i]) && L[j] + 1 > curL)
					curL = L[j] + 1;
			L[i] = curL;
		}
		return L;
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