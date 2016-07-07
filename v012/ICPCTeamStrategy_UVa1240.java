package v012;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ICPCTeamStrategy_UVa1240 {

	static int N;
	static int[][] A;
	static int memo[][][];

	static int dp(int lst, int remT, int remP)
	{
		if(Integer.bitCount(remP) == 0)
			return 0;
		if(memo[lst][remT][remP] != -1)
			return memo[lst][remT][remP];
		int ret = 0;
		for(int i = 0; i < N; ++i)
			if((remP & 1<<i) != 0)
				for(int j = 0; j < 3; ++j)
					if(j != lst && A[j][i] <= remT)
						ret = Math.max(ret, 1 + dp(j, remT - A[j][i], remP & ~(1<<i)));
		return memo[lst][remT][remP] = ret;
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			N = sc.nextInt();
			A = new int[3][N];
			for(int i = 0; i < 3; ++i)
				for(int j = 0; j < N; ++j)
					A[i][j] = sc.nextInt();

			memo = new int[4][281][1<<N];
			for(int i = 0; i < 4; ++i)
					for(int j = 0; j <= 280; ++j)
						Arrays.fill(memo[i][j], -1);
			out.println(dp(3, 280, (1<<N) - 1));			
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}