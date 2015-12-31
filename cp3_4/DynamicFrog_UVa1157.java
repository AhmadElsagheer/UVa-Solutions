package cp3_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DynamicFrog_UVa1157 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for(int i = 1; i <= tc; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int[][] rocks = new int[n+2][2];
			rocks[n+1][0] = d;
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++)
			{
				String rock = st.nextToken();
				if(rock.charAt(0)=='S')
					rocks[j][1] = 1;
				rocks[j][0] = Integer.parseInt(rock.substring(2));
			}
			int max = 0;
			boolean[] invalid = new boolean[n+2];
			for(int j = 0; j < n + 1; j++)
				if(invalid[j])
				{
					if(rocks[j+1][1]==1)
					{
						max = Math.max(rocks[j+2][0] - rocks[j][0], max);
						if(rocks[j+2][1]==1)
							invalid[j+2] = true;
						j++;
					}
					else
						max = Math.max(rocks[j+1][0] - rocks[j][0], max);
					
				}
				else
				{
					max = Math.max(rocks[j+1][0] - rocks[j][0], max);
					if(rocks[j+1][1]==1)
						invalid[j+1] = true;
				}
			for(int j = 0; j < n + 1; j++)
				if(invalid[j+1])
					max = Math.max(rocks[j+2][0] - rocks[j++][0], max);
				else
					max = Math.max(rocks[j+1][0] - rocks[j][0], max);
			sb.append("Case "+i+": "+max+"\n");
		}
		System.out.print(sb);
	}
}
