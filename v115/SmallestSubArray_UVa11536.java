package v115;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class SmallestSubArray_UVa11536 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt(), c = 0;
			int[] f = new int[k + 1];
			int ans = n + 1, a[] = new int[n + 1];
			for(int i = 1, j = 1; i <= n; ++i)
			{
				if(i <= 3)
					a[i] = i;
				else
					a[i] = (a[i - 1] + a[i - 2] + a[i - 3]) % m + 1;
				if(a[i] > k)
					continue;
				if(f[a[i]]++ == 0)
					++c;
					
				while(j <= i && c == k)
				{
					ans = Math.min(ans, i - j + 1);
					if(a[j] <= k && --f[a[j]] == 0)
						--c;
					++j;
				}
			}

			if(ans == n + 1)
				out.printf("Case %d: sequence nai\n", t);
			else
				out.printf("Case %d: %d\n", t, ans);
		}
		out.flush();
		out.close();
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){    br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){    br = new BufferedReader(r);}

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