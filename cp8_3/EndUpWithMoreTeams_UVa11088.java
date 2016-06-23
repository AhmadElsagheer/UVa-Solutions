package cp8_3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EndUpWithMoreTeams_UVa11088 {

	static int[] power, memo;
	
	static int dp(int msk)
	{
		
		if(bitCount[msk] < 3)
			return 0;
		if(memo[msk] != -1)
			return memo[msk];
		int c = 0;
		int z = mOne[msk];
		for(int i = 0; i < z; ++i)
			if(((1<<i) & msk) != 0)
				for(int j = i + 1; j < z; ++j)
					if(((1<<j) & msk) != 0 && power[i] + power[j] + power[z] >= 20)
						c = Math.max(c, dp(msk & ~(1<<i | 1<<j | 1<<z)) + 1);
		return memo[msk] = c;
	}
	
	public static void main(String[] args) throws IOException {
		pre(15);
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			power  = new int[n];
			for(int i = 0; i < n; ++i)
				power[i] = sc.nextInt();
			Arrays.sort(power);
			memo = new int[1<<n];
			Arrays.fill(memo, -1);
			out.format("Case %d: %d\n", tc++, dp((1<<n) - 1));
		}
		
		out.flush();
	}
	
	static int[] bitCount, mOne;

	static void pre(int n)
	{
		bitCount = new int[1<<n];
		for(int i = 0; i < n; i++)
			bitCount[1<<i] = 1;
		
		for(int i = 1; i < 1<<n; i++)
			bitCount[i] = 1 + bitCount[i ^ (i & -i)];
		
		mOne = new int[1<<n];
		for(int i = 1; i <= n; ++i)
			for(int j = 1<<i - 1; j < 1<<i; ++j)
				mOne[j] = i - 1;
		
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
