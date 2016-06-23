package cp1_4;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; 

	
public class ReverseAndAdd{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++)
		{
			long number = Integer.parseInt(br.readLine());
			reverseAndAdd(number, sb);
		}
		System.out.print(sb);
	
		
	}
	
	public static void reverseAndAdd(long number, StringBuilder sb)
	{
		int count = 0;
		while(!isPalindrome(number))
		{
			count++;
			number += reverse(number);
		}
		sb.append(count+" "+number+"\n");
		
	}
	public static long reverse(long number)
	{
		return new Integer(new StringBuffer(""+number).reverse().toString());
	}
	
	public static boolean isPalindrome(long number)
	{
		return isPalindrome(""+number);
	}
	
	public static boolean isPalindrome(String s)
	{
		if(s.length()<=1)
			return true;
		if(s.charAt(0)!=s.charAt(s.length()-1))
			return false;
		return isPalindrome(s.substring(1,s.length()-1));
	}

}	