<<<<<<< HEAD
package cp2_2;
	
import java.io.*; 
import java.util.*;

public class NoProblem{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int curN; int k = 1;
		
		while((curN=Integer.parseInt(br.readLine()))>-1)
		{
			sb.append("Case "+k+++":\n");
			StringTokenizer s1 = new StringTokenizer(br.readLine());
			StringTokenizer s2 = new StringTokenizer(br.readLine());
			for(int i = 0; i < 12; i++)
			{
				int required = Integer.parseInt(s2.nextToken());
				if(curN>=required)
				{
					sb.append("No problem! :D\n"); curN = curN - required;
				}
				else
					sb.append("No problem. :(\n");
				
				curN += Integer.parseInt(s1.nextToken());
			}
		}
		System.out.print(sb);
	
		
	}
	
	
=======
package cp2_2;
	
import java.io.*; 
import java.util.*;

public class NoProblem{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int curN; int k = 1;
		
		while((curN=Integer.parseInt(br.readLine()))>-1)
		{
			sb.append("Case "+k+++":\n");
			StringTokenizer s1 = new StringTokenizer(br.readLine());
			StringTokenizer s2 = new StringTokenizer(br.readLine());
			for(int i = 0; i < 12; i++)
			{
				int required = Integer.parseInt(s2.nextToken());
				if(curN>=required)
				{
					sb.append("No problem! :D\n"); curN = curN - required;
				}
				else
					sb.append("No problem. :(\n");
				
				curN += Integer.parseInt(s1.nextToken());
			}
		}
		System.out.print(sb);
	
		
	}
	
	
>>>>>>> 39f869b7a0b14772c795150d9bb52127d820fece
}	