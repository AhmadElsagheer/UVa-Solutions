package cp3_3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FillTheContainers_UVa11413 {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(sc.ready())
		{
			int n = sc.nextInt(), m = sc.nextInt();
			int[] c = new int[n];
			for(int i = 0; i < n; ++i)
				c[i] = sc.nextInt();
			int ans = -1, lo = 1, hi = (int)1e9;
			while(lo <= hi)
			{
				int mid = lo + (hi - lo) / 2;
				if(possible(c, n, m, mid))
				{
					ans = mid;
					hi = mid - 1;
				}
				else
					lo = mid + 1;
			}
			out.println(ans);
		}
		
		out.flush();
		out.close();
	}	
	
	static boolean possible(int[] c, int n, int m, int cc)
	{
		int v = 0,  curCap = cc;
		while(v < n && m > 0)
			if(c[v] <= curCap)
				curCap -= c[v++];
			else
			{
				curCap = cc;
				--m;
			}
		return v == n;
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

		public boolean ready() throws IOException {return br.ready();}

		public double nextDouble(String x) 
		{

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


	}
}
