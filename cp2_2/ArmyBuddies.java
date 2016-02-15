<<<<<<< HEAD
package cp2_2;
	
import java.io.*; 
import java.util.*;
public class ArmyBuddies{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			if(s==0)
				break;
			int b = Integer.parseInt(st.nextToken());
			
			LinkedList<Integer> x = new LinkedList<Integer>();
			for(int j = 1; j <= s; j++)
				x.add(j);
			for(int i = 0; i < b; i++)
			{
				st = new StringTokenizer(br.readLine());
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				
				int index = x.indexOf(l);
				if(index==0)
					sb.append("* ");
				else
					sb.append(x.get(index-1)+" ");
				if(index + r - l==x.size() - 1)
					sb.append("*\n");
				else
					sb.append(x.get(index + r - l + 1)+"\n");
					
					
				for(int j = 1; j <= r - l + 1; j++)
					x.remove(index);
								
				
			}
			sb.append("-\n");
		}

		
		
		System.out.print(sb);
	
		
	}
	
	
	
=======
package cp2_2;
	
import java.io.*; 
import java.util.*;
public class ArmyBuddies{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			if(s==0)
				break;
			int b = Integer.parseInt(st.nextToken());
			
			LinkedList<Integer> x = new LinkedList<Integer>();
			for(int j = 1; j <= s; j++)
				x.add(j);
			for(int i = 0; i < b; i++)
			{
				st = new StringTokenizer(br.readLine());
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				
				int index = x.indexOf(l);
				if(index==0)
					sb.append("* ");
				else
					sb.append(x.get(index-1)+" ");
				if(index + r - l==x.size() - 1)
					sb.append("*\n");
				else
					sb.append(x.get(index + r - l + 1)+"\n");
					
					
				for(int j = 1; j <= r - l + 1; j++)
					x.remove(index);
								
				
			}
			sb.append("-\n");
		}

		
		
		System.out.print(sb);
	
		
	}
	
	
	
>>>>>>> 39f869b7a0b14772c795150d9bb52127d820fece
}	