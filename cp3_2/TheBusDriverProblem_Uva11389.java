package cp3_2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class TheBusDriverProblem_Uva11389 {
		

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			if(n==0)
				break;
			int d = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int[] morning = new int[n];
			int[] afternoon = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++)
				morning[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++)
				afternoon[i] = Integer.parseInt(st.nextToken());
			
			int minCost = 0;
			for(int i = 0; i < n; i++)
			{
				int selectedAfternoon = -1;
				int minDistance = 30000;
				for(int j =0; j < n; j++)
				{
					if(afternoon[j]==-1)
						continue;
					int distance = morning[i] + afternoon[j] - d;
					if(distance < minDistance)
					{
						minDistance = distance;
						selectedAfternoon = j;
					}
				}
				afternoon[selectedAfternoon] = -1;        //this route can not be used again
				if(minDistance>0)
					minCost += r*minDistance;
			}
			
			sb.append(minCost+"\n");
		}

		System.out.print(sb);
	}
	

}
