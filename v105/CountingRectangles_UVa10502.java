package v105;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CountingRectangles_UVa10502 {

	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int R = sc.nextInt();
			if(R == 0)
				break;
			int C = sc.nextInt();
			int[][] A = new int[R][C];
			for(int i = 0; i < R; ++i)
			{
				String s = sc.next();
				for(int j = 0; j < C; ++j)
				{
					int val = s.charAt(j) - '0';
					if(i > 0) val += A[i-1][j];
					if(j > 0) val += A[i][j-1];
					if(i > 0 && j > 0) val -= A[i-1][j-1];
					A[i][j] = val;
				}
			}
			
			int ans = 0;
			for(int i = 0; i < R; ++i)
				for(int j = 0; j < C; ++j)
					for(int k = 0; i + k < R; ++k)
					{
						int w = -1, lo = 0, hi = C - j - 1;
						while(lo <= hi)
						{
							int mid = lo + hi >> 1;
							int sum = A[i + k][j + mid];
							if(i > 0)
								sum -= A[i - 1][j + mid];
							if(j > 0)
								sum -= A[i + k][j - 1];
							if(i > 0 && j > 0)
								sum += A[i-1][j-1];
							if(sum == (mid + 1) * (k + 1))
							{
								w = mid;
								lo = mid + 1;
							}
							else
								hi = mid - 1;
						}
						ans += w + 1;
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
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}

		public boolean ready() throws IOException {return br.ready();}

		public boolean nextEmpty() throws IOException
		{
			String s = nextLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}
	}
}