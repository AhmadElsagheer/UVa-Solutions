package v004;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RaucousRockers_UVa473 {


	static int n, maxT, t[], memo[][][];
	
	static int dp(int idx, int remDisks, int remTime)
	{
		if(idx == n)
			return 0;
		if(memo[idx][remTime][remDisks] != -1)
			return memo[idx][remTime][remDisks];
		
		int a = 0, b = 0, c = 0;
		if(remDisks > 0)
			a = dp(idx, remDisks - 1, maxT);
		if(remTime >= t[idx])
			b = 1 + dp(idx + 1, remDisks, remTime - t[idx]);
		c = dp(idx + 1, remDisks, remTime);
		return memo[idx][remTime][remDisks] = Math.max(a, Math.max(b, c));
	}
	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			n = sc.nextInt();
			maxT = sc.nextInt();
			int m = sc.nextInt();
			t = new int[n];
			for(int i = 0; i < n; ++i)
				t[i] = sc.nextInt();
			memo = new int[n][maxT + 1][m + 1];
			for(int i = 0; i < n; ++i)
				for(int j = 0; j <= maxT; ++j)
					Arrays.fill(memo[i][j], -1);
			out.println(dp(0, m, 0));
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

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine(), ",| ");
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready();}

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


	}
}
