package cp3_5;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;


public class WavioSequence_UVa10534 {

	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(sc.ready())
		{
			int n = sc.nextInt(), a[] = new int[n];
			for(int i = 0; i < n; ++i)
				a[i] = sc.nextInt();
			out.println(lis(a, n));
		}
		out.flush();
		out.close();
	}
	
	static int lis(int[] A, int n)
	{
		ArrayList<Integer> L = new ArrayList<Integer>();
		int[] left = new int[n], right = new int[n];
		
		for(int i = 0; i < n; ++i) 
		{
			int pos = Collections.binarySearch(L, A[i]);
			if (pos < 0) pos = -(pos + 1);

			if(pos >= L.size()) L.add(A[i]);
			else                 L.set(pos, A[i]);
			
			left[i] = pos;
		}
		
		L = new ArrayList<Integer>();
		for(int i = n - 1; i >= 0; --i) 
		{
			int pos = Collections.binarySearch(L, A[i]);
			if (pos < 0) pos = -(pos + 1);

			if(pos >= L.size()) L.add(A[i]);
			else                 L.set(pos, A[i]);
			
			right[i] = pos;
		}
		
		int ret = 1;
		for(int i = 0; i < n; ++i)
			ret = Math.max(ret, 1 + 2 * Math.min(left[i], right[i]));
		return ret;
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