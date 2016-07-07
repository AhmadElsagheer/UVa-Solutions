package v007;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CopyingBooks_UVa714 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int m = sc.nextInt(), k = sc.nextInt(), p[] = new int[m];
			for(int i = 0; i < m; ++i)
				p[i] = sc.nextInt();
			long ans = -1, lo = 1, hi = (long)5e9;
			while(lo <= hi)
			{
				long mid = lo + hi >> 1;
				if(possible(k, m, mid, p))
				{
					ans = mid;
					hi = mid - 1;
				}
				else
					lo = mid + 1;
			}
			int[] ass = assign(k, m, ans, p);
			int cur = -1;

			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < m; ++i)
			{
				if(ass[i] != cur)
				{
					if(cur != -1)
						sb.append(" /");
					cur = ass[i];
				}
				if(i == 0)
					sb.append(p[i]);
				else
					sb.append(" " + p[i]);
			}
			out.println(sb);
		}
		out.flush();
		out.close();
	}
	
	static int[] assign(int k, int m, long max, int[] p)
	{
		int[] ass = new int[m];
		for(int i = k - 1, j = m - 1; i >= 0; --i)
		{
			long remPages = max;
			while(i <= j && remPages >= p[j])
			{
				ass[j] = i;
				remPages -= p[j--];
			}
		}
		return ass;
	}
	
	static boolean possible(int k, int m, long max, int[] p)
	{
		int curBook = 0;
		while(k-->0 && curBook < m)
		{
			long remPages = max;
			while(curBook < m && p[curBook] <= remPages)
			{
				remPages -= p[curBook];
				++curBook;
			}
		}
		return curBook == m;
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