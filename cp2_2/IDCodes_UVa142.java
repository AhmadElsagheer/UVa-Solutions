package cp2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// generate next permutation

public class IDCodes_UVa142 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			char[] characters = br.readLine().toCharArray();
			if(characters[0]=='#')
				break;
			boolean found = false;
			for(int i = characters.length - 1; i > 0; i--)
			{
				int current = characters[i];
				int next =  characters[i-1];
				if(next-current >= 0)
					continue;
				found = true;int k = 1;
				
				while(characters[characters.length - k]-next<=0)
					k++;
				char tmp = characters[characters.length - k];
				characters[characters.length - k] = characters[i-1];
				characters[i - 1] = tmp;
				//reverse from i to end of the string
				int j = characters.length - 1;
				while(i<j)
				{
					char temp = characters[j];
					characters[j] = characters[i];
					characters[i] = temp;
					i++; j--;
				}
				break;
			}
			if(!found)
				sb.append("No Successor\n");
			else
			{
				for(int i = 0; i < characters.length; i++)
					sb.append(characters[i]);
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}
}
