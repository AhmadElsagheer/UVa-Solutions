package cp9;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class BubblesAndBuckets_UVa11495 {

	static final int INF = (int)2e9;
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			int a[] = new int[n];
			for(int i = 0; i < n; ++i)
				a[i] = sc.nextInt();
			out.println(mergeSort(a, 0, n - 1)%2 == 0 ? "Carlos" : "Marcelo");
			
		}
		out.flush();
		out.close();
	}
	
	static long mergeSort(int[] a, int p, int r)
	{
		long inv = 0;
		if(p < r)
		{
			int q = (p + r) / 2;
			inv += mergeSort(a, p, q) + mergeSort(a, q + 1, r) + merge(a, p, q, r);
		}
		return inv;
	}


	static long merge(int[] a, int p, int q, int r)
	{
		long inv = 0;
		int n1 = q - p + 1;
		int n2 = r - q;
		int[] L = new int[n1+1], R = new int[n2+1];

		for(int i = 0; i < n1; i++)  L[i] = a[p + i];
		for(int i = 0; i < n2; i++)  R[i] = a[q + 1 + i];
		L[n1] = R[n2] = INF;

		for(int k = p, i = 0, j = 0; k <= r; k++)
			if(L[i] <= R[j])
				a[k] = L[i++];
			else
			{	
				a[k] = R[j++];
				inv += n1 - i;
			}
		return inv;
	}  


	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){	br = new BufferedReader(r);}

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


	}
}