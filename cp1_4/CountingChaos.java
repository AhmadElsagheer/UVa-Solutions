package cp1_4;
	
import java.io.*; 
import java.text.DecimalFormat;
	
public class CountingChaos{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++)
		{
			String s = br.readLine();
			sb.append(nextTime(incrementTime(s))+"\n");
		}
		System.out.print(sb);
		
	}
	
	public static String nextTime(String s)
	{
		String tmp = "" + Integer.parseInt(s.substring(0,2) + s.substring(3));
		if(isPalindrome(tmp))
			return s;
		return nextTime(incrementTime(s));
		
	}
	
	public static String incrementTime(String s)
	{
		int hours = Integer.parseInt(s.substring(0,2));
		int minutes = Integer.parseInt(s.substring(3));
		minutes++;
		if(minutes==60)
		{
			minutes = 0;
			hours = (hours+1)%24;
			
				
		}
		return new DecimalFormat("00").format(hours) + ":" + new DecimalFormat("00").format(minutes);
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