<<<<<<< HEAD
package cp2_2;
	
import java.io.*; 
import java.util.*;

public class PolePosition{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n;
		while((n=Integer.parseInt(br.readLine()))!=0)
		{
			int[] grid = new int[n];
			boolean possible = getGrid(grid, br);
			if(!possible)
				sb.append("-1\n");
			else
			{
				for(int i = 0; i < n - 1; i++)
					sb.append(grid[i]+" ");
				sb.append(grid[n-1]+"\n");
			}
		}
		
		
		System.out.print(sb);
	
		
	}
	
	public static boolean getGrid(int[] grid, BufferedReader br) throws IOException
	{
		StringTokenizer st;boolean flag = true;
		for(int i = 0; i < grid.length; i++)
		{
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int pos = i + p;
			if(pos<0||pos>grid.length-1||grid[pos]!=0)
				flag = false;
			else
				grid[pos] = c;
		}
		return flag;
	}
	
	
=======
package cp2_2;
	
import java.io.*; 
import java.util.*;

public class PolePosition{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n;
		while((n=Integer.parseInt(br.readLine()))!=0)
		{
			int[] grid = new int[n];
			boolean possible = getGrid(grid, br);
			if(!possible)
				sb.append("-1\n");
			else
			{
				for(int i = 0; i < n - 1; i++)
					sb.append(grid[i]+" ");
				sb.append(grid[n-1]+"\n");
			}
		}
		
		
		System.out.print(sb);
	
		
	}
	
	public static boolean getGrid(int[] grid, BufferedReader br) throws IOException
	{
		StringTokenizer st;boolean flag = true;
		for(int i = 0; i < grid.length; i++)
		{
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int pos = i + p;
			if(pos<0||pos>grid.length-1||grid[pos]!=0)
				flag = false;
			else
				grid[pos] = c;
		}
		return flag;
	}
	
	
>>>>>>> 39f869b7a0b14772c795150d9bb52127d820fece
}	