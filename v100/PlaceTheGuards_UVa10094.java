package v100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlaceTheGuards_UVa10094 {

	static int[] dx = new int[]{0,0,1,1,1,-1,-1,-1};
	static int[] dy = new int[]{-1,1,1,0,-1,1,0,-1};
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(br.ready())
		{
			int n = Integer.parseInt(br.readLine());
			if(n < 4)
			{
				sb.append("Impossible\n");
				continue;
			}
			int[] row = new int[n+1];
			
			for(int i = 1; i <= n/2; i++)
				row[i] = 2 * i;
			for(int i = n/2 + 1, k = 1; i <= n; i++,k+=2)
				row[i] = k;
			if(n%6==2)
			{
				row[n/2+1] = 3;
				row[n/2+2] = 1;
				for(int i = n/2+3; i <= n; i++)
					row[i] += 2;
				row[n] = 5;
			}
			else
				if(n%6==3)
				{
					for(int i = 1; i <= n/2; i++)
						row[i] += 2;
					row[n/2] = 2;
					for(int i = n/2+1; i <= n; i++)
						row[i] += 4;
					row[n-1] = 1;
					row[n] = 3;
				}
					
					
			
			
			for(int i = 1; i < n; i++)
				sb.append(row[i]).append(" ");
			sb.append(row[n]).append("\n");
		}
		System.out.print(sb);
	}
	
	
}
