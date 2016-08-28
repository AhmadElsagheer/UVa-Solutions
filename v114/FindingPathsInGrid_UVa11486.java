package v114;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class FindingPathsInGrid_UVa11486 {
	
	static final int mod = (int)1e9 + 7;
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int[][] T = new int[35][35];
		Comb[] combs = new Comb[35];
		for(int a = 0, k = 0; a < 7; ++a)
			for(int b = a + 1; b < 7; ++b)
				for(int c = b + 1; c < 7; ++c)
					for(int d = c + 1; d < 7; ++d, ++k)
						combs[k] = new Comb(a, b, c, d);

		for(int i = 0; i < 35; ++i)
			for(int j = 0; j < 35; ++j)
				T[i][j] = check(combs[i], combs[j]);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt();
			int[] a = new int[4];
			for(int i = 0, k = 0; i < 7; ++i)
				if(sc.nextInt() != 0)
					a[k++] = i;
			int idx = 0;
			while(!equal(a, combs[idx].a))
				++idx;
			int[][] M = matPow(T, 35, n - 1);
			int ans = 0;
			for(int i = 0; i < 35; ++i)
				ans = (ans + M[idx][i])%mod;
			out.println(ans);
		}
		out.flush();
		out.close();
	}
	
	static boolean equal(int[] x, int[] y)
	{
		for(int i = 0; i < 4; ++i)
			if(x[i] != y[i])
				return false;
		return true;
	}
	
	static int check(Comb x, Comb y)
	{
		boolean[] used = new boolean[4];
		for(int i = 0; i < 4; ++i)
		{
			boolean ok = false;
			for(int j = 0; j < 4; ++j)
				if(!used[j] && (y.a[j] - x.a[i] == 1 || y.a[j] - x.a[i] == -1))
				{
					ok = true;
					used[j] = true;
					break;
				}
			if(!ok)
				return 0;
		}
		return 1;
	}
	
	static int[][] mult(int[][] A, int[][] B, int p, int q, int r)
	{
		int[][] C = new int[p][r];
		for(int i = 0; i < p; ++i)
			for(int j = 0; j < r; ++j)
			{
				long x = 0;
				for(int k = 0; k < q; ++k)
					x = (x + 1l * A[i][k] * B[k][j])%mod;
				C[i][j] = (int)x;
			}
		return C;
	}
	
	static int[][] matPow(int[][] b, int n, int e)
	{
		int[][] res = new int[n][n];
		for(int i = 0; i < n; ++i)
			res[i][i] = 1;
		while(e > 0)
		{
			if((e & 1) == 1)
				res = mult(res, b, n, n, n);
			b = mult(b, b, n, n, n);
			e >>= 1;
		}
		return res;
	}
	
	static class Comb { int[] a; Comb(int x, int y, int z, int w) {a = new int[]{x, y, z, w}; } }
	
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}


	}
}