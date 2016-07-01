package v114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Cubes_UVa11428 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		

		while(true)
		{
			int N = sc.nextInt(), x = 2, y = 1;
			if(N == 0)
				break;
			int ans = cube(x) - cube(y);
			while(x != y && ans != N)
			{
				if(ans > N)
					++y;
				else
					++x;
				ans = cube(x) - cube(y);
			}
			if(ans != N)
				out.println("No solution");
			else
				out.println(x + " " + y);
		}

		out.flush();
		out.close();
	}
	
	static int cube(int x) { return x * x * x; }

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

		public double nextDouble() throws IOException
		{
			return Double.parseDouble(next());
		}

		public boolean ready() throws IOException {return br.ready();}


	}
}