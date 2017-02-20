package v113;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SortSortAndSort_UVa11321 {


	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			int N = sc.nextInt(), M = sc.nextInt();
			out.printf("%d %d\n", N, M);
			if(N == 0)
				break;
			Integer[] a = new Integer[N];
			for(int i = 0; i < N; ++i)
				a[i] = sc.nextInt();
			Arrays.sort(a, new Sorter(M));
			for(int i = 0; i < N; ++i)
				out.println(a[i]);
		}
		out.flush();
		out.close();
	}
	
	static class Sorter implements Comparator<Integer>
	{
		int mod;
		
		Sorter(int a) { mod = a; }
		
		public int compare(Integer a, Integer b)
		{
			if(a == b)
				return 0;
			long ma = a%mod, mb = b%mod;
			
			if(ma != mb)
				return ma > mb ? 1 : -1;
			if((a & 1) != (b & 1))
				return (a & 1) == 1 ? -1 : 1;
			if((a & 1) == 0)
				return a > b  ? 1 : -1;
			return a > b ? -1 : 1;	
		}
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


	}
}
