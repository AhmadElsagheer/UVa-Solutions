<<<<<<< HEAD
package cp2_2;


import java.io.*; 
import java.util.*;

public class GroupReverse{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n;
		while((n=Integer.parseInt(st.nextToken()))!=0)
		{
			String r = "";
			String s = st.nextToken();
			int size = s.length()/n;
			for(int i = 0; i < s.length(); i+=size)
				r += getReverse(s.substring(i,i+size));
			sb.append(r+"\n");
			st = new StringTokenizer(br.readLine());
		}
		System.out.print(sb);
		
	}
	
	public static String getReverse(String s)
	{
		String r = "";
		for(int i = 0; i < s.length(); i++)
			r = s.charAt(i) + r;
		return r;
	}
	
=======
package cp2_2;


import java.io.*; 
import java.util.*;

public class GroupReverse{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n;
		while((n=Integer.parseInt(st.nextToken()))!=0)
		{
			String r = "";
			String s = st.nextToken();
			int size = s.length()/n;
			for(int i = 0; i < s.length(); i+=size)
				r += getReverse(s.substring(i,i+size));
			sb.append(r+"\n");
			st = new StringTokenizer(br.readLine());
		}
		System.out.print(sb);
		
	}
	
	public static String getReverse(String s)
	{
		String r = "";
		for(int i = 0; i < s.length(); i++)
			r = s.charAt(i) + r;
		return r;
	}
	
>>>>>>> 39f869b7a0b14772c795150d9bb52127d820fece
}	