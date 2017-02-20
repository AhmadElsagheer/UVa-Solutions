package v011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class AncientMessages_UVa1103 {

	static int[] dx = new int[]{0,0,1,-1};
	static int[] dy = new int[]{-1,1,0,0};
	static int[][] message;	//0 white, 1 black, > 1 shape
	static int[] gaps;
	static int R,C;
	static int counter;	//initial = 1
	
	static void bfs(int i, int j, boolean black)
	{
		if(black)
			message[i][j] = ++counter;
		else
			message[i][j] = 1;
		int bound = 0;
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(i,j));
		while(!q.isEmpty())
		{
			Pair cur = q.remove();
			for(int k = 0; k < 4; k++)
			{
				int x = cur.i + dx[k];
				int y = cur.j + dy[k];
				if(black)
				{
					if(valid(x,y) && message[x][y]==1)
					{
						message[x][y] = counter;
						q.add(new Pair(x,y));
					}
				}
				else
					if(valid(x,y))
					{
						if(message[x][y]==0)
						{
							message[x][y] = 1;
							q.add(new Pair(x,y));
						}
						else
							if(message[x][y]!=1)
								if(bound==0)
									bound = message[x][y];
								else
									if(message[x][y]!=bound)
										bound = -1;
								
					}
					else
						if(bound!=0)
							bound = -1;
					
				
			}
			
		}
		
		if(!black && bound > 1)
			gaps[bound]++;
	}
	
	static boolean valid(int i, int j)
	{
		if(i==-1 || j==-1 || i==R || j==C)
			return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 1;
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken()) * 4;
			if(R==0)
				break;
			message = new int[R][C];
			for(int i = 0; i < R; i++)
			{
				String line = br.readLine();
				for(int j = 0, k = 0; j < line.length(); j++)
				{
					String hex = Integer.toBinaryString(Integer.parseInt(""+line.charAt(j), 16));
					while(hex.length()<4)
						hex = "0"+hex;
					for(int count = 0; count < 4; count++, k++)
						message[i][k] = hex.charAt(count) - '0';
				}
			}
			
			counter = 1;
			for(int i = 0; i < R; i++)
				for(int j = 0; j < C; j++)
					if(message[i][j]==1)
						bfs(i,j,true);
			gaps = new int[counter+1];
			
			for(int i = 0; i < R; i++)
				for(int j = 0; j < C; j++)
					if(message[i][j]==0)
						bfs(i,j,false);
			ArrayList<Character> x = new ArrayList<Character>(counter);
			for(int i = 2; i <= counter; i++)
				switch(gaps[i])
				{
				case 0:x.add('W');break;
				case 1:x.add('A');break;
				case 2:x.add('K');break;
				case 3:x.add('J');break;
				case 4:x.add('S');break;
				case 5:x.add('D');
				}
			Collections.sort(x);
			sb.append("Case "+tc+++": ");
			for(int i = 0; i < x.size(); i++)
				sb.append(x.get(i));
			sb.append("\n");
		}
		System.out.print(sb);
		
	}
	
	static class Pair
	{
		int i,j;
		Pair(int x, int y){i = x; j = y;}
	}
}