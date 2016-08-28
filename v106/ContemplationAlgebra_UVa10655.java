package v106;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class ContemplationAlgebra_UVa10655 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			int p = sc.nextInt(), q = sc.nextInt();
			if(p == 0 && q == 0 && !sc.ready())
				break;
			int n = sc.nextInt();
			long[][] F = {{2}, {p}}, T = {{0, 1}, {-q, p}};
			long[][] res = mult(matPow(T, 2, n), F, 2, 2, 1);
			out.println(res[0][0]);
		}
		out.flush();
		out.close();
	}
	
	static long[][] mult(long[][] A, long[][] B, int p, int q, int r)
	{
		long[][] C = new long[p][r];
		for(int i = 0; i < p; ++i)
			for(int j = 0; j < r; ++j)
				for(int k = 0; k < q; ++k)
					C[i][j] += A[i][k] * B[k][j];
		return C;
	}
	
	static long[][] matPow(long[][] b, int n, int e)
	{
		long[][] res = new long[n][n];
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