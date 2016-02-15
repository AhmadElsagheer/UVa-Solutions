package cp2_2;
	
import java.io.*; 

public class Soundex{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(br.ready())
		{
			String word = br.readLine();
			String code = ""; String lastDigit = "";
			for(int i = 0; i < word.length(); i++)
			{
				String curDigit = getDigit(word.charAt(i));
				if(!curDigit.equals(lastDigit))
					code +=curDigit;
				lastDigit = curDigit;
			}
					
			
			sb.append(code+"\n");
		}
		System.out.print(sb);
		
	}
	
	public static String getDigit(char c)
	{
		switch(c)
		{
			case 'B': case 'F': case 'P': case 'V':
				return "1";
			case 'C': case 'G': case 'J': case 'K':
			case 'Q': case 'S': case 'X': case 'Z':
				return "2";
			case 'D': case 'T':	return "3";
			case 'L': return "4";
			case 'M': case 'N':	return "5";
			case 'R': return "6";
		}
		return "";
	}
	
}	