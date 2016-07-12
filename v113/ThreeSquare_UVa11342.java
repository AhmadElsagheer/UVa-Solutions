package v113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ThreeSquare_UVa11342 {

	static final int upperBound = 50000;
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		Triple[] ans = new Triple[upperBound + 1];
		for(int a = 0; a * a <= upperBound; ++a)
			for(int b = a; a * a + b * b <= upperBound; ++b)
				for(int c = b; true; ++c)
				{
					int k = a * a + b * b + c * c;
					if(k > upperBound)
						break;
					if(ans[k] == null)
						ans[k] = new Triple(a, b, c);
				}
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int k = sc.nextInt();
			Triple t = ans[k];
			if(t == null)
				out.println(-1);
			else
				out.printf("%d %d %d\n", t.x, t.y, t.z);
		}
		out.flush();
		out.close();
	}
	
	static class Triple { int x, y, z; Triple(int a, int b, int c) { x = a; y = b; z = c; } }
	
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
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}
		

		public boolean ready() throws IOException {return br.ready();}

	}
}