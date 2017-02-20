package v100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TakeTheLand_UVa10074 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			if(R==0)
				break;
			int[][] prefix = new int[R][C];
			for(int i = 0; i < R; i++)
			{
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < C; j++)
				{
					prefix[i][j] = Integer.parseInt(st.nextToken());
					if(j > 0) prefix[i][j] += prefix[i][j-1];
				}
			}
			int max = 0;
			for(int j = 0; j < C; j++)
				for(int k = j; k < C; k++)
				{
					int cur = 0, start = 0;
					for(int r = 0; r < R; r++)
					{
						if(j > 0)	cur += prefix[r][k] - prefix[r][j-1];
						else		cur += prefix[r][k];
						
						if(cur==0)
							max = Math.max(max, (r - start + 1) * (k - j + 1));
						if(cur!=0)
						{
							start = r + 1;
							cur = 0;
						}
					}
				}
			sb.append(max).append("\n");
		}
		System.out.print(sb);
	}
}
