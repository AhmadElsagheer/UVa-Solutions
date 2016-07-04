package v113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BlobsInTheBoard_UVa11391 {

	static int[][][] memo;
	static int R, C;
	static int[] dx = new int[]{0,0,1,1,1,-1,-1,-1};
	static int[] dy = new int[]{1,-1,1,0,-1,1,0,-1};
	
	static int dp(int board)
	{
		if((board & (board - 1)) == 0) return 1;
		if(memo[R-1][C-1][board] != -1) return memo[R-1][C-1][board];
		
		int count = 0;
		for(int idx = 0; idx < R * C; idx++)
			if((board & (1<<idx)) != 0)
			{
				int  i = idx / C, j = idx%C;
				for(int k = 0; k < 8; k++)
				{
					int x = dx[k], y = dy[k];
					if(valid(i+x, j+y) && valid(i+(x<<1),j+(y<<1)))	
						if((1<<(j+y + (i+x) * C) & board) != 0 && (1<<(j+(y<<1) + (i+(x<<1)) * C) & board) == 0)
							count += dp(board ^ (1<<idx) ^ (1<<(j+y + (i+x) * C) | (1<<(j+(y<<1) + (i+(x<<1)) * C))));
				}
					
			}
			
				
		return memo[R-1][C-1][board] = count;
	}
	
	static boolean valid(int i, int j)
	{
		if(i < 0 || j < 0 || i >= R || j >= C)
			return false;
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		 
		 int tc = Integer.parseInt(br.readLine());
		 memo = new int[4][4][1<<16];
		 for(int i = 0; i < 4; i++)
			 for(int j = 0; j < 4; j++)
				 Arrays.fill(memo[i][j], -1);
		 for(int t = 1; t <= tc; t++)
		 {
			 sb.append("Case "+t+": ");
			 StringTokenizer st = new StringTokenizer(br.readLine());
			 R = Integer.parseInt(st.nextToken());
			 C = Integer.parseInt(st.nextToken());
			 int n = Integer.parseInt(st.nextToken());
			 int board = 0;
			 while(n-->0)
			 {
				 st = new StringTokenizer(br.readLine());
				 int x = Integer.parseInt(st.nextToken()) - 1;
				 int y = Integer.parseInt(st.nextToken()) - 1;
				 board |= 1<<(y + x * C);
			 }
			 sb.append(dp(board)+"\n");
		}
		 System.out.print(sb);
	}
}
