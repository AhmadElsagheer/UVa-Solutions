package v106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Coprimes_UVa10637 {

	static StringBuilder sb = new StringBuilder();
	static int t, seq[], gcd[][];

	static void generate(int idx, int val, int n)
	{
		if(idx == t)
		{
			if(n == 0)
			{
				for(int i = 0; i < t - 1; ++i)
					sb.append(seq[i]+" ");
				sb.append(seq[t-1]+"\n");
			}
			return;
		}
		if(val > n)
			return;

		boolean possible = true;
		for(int i = 0; i < idx; ++i)
			if(gcd[seq[i]][val] != 1)
			{
				possible = false;
				break;
			}
		if(possible)
		{
			seq[idx] = val;
			generate(idx + 1, val, n - val);
		}
		generate(idx, val + 1, n);		
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		gcd = new int[101][101];
		for(int i = 1; i <= 100; ++i)
			for(int j = 1; j <= 100; ++j)
				gcd[i][j] = gcd(i, j);
		
		int tc = sc.nextInt();
		for(int tt = 1; tt <= tc; ++tt)
		{
			sb.append("Case "+tt+":\n");
			int n = sc.nextInt();
			t = sc.nextInt();
			seq = new int[t];
			generate(0, 1, n);
		}
		out.print(sb);
		out.flush();
		out.close();
	}

	static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a%b); }

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