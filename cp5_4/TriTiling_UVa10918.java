package cp5_4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class TriTiling_UVa10918 {
	
	static int[][] memo;
	
	static int dp(int occ, int N)		//occ(upied) = 3 bits one for each cell in current column
	{
		if(N == 0)
			return 1;
		if(memo[occ][N] != -1)
			return memo[occ][N];
		
		int count = 0;
		switch(occ)
		{
		case 0:
			// 3 hor | 1 ver + 1 hor | 1 hor + 1 ver
			if(N > 1)
				count = dp(7, N - 1) + dp(4, N - 1) + dp(1, N - 1);			
			break;
		case 1:
			count = dp(0, N - 1);
			if(N > 1)
				count += dp(6, N - 1);
			break;
			
		case 3:
			if(N > 1)
				count = dp(4, N - 1);
			break;
			
		case 4:
			count = dp(0, N - 1);
			if(N > 1)
				count += dp(3, N - 1);
			break;
		case 6:
			if(N > 1)
				count = dp(1, N - 1);
			break;	
		case 7:
			count = dp(0, N - 1);
		}
		return memo[occ][N] = count;
	}
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		memo = new int[8][31];
		for(int i = 0; i < 8; ++i)
			Arrays.fill(memo[i], -1);
		while(true)
		{
			int n = sc.nextInt();
			if(n == -1)
				break;
			out.println(dp(0, n));
		}
		out.flush();
		out.close();

	}
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}
		
		public Scanner(FileReader r) {  br = new BufferedReader(r); }

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