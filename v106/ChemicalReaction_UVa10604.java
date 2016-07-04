package v106;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ChemicalReaction_UVa10604 {


	static int[][] mix;
	static int[][] outHeat;
	static int[][][][][][] memo;
	
	static int dp(int c1, int c2, int c3, int c4, int c5, int c6, int k)
	{
		if(k == 1)
			return 0;
		if(memo[c1][c2][c3][c4][c5][c6] != -1)
			return memo[c1][c2][c3][c4][c5][c6];
		int min = (int)1e7, c[] = new int[]{c1, c2, c3, c4, c5, c6};
		for(int i = 0; i < 6; ++i)
			if(c[i] != 0)
			{
				c[i]--;
				for(int j = 0; j < 6; ++j)
					if(c[j] != 0)
					{
						c[j]--;
						c[mix[i][j]]++;
						min = Math.min(min, outHeat[i][j] + dp(c[0], c[1], c[2], c[3], c[4], c[5], k - 1));
						c[j]++;
						c[mix[i][j]]--;
					}
				
				c[i]++;
			}
		return memo[c1][c2][c3][c4][c5][c6] = min;
	}
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int m = sc.nextInt();
			mix = new int[m][m];
			outHeat = new int[m][m];
			for(int i = 0; i < m; ++i)
				for(int j = 0; j < m; ++j)
				{
					mix[i][j] = sc.nextInt() - 1;
					outHeat[i][j] = sc.nextInt();
				}
			int k = sc.nextInt();
			int[] c = new int[6];
			for(int i = 0; i < k; ++i)
				c[sc.nextInt() - 1]++;
			sc.nextLine();
			memo = new int[k + 1][k + 1][k + 1][k + 1][k + 1][k + 1];
			for(int a = 0; a < k + 1; ++a)
				for(int b = 0; b < k + 1; ++b)
					for(int d = 0; d < k + 1; ++d)
						for(int e = 0; e < k + 1; ++e)
							for(int f = 0; f < k + 1; ++f)
								Arrays.fill(memo[a][b][d][e][f], -1);
			out.println(dp(c[0], c[1], c[2], c[3], c[4], c[5], k));
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
		
		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
