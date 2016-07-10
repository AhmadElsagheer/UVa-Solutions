package v115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
 * Solution 1: Greedy
 * Solution 2: DP
 */
public class SimpleAdjacencyMaximization_UVa11532 {

	static Pair[][][] memo;
	
	static Pair dp(int lst, int p, int q)
	{
		if(p + q == 0)
			return new Pair(0, 0);
		if(memo[lst][p][q] != null)
			return memo[lst][p][q];
		
		Pair ret = new Pair(-1, 0), nxt;
		if(p > 0)
		{
			nxt = dp((lst & 1)<<1 | 1, p - 1, q);
			ret.num = 1l<<(p+q-1) | nxt.num;
			ret.ones = nxt.ones + ((lst & 1) == 0 ? 1 : 0);
		}
		
		if(q > 0)
		{
			nxt = dp((lst & 1)<<1, p, q - 1);
			Pair cur = new Pair(nxt.ones + (lst == 3 ? 1 : 0), nxt.num);
			if(cur.ones > ret.ones || cur.ones == ret.ones && cur.num < ret.num)
				ret = cur;
		}
		
		return memo[lst][p][q] = ret;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

//		memo = new Pair[4][51][52];
//		int tc = sc.nextInt();
//		while(tc-->0)
//		{
//			Pair ans = dp(1, sc.nextInt(), sc.nextInt());
//			out.println(ans.num);
//		}
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int p = sc.nextInt(), q = sc.nextInt(), c = 0;
			long ans = 0;
			while(p > 1 && q > 0)
			{
				ans = ans<<3 | 5;
				p -= 2;
				--q;
				c += 3;
			}
			if(p > 0 && q > 0)
			{
				ans |= 1l<<c;
				--p;
				--q;
			}
			while(p-- > 0)
				ans = ans<<1 | 1;
			//remaining zeros are added as leading here...
			out.println(ans);
		}
		
		out.flush();
		out.close();
	}
	
	static class Pair
	{
		int ones;
		long num;
		
		Pair(int a, long b) { ones = a; num = b; }
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