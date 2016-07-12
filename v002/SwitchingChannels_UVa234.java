package v002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SwitchingChannels_UVa234 {
	
	static int N, A, totalMiss;
	static int[] len, sol, cur, miss, align, imp;
	
	static void bt(int idx, int used)
	{
		if(idx == N)
		{
			int[] curMiss = new int[5];
			for(int i = 0; i < A; ++i)
			{
				int t = align[i], missTime = t, curTime = 0;
				for(int j = 0; j < N; ++j)
				{
					curTime += len[cur[j]];
					missTime = Math.min(missTime, Math.abs(t - curTime));
				}
				curMiss[imp[i]] += missTime;
			}
			for(int i = 0; i < 5; ++i)
				if(curMiss[i] < miss[i])
				{
					totalMiss = 0;
					for(int j = 0; j < N; ++j)
						sol[j] = cur[j];
					for(int j = 0; j < 5; ++j)
						totalMiss += miss[j] = curMiss[j];
					break;
				}
				else if(curMiss[i] > miss[i])
					break;
			return;
		}
		for(int i = 0; i < N; ++i)
			if((used & 1<<i) == 0)
			{
				cur[idx] = i;
				bt(idx + 1, used | 1<<i);
			}
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = 1;
		while(true)
		{
			N = sc.nextInt();
			if(N == 0)
				break;
			len = new int[N]; cur = new int[N]; sol = new int[N];
			totalMiss = 0;
			for(int i = 0; i < N; ++i)
				len[i] = sc.nextInt();
			A = sc.nextInt();
			align = new int[A]; imp = new int[A]; miss = new int[5];
			Arrays.fill(miss, (int)1e9);
			for(int i = 0; i < A; ++i)
			{
				imp[i] = sc.nextInt() - 1;
				align[i] = sc.nextInt();
			}
			bt(0, 0);
			out.printf("Data set %d\n  Order:", tc++);
			for(int x: sol)
				out.print(" " + len[x]);
			out.printf("\n  Error: %d\n", totalMiss);
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