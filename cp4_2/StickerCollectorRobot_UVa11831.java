package cp4_2;

import java.io.*;
import java.util.*;

public class StickerCollectorRobot_UVa11831 {

	
	static int R, C;
	static char[][] arena;
	static int counter;
	static int x, y;
	static int dir;
	static int[] dx = new int[]{-1,0,1,0};
	static int[] dy = new int[]{0,1,0,-1};
	
	public static void rotate(int k)
	{
		dir = ((dir+k)%4+4)%4;
	}
	
	public static void move()
	{
		int i = x +dx[dir];
		int j = y +dy[dir];
		if(!valid(i,j))
			return;
		x = i;
		y = j;
		if(arena[x][y]=='*')
		{
			counter++;
			arena[x][y] = '.';
		}
	}
	
	public static boolean valid(int i, int j)
	{
		if(i==-1 || j==-1 || i==R || j==C || arena[i][j]=='#')
			return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			if(R==0)
				break;
			arena = new char[R][C];
			for(int i = 0; i < R; i++)
			{
				String row = br.readLine();
				for(int j = 0; j < C; j++)
					if(row.charAt(j) == '#' ||row.charAt(j) == '*'||row.charAt(j)=='.')
						arena[i][j] = row.charAt(j);
					else
					{
						switch(row.charAt(j))
						{
						case 'N':dir=0;break;
						case 'L':dir=1;break;
						case 'S':dir=2;break;
						default: dir = 3;
							
						}
						x = i;
						y = j;
						arena[i][j] = '.';
					}
			}
			counter  = 0;
			String inst = br.readLine();
			for(int i = 0; i < S; i++)
			{
				char c = inst.charAt(i);
				switch(c)
				{
				case 'D': rotate(1);break;
				case 'E': rotate(-1);break;
				default: move();
				}
			}
			sb.append(counter+"\n");
		}
		
		
		System.out.print(sb);
	}
}
