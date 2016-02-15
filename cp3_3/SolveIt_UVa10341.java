package cp3_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SolveIt_UVa10341 {

	static final double EPS = 1e-7;
	static int p, q, r, s, t, u;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		
		while(sc.ready())
		{
			p = sc.nextInt(); q = sc.nextInt(); r = sc.nextInt();
					s = sc.nextInt(); t = sc.nextInt(); u = sc.nextInt();
		
		
			if(f(0) * f(1) > 0)
				out.print("No solution\n");
			else
			{
				double lo = 0, hi = 1;
				for(int i = 0; i < 100; i++)
				{
					double mid = (lo + hi) / 2;
					if(f(0) * f(mid) <= 0)
						hi = mid;
					else
						lo = mid;
				}
				out.printf("%.4f\n", (lo + hi) / 2);
			}
			
		}
		out.flush();
		
	}
	
	static double f(double x)
	{
		
		return p * Math.exp(-x) + q * Math.sin(x) + r * Math.cos(x) + s * Math.tan(x) + t * x * x + u;
	}
	

	static class Scanner {
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
