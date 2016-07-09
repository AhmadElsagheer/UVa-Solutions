package v004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BlockVoting_UVa435 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt(), v[] = new int[n], p[] = new int[n];
			int hSum = 0;
			for(int i = 0; i < n; ++i)
				hSum += v[i] = sc.nextInt();
			hSum >>= 1;
			
			int[] vv = new int[1<<n];
			for(int i = 0; i < n; ++i)
				vv[1<<i] = v[i];
			for(int i = 1; i < 1<<n; ++i)
				vv[i] = vv[i & -i] + vv[i ^ (i & -i)];
			
			for(int msk = 1, end = 1<<n; msk < end; ++msk)
			{
				int votes = vv[msk];
				if(votes > hSum)
					for(int i = 0; i < n; ++i)
						if((msk & 1<<i) != 0 && votes - v[i] <= hSum)
							++p[i];
			}
			
			for(int i = 0; i < n; ++i)
				out.printf("party %d has power index %d\n", i + 1, p[i]);
			out.println();
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}