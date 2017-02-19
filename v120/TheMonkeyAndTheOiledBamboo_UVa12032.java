package v120;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class TheMonkeyAndTheOiledBamboo_UVa12032 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int n = sc.nextInt(), h[] = new int[n];
			for(int i = 0; i < n; ++i)
				h[i] = sc.nextInt();
			int ans = -1, lo = 1, hi = (int)1e7;
			while(lo <= hi)
			{
				int mid = lo + hi >> 1;
				if(possible(n, h, mid))
				{
					ans = mid;
					hi = mid - 1;
				}
				else
					lo = mid + 1;
			}
			out.printf("Case %d: %d\n", t, ans);
		}
		out.flush();
		out.close();
	}
	
	static boolean possible(int n, int[] h, int k)
	{
		int cur = 0;
		for(int i = 0; i < n; ++i)
			if(cur + k < h[i])
				return false;
			else
			{
				if(cur + k == h[i])
					--k;
				cur = h[i];
			}
		return true;
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