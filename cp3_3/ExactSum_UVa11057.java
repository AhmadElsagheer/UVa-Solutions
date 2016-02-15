package cp3_3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class ExactSum_UVa11057 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		
		while(sc.ready())
		{
			int n = sc.nextInt();
			int[] f = new int[1000001];
			while(n-->0)
				f[sc.nextInt()]++;
			n = sc.nextInt();
			int i = -1, j = -1, diff = 2000000;
			for(int k = 0; k <= 1000000; ++k)
				if(f[k] != 0)
				{
					f[k]++;
					if(n-k <= 1000000 && n-k >= 0 && f[n - k] != 0 && Math.abs(n - (k<<1)) < diff)
					{
						i = Math.min(k, n-k);
						j = Math.max(k, n-k);
						diff = Math.abs(i - j);
					}
						
					f[k]--;
				}
			out.format("Peter should buy books whose prices are %d and %d.\n\n", i, j);
			sc.nextLine();
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
