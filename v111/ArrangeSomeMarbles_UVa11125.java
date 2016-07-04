package v111;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ArrangeSomeMarbles_UVa11125 {

	static int[][] memo;
	
	static int dp(int msk1, int msk2)
	{
		int lc = msk1 & 3, ls = (msk1 & 3<<2)>>2, fc = (msk1 & 3<<4)>>4, fs = (msk1 & 3<<6)>>6;
		if(msk2 == 0)
			return lc != fc && ls != fs ? 1 : 0;
		if(memo[msk1][msk2] != -1)
			return memo[msk1][msk2];
		int ans = 0;
		for(int i = 0; i < 4; ++i)
			if(i != lc)
			{
				int shift = 7<<i * 3, count = (msk2 & shift)>>i * 3;
				for(int j = 1; j <= 3; ++j)
					if(j <= count && j != ls)
						ans += dp((msk1 & ~15) | i | j<<2, (msk2 & ~shift) | (count - j<<i * 3));
			}
		return memo[msk1][msk2] = ans;
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		memo = new int[1<<8][1<<12];
		for(int i = 0; i < 1<<8; ++i)
			Arrays.fill(memo[i], -1);
		while(tc-->0)
		{
			int n = sc.nextInt(), msk2 = 0;
			int[] c = new int[4];
			int colors = 0, sum = 0;
			for(int i = 0; i < n; ++i)
			{
				sum += c[i] = sc.nextInt();
				msk2 |= c[i]<<i * 3;
				if(c[i] != 0)
					++colors;
			}
			long ans = 0;
			for(int i = 0; i < 4; ++i)
			{
				int shift = 7<<i * 3, count = (msk2 & shift)>>i * 3;
				for(int j = 1; j <= 3; ++j)
					if(j <= count)
					{
						int ss = i | j<<2;
						ans += dp(ss | ss<<4, (msk2 & ~shift) | (count - j<<i * 3));
					}
			}
			if(colors <= 1 && sum <= 3)
				ans = 1;
			out.println(ans);
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

		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
