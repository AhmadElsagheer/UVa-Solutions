package v114;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class AgeSort_UVa11462 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			int[] a = new int[n], c = new int[100];
			for(int i = 0; i < n; ++i)
				c[a[i] = sc.nextInt()]++;
			for(int i = 0, sum = 0; i < 100; ++i)
			{
				int t = c[i];
				c[i] = sum;
				sum += t;
			}
			int[] b = new int[n];
			for(int i = 0; i < n; ++i)
				b[c[a[i]]++] = a[i];
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < n - 1; ++i)
				sb.append(b[i] + " ");
			sb.append(b[n-1]);
			out.println(sb);
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