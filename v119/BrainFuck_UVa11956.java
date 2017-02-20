package v119;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
	
public class BrainFuck_UVa11956{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= t; i++)
		{
			int[] memory = new int[100]; int pointer = 0;
			String line = br.readLine();
			for(int j = 0; j < line.length(); j++)
			{
				char c = line.charAt(j);
				if(c=='>')
					pointer = (pointer + 1)%100;
				else
					if(c=='<')
					{
						pointer--;
						if(pointer==-1)
							pointer = 99;
					}
					else
						if(c=='+')
							memory[pointer] = (memory[pointer] + 1)%256;
						else
							if(c=='-')
							{
								
									memory[pointer]--;
									if(memory[pointer]==-1)
										memory[pointer] = 255;
								
							}
			}
			
			
			
			sb.append("Case ").append(i).append(": ");
			for(int j = 0; j < 99; j++)
			{
				String tmp = Integer.toHexString(memory[j]).toUpperCase();
				if(tmp.length()==1) tmp = "0" + tmp;
				sb.append(tmp).append(" ");
			}
			String tmp = Integer.toHexString(memory[99]).toUpperCase();
			if(tmp.length()==1) tmp = "0" + tmp;
			sb.append(tmp).append("\n");	
		}
		
		
		
		System.out.print(sb);
	
	
	}
}	