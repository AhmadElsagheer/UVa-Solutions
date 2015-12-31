package cp4_2;
import java.util.*;
import java.io.*;

public class RobotMotion_UVa10116 {

	static char[][] grid;
	static int counter,R,C;
	static StringBuilder sb = new StringBuilder();
	static int[][] dfs_num;
	
	public static void dfs(int i, int j)
	{
		dfs_num[i][j] = ++counter;
		
		int[] next = dir(i,j);
		if(exit(next[0],next[1]))
			sb.append(counter+" step(s) to exit\n");
		else
			if(dfs_num[next[0]][next[1]]!=0)
				sb.append(dfs_num[next[0]][next[1]]-1+" step(s) before a loop of "+(counter-dfs_num[next[0]][next[1]]+1)+" step(s)\n");
			else
				dfs(next[0],next[1]);
				
	}
	
	public static int[] dir(int i, int j)
	{
		switch(grid[i][j])
		{
		case 'E':return new int[]{i,j+1};
		case 'N':return new int[]{i-1,j};
		case 'S':return new int[]{i+1,j};
		default:return new int[]{i,j-1};
		}
	}
	
	public static boolean exit(int i, int j)
	{
		if(i==-1 || j==-1 || i==R || j==C)
			return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			if(R==0)
				break;
			grid = new char[R][];
			for(int i = 0; i < R; i++)
				grid[i] = br.readLine().toCharArray();
			dfs_num  = new int[R][C];
			counter = 0;
			dfs(0,start-1);
			
		}
		System.out.print(sb);
	}
}

