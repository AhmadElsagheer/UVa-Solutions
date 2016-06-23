package cp3_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DetermineIt_UVa10520 {

	static int start, n;
	static long[][] memo;
	public static long backtrack(int i, int j)
	{
		if(memo[i][j]!=-1)
			return memo[i][j];
		if(i==n && j==1)
			return start;
		if(i < j)
		{
			long max = -1;
			for(int k = i; k < j; k++)
				max = Math.max(max, backtrack(i,k) + backtrack(k+1,j));
			return memo[i][j] = max;
		}
		else
		{
	
			long max1 = 0;
			if(i < n)
			{
				for(int k = i + 1; k <= n; k++)
					max1 = Math.max(max1, backtrack(k,1) + backtrack(k,j));
			}
			long max2 = 0;
			if(j > 0)
			{
				for(int k = 1; k < j; k++)
					max2 = Math.max(max2, backtrack(n,k)+backtrack(i,k));
			}
					
			return memo[i][j] = max1 + max2;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			memo = new long[n+1][n+1];
			memo[n][1] = start;
			for(int i = 0; i <= n; i++)
				Arrays.fill(memo[i], -1);
			out.println(backtrack(1,n));
		}
		out.flush();
	}
}
