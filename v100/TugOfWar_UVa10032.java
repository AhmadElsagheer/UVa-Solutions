package v100;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class TugOfWar_UVa10032 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt(), w[] = new int[n], s = 0;
			for(int i = 0; i < n; ++i)
				s += w[i] = sc.nextInt();
			int hs = s / 2, hn = n / 2;
			long[] dp = new long[hs + 1];
			//dp[i] = a mask where jth bit is one iff sum i can be made using j persons
			dp[0] = 1;
			for(int p = 0; p < n; ++p)
				for(int sum = hs, wp = w[p]; sum >= wp; --sum)
					dp[sum] |= dp[sum - wp]<<1;
			if((n & 1) == 0)
				while((dp[hs] & 1l<<hn) == 0)
					--hs;
			else
				while((dp[hs] & 1l<<hn) == 0 && (dp[hs] & 1l<<hn+1) == 0)
					--hs;
		
			out.println(hs + " " + (s - hs));
			if(tc != 0)
				out.println();
		}
		out.flush();
		out.close();
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){	br = new BufferedReader(r);}

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