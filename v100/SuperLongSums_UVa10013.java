package v100;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SuperLongSums_UVa10013 {

	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int m = sc.nextInt();
			int[] a = new int[m], b = new int[m];
			for(int i = 0; i < m; ++i)
			{
				a[m - i - 1] = sc.nextInt();
				b[m - i - 1] = sc.nextInt();
			}
			StringBuilder ans = new StringBuilder();
			for(int i = 0, c = 0; i < m; ++i)
			{
				int s = a[i] + b[i] + c;
				ans.append(s % 10);
				c = s / 10;
			}
			out.println(ans.reverse());
			if(tc != 0)
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