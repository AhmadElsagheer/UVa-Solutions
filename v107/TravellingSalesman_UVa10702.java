package v107;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class TravellingSalesman_UVa10702 {

	static final int INF = 100000000;
	static int C, memo[][], profit[][];
	static boolean[] isEnd;
	
	static int dp(int city, int remCities)
	{
		if(remCities == 0)
			return isEnd[city]? 0 : -INF;
		if(memo[city][remCities] != -1) return memo[city][remCities];
		
		int max = 0;
		for(int i = 0; i < C; i++)
			max = Math.max(max, profit[city][i] + dp(i, remCities - 1));
		
		return memo[city][remCities] = max;
	}
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			C = sc.nextInt();
			if(C == 0) break;
			
			int start = sc.nextInt() - 1, end = sc.nextInt(), inter = sc.nextInt();
			
			profit = new int[C][C];
			for(int i = 0; i < C; i++)
				for(int j = 0; j < C; j++)
					profit[i][j] = sc.nextInt();
			
			isEnd = new boolean[C];
			while(end-->0) isEnd[sc.nextInt() - 1] = true;
			
			memo = new int[C][inter+1];
			for(int i = 0; i < C; i++) Arrays.fill(memo[i], -1);
			out.println(dp(start, inter));
		}
		out.flush();
		
	}
	
	static class Scanner {
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

		public boolean ready() throws IOException {return br.ready();}


	}
}
