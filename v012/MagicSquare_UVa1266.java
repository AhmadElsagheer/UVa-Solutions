package v012;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class MagicSquare_UVa1266 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		boolean first = true;
		while(sc.ready())
		{
			if(first)
				first = false;
			else
				out.println();
			int n = sc.nextInt(), a[][] = new int[n][n];
			int k = 0, i = 0, j = n / 2;
			while(k < n * n)
			{
				if(a[i][j] != 0)
				{
					i = (i + 2)%n;
					j = (j - 1 + n)%n;
					continue;
				}
				a[i][j] = ++k;
				i = (i - 1 + n) % n;
				j = (j + 1) % n;
			}
			int s = 0;
			for(int x: a[0])
				s += x;
			int len = (n * n + "").length();
			out.printf("n=%d, sum=%d\n", n, s);
			for(int[] x: a)
			{
				for(int y: x)
					out.printf(" %"+len+"d", y);
				out.println();
			}
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
		
		boolean ready() throws IOException { return br.ready(); }
	}
}