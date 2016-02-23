package cp4_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SquareSums_UVa11470 {
	

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = 1;
		while(true)
		{
			int n = sc.nextInt();
			
			if(n == 0)
				break;
			int[][] a = new int[n][n];
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j)
					a[i][j] = sc.nextInt();
			out.printf("Case %d:", tc++);
			for(int k = n, s = 0; k > 0; k -= 2, ++s)
				out.printf(" %d", findSum(a, s, k));
			out.println();
		}
		out.flush();
		out.close();
	}
	
	static int findSum(int[][] a, int s, int n)
	{
		if(n == 1)
			return a[s][s];
		int sum = 0;
		for(int i = s; i < s + n; ++i)
			sum += a[i][s] + a[i][s + n - 1];
		for(int j = s + 1; j < s + n - 1; ++j)
			sum += a[s][j] + a[s + n - 1][j];
		return sum;
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


	}
}
