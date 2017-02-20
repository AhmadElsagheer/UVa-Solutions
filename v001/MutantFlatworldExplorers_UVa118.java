package v001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MutantFlatworldExplorers_UVa118 {

	static int[][] grid;			//0-based
	static final int OFF = -1, OFF_CHECKED = -2;
	static int x,y;
	
	static void buildGrid()
	{
		int size = (x+1)*(y+1);
		grid = new int[size][4];
		
		for(int i = 0; i < size; i++)
			for(int j = 0; j < 4; j++)
			{
				grid[i][0] = (i>= size - (x+1))?OFF:(i + x+1);		//North
				grid[i][1] = (i%(x+1)==0)?OFF:(i-1);			//West
				grid[i][2] = (i <= x)?OFF:(i - (x+1));			//South			
				grid[i][3] = (i%(x+1)==x)?OFF:(i+1);			//East
				
			}
		
	}
	
	static int[] move(int pos, String inst, int dir, int lost)
	{
		
		if(inst.length()==0 || inst.charAt(0)==' ')
			return new int[]{pos,dir,lost};
		int next = pos;
		char c = inst.charAt(0);
		inst = inst.substring(1);
		switch(c)
		{
		case 'R':dir = (dir-1+4)%4;	break;
		case 'L':dir = (dir+1+4)%4;	break;
			default:	
				
				next = grid[pos][dir];
				if(next==OFF)
				{
					//robot lost
					grid[pos][dir] = OFF_CHECKED;
					//for corners
					if(pos/(x+1)==0)
					{
						if(pos%(x+1)==0)
							grid[pos][1] = OFF_CHECKED;
						if(pos%(x+1)==x)
							grid[pos][3] = OFF_CHECKED;
						grid[pos][2] = OFF_CHECKED;
					}
					else
						if(pos/(x+1)==y)
						{
							if(pos%(x+1)==0)
								grid[pos][1] = OFF_CHECKED;
							if(pos%(x+1)==x)
								grid[pos][3] = OFF_CHECKED;
							grid[pos][0] = OFF_CHECKED;
						}
					
					inst = "";lost = 1;next=pos;
				}
				else
					if(next==OFF_CHECKED)
						next = pos;
					
			}
		//go to next instruction
		return move(next, inst, dir,lost);
	}
	
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		buildGrid();
		
		
		while(br.ready())
		{
			st = new StringTokenizer(br.readLine());
			//Current Robot
			int xcur = Integer.parseInt(st.nextToken());
			int ycur = Integer.parseInt(st.nextToken());
			int startPos = xcur + (x+1)*ycur;
			int dir;
			switch(st.nextToken().charAt(0))
			{
			case 'N':	dir = 0;break;
			case 'W':	dir = 1;break;
			case 'S':	dir = 2;break;
			default:	dir = 3;
			}
			int[] finalState = move(startPos,br.readLine(),dir,0);
			
			xcur = finalState[0]%(x+1);
			ycur = finalState[0]/(x+1);
			char c;
			
			
			switch(finalState[1])
			{
			case 0:	c = 'N';break;
			case 1:	c = 'W';break;
			case 2:	c = 'S';break;
			default:	c = 'E';break;
			}
			sb.append(xcur+" "+ycur+" "+c);
			if(finalState[2]==1)
				sb.append(" LOST");
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}
