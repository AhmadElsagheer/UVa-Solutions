package v009;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FibonnaccimalBase_UVa948 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int[] fib = new int[40];
		fib[0] = 0;
		fib[1] = 1;
		for(int i = 2; i < 40; ++i)
			fib[i] = fib[i-1] + fib[i-2];
		int tc = sc.nextInt();
		while(tc-->0)
		{
			StringBuilder sb = new StringBuilder();
			int n = sc.nextInt(), k = n;
			for(int i = 39; i > 1; --i)
				if(fib[i] <= k)
				{
					sb.append(1);
					k -= fib[i];
				}
				else
					if(k != n)
						sb.append(0);
			out.format("%d = %s (fib)\n", n, sb.toString());
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
			
		public boolean ready() throws IOException {return br.ready(); }


	}
}
