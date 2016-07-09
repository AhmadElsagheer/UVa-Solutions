package v101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Yahtzee_UVa10149 {
		
	static int[][] round, memo;
	static int[][] roundInfo;
	
	static int dp(int idx, int used, int fSum)
	{
		if(idx == 13)
			return fSum >= 63 ? 35: 0;
		
		if(memo[used][fSum] != -1)
			return memo[used][fSum];
		int rSum = roundInfo[idx][0], ret = 0;
		
		// Category 7
		if((used & 1) == 0)
			ret = Math.max(ret, rSum + dp(idx + 1, used | 1, fSum));
		
		// Categories 1 - 6
		for(int i = 1; i <= 6; ++i)
			if((used & 1<<i) == 0)
			{
				int x = roundInfo[idx][i];
				ret = Math.max(ret, x + dp(idx + 1, used | 1<<i, fSum + x));
			}
		
		int maxSimilar = roundInfo[idx][8];
		// Category 8
		if((used & 1<<7) == 0)
			ret = Math.max(ret, (maxSimilar >= 3 ? rSum : 0) + dp(idx + 1, used | 1<<7, fSum));
		
		
		// Category 9		
		if((used & 1<<8) == 0)
			ret = Math.max(ret, (maxSimilar >= 4 ? rSum : 0) + dp(idx + 1, used | 1<<8, fSum));
		
		// Category 10
		if((used & 1<<9) == 0)
			ret = Math.max(ret, (maxSimilar >= 5 ? 50 : 0) + dp(idx + 1, used | 1<<9, fSum));
		
		int seq = roundInfo[idx][9];
		
		// Category 11
		if((used & 1<<10) == 0)
			ret = Math.max(ret,  (seq >= 4 ? 25 : 0) + dp(idx + 1, used | 1<<10, fSum));
		

		// Category 12
		if((used & 1<<11) == 0)
			ret = Math.max(ret,  (seq >= 5 ? 35 : 0) + dp(idx + 1, used | 1<<11, fSum));
		
		// Category 13
		if((used & 1<<12) == 0)
			ret = Math.max(ret, (maxSimilar == 3  && roundInfo[idx][7] == 1 ? 40 : 0) + dp (idx + 1, used | 1<<12, fSum));
		
		return memo[used][fSum] = ret;
	}
	
	static int[] sol;
	static void print(int idx, int used, int fSum)
	{
		if(idx == 13)
		{
			sol[13] = fSum >= 63 ? 35 : 0;
			return;
		}
		
		int rSum = roundInfo[idx][0], opt = dp(idx, used, fSum);
		
		// Category 7
		if((used & 1) == 0 && opt == rSum + dp(idx + 1, used | 1, fSum))
		{
			sol[6] = rSum;
			print(idx + 1, used | 1, fSum);
			return;
		}
		// Categories 1 - 6
		for(int i = 1; i <= 6; ++i)
			if((used & 1<<i) == 0)
			{
				int x = roundInfo[idx][i];
				if(opt == x + dp(idx + 1, used | 1<<i, fSum + x))
				{
					sol[i-1] = x;
					print(idx + 1, used | 1<<i, fSum + x);
					return;					
				}
			}
		
		int maxSimilar = roundInfo[idx][8];
		// Category 8
		if((used & 1<<7) == 0 && opt == (maxSimilar >= 3 ? rSum : 0) + dp(idx + 1, used | 1<<7, fSum))
		{
			sol[7] = maxSimilar >= 3 ? rSum : 0;
			print(idx + 1, used | 1<<7, fSum);
			return;
		}
		
		// Category 9		
		if((used & 1<<8) == 0 && opt == (maxSimilar >= 4 ? rSum : 0) + dp(idx + 1, used | 1<<8, fSum))
		{
			sol[8] = maxSimilar >= 4 ? rSum : 0;
			print(idx + 1, used | 1<<8, fSum);
			return;
		}
		
		// Category 10
		if((used & 1<<9) == 0 && opt == (maxSimilar >= 5 ? 50 : 0) + dp(idx + 1, used | 1<<9, fSum))
		{
			sol[9] = maxSimilar >= 5 ? 50 : 0;
			print(idx + 1, used | 1<<9, fSum);
			return;
		}
		
		int seq = roundInfo[idx][9];
		
		// Category 11
		if((used & 1<<10) == 0 && opt == (seq >= 4 ? 25 : 0) + dp(idx + 1, used | 1<<10, fSum))
		{
			sol[10] = seq >= 4 ? 25 : 0;
			print(idx + 1, used | 1<<10, fSum);
			return;
		}

		// Category 12
		if((used & 1<<11) == 0 && opt == (seq >= 5 ? 35 : 0) + dp(idx + 1, used | 1<<11, fSum))
		{
			sol[11] = seq >= 5 ? 35 : 0;
			print(idx + 1, used | 1<<11, fSum);
			return;
		}
		
		// Category 13
		sol[12] = maxSimilar == 3  && roundInfo[idx][7] == 1 ? 40 : 0;
		print(idx + 1, used | 1<<12, fSum);
	}
	
	static int maxSimilar(int[] x)
	{
		int ret = 0;
		int[] f = new int[6];
		for(int y: x)
			f[y-1]++;
		for(int y: f)
			ret = Math.max(ret, y);
		return ret;
	}
	
	static int pairExists(int[] x)
	{
		int[] f = new int[6];
		for(int y: x)
			f[y-1]++;
		for(int y: f)
			if(y == 2)
				return 1;
		return 0;
	}
	
	static int longestSequence(int[] x)
	{
		int[] f = new int[6];
		for(int y: x)
			f[y-1]++;
		int ans = 0;
		for(int i = 0; i < 3; ++i)
		{
			int cur = 0;
			for(int j = i; j < 6; ++j)
				if(f[j] >= 1)
					++cur;
				else
					break;
			ans = Math.max(ans, cur);
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(sc.ready())
		{
			round = new int[13][5];
			roundInfo = new int[13][10];
			for(int i = 0; i < 13; ++i)
			{
				int s = 0, r[] = round[i];
				for(int j = 0; j < 5; ++j)
				{
					int x = sc.nextInt();
					s += r[j] = x;
					roundInfo[i][x] += x;
				}
				roundInfo[i][0] = s;
				roundInfo[i][7] = pairExists(r);
				roundInfo[i][8] = maxSimilar(r);
				roundInfo[i][9] = longestSequence(r);
			}
			memo = new int[1<<13][106];
			for(int i = 0; i < 1 << 13; ++i)
				Arrays.fill(memo[i], -1);
			sol = new int[14];
			print(0, 0, 0);
			for(int x: sol)
				out.print(x + " ");
			out.println(dp(0, 0, 0));
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