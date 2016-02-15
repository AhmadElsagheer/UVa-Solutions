package cp1_4;

import java.io.*; 
	
public class MagicSquarePalindromes{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for(int i = 1; i <= t; i++)
		{
			sb.append("Case #"+i+":\n");
			String line = clean(br.readLine());
			double x = Math.sqrt(line.length());
			
			if(x*x!=line.length())
			{
				sb.append("No magic :(\n");continue;
			}
			int k = (int)x;
			
			if(!isPalindrome(line))
			{
				sb.append("No magic :(\n");continue;
			}
			
			String s = getVertical(line, k);
			
			if(!s.equals(line)||!isPalindrome(s)) 
			{
				sb.append("No magic :(\n");continue;
			}
			sb.append(k+"\n");
		}
		System.out.print(sb);
		
	}
	
	public static String clean(String line)
	{
		String result = "";
		for(int i = 0; i < line.length(); i++)
		{
			char c = line.charAt(i);
			if(c>='a'&&c<='z')
				result += c;
		}
		return result;
	}
	
	public static boolean isPalindrome(String s)
	{
		if(s.length()<=1)
			return true;
		if(s.charAt(0)!=s.charAt(s.length()-1))
			return false;
		return isPalindrome(s.substring(1,s.length()-1));
	}
	
	public static String getVertical(String line, int k)
	{
		String[] parts = new String[k];
		for(int i = 0; i < k; i++)
			parts[i] = "";
		for(int i =0; i < line.length(); i++)
			parts[i%k] += line.charAt(i);
		String r = "";
		for(int i = 0; i < k; i++)
			r += parts[i];
		return r;
	}
	

}	