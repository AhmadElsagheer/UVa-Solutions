package cp2_2;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; 

public class Hartals{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < t; i++)
		{
			int n = Integer.parseInt(br.readLine());
			int p = Integer.parseInt(br.readLine());
			boolean[] days = new boolean[n];int count=0;
			for(int j = 0; j < p; j++)
			{
				int curh = Integer.parseInt(br.readLine());
				int curDay = 0;
				while((curDay += curh)<=n)
				{
					if(curDay%7==6||curDay%7==0||days[curDay-1])
						continue;
					count++;
					days[curDay-1] = true;
				}
			}
			sb.append(count+"\n");
			
		}
		System.out.print(sb);
	}
	
	
}	