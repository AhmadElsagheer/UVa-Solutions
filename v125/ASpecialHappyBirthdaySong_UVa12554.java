package v125;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
	
public class ASpecialHappyBirthdaySong_UVa12554{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		String[] people = new String[n];
		for(int i = 0; i < n; i++)
		{
			people[i] = br.readLine();
		}
		
		for(int i = 1; i <= n || i%16-1 != 0; i++)
		{
			sb.append(people[(i-1)%n]+": ");
			if(i%4==0)
			{
				if(i%16!=0&&i%16%12==0)
					sb.append("Rujia\n");
				else
					sb.append("you\n");
			}
			else
				if(i%4==3)
					sb.append("to\n");
				else
					if(i%4==2)
						sb.append("birthday\n");
					else
						sb.append("Happy\n");
		}
		
		System.out.print(sb);
		
		
	}
		
}
