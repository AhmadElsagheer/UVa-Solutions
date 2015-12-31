package cp4_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TowerOfCubes_UVa10051 {

	static final int UNCAL = -1;
	static int[][] memo;
	static int[][] color;
	static String[] face = new String[]{"front","left","top","bottom","right","back"};
	static int N;
	static StringBuilder sb = new StringBuilder();
	
	static int dp(int idx, int pre_color)
	{
		if(idx == N) return 0;
		
		if(memo[idx][pre_color]!=UNCAL)
			return memo[idx][pre_color];
		
		int max = dp(idx+1,pre_color);
		for(int i = 0; i < 6; i++)
			if(pre_color == 0 || color[idx][i] == pre_color)
				max = Math.max(max, 1 + dp(idx+1,color[idx][5-i]));
			
			
		
		return memo[idx][pre_color] = max;
	}
	
	static void print(int idx, int pre_color)
	{
		if(idx == N)
			return;
		
		int optimal = dp(idx,pre_color);
		
		for(int i = 5; i >= 0; i--)
			if((pre_color == 0 || color[idx][i] == pre_color) && optimal == 1 + dp(idx+1,color[idx][5-i]))
			{
				print(idx+1,color[idx][5-i]);
				sb.append(N - idx).append(" ").append(face[5-i]+"\n");
				return;
			}
		print(idx+1,pre_color);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int k = 1;
		while(true)
		{
			N = Integer.parseInt(br.readLine());
			if(N == 0)
				break;
			if(k!=1)
				sb.append("\n");
			sb.append("Case #"+k+++"\n");
			color = new int[N][6];
			for(int i = N - 1; i >= 0; i--)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 3; j++)
				{
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					color[i][j] = x;
					color[i][5-j] = y;
				}
			}
			memo = new int[N][101];
			for(int i = 0; i < N; i++)
				Arrays.fill(memo[i], UNCAL);
			
			int max = dp(0,0);
			sb.append(max+"\n");
			print(0,0);
		}
		System.out.print(sb);
	}
	
}
