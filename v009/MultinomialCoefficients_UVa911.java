package v009;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MultinomialCoefficients_UVa911 {

	static int comb[][] = new int[1000][1000];
	
	static int nCr(int n, int k)
	{
		if(n < k)
			return 0;
		if(k == 0 || k == n)
			return 1;
		if(k == 1)
			return n;
		if(comb[n][k] != -1)
			return comb[n][k];
		if(n - k < k)
			return comb[n][k] = nCr(n, n - k);
		return comb[n][k] = nCr(n - 1, k - 1) + nCr(n - 1, k);
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		for(int i = 0; i < 1000; ++i)
			Arrays.fill(comb[i], -1);
		while(sc.ready())
		{
			int n = sc.nextInt(), k = sc.nextInt();
			int ans = 1;
			while(k-->1)
			{
				int r = sc.nextInt();
				ans *= nCr(n, r);
				n -= r;
			}
			sc.nextInt();
			out.println(ans);
		}
		out.flush();

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


	}
}
