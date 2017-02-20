package v004;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; 
	
public class Palindromes_UVa401{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(br.ready())
		{
			String word = br.readLine();
			if(isPalindrome(word))
				if(isMirrored(word))
					sb.append(word).append(" -- is a mirrored palindrome.\n\n");
				else
					sb.append(word).append(" -- is a regular palindrome.\n\n");
			else
				if(isMirrored(word))
					sb.append(word).append(" -- is a mirrored string.\n\n");
				else
					sb.append(word).append(" -- is not a palindrome.\n\n");
		}
		System.out.print(sb);
		
	
	}
	
	public static boolean isPalindrome(String s)
	{
		if(s.length()<=1)
			return true;
		if(s.charAt(0)!=s.charAt(s.length()-1))
			return false;
		return isPalindrome(s.substring(1,s.length()-1));
	}
	
	public static boolean isMirrored(String s)
	{
		if(s.length()==0)
			return true;
		if(s.charAt(0)!=getReverse(s.charAt(s.length()-1)))
			return false;
		if(s.length()==1)
			return true;
		return isMirrored(s.substring(1,s.length()-1));
	}
	
	public static char getReverse(char c)
	{
		switch(c)
		{
		case 'A': case 'H': case 'I': case 'M': case 'O': 
		case 'T': case 'U': case 'V': case 'W': case 'X':
		case 'Y': case '1': case '8': return c;
		case 'E': return '3';
		case 'J': return 'L';
		case 'L': return 'J';
		case 'S': return '2';
		case 'Z': return '5';
		case '2': return 'S';
		case '3': return 'E';
		case '5': return 'Z';	
		default: return '#';
		}
	}
	

}	