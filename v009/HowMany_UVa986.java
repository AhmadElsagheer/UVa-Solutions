package v009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HowMany_UVa986 {

	static final int UNCAL = -1;
	static int[][][][] memo;
	static int k, n;
	
	static int dp(int x, int y, int r, int y_last)
	{
		if(r < 0 || y < 0)
			return 0;
		if(x == n<<1)
			if(y == 0 && r == 0)
				return 1;
			else
				return 0;
		if(memo[x][y][r][y_last] != UNCAL)
			return memo[x][y][r][y_last];
		
		return memo[x][y][r][y_last] = dp(x+1,y+1,r,0) + dp(x+1,y-1, y_last==0 && y == k?(r-1):r,1);	
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			memo = new int[n<<1][n<<1][r+1][2];
			for(int i = 0; i < n<<1; i++)
				for(int j = 0; j < n<<1; j++)
					for(int k = 0; k < r + 1; k++)
						Arrays.fill(memo[i][j][k], UNCAL);
			int ways = dp(0,0,r,0);
			sb.append(ways+"\n");
		}
		System.out.print(sb);
	}
}
