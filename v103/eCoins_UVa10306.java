package v103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class eCoins_UVa10306 {

	static final int UNCAL = -1, INF = 100000000;
	static int[][][] memo;
	static int[][] value;
	static int N,S;
	
	public static int dp(int i, int X, int Y)
	{
		if(X * X + Y * Y== S * S)
			return 0;
		if(X > S || Y > S || i == N)
			return INF;
		if(memo[i][X][Y]!=UNCAL)
			return memo[i][X][Y];
		
		return memo[i][X][Y] = Math.min(1+dp(i,X+value[i][0],Y+value[i][1]),dp(i+1,X,Y));
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		
		int TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		while(TC-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());
			value = new int[N][2];
			for(int i = 0; i < N; i++)
			{
				st = new StringTokenizer(br.readLine());
				value[i][0] = Integer.parseInt(st.nextToken());
				value[i][1] = Integer.parseInt(st.nextToken());
			}
			memo = new int[N][S+1][S+1];
			for(int i = 0; i < N; i++)
				for(int j = 0; j < S + 1; j++)
					Arrays.fill(memo[i][j], UNCAL);
			
			int min = dp(0,0,0);
			if(min >= INF)
				out.println("not possible");
			else
				out.println(min);	
			
			if(TC!=0)
				br.readLine();
						
		}
		
		out.flush();
		out.close();
	}
	
}
