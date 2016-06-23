package cp3_2;

	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class CitizenAttentionOffices_UVa10660{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
				
		int t = Integer.parseInt(br.readLine());
		for(int k = 0; k < t; k++)
		{
			int n = Integer.parseInt(br.readLine());
			int[][] areas = new int[n][3];
			for(int i = 0; i < n; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int row = Integer.parseInt(st.nextToken());
				int column = Integer.parseInt(st.nextToken());
				int people = Integer.parseInt(st.nextToken());
				areas[i][0] = row;
				areas[i][1] = column;
				areas[i][2] = people;
			}
			int minTotalDistance = -1;
			int[] offices = new int[5];
			int[] optimum = new int[5];
			for(offices[0] = 0; offices[0] < 21; offices[0]++)
				for(offices[1] = offices[0]+1; offices[1] < 22; offices[1]++)
					for(offices[2] = offices[1]+1; offices[2] < 23; offices[2]++)
						for(offices[3]= offices[2]+1; offices[3]< 24; offices[3]++)
							for(offices[4] = offices[3]+1; offices[4] < 25; offices[4]++)
							{
								int currentTotalDistance = 0;
								for(int i = 0; i < n; i++)
								{
									int minLocalDistance = -1;
									for(int j = 0; j < 5; j++)
									{
										int officeRow = offices[j]/5;
										int officeColumn = offices[j]%5;
										int currentLocalDistance = Math.abs(officeRow-areas[i][0]) + Math.abs(officeColumn-areas[i][1]);
										if(minLocalDistance==-1 || currentLocalDistance < minLocalDistance)
											minLocalDistance = currentLocalDistance;
												
									}
									currentTotalDistance += minLocalDistance*areas[i][2];
								}
								if(minTotalDistance == -1 || currentTotalDistance < minTotalDistance)
								{
									minTotalDistance = currentTotalDistance;
									optimum = offices.clone();
								}
							}
			sb.append(optimum[0]+" "+optimum[1]+" "+optimum[2]+" "+optimum[3]+" "+optimum[4]+"\n");
								
			
		}
		System.out.print(sb);

		
		
	}
}	
