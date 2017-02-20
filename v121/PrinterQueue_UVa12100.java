package v121;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class PrinterQueue_UVa12100 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt(), m = sc.nextInt();
			int[] a = new int[n];
			for(int i = 0; i < n; ++i)
				a[i] = sc.nextInt();
			int ans = 0;
			while(true)
			{
				++ans;
				int nxt = -1, p = 0, k = 0;
				for(int i = 0; i < n; ++i)
					if(a[i] > p)
					{
						p = a[i];
						nxt = i;
					}
				if(nxt == m)
					break;
				if(m > nxt)
					m -= nxt + 1;
				else
					m += n - nxt - 1;
				int[] b = new int[n-1];
				for(int i = nxt + 1; i < n; ++i)
					b[k++] = a[i];
				for(int i = 0; i < nxt; ++i)
					b[k++] = a[i];
				a = b;
				--n;
			}
			out.println(ans);
			
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

		public double nextDouble() throws IOException
		{
			return Double.parseDouble(next());
		}

		public boolean ready() throws IOException {return br.ready();}


	}
}