package cp8_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BusyProgrammer_UVa11432 {

	static long[][] memo[], ans;
	static int D, G;
	
	static long dp(int proj, int day, int p1_rem)
	{
		if(day == D <<1) return 1;
		
		int p2_rem = (D<<1) - p1_rem - day;		
		if(p2_rem == 0) return 0;
			
		if(memo[proj][day][p1_rem] != -1) return  memo[proj][day][p1_rem];
		
		int rem = proj == 0 ? p1_rem:p2_rem;
		
		int limit = proj == 1 && p1_rem == 0?D<<1:G;
		
		long count = 0;
		for(int i = 1; i <= limit && i <= rem; i++)
			count += dp((proj+1)%2, day + i, p1_rem - (proj == 0 ? i:0));
		return memo[proj][day][p1_rem] = count;
	}
	
	static void pre()
	{
		ans = new long[34][34];
		for(int a = 1; a <= 33; a++)
			for(int b = 1; b <= 33; b++)
			{
				D = a;	G = b;
				memo = new long[2][D<<1][D+1];
				for(int i = 0; i < 2; i++)
					for(int j = 0; j < D<<1; j++) 
						Arrays.fill(memo[i][j], -1);
		
				ans[a][b] = dp(0,0,D)<<1;
			}
				
	}
	public static void main(String[] args) throws IOException {
		
		pre();
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int k = 1;
		while(true)
		{
			int x = sc.nextInt(), y = sc.nextInt();
			if(x == -1 && y == -1) break;
			sb.append("Case "+k++).append(": ").append(ans[x][y]).append("\n");
		}
		System.out.print(sb);
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
