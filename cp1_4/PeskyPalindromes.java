<<<<<<< HEAD
package cp1_4;
	
import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
	
public class PeskyPalindromes{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(br.ready())
		{
			
			String line = br.readLine();
			HashSet<String> h = new HashSet<String>();
			int count = 0;
			for(int i = 0; i < line.length(); i++)
			{
				for(int j = 0; j < line.length() - i; j++)
				{
					String cur = line.substring(j, j + i + 1);
					if(isPalindrome(cur)&&!h.contains(cur))
					{
						count++;
						h.add(cur);
					}
				}
			}
			sb.append("The string '").append(line).append("' contains ").append(count).append(" palindromes.\n");
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
=======
package cp1_4;
	
import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
	
public class PeskyPalindromes{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(br.ready())
		{
			
			String line = br.readLine();
			HashSet<String> h = new HashSet<String>();
			int count = 0;
			for(int i = 0; i < line.length(); i++)
			{
				for(int j = 0; j < line.length() - i; j++)
				{
					String cur = line.substring(j, j + i + 1);
					if(isPalindrome(cur)&&!h.contains(cur))
					{
						count++;
						h.add(cur);
					}
				}
			}
			sb.append("The string '").append(line).append("' contains ").append(count).append(" palindromes.\n");
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
>>>>>>> 39f869b7a0b14772c795150d9bb52127d820fece
}	