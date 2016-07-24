package v003;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class ExtrapolationUsingADifferenceTable_UVa326 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			int[][] diff = new int[n][n];
			for(int i = 0; i < n; ++i)
				diff[n-1][i] = sc.nextInt();
			for(int i = n - 2; i >= 0; --i)
				for(int j = 0; j <= i; ++j)
					diff[i][j] = diff[i+1][j+1] - diff[i+1][j];
			int k = sc.nextInt();
			int[] prev = new int[k + 1];
			Arrays.fill(prev, diff[0][0]);
			for(int i = 1; i < n; ++i)
			{
				int[] cur = new int[i + k + 1];
				for(int j = 0; j <= i; ++j)
					cur[j] = diff[i][j];
				for(int j = i + 1; j <= i + k; ++j)
					cur[j] = cur[j-1] + prev[j-1];
				prev = cur;
			}
			
			out.printf("Term %d of the sequence is %d\n", n + k, prev[n + k - 1]);
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