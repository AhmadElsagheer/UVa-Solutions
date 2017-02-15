package v117;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Car_UVa11715 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			int type = sc.nextInt();
			if(type == 0)
				break;
			if(type == 1)
			{
				double u = sc.nextDouble(), v = sc.nextDouble(), t = sc.nextDouble();
				double a = (v - u) / t, s = u * t + 0.5 * a * t * t;
				out.printf("Case %d: %.3f %.3f\n", tc++, s, a);
			}
			else if(type == 2)
			{
				double u = sc.nextDouble(), v = sc.nextDouble(), a = sc.nextDouble();
				double t = (v - u) / a, s = u * t + 0.5 * a * t * t;
				out.printf("Case %d: %.3f %.3f\n", tc++, s, t);
			}
			else if(type == 3)
			{
				double u = sc.nextDouble(), a = sc.nextDouble(), s = sc.nextDouble();
				double v = Math.sqrt(u * u  + 2 * a * s), t = (v - u) / a; 
				out.printf("Case %d: %.3f %.3f\n", tc++, v, t);
			}
			else
			{
				double v = sc.nextDouble(), a = sc.nextDouble(), s = sc.nextDouble();
				double u = Math.sqrt(v * v  - 2 * a * s), t = (v - u) / a; 
				out.printf("Case %d: %.3f %.3f\n", tc++, u, t);
			}
				
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