package v110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LittleRedRidingHood_UVa11067 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int C = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			if(R == 0)
				break;
			int n = Integer.parseInt(br.readLine());
			boolean[][] wolf = new boolean[R+1][C+1];
			while(n-->0)
			{
				st = new StringTokenizer(br.readLine());
				int j = Integer.parseInt(st.nextToken());
				int i = Integer.parseInt(st.nextToken());
				wolf[i][j] = true;
			}
			long[][] dp = new long[R+1][C+1];
			dp[0][0] = 1;
			for(int i = 0; i <= R; i++)
				for(int j = 0; j <= C; j++)
				{
					if(i + 1 <= R && !wolf[i+1][j])
						dp[i+1][j] += dp[i][j];
					if(j + 1 <= C && !wolf[i][j+1])
						dp[i][j+1] += dp[i][j];
				}
			long ways = dp[R][C];
			if(ways == 0)
				sb.append("There is no path.\n");
			else
				if(ways == 1)
					sb.append("There is one path from Little Red Riding Hood's house to her grandmother's house.\n");
				else
					sb.append("There are "+ways+" paths from Little Red Riding Hood's house to her grandmother's house.\n");
			
		}
		System.out.print(sb);
	}
}
