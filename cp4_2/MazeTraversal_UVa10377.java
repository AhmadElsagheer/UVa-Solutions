package cp4_2;
import java.util.*;
import java.io.*;

public class MazeTraversal_UVa10377 {

	static char[][] maze;
	static int R,C;
	static char dir;
	static int i,j;
	static StringBuilder sb = new StringBuilder();
	public static boolean go(String x, int k)
	{
		if(k==x.length())
			return false;
		boolean flag = instruction(x.charAt(k));
		if(flag)
			return true;
		return go(x,k+1);
	}
	
	public static boolean instruction(char c)
	{
		
		switch(c)
		{
		case 'R':rotate(1);break;
		case 'L':rotate(2);break;
		case 'F':move();break;
		case 'Q': print();return true;
		}
		
		return false;
		
	}
	
	public static void rotate(int i)
	{
		if(i==1)
		{
			if(dir=='N')
				dir = 'E';
			else
				if(dir=='E')
					dir = 'S';
				else
					if(dir=='S')
						dir = 'W';
					else
						dir = 'N';
		}
		else
		{
			if(dir=='N')
				dir = 'W';
			else
				if(dir=='W')
					dir = 'S';
				else
					if(dir=='S')
						dir = 'E';
					else
						dir = 'N';
		}
	}
	
	public static void move()
	{
		int x = 0, y = 0;
		switch(dir)
		{
		case 'N': x = i - 1; y = j;break;
		case 'S': x = i + 1; y = j;break;
		case 'E': x = i; y = j + 1;break;
		default:x = i; y = j - 1;break;
		}
		
		if(maze[x][y]==' ')
		{
			i = x; j = y;
		}
	}
	
	public static void print()
	{
		i++;j++;
		sb.append(i+" "+j+" "+dir+"\n");
		i--;j--;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		br.readLine();
		while(TC-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			dir = 'N';
			maze = new char[R][];
			for(int i = 0; i < R; i++)
				maze[i] = br.readLine().toCharArray(); 
			st = new StringTokenizer(br.readLine());
			i = Integer.parseInt(st.nextToken()) - 1;
			j = Integer.parseInt(st.nextToken()) - 1;
			boolean continueX = true;
			while(br.ready())
			{
				st = new StringTokenizer(br.readLine());
				if(st.countTokens()==0)
					break;
				while(st.hasMoreTokens())
				{
					String x = st.nextToken();
					
					if(continueX)
					{
						boolean flag = !go(x,0);
						continueX = flag;
					}
					
					
					
				}
				
			}
			if(TC!=0)
				sb.append("\n");
			
		}
		System.out.print(sb);
	}
}
