package v118;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class NNODN_UVa11876 {

	static final int upperBound = 65000;
	
	static int numDivs(int N)
	{
		int ans = 0;
		for(int i = 1; i * i <= N; ++i)
			if(N % i == 0)
			{
				++ans;
				if(N / i != i)
					++ans;
			}
		return ans;
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		int[] A = new int[upperBound];
		A[0] = 1;

		for(int i = 1; i < upperBound; ++i)
			A[i] = A[i-1] + numDivs(A[i-1]);
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int i = lowerBound(A, sc.nextInt());
			int j = upperBound(A, sc.nextInt());
			sb.append("Case " + t +": " + (j - i + 1) + "\n");
		}
		out.print(sb);
		out.flush();
		out.close();
	}

	static int upperBound(int[] A, int K)
	{
		int lo = 0, hi = upperBound - 1, ans = -1;
		while(lo <= hi)
		{
			int mid = lo + hi >> 1;
			if(A[mid] <= K)
			{
				ans = mid;
				lo = mid + 1;
			}
			else
				hi = mid - 1;
		}
		return ans;
	}
	
	static int lowerBound(int[] A, int K)
	{
		int lo = 0, hi = upperBound - 1, ans = -1;
		while(lo <= hi)
		{
			int mid = lo + hi >> 1;
			if(A[mid] >= K)
			{
				ans = mid;
				hi = mid - 1;
			}
			else
				lo = mid + 1;
		}
		return ans;
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