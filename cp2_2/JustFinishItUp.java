package cp2_2;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class JustFinishItUp{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		
		for(int i = 1; i <= t; i++)
		{
			int n = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			StringTokenizer st;
			int sumP = 0;int[] ps = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++)
			{
				if(!st.hasMoreTokens())
				{
					st = new StringTokenizer(br.readLine());	
				}
				ps[j] = Integer.parseInt(st.nextToken());
				sumP += ps[j];
			}
			int sumQ = 0; int[] qs = new int[n];
			
			for(int j = 0; j < n; j++)
			{
				if(!st.hasMoreTokens())
				{
					st = new StringTokenizer(br.readLine());	
				}
				qs[j] = Integer.parseInt(st.nextToken());
				sumQ += qs[j];
			}		
			if(sumQ>sumP)
				sb.append("Case "+i+": Not possible\n");
			else
			{
				int start = 0; int tank = 0; 
				for(int j = 0; j < n; j++)
				{
					tank += ps[j] - qs[j];
					if(tank<0)
					{
						tank = 0;start = j + 1;
					}
				}
				
				sb.append("Case "+i+": Possible from station "+(++start)+"\n");
			}
		}
		
		System.out.print(sb);
	}
	
}	