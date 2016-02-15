package cp2_2;
	
import java.io.*; 

	
public class MachinedSurfaces{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	    int t;
		while((t=Integer.parseInt(br.readLine()))!=0)
		{
			int[] rowXs = new int[t]; int max = 0;
			for(int i = 0; i < t; i++)
			{
				int count = 0;
				String s = br.readLine();
				for(int j = 0; j < 25; j++)
					if(s.charAt(j)=='X')
						count++;
				if(count > max)
					max = count;
				rowXs[i] = count;
			}
			int spaces = 0;
			for(int i = 0; i < t; i++)
				spaces += max - rowXs[i];
			sb.append(spaces+"\n");
		}
		System.out.print(sb);
		
	}
	
	
	
}	