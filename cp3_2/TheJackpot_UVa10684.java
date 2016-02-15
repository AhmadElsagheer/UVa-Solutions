package cp3_2;


import java.io.*;
import java.util.*;

public class TheJackpot_UVa10684 {
		
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while(true)
		{
			
			
			
			int n = Integer.parseInt(br.readLine());
			if(n==0)
				break;
			
			int maxStreak = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int winStreak = 0;
			while(n-->0)
			{
				if(!st.hasMoreTokens())
					st = new StringTokenizer(br.readLine());
				
				int current = Integer.parseInt(st.nextToken());
				winStreak += current;
				if(winStreak < 0)
					winStreak = 0;
				else
					maxStreak = Math.max(maxStreak, winStreak);

			
			}
			if(maxStreak==0)
				sb.append("Losing streak.\n");
			else
				sb.append("The maximum winning streak is "+maxStreak+".\n");
			


			
		}
		
		System.out.print(sb);
	}
	

	

}
