package v114;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class EvenParity_UVa11464 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int n = sc.nextInt(), grid[] = new int[n];
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j)
					grid[i] |= sc.nextInt()<<j;
			int ans = -1;
			for(int msk = 0; msk < 1<<n; ++msk)
			{
				int cur = compute(grid, n, msk);
				if(cur != -1)
					if(ans == -1 || cur < ans)
						ans = cur;
			}
			out.printf("Case %d: %d\n", t, ans);
				
		}
		out.flush();
		out.close();
	}
	
	static int compute(int[] grid, int n, int msk)
	{
		int ret = 0, lst = -1;
		for(int i = 0; i < n; ++i)
		{
			if((grid[i] | msk) != msk)
				return -1;
			ret += Integer.bitCount(grid[i] ^ msk);
			int nxt = 0;
			for(int j = 0; j < n; ++j)
			{
				int p = 0;
				if(j > 0 && (msk & 1<<(j-1)) != 0)
					p ^= 1;
				if(j < n - 1 && (msk & 1<<(j+1)) != 0)
					p ^= 1;
				if(i > 0 && (lst & 1<<j) != 0)
					p ^= 1;
				if(p == 1)
					nxt |= 1<<j;
			}
			lst = msk;
			msk = nxt;
		}
		return ret;
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