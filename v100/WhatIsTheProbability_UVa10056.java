package v100;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class WhatIsTheProbability_UVa10056 {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int N = sc.nextInt();
			double p = sc.nextDouble(), ans;
			int I = sc.nextInt();
			if(Math.abs(p) < 1e-9)
				ans = 0;
			else if(Math.abs(p - 1) < 1e-9)
				ans = I == 1 ? 1 : 0;
			else
				ans = p * Math.pow(1 - p, I - 1) / (1 - Math.pow(1 - p, N));
			out.printf("%.4f\n", ans);
		}
		out.flush();
		out.close();
	}


	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){  br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}
		
		public double nextDouble() throws NumberFormatException, IOException { return Double.parseDouble(next()); }

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready(); }


	}

}