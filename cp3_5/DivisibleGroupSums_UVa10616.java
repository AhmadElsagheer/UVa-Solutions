package cp3_5;

import java.util.*;
import java.io.*;

public class DivisibleGroupSums_UVa10616 {

	static final int UNCAL = -1;
	static int[][][] memo;
	static int N, M, D;
	static int[] num;
	
	public static int dp(int i, int mod, int cur)
	{
		if(cur==M)
			return mod%D==0?1:0;
		if(i==N)
			return 0;
		if(memo[i][mod][cur]!=UNCAL)
			return memo[i][mod][cur];
		int take = dp(i+1,fix(mod+num[i]),cur+1);
		int leave = dp(i+1,mod,cur);
		
		return memo[i][mod][cur] = take + leave;
	}
	
	public static int fix(int x)
	{
		return (x%D+D)%D;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int k = 1;
		while(true)
		{
			StringTokenizer st= new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			if(N==0 && Q==0)
				break;
			num = new int[N];
			for(int i = 0; i < N; i++)
				num[i] = Integer.parseInt(br.readLine());
			sb.append("SET "+k+++":\n");
			for(int j = 1; j <= Q; j++)
			{
				
				st = new StringTokenizer(br.readLine());
				D = Integer.parseInt(st.nextToken());
				M = Integer.parseInt(st.nextToken());
				memo = new int[N][D][M];
				for(int i = 0; i < N; i++)
					for(int z = 0; z < D; z++)
						Arrays.fill(memo[i][z], UNCAL);
				int ways = dp(0,0,0);
				sb.append("QUERY "+j+": "+ways+"\n");
			}
		}
		System.out.print(sb);
		
	}
}
