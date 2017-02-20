package v001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BlocksProblem_UVa101 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int  n = Integer.parseInt(br.readLine());
		
		int[][] blocks = new int[n][2]; //0--> bottom, 1-->top
		for(int i = 0; i < n; i++)
			Arrays.fill(blocks[i], -1);
		
		
		while(br.ready())
		{
			StringTokenizer inst = new StringTokenizer(br.readLine());
			String x = inst.nextToken();
			if(x.equals("quit"))
				break;
			int a = Integer.parseInt(inst.nextToken());
			String y = inst.nextToken();
			int b = Integer.parseInt(inst.nextToken());
			//check if in the same stack or equal
			if(a==b)
				continue;
			boolean sameStack = false;
			int cur = b;
			while(blocks[cur][1]!=-1&&!sameStack) 
			{
				if(blocks[cur][1]==a)
					sameStack = true;
				cur = blocks[cur][1];
			}
			cur =  b;
			while(blocks[cur][0]!=-1&&!sameStack) 
			{
				if(blocks[cur][0]==a)
					sameStack = true;
				cur = blocks[cur][0];
			}
			if(sameStack)
				continue;
			if(x.equals("move"))
			{
				//remove all above a
				while(blocks[a][1]!=-1)
				{
					int above = blocks[a][1];
					blocks[a][1] = blocks[above][1];
					
					blocks[above][0] = blocks[above][1] = -1;
				}
				if(y.equals("onto"))
				{
					while(blocks[b][1]!=-1)
					{
						int above = blocks[b][1];
						blocks[b][1] = blocks[above][1];
						
						blocks[above][0] = blocks[above][1] = -1;
					}
				}
				
				//add a to the top of the stack of b -- detect top
				int top = b;
				while(blocks[top][1]!=-1)
					top = blocks[top][1];
				if(blocks[a][0]!=-1)
					blocks[blocks[a][0]][1] = -1;
				blocks[top][1] = a;
				blocks[a][0] = top;
				
			}
			else //pile
			{
				if(y.equals("onto"))
				{
					while(blocks[b][1]!=-1)
					{
						int above = blocks[b][1];
						blocks[b][1] = blocks[above][1];
						
						blocks[above][0] = blocks[above][1] = -1;
					}
				}
				
				//add a to the top of the stack of b -- detect top
				int top = b;
				while(blocks[top][1]!=-1)
					top = blocks[top][1];
				if(blocks[a][0]!=-1)
					blocks[blocks[a][0]][1] = -1;
				blocks[top][1] = a;
				blocks[a][0] = top;
				
			}

		}
		//print output
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++)
		{
			sb.append(i+":");
			if(blocks[i][0]!=-1)
			{
				sb.append("\n");continue;
			}
			else
			{
				int cur = i;
				sb.append(" "+cur);
				while(blocks[cur][1]!=-1)
				{
					cur = blocks[cur][1];
					sb.append(" "+cur);
				}
				sb.append("\n");
			}
			
		}
		System.out.print(sb);
		
	}
}
