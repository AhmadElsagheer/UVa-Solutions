package v006;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SelfNumbers_UVa640 {
	
	static final int upperbound = 1000000;
	
	static int d(int x)
	{
		int s = 0, y = x;
		while(x > 0)
		{
			s += x % 10;
			x /= 10;
		}
		return s + y;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		boolean[] self = new boolean[upperbound + 1];
		Arrays.fill(self, true);
		for(int i = 1; i <= upperbound; ++i)
			if(self[i])
			{
				int x = d(i);
				while(x <= upperbound && self[x])
				{
					self[x] = false;
					x = d(x);
				}
			}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= upperbound; ++i)
			if(self[i])
				sb.append(i + "\n");
		out.print(sb);
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