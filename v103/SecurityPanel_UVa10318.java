package v103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SecurityPanel_UVa10318 {

	static final int INF = (int)1e9;
	static int R, C, OFF, FULL;
	static int[] patt;
	static int[][] memo;
	
	static int dp(int x, int y, int above, int cur, int below)
	{
		if(x == R)
			return above == FULL ? 0 : INF;
		
		if(y == C)
			return x == 0 || above == FULL ? dp(x + 1, 0, cur, below, 0) : INF;
			
		int cell = x * C + y, msk = (above * OFF + cur) * OFF + below;
		if(memo[cell][msk] != -1)
			return memo[cell][msk];
		//press
		int press = 1 + dp(x, y + 1, (above ^ shift(patt[0], y-1)) & FULL, (cur ^ shift(patt[1], y-1)) & FULL, (below ^ shift(patt[2], y-1)) & FULL);
		//don't press
		int leave = dp(x, y + 1, above, cur, below);
		return memo[cell][msk] = Math.min(press, leave);
	}
	
	static ArrayList<Integer> sol;
	
	static void print(int x, int y, int above, int cur, int below)
	{
		if(x == R)
			return;	
		if(y == C)
		{
			print(x + 1, 0, cur, below, 0);
			return;
		}
			
		int opt = dp(x, y, above, cur, below);
		if(dp(x, y + 1, above, cur, below) == opt)
			print(x, y + 1, above, cur, below);		//leave leads to opt
		else
		{		sol.add(x * C + y + 1);				 //press leads to opt
				print(x, y + 1, (above ^ shift(patt[0], y-1)) & FULL, (cur ^ shift(patt[1], y-1)) & FULL, (below ^ shift(patt[2], y-1)) & FULL);
		}
	}
	
	static int shift(int x, int amt)
	{
		if(amt < 0)
			return x >> Math.abs(amt);
		return x << amt;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			R = sc.nextInt();
			C = sc.nextInt();
			if(R == 0 && C == 0)
				break;
			FULL = (OFF = 1<<C) - 1;
			patt = new int[3];
			for(int i = 0; i < 3; ++i)
			{
				String s = sc.next();
				for(int j = 0; j < 3; ++j)
					if(s.charAt(j) == '*')
						patt[i] |= 1<<j;
			}

			memo = new int[R * C][1<<C*3];
			for(int i = 0; i < R * C; ++i)
				Arrays.fill(memo[i], -1);
			
			out.println("Case #" + tc++);
			if(dp(0, 0, 0, 0, 0) == INF)
				out.println("Impossible.");
			else
			{
				sol = new ArrayList<Integer>();
				print(0, 0, 0, 0, 0);
				for(int i = 0, size = sol.size(); i < size; ++i)
					out.printf("%d%c", sol.get(i), i == size - 1 ? '\n' : ' ');
			}
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