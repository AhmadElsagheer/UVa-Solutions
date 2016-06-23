package cp2_2;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
	
public class P4873279{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
		
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++)
		{
			br.readLine();
			int n = Integer.parseInt(br.readLine());
			ArrayList<String> phonebook = new ArrayList<String>(n);
			for(int j = 0; j < n; j++)
				phonebook.add(getNumber(br.readLine()));
			
			Collections.sort(phonebook);
			int count = 1; boolean dups = false;int size = phonebook.size();
			
			for(int j = 1; j < size; j++)
				if(phonebook.get(j).equals(phonebook.get(j-1)))
					count++;
				else
					if(count!=1)
					{
						sb.append(phonebook.get(j-1)).append(" "+count+"\n");
						count = 1;dups = true;
					}
			if(count!=1)
			{
				sb.append(phonebook.get(size-1)).append(" "+count+"\n");
				count = 1;dups = true;
			}
			if(!dups)
				sb.append("No duplicates.\n");
				
			if(i!=t-1)
				sb.append("\n");
		}
		System.out.print(sb);
	}
	
	public static String getNumber(String code)
	{
		String number = "";
		int k = 1;
		int i;
		for(i = 0; k <= 3; i++)
			switch(code.charAt(i))
			{
			case '-': continue;
			case 'A': case 'B': case 'C': number += "2";k++;break;
			case 'D': case 'E': case 'F': number += "3";k++;break;
			case 'G': case 'H': case 'I': number += "4";k++;break;
			case 'J': case 'K': case 'L': number += "5";k++;break;
			case 'M': case 'N': case 'O': number += "6";k++;break;
			case 'P': case 'R': case 'S': number += "7";k++;break;
			case 'T': case 'U': case 'V': number += "8";k++;break;
			case 'W': case 'X': case 'Y': number += "9";k++;break;
				default: number += code.charAt(i);k++;
			}
		number += "-";
		for(; k <= 7; i++)
			switch(code.charAt(i))
			{
			case '-': continue;
			case 'A': case 'B': case 'C': number += "2";k++;break;
			case 'D': case 'E': case 'F': number += "3";k++;break;
			case 'G': case 'H': case 'I': number += "4";k++;break;
			case 'J': case 'K': case 'L': number += "5";k++;break;
			case 'M': case 'N': case 'O': number += "6";k++;break;
			case 'P': case 'R': case 'S': number += "7";k++;break;
			case 'T': case 'U': case 'V': number += "8";k++;break;
			case 'W': case 'X': case 'Y': number += "9";k++;break;
				default: number += code.charAt(i);k++;
			}
		return number;
	}
}