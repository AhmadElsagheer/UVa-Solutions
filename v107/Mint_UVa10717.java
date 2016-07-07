package v107;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Mint_UVa10717 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int n = sc.nextInt(), t = sc.nextInt();
			if(n + t == 0)
				break;
			int[] c = new int[n];
			for(int i = 0; i < n; ++i)
				c[i] = sc.nextInt();

			while(t-->0)
			{
				int h = sc.nextInt(), lo = 0, hi = (int)1e9;
				for(int x = 0; x < n; ++x)
					for(int y = x + 1; y < n; ++y)
						for(int z = y + 1; z < n; ++z)
							for(int l = z + 1; l < n; ++l)
							{
								int d = lcm(lcm(c[x], c[y]), lcm(c[z], c[l]));
								lo = Math.max(lo, h / d * d);
								hi = Math.min(hi, (h + d - 1) / d * d);
							}
				
				
				out.printf("%d %d\n", lo, hi);
			}
		}
		out.flush();
		out.close();
	}

	static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a%b); }
	
	static int lcm(int a, int b) { return a / gcd(a, b) * b; }
	
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