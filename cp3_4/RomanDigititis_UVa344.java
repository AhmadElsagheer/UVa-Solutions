package cp3_4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class RomanDigititis_UVa344 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int[] val = {1, 4, 5, 9, 10, 40, 50, 90, 100};
		int[] sym1 = {0, 0, 1, 0, 2, 2, 3, 2, 4};
		int[] sym2 = {-1, 1, -1, 2, -1, 3, -1, 4, -1};
		int[][] a = new int[5][101];
		for(int k = 1; k <= 100; ++k)
		{
			int n = k;
			while(n > 0)
			{
				for(int i = 8; i >= 0; --i)
					if(val[i] <= n)
					{
						a[sym1[i]][k]++;
						if(sym2[i] != -1)
							a[sym2[i]][k]++;
						n -= val[i];
						break;
					}
			}
			for(int i = 0; i < 5; ++i)
				a[i][k] += a[i][k-1];
		}
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			out.printf("%d: %d i, %d v, %d x, %d l, %d c\n", n, a[0][n], a[1][n], a[2][n], a[3][n], a[4][n]);
		}
		out.flush();
		out.close();
	}
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){    br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){    br = new BufferedReader(r);}

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