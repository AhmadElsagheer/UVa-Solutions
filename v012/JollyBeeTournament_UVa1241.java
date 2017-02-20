package v012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class JollyBeeTournament_UVa1241 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int N = sc.nextInt(), M = sc.nextInt(), K = 1<<N;
			int[] tournament = new int[K<<1];
			while(M-->0)
			{
				int idx = sc.nextInt() + K - 1;
				tournament[idx] = -1;
			}
			int wo = 0;
			for(int i = (1<<N) - 1; i > 0; --i)
			{
				int l = tournament[i<<1], r = tournament[i<<1|1];
				if(l + r == -1)
					++wo;
				tournament[i] = Math.max(l, r);
			}
			out.println(wo);
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

		public double nextDouble() throws IOException
		{
			return Double.parseDouble(next());
		}

		public boolean ready() throws IOException {return br.ready();}


	}
}